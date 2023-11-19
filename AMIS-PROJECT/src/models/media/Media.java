package models.media;

public class Media {
    protected int id;
    protected String title;
    protected String category;
    protected double value;
    protected double price;
    protected int quantity;
    protected String imageURL;
    protected boolean rushDelivery;

    //Constructor
    public Media(int id, String title, double price, int quantity, String imageURL ) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.imageURL = imageURL;
    }
    public Media(int id, String title, String category, double value, double price, int quantity, String imageURL, Boolean rushDelivery) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.value = value;
        this.price = price;
        this.quantity = quantity;
        this.imageURL = imageURL;
        this.rushDelivery = rushDelivery;
    }

    // Getter - Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean getRushDelivery() {
        return rushDelivery;
    }

    public void setRushDelivery(Boolean rushDelivery) {
        this.rushDelivery = rushDelivery;
    }

}
