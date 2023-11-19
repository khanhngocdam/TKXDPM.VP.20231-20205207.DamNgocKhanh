package models.cart;

import models.media.Media;

public class CartMedia {
    private Media media;
    private int quantity;
    private double price;

    public CartMedia(){

    }

    public CartMedia(Media media, int quantity) {
        this.media = media;
        this.quantity = quantity;
        this.price = media.getPrice() * quantity;
    }

    public Media getMedia() {
        return this.media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return this.price;
    }

}
