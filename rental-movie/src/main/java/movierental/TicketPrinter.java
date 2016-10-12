package movierental;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class TicketPrinter implements Printer {

    @Override
    public String print(Customer customer) {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        StringBuilder result = new StringBuilder();
        result.append("Rental Record for ").append(customer.getName()).append('\n');

        for (Rental each : customer.getRentals()) {
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
