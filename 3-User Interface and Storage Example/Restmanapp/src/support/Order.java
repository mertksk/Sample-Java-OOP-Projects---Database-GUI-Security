package assignment.support; /**
 *
 * @author mert_
 * This is abstract class that implements assignment.support.Payment interface
 * The main purpose is to create a Roof for all kind of Orders will be occured in our system.
 **/

import java.util.Date;

public abstract class Order implements Payment {

   private Date orderDate;
   private String orderDetails;
   private String extraNotes;
   private static int index=0; // This variable keeps the number of assignment.support.Order objects automatically ,and we derrive Unique OrderID from this.
   private int OrderID;

   /*Default Constructor*/
    public Order() {
        this.orderDetails="none";
        this.extraNotes="none";
        OrderID=index+1000;
        index++;
        orderDate=null;
    }
    /*Second Constructor*/
    public Order(String orderDetails, String extraNotes) {
        java.util.Date date=new java.util.Date();
        this.orderDate=new Date();
        this.orderDate=date;
        this.OrderID=index+1000;
        index++;

        this.orderDetails=orderDetails;
        this.extraNotes=extraNotes;

    }


    /*assignment.support.Payment Method*/
    public void processPayment(){
       System.out.println("assignment.support.Payment Completed!");
    }

    /*Get method for orderDate */
    public Date getOrderDate() {
        return orderDate;
    }

    /*Set method for orderDate */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /*Get method for orderDetails */
    public String getOrderDetails() {
        return orderDetails;
    }

    /*Set method for orderDetails */
    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    /*Get method for extraNotes */
    public String getExtraNotes() {
        return extraNotes;
    }

    /*Set method for extraNotes */
    public void setExtraNotes(String extraNotes) {
        this.extraNotes = extraNotes;
    }

    /*Get method for OrderID */
    public int getOrderID() {
        return OrderID;
    }

    /*Set method for OrderID */
    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

}
