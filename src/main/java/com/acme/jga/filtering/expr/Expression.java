package com.acme.jga.filtering.expr;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Expression {
    private ExpressionType type;
    private String value;
}
