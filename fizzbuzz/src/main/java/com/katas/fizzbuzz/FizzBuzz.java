package com.katas.fizzbuzz;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class FizzBuzz {

    public static Object convert(int input) {
        if (isAMultipleOfThreeAndFive(input)) {
            return "fizzbuzz";
        }
        if (isAMultipleOfThree(input)) {
            return "fizz";
        }
        else if (isAMultipleOfFive(input)) {
            return "buzz";
        }
        return input;
    }


    private static boolean isAMultipleOfThree(int value) {
        return value % 3 == 0;
    }


    private static boolean isAMultipleOfFive(int value) {
        return value % 5 == 0;
    }


    private static boolean isAMultipleOfThreeAndFive(int value) {
        return value % 15 == 0;
    }

}
