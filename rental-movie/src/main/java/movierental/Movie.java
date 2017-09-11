package movierental;

public class Movie {

    public enum Category {
        REGULAR, CHILDREN, NEW_RELEASE;
    }

    private String title;

    private Category category;


    public Movie(String title, Category priceCode) {
        this.title = title;
        this.category = priceCode;
    }


    public boolean isANewRelease() {
        return this.category == Category.NEW_RELEASE;
    }


    public Category getCategory() {
        return this.category;
    }


    public void setCategory(Category arg) {
        this.category = arg;
    }


    public String getTitle() {
        return this.title;
    }

}
