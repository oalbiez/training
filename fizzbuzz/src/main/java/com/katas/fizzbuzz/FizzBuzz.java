package com.katas.fizzbuzz;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class FizzBuzz {
    
    public static Object convert(int i) {
        if (i % 15 == 0) {
            return "fizzbuzz";
        }
        if (i % 3 == 0) {
            return "fizz";
        }
        else if (i % 5 == 0) {
            return "buzz";
        }
        return i;
    }

}
