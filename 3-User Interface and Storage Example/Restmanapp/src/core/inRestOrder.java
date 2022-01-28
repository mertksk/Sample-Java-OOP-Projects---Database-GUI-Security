package core;

import assignment.support.Order;

/**
 *
 * @author mert_
 * This class is defined for inRestOrders.
 * The main purpose of this class is to collect Orders held in Restaurant.
 * This class extends Abstract class "Order"
 **/

public class inRestOrder extends Order {

  private int tableNumber;
  private static int index=0; // Index is kept to create unique Table Numbers

    /*Default Constructor*/
    public inRestOrder() {
        super();
        tableNumber=0;

    }
    /*Second Constructor*/
    public inRestOrder(String orderDetails, String extraNotes) {
        super(orderDetails, extraNotes);
        this.tableNumber = index;
        index++;
    }
    /*Get method for Table Number*/
    public int getTableNumber() {
        return tableNumber;
    }
    /*Set method for Table Number*/
    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    /* Get method for index*/
    public static int getIndex() {
        return index;
    }
    /* Set method for index*/
    public static void setIndex(int index) {
        inRestOrder.index = index;
    }
}












