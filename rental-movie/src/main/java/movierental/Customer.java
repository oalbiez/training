package movierental;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String name;

    private List<Rental> rentals = new ArrayList<Rental>();


    public Customer(String name) {
        this.name = name;
    }


    public void addRental(Rental arg) {
        this.rentals.add(arg);
    }


    public String getName() {
        return this.name;
    }


    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        StringBuilder result = new StringBuilder();
        result.append("Rental Record for ").append(getName()).append('\n');

        for (Rental each : this.rentals) {
            double thisAmount = each.getRentalPrice();

            frequentRenterPoints += each.getFidelityPoints();

            // show figures for this rental
            result.append('\t').append(each.getMovie().getTitle()).append('\t').append(String.valueOf(thisAmount)).append('\n');
            totalAmount += thisAmount;
        }

        // add footer lines
        result.append("Amount owed is ").append(String.valueOf(totalAmount)).append('\n');
        result.append("You earned ").append(String.valueOf(frequentRenterPoints)).append(" frequent renter points");

        return result.toString();
    }
}
