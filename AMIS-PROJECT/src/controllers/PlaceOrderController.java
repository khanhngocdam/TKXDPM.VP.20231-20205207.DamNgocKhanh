package controllers;

import models.DeliveryInfo;
import models.Invoice;
import models.Order;
import models.cart.Cart;
import models.cart.CartMedia;
import models.media.Media;
import utils.ConnectDB;

import java.sql.SQLException;

public class PlaceOrderController {
    public void placeOrder() {
        Cart.getCart().checkAvailabilityOfProduct();
    }

    public boolean validateAddressPlaceRushOrder(String province, String address) {
        if (!validateAddress(address))
            return false;
        if(!province.equals("Hà Nội"))
            return false;
        return true;
    }

    public boolean validateAddress(String address) {
        // Check address is not null
        if (address == null)
            return false;
        // Check if contain leter space only
        if (address.trim().replaceAll(",", "").length() == 0)
            return false;
        return true;
    }

    public boolean validateName(String name) {
        // Check name is not null
        if (name == null)
            return false;
        // Check if contain leter space only
        if (name.trim().length() == 0)
            return false;
        // Check if contain only leter and space
        if (name.matches("^[\\p{L} \\s]+$") == false)
            return false;
        return true;
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        if(phoneNumber.length() == 0) {
            return false;
        }
        if(phoneNumber.matches("\\d+") == false) {
            return false;
        }
        return true;
    }

    public Order createOrder(DeliveryInfo deliveryInfo) throws SQLException {
        Order order = new Order(deliveryInfo, Cart.getCart().getLstCartMedia());

        return order;
    }
    public double calculateShippingFee(Order order) {
        int shippingFee = 0;
        for (CartMedia cartMedia: order.getListOrderMedia()) {
            shippingFee += cartMedia.getQuantity();
        }
        return shippingFee * 5;
    }
    public void saveInvoice(Invoice invoice) {

    }
}
