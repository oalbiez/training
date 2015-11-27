package com.katas.rpn.stack.operators;

import com.mycompany.kata4.rpnwork.RpnCalculatorWithRecursivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.mycompany.kata4.rpnwork.RpnCalculatorWithRecursivity.*;

/**
 *
 * @author Chloé Mahalin
 */
public enum Operator implements IOperator {

    DIVIDED("/") {
            public int compute(List<String> numbers) {
                int b = calculate(numbers);
                int a = calculate(numbers);
                return a / b;
            }
        },
    MINUS("-") {
            public int compute(List<String> numbers) {
                int b = calculate(numbers);
                int a = calculate(numbers);
                return a - b;
            }
        },
    MULTIPLIED("*") {
            public int compute(List<String> numbers) {
                int b = calculate(numbers);
                int a = calculate(numbers);
                return a * b;
            }
        },
    PLUS("+") {
            public int compute(List<String> numbers) {
                int b = calculate(numbers);
                int a = calculate(numbers);
                return a + b;
            }
        },
    SQRT("sqrt") {
            public int compute(List<String> numbers) {
                return (int) Math.sqrt(calculate(numbers));
            }
        },
    MAX("max") {
            public int compute(List<String> numbers) {
                List<Integer> values = new ArrayList<Integer>();
                while (!numbers.isEmpty()) {
                    values.add(calculate(numbers));
                }
                return Collections.max(values);
            }
        },
    MIN("min") {
            public int compute(List<String> numbers) {
                return 0;
            }
        },
    SQUARED("squared") {
            public int compute(List<String> numbers) {
                return (int) Math.pow(calculate(numbers), 2);
            }
        };

    protected String symbol;


    private Operator(String symbol) {
        this.symbol = symbol;
    }


    public String getSymbol() {
        return symbol;
    }


    public static Operator getOperatorFromString(String stringOperator) {

        for (Operator operator : Operator.values()) {
            if (operator.getSymbol().equals(stringOperator)) {
                return operator;
            }
        }
        throw new RuntimeException("No operator for " + stringOperator);
    }

}
