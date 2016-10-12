package movierental;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class TicketPrinter implements Printer {

    @Override
    public String print(Customer customer) {
        StringBuilder result = new StringBuilder();

        double totalAmount = 0;
        int frequentRenterPoints = 0;
        result.append("Rental Record for ")
            .append(customer.getName())
            .append('\n');

        for (Rental currentRental : customer.getRentals()) {
            double movieAmount = currentRental.getRentalPrice();

            frequentRenterPoints += currentRental.getFidelityPoints();

            // show figures for this rental
            result.append('\t')
                .append(currentRental.getMovie().getTitle())
                .append('\t')
                .append(String.valueOf(movieAmount))
                .append('\n');
            totalAmount += movieAmount;
        }

        // add footer lines
        result.append("Amount owed is ")
            .append(String.valueOf(totalAmount))
            .append('\n');
        result.append("You earned ")
            .append(String.valueOf(frequentRenterPoints))
            .append(" frequent renter points");

        return result.toString();
    }

}
