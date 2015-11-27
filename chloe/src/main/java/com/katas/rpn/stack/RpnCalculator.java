package com.katas.rpn.stack;

/**
 *
 * @author Chloé Mahalin
 */
public class RpnCalculator {

    public int calculate(String input) throws CalculationException {
        /*
        Stack<Integer> stack = new Stack<Integer>();
        String[] listInput = input.split(" ");

        for (String elem : listInput) {
            if (isANumber(elem)) {
                stack.push(Integer.parseInt(elem));
            } else {
                Operator op = Operator.getOperatorFromString(elem);
                stack.push(op.compute(stack));
            }
        }

        return stack.pop();*/
        return 0;
    }


    public boolean isANumber(String number) {
        return number.matches("[0-9]*");
    }

}
