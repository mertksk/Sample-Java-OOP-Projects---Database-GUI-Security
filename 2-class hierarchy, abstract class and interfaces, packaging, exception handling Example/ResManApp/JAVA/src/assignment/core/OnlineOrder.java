package assignment.core;

import assignment.support.Order;

/**
 *
 * @author mert_
 * This class is defined for Online Orders in our Restaurant.
 * The main purpose of this class is to collect details of track Online Orders in our restaurants.
 *This class extends abstract "Order" Class
 **/
public class OnlineOrder extends Order {
    private String paymentType;
    private Junior deliveredBy;

    /*Default Constructor*/
    public OnlineOrder() {
        super();
        this.paymentType = "none";
        this.deliveredBy = null;
    }
    /*Second Constructor*/
    public OnlineOrder(String orderDetails, String extraNotes, String paymentType) {
        super(orderDetails, extraNotes);
        this.paymentType = paymentType;
    }
    /*Third Constructor*/
    public OnlineOrder(String orderDetails, String extraNotes, String paymentType, Junior deliveredBy) {
        super(orderDetails, extraNotes);
        this.paymentType = paymentType;
        this.deliveredBy = deliveredBy;
    }
    /*Get Method for PaymentType*/
    public String getPayment() {
        return paymentType;
    }
    /*Set Method for PaymentType*/
    public void setPayment(String payment) {
        this.paymentType = payment;
    }
    /*Get Method for deliveredBy*/
    public Junior getDeliveredBy() {
        return deliveredBy;
    }
    /*Set Method for deliveredBy*/
    public void setDeliveredBy(Junior deliveredBy) {
        this.deliveredBy = deliveredBy;
    }
    /*Get Method for paymentType*/
    public String getPaymentType() {
        return paymentType;
    }
    /*Set Method for paymentType*/
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
