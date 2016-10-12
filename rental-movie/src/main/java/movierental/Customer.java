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
        String result = "Rental Record for " + getName() + "\n";

        for (Rental each : this.rentals) {
            double thisAmount = each.getRentalPrice();

            frequentRenterPoints += each.getFidelityPoints();

            // show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }

        // add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";

        return result;
    }
}
