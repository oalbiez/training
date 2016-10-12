package com.katas.fizzbuzz;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class FizzBuzzTest {

    @Test
    public void number_multiple_of_three_returns_fizz() {
        this.compare("fizz", 3);
    }


    @Test
    public void confirm_number_multiple_of_three_returns_fizz() {
        this.compare("fizz", 6);
    }


    @Test
    public void reconfirm_number_multiple_of_three_returns_fizz() {
        this.compare("fizz", -9);
    }


    @Test
    public void number_multiple_of_five_returns_buzz() {
        this.compare("buzz", 5);
    }


    @Test
    public void confirm_number_multiple_of_five_returns_buzz() {
        this.compare("buzz", 10);
    }


    @Test
    public void reconfirm_number_multiple_of_five_returns_buzz() {
        this.compare("buzz", -20);

    }


    @Test
    public void number_not_multiple_of_3_or_5_return_itself() {
        this.compare(7, 7);

    }


    @Test
    public void confirm_number_not_multiple_of_3_or_5_return_itself() {
        this.compare(4, 4);
    }


    @Test
    public void number_multiple_of_three_and_five_returns_fizzbuzz() {
        this.compare("fizzbuzz", 15);
    }


    @Test
    public void confirm_number_multiple_of_three_and_five_returns_fizzbuzz() {
        this.compare("fizzbuzz", 30);
    }


    public void compare(Object expected, int input) {
        Assert.assertEquals(expected, FizzBuzz.convert(input));
    }

}
