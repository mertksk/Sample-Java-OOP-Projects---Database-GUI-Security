 package core;

import java.io.Serializable;
import java.util.Date;
import java.util.*;
/**
 *
 * @author mert_
 * This class is defined for Bookings.
 * The main purpose of this class is to collect details of Bookings.
 *
 **/
public class Booking implements Serializable {
    private Date bookingDate;
    private int bookingTable;
    private static int index=1;
    private ArrayList<inRestOrder> orders;

    /**
     * Default Constructor
     */
    public Booking() {

        this.orders = new ArrayList<inRestOrder>();
        bookingDate=new Date(0,0,0);
        this.bookingTable=index;
        index++;

    }

    /**
     * Second Constructor
     * @param bookingDate bookingDate is the variable that holds Booking Dates of Customers
     */
    public Booking(Date bookingDate) {
        this.orders = new ArrayList<inRestOrder>();
        this.bookingDate = bookingDate;
        this.bookingTable = index;
        index++;
    }

    /**
     *
     * Third Constructor
     * @param bookingDate bookingDate is the variable that holds Booking Dates of Customers
     * @param orders represent Orders done through this booking object
     */
    public Booking(Date bookingDate, ArrayList<inRestOrder> orders) {
        this.bookingDate = bookingDate;
        this.orders = orders;
        this.bookingTable=index;
        index++;
    }

    /**
     * Fourth Constructor
     * @param bookingDate  is the variable that holds Booking Dates of Customers
     * @param bookingTable is a variable that holds the number of tables
     * @param orders represent Orders done through this booking object
     */
    public Booking(Date bookingDate, int bookingTable, ArrayList<inRestOrder> orders) {

        this.bookingDate = bookingDate;
        this.bookingTable = bookingTable;
        this.orders = orders;
        this.bookingTable=index;
        index++;
    }
/*
 * toString method which allows us to print object as string
 */
    @Override
    public String toString() {
        return "Booking{" + "bookingDate=" + bookingDate + ", bookingTable=" + bookingTable + ", orders=" + orders + '}';
    }

    /*Getters and Setter of Class*/
    public ArrayList<inRestOrder> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<inRestOrder> orders) {
        this.orders = orders;
    }


    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getBookingTable() {
        return bookingTable;
    }

    public void setBookingTable(int bookingTable) {
        this.bookingTable = bookingTable;
    }

    /*This method is to make in Restaurant Order*/
    public void makeOrder(){

        Scanner scanner =new Scanner(System.in); //Input taker mechanism
        System.out.println("\nWelcome to the Order Screen");
        System.out.println("It is a pleasure to serve you! (Table %d)"+bookingTable);
        System.out.println("Which size do you want your pizza (Big | Medium | Small)");
        String details = scanner.nextLine();
        System.out.println("What do you want on it? (You can type more than one ,Cheddar , Salami, Pineapple etc )");
        String description = scanner.nextLine();
        orders.add(new inRestOrder(details,description));

    }

}

