package movierental;

import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {

    @Test
    public void test_statement_no_information() {
        Customer customer = new Customer(null);
        Assert.assertEquals("Rental Record for null\nAmount owed is 0.0\nYou earned 0 frequent renter points", customer.statement());
    }


    @Test
    public void test_statement_customer_with_name_empty() {
        Customer customer = new Customer("");
        Assert.assertEquals("Rental Record for \nAmount owed is 0.0\nYou earned 0 frequent renter points", customer.statement());
    }


    @Test
    public void test_statement_customer_with_name() {
        Customer customer = new Customer("toto");
        Assert.assertEquals("Rental Record for toto\nAmount owed is 0.0\nYou earned 0 frequent renter points", customer.statement());
    }


    @Test(expected = NullPointerException.class)
    public void test_statement_customer_with_one_rental_no_movie() {
        Customer customer = new Customer("toto");
        Rental rental = new Rental(null, 0);
        customer.addRental(rental);

        Assert.assertEquals("Rental Record for toto\nAmount owed is 0.0\nYou earned 0 frequent renter points", customer.statement());
    }

    @Test
    public void test_statement_customer_with_one_rental_one_movie_no_days() {
        Customer customer = new Customer("toto");

        Movie movie = new Movie(null, 0);
        Rental rental = new Rental(movie, 0);
        customer.addRental(rental);

        Assert.assertEquals("Rental Record for toto\n\tnull\t2.0\nAmount owed is 2.0\nYou earned 1 frequent renter points", customer.statement());
    }

    @Test
    public void test_statement_customer_with_one_rental_one_movie_price_code_0_one_days() {
        Customer customer = new Customer("toto");

        Movie movie = new Movie(null, 0);
        Rental rental = new Rental(movie, 1);
        customer.addRental(rental);

        Assert.assertEquals("Rental Record for toto\n\tnull\t2.0\nAmount owed is 2.0\nYou earned 1 frequent renter points", customer.statement());
    }

    @Test
    public void test_statement_customer_with_one_rental_one_movie_price_code_0_two_days() {
        Customer customer = new Customer("toto");

        Movie movie = new Movie(null, 0);
        Rental rental = new Rental(movie, 2);
        customer.addRental(rental);

        Assert.assertEquals("Rental Record for toto\n\tnull\t2.0\nAmount owed is 2.0\nYou earned 1 frequent renter points", customer.statement());
    }

    @Test
    public void test_statement_customer_with_one_rental_one_movie_price_code_0_three_days(){
        Customer customer = new Customer("toto");

        Movie movie = new Movie(null, 0);
        Rental rental = new Rental(movie, 2);
        customer.addRental(rental);

        Assert.assertEquals("Rental Record for toto\n\tnull\t2.0\nAmount owed is 2.0\nYou earned 1 frequent renter points", customer.statement());
    }

    @Test
    public void test_statement_customer_with_one_rental_one_movie_price_code_1_one_days() {
        Customer customer = new Customer("toto");

        Movie movie = new Movie(null, 1);
        Rental rental = new Rental(movie, 1);
        customer.addRental(rental);

        Assert.assertEquals("Rental Record for toto\n\tnull\t3.0\nAmount owed is 3.0\nYou earned 1 frequent renter points", customer.statement());
    }


    @Test
    public void test_statement_customer_with_one_rental_one_movie_price_code_1_two_days() {
        Customer customer = new Customer("toto");

        Movie movie = new Movie(null, 1);
        Rental rental = new Rental(movie, 2);
        customer.addRental(rental);

        Assert.assertEquals("Rental Record for toto\n\tnull\t6.0\nAmount owed is 6.0\nYou earned 2 frequent renter points", customer.statement());
    }


    @Test
    public void test_statement_customer_with_one_rental_one_movie_price_code_1_three_days() {
        Customer customer = new Customer("toto");

        Movie movie = new Movie(null, 1);
        Rental rental = new Rental(movie, 2);
        customer.addRental(rental);

        Assert.assertEquals("Rental Record for toto\n\tnull\t6.0\nAmount owed is 6.0\nYou earned 2 frequent renter points", customer.statement());
    }

           @Test
    public void test_statement_customer_with_one_rental_one_movie_price_code_2_one_days() {
        Customer customer = new Customer("toto");

        Movie movie = new Movie(null, 2);
        Rental rental = new Rental(movie, 1);
        customer.addRental(rental);

        Assert.assertEquals("Rental Record for toto\n\tnull\t1.5\nAmount owed is 1.5\nYou earned 1 frequent renter points", customer.statement());
    }

    @Test
    public void test_statement_customer_with_one_rental_one_movie_price_code_2_two_days() {
        Customer customer = new Customer("toto");

        Movie movie = new Movie(null, 2);
        Rental rental = new Rental(movie, 2);
        customer.addRental(rental);

        Assert.assertEquals("Rental Record for toto\n\tnull\t1.5\nAmount owed is 1.5\nYou earned 1 frequent renter points", customer.statement());
    }

    @Test
    public void test_statement_customer_with_one_rental_one_movie_price_code_2_three_days(){
        Customer customer = new Customer("toto");

        Movie movie = new Movie(null, 2);
        Rental rental = new Rental(movie, 2);
        customer.addRental(rental);

        Assert.assertEquals("Rental Record for toto\n\tnull\t1.5\nAmount owed is 1.5\nYou earned 1 frequent renter points", customer.statement());
    }
}
