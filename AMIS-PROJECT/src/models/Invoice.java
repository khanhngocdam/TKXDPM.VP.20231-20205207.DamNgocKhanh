package models;

import models.payment.PaymentTransaction;

public class Invoice {
    Order order;
    PaymentTransaction paymentTransaction;
    double totalAmount;

    public Invoice(Order order, PaymentTransaction paymentTransaction, double totalAmount) {
        this.order = order;
        this.paymentTransaction = paymentTransaction;
        this.totalAmount = totalAmount;
    }
}
