package com.acme.jga.filtering;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.acme.jga.filtering.expr.ExpressionType;
import com.acme.jga.filtering.utils.ParsingResult;

class BasicTest {

        @Test
        void basicPropertyTest() {
                String qry = "name eq 'GAUTIER'";
                QueryParser queryParser = new QueryParser();
                ParsingResult parsingResult = queryParser.parseQuery(qry);

                assertTrue(parsingResult.getErrorNodes().isEmpty());

                assertEquals(3, parsingResult.getExpressions().size());

                assertEquals(ExpressionType.PROPERTY, parsingResult.getExpressions().get(0).getType(),
                                "Tuple 0 of type " + ExpressionType.PROPERTY.name());
                assertEquals("name", parsingResult.getExpressions().get(0).getValue(), "Tuple 0 with value 'name'");

                assertEquals(ExpressionType.COMPARISON, parsingResult.getExpressions().get(1).getType(),
                                "Tuple 1 of type " + ExpressionType.COMPARISON.name());
                assertEquals("eq", parsingResult.getExpressions().get(1).getValue(), "Tuple 1 with value 'eq'");

                assertEquals(ExpressionType.VALUE, parsingResult.getExpressions().get(2).getType(),
                                "Tuple 2 of type " + ExpressionType.VALUE.name());
                assertEquals("'GAUTIER'", parsingResult.getExpressions().get(2).getValue(),
                                "Tuple 2 with value 'GAUTIER'");
        }

        @Test
        void parenthesisTest() {
                String qry = "( name eq 'GAUTIER' and firstname eq 'JEROME')";
                QueryParser queryParser = new QueryParser();
                ParsingResult parsingResult = queryParser.parseQuery(qry);

                assertTrue(parsingResult.getErrorNodes().isEmpty());

                assertEquals(9, parsingResult.getExpressions().size());

                assertEquals(ExpressionType.OPENING_PARENTHESIS, parsingResult.getExpressions().get(0).getType(),
                                "Tuple 0 of type " + ExpressionType.OPENING_PARENTHESIS.name());

                assertEquals(ExpressionType.PROPERTY, parsingResult.getExpressions().get(1).getType(),
                                "Tuple 1 of type " + ExpressionType.PROPERTY.name());
                assertEquals("name", parsingResult.getExpressions().get(1).getValue(), "Tuple 1 with value 'name'");

                assertEquals(ExpressionType.COMPARISON, parsingResult.getExpressions().get(2).getType(),
                                "Tuple 2 of type " + ExpressionType.COMPARISON.name());
                assertEquals("eq", parsingResult.getExpressions().get(2).getValue(), "Tuple 2 with value 'eq'");

                assertEquals(ExpressionType.VALUE, parsingResult.getExpressions().get(3).getType(),
                                "Tuple 3 of type " + ExpressionType.VALUE.name());
                assertEquals("'GAUTIER'", parsingResult.getExpressions().get(3).getValue(),
                                "Tuple 3 with value 'GAUTIER'");

                assertEquals(ExpressionType.OPERATOR, parsingResult.getExpressions().get(4).getType(),
                                "Tuple 4 of type " + ExpressionType.OPERATOR.name());
                assertEquals("and", parsingResult.getExpressions().get(4).getValue(), "Tuple 3 with value 'and'");

                assertEquals(ExpressionType.PROPERTY, parsingResult.getExpressions().get(5).getType(),
                                "Tuple 5 of type " + ExpressionType.PROPERTY.name());
                assertEquals("firstname", parsingResult.getExpressions().get(5).getValue(),
                                "Tuple 1 with value 'firstname'");

                assertEquals(ExpressionType.COMPARISON, parsingResult.getExpressions().get(6).getType(),
                                "Tuple 6 of type " + ExpressionType.COMPARISON.name());
                assertEquals("eq", parsingResult.getExpressions().get(2).getValue(), "Tuple 6 with value 'eq'");

                assertEquals(ExpressionType.VALUE, parsingResult.getExpressions().get(7).getType(),
                                "Tuple 7 of type " + ExpressionType.VALUE.name());
                assertEquals("'JEROME'", parsingResult.getExpressions().get(7).getValue(),
                                "Tuple 7 with value 'JEROME'");

                assertEquals(ExpressionType.CLOSING_PARENTEHSIS, parsingResult.getExpressions().get(8).getType(),
                                "Tuple 8 of type " + ExpressionType.CLOSING_PARENTEHSIS.name());
        }

        @Test
        void negationTest() {
                String query = "not fruit eq 'apple'";
                QueryParser queryParser = new QueryParser();
                ParsingResult parsingResult = queryParser.parseQuery(query);

                assertTrue(parsingResult.getErrorNodes().isEmpty());

                assertEquals(4, parsingResult.getExpressions().size());

                assertEquals(ExpressionType.NEGATION, parsingResult.getExpressions().get(0).getType(),
                                "Tuple 0 of type " + ExpressionType.NEGATION.name());

                assertEquals(ExpressionType.PROPERTY, parsingResult.getExpressions().get(1).getType(),
                                "Tuple 1 of type " + ExpressionType.PROPERTY.name());
                assertEquals("fruit", parsingResult.getExpressions().get(1).getValue(), "Tuple 1 with value 'fruit'");

                assertEquals(ExpressionType.COMPARISON, parsingResult.getExpressions().get(2).getType(),
                                "Tuple 2 of type " + ExpressionType.COMPARISON.name());
                assertEquals("eq", parsingResult.getExpressions().get(2).getValue(), "Tuple 2 with value 'eq'");

                assertEquals(ExpressionType.VALUE, parsingResult.getExpressions().get(3).getType(),
                                "Tuple 3 of type " + ExpressionType.VALUE.name());
                assertEquals("'apple'", parsingResult.getExpressions().get(3).getValue(), "Tuple 3 with value 'apple'");
        }

        @Test
        void notAllowedCharTest() {
                String query = "b@lance eq 1.4";
                QueryParser queryParser = new QueryParser();
                ParsingResult parsingResult = queryParser.parseQuery(query);

                assertFalse(parsingResult.getErrorNodes().isEmpty());
        }

        @Test
        void likeTest() {
                String query = "name lk 'GAU%TIER'";
                QueryParser queryParser = new QueryParser();
                ParsingResult parsingResult = queryParser.parseQuery(query);

                assertTrue(parsingResult.getErrorNodes().isEmpty());
        }

        @Test
        void invalidParenthesisTest(){
                String query = "(name lk 'GAU%TIER'";
                QueryParser queryParser = new QueryParser();
                ParsingResult parsingResult = queryParser.parseQuery(query);
                // Generated error:  "<missing ')'>"
                assertFalse(parsingResult.getErrorNodes().isEmpty());
                assertEquals("<missing ')'>", parsingResult.getErrorNodes().get(0).getText());
        }

}
