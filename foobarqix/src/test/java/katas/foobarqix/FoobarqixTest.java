package katas.foobarqix;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class FoobarqixTest {

    @Test
    public void number_multiple_of_three_contain_3() {
        this.compare("FooFoo", 3);
    }


    @Test
    public void confirm_number_multiple_of_three_write_foo_instead_of_number() {
        this.compare("Foo", 6);
    }


    @Test
    public void number_multiple_of_five_contain_5() {
        this.compare("BarBar", 5);
    }


    @Test
    public void confirm_number_multiple_of_five_write_bar_instead_of_number() {
        this.compare("Bar*", 10);
    }


    @Test
    public void number_multiple_of_seven_contain_seven() {
        this.compare("QixQix", 7);
    }


    @Test
    public void confirm_number_multiple_of_seven_write_bar_instead_of_number() {
        this.compare("Qix", 14);
    }


    @Test
    public void random_number_not_multiple_3_5_7() {
        this.compare("8", 8);
    }


    @Test
    public void multiple_3_5_contains_5() {
        this.compare("FooBarBar", 15);
    }


    @Test
    public void multiple_3_contains_3_3() {
        this.compare("FooFooFoo", 33);
    }


    @Test
    public void multiple_3_7() {
        this.compare("FooQix", 21);
    }


    @Test
    public void contain_5_3() {
        this.compare("BarFoo", 53);
    }


    @Test
    public void replace_zeros_by_star_in_random_number_not_multiple_3_5_7() {
        this.compare("1*1", 101);
    }


    @Test
    public void replace_zeros_by_star_multiple_3_contains_two_3() {
        this.compare("FooFoo*Foo", 303);
    }


    public void compare(String expected, int input) {
        Assert.assertEquals(expected, Foobarqix.convert(input));
    }

}
