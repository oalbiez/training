package movierental;

/**
 * The rental class represents a customer renting a movie.
 */
public class Rental {

    private Movie movie;

    private int daysRented;


    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }


    public double getRentalPrice() {
        switch (this.getMovie().getPriceCode()) {
            case REGULAR:
                return 2 + Math.max(0, (this.daysRented - 2) * 1.5);
            case NEW_RELEASE:
                return this.daysRented * 3;
            case CHILDREN:
                return 1.5 + Math.max(0, (this.daysRented - 3) * 1.5);
            default:
                return 0;
        }
    }


    public int getDaysRented() {
        return this.daysRented;
    }


    public Movie getMovie() {
        return this.movie;
    }
}
