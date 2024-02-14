package com.acme.jga.filtering;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.acme.jga.filtering.antlr.FilterLexer;
import com.acme.jga.filtering.antlr.FilterParser;
import com.acme.jga.filtering.listener.SearchFilterListener;
import com.acme.jga.filtering.utils.ParsingResult;

public class QueryParser {
    
    public ParsingResult parseQuery(String query){
        CodePointCharStream stream = CharStreams.fromString(query);
        FilterLexer lexer = new FilterLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FilterParser parser = new FilterParser(tokens);
        Vocabulary vocabulary = parser.getVocabulary();        
        SearchFilterListener searchFilterListener = new SearchFilterListener(vocabulary);
        new ParseTreeWalker().walk(searchFilterListener, parser.filter());
        return new ParsingResult(searchFilterListener.getExpressions(), searchFilterListener.getErrors());
    }

}
