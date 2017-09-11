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


    public List<Rental> getRentals() {
        return rentals;
    }


    public String getName() {
        return this.name;
    }

}
