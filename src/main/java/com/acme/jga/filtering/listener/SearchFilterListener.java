package com.acme.jga.filtering.listener;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.acme.jga.filtering.antlr.FilterListener;
import com.acme.jga.filtering.antlr.FilterParser.ExprContext;
import com.acme.jga.filtering.antlr.FilterParser.FilterContext;
import com.acme.jga.filtering.expr.Expression;
import com.acme.jga.filtering.expr.ExpressionType;

public class SearchFilterListener implements FilterListener {
    private Vocabulary vocabulary;
    private List<Expression> expressions;
    private List<ErrorNode> errors;

    public SearchFilterListener(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
        this.expressions = new ArrayList<>();
        this.errors = new ArrayList<>();
    }

    public List<Expression> getExpressions() {
        return this.expressions;
    }

    public List<ErrorNode> getErrors() {
        return this.errors;
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        int symbolType = node.getSymbol().getType();
        String symbolicName = this.vocabulary.getSymbolicName(symbolType);
        switch (symbolicName) {
            case "PROPERTY":
                expressions.add(new Expression(ExpressionType.PROPERTY, node.getText()));
                break;
            case "COMPARISON":
                expressions.add(new Expression(ExpressionType.COMPARISON, node.getText()));
                break;
            case "VALUE":
                expressions.add(new Expression(ExpressionType.VALUE, node.getText()));
                break;
            case "AND":
                expressions.add(new Expression(ExpressionType.OPERATOR, node.getText()));
                break;
            case "OR":
                expressions.add(new Expression(ExpressionType.OPERATOR, node.getText()));
                break;
            case "OPAR":
                expressions.add(new Expression(ExpressionType.OPENING_PARENTHESIS, node.getText()));
                break;
            case "CPAR":
                expressions.add(new Expression(ExpressionType.CLOSING_PARENTEHSIS, node.getText()));
                break;
            case "NOT":
                expressions.add(new Expression(ExpressionType.NEGATION, node.getText()));
                break;
            default:
                break;
        }
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        this.errors.add(node);
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        // N/A
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        // N/A
    }

    @Override
    public void enterFilter(FilterContext ctx) {
        // N/A
    }

    @Override
    public void exitFilter(FilterContext ctx) {
        // N/A
    }

    @Override
    public void enterExpr(ExprContext ctx) {
        // N/A
    }

    @Override
    public void exitExpr(ExprContext ctx) {
        // N/A
    }

}
