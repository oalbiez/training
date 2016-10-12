package movierental;

public class Movie {

    public enum Category {
        REGULAR,  //0
        CHILDREN, //2
        NEW_RELEASE; //1
    }

    private String _title;
    private Category _priceCode;

    public Movie(String title, Category priceCode) {
        _title = title;
        _priceCode = priceCode;
    }

    public Category getPriceCode() {
        return _priceCode;
    }

    public void setPriceCode(Category arg) {
        _priceCode = arg;
    }

    public String getTitle() {
        return _title;
    }


}
