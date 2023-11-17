package models.cart;

import exception.MediaNotAvailableException;
import models.media.Media;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartMedia> lstCartMedia;
    private static Cart cartInstance;
    public static Cart getCart(){
        if(cartInstance == null) cartInstance = new Cart();
        return cartInstance;
    }
    private Cart(){
        lstCartMedia = new ArrayList<>();
    }
    public List<CartMedia> getLstCartMedia() {
        return lstCartMedia;
    }

    public void setLstCartMedia(List<CartMedia> lstCartMedia) {
        this.lstCartMedia = lstCartMedia;
    }

    public double getSubTotal() {
        double total = 0.0;
        for (CartMedia cardMedia : lstCartMedia) {
            total += cardMedia.getPrice();
        }
        return total;
    }

    public void checkAvailabilityOfProduct() throws RuntimeException {
        boolean allAvai = true;
        for (Object object : lstCartMedia) {
            CartMedia cartMedia = (CartMedia) object;
            int requiredQuantity = cartMedia.getQuantity();
            int availQuantity = cartMedia.getMedia().getQuantity();
            if (requiredQuantity > availQuantity) allAvai = false;
        }
        if (!allAvai) throw new MediaNotAvailableException("Some media not available");
    }

}
