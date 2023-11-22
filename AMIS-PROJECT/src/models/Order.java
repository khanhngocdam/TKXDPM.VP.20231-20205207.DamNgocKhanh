package models;

import models.cart.Cart;

import java.util.List;

public class Order {
    public Cart cart = Cart.getCart();
     public DeliveryInfo deliveryInfo;
    double shippingFee;

    public Order() {
    }

    public double getShippingFee() {
        return  Cart.getCart().getLstCartMedia().size() * 2;
    }
    public Order(DeliveryInfo deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }

    public Cart getCart() {
        return cart;
    }
    public DeliveryInfo getDeliveryInfo() {
        return this.deliveryInfo;
    }
    double calculateShippingFee() {
        return 30.5;
    }

    public void setDeliveryInfo(DeliveryInfo deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }

}
