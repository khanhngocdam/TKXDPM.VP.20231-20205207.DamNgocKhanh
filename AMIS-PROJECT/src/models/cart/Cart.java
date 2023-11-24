package models.cart;

import exception.MediaNotAvailableException;

import java.util.ArrayList;
import java.util.Iterator;
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

    public void addCartMedia(CartMedia cartMedia) {
        Iterator<CartMedia> iterator = lstCartMedia.iterator();
        while (iterator.hasNext()) {
            CartMedia existingMedia = iterator.next();
            if (existingMedia.getMedia().equals(cartMedia.getMedia())) {
                // Nếu phần tử đã tồn tại, thực hiện cập nhật
                existingMedia.setQuantity(existingMedia.getQuantity() + cartMedia.getQuantity());
                return;
            }
        }
        // Nếu không tìm thấy, thêm mới
        lstCartMedia.add(cartMedia);
    }

    public void removeCartMedia(CartMedia cartMedia) {
        lstCartMedia.remove(cartMedia);
    }
    public void emptyCart() {
        Cart.getCart().getLstCartMedia().clear();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("My cart:\n");
        double totalPrice = 0;
        for (CartMedia cartMedia : lstCartMedia) {
            result.append(cartMedia.getMedia().getId()).append(". ").append(cartMedia.getMedia().getTitle()).append("\n");
            totalPrice += cartMedia.getPrice();
        }

        return result.append(totalPrice).append("\n").toString();
    }

}
