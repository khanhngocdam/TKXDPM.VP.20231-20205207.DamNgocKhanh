package models;

import models.cart.Cart;
import models.cart.CartMedia;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<CartMedia> listOrderMedia;
     public DeliveryInfo deliveryInfo;
    double shippingFee;


    public double getShippingFee() {
        return  this.shippingFee;
    }
    public Order(DeliveryInfo deliveryInfo, List<CartMedia> listCardMedia) {

        this.deliveryInfo = deliveryInfo;
        this.listOrderMedia = new ArrayList<>(listCardMedia);
    }

    public DeliveryInfo getDeliveryInfo() {
        return this.deliveryInfo;
    }

    public void setDeliveryInfo(DeliveryInfo deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }

    public List<CartMedia> getListOrderMedia() {
        return listOrderMedia;
    }

    public void setListOrderMedia(List<CartMedia> listOrderMedia) {
        this.listOrderMedia = listOrderMedia;
    }

    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
    }
    public double subTotal() {
        double subTotal = 0.0;
        for (CartMedia cartMedia: listOrderMedia) {
            subTotal += cartMedia.getPrice();
        }
        return subTotal * 1.1;
    }

    public double totalAmount() {
        return subTotal() + this.shippingFee;
    }

}
