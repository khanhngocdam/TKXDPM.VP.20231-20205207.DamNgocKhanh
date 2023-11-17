package models;

import models.cart.Cart;

import java.util.List;

public class Order {
    public Cart cart;
     public DeliveryInfo deliveryInfo;
    double shippingFee;

    public Order(Cart cart, DeliveryInfo deliveryInfo) {
        this.cart = cart;
        this.deliveryInfo = deliveryInfo;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public DeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }
    double calculateShippingFee() {
        return 30.5;
    }

    public void setDeliveryInfo(DeliveryInfo deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }

}
