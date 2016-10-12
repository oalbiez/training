package movierental;

public class Movie {

    public enum Category {
        REGULAR, //0
        CHILDREN, //2
        NEW_RELEASE; //1
    }

    private String title;

    private Category priceCode;


    public Movie(String title, Category priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }


    public Category getPriceCode() {
        return this.priceCode;
    }


    public void setPriceCode(Category arg) {
        this.priceCode = arg;
    }


    public String getTitle() {
        return this.title;
    }

}
