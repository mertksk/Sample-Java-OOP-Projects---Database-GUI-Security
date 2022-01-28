package core;

import assignment.support.Person;
import java.io.Serializable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mert_
 * This class is defined for Customers.
 * The main purpose of this class is to collect details of Customers.
 *
 **/


/*
ssn,name, gender,dateOfBirth

*/
public class Customer implements Serializable  {

    private int ssn;
    private String name;
    private char gender;
    private Date dateOfBirth;
    private Date registrationDate;
    private ArrayList<Booking> bookings;
    private ArrayList<OnlineOrder> onlineOrders; // will be updated
    private String creditCardDetails;
    /**
     * Default Constructor
     */
    public Customer() {

        this.ssn=0;
        this.name="null";
        this.gender='U';
        this.dateOfBirth=null;
        this.registrationDate =null;
        this.bookings=new ArrayList<Booking>();
        this.onlineOrders=null;
        this.creditCardDetails="None";
    }

    /**
     * Second Constructor
     */
    public Customer(int ssn, String name, char gender, Date dateOfBirth, Date registrationDate, ArrayList<Booking> bookings) {
        this.ssn=ssn;
        this.name=name;
        this.gender=gender;
        this.dateOfBirth=dateOfBirth;
        this.registrationDate = registrationDate;
        this.bookings = bookings;
        this.onlineOrders=null;
    }


    /**
     * Third Constructor
     */
    public Customer(int ssn, String name, char gender, Date dateOfBirth, Date registrationDate, String creditCardDetails, ArrayList<OnlineOrder> orders) {
        this.ssn=ssn;
        this.name=name;
        this.gender=gender;
        this.dateOfBirth=dateOfBirth;
        this.registrationDate = registrationDate;
        this.bookings=new ArrayList<Booking>();
        this.creditCardDetails=creditCardDetails;
        this.onlineOrders=orders;
    }

    /**
     * Fourth Constructor
     */
    public Customer(int ssn, String name, char gender, Date dateOfBirth, Date registrationDate, ArrayList<Booking> bookings, ArrayList<OnlineOrder> onlineOrders, String creditCardDetails) {
        this.ssn=ssn;
        this.name=name;
        this.gender=gender;
        this.dateOfBirth=dateOfBirth;
        this.registrationDate = registrationDate;
        this.bookings = bookings;
        this.onlineOrders = onlineOrders;
        this.creditCardDetails = creditCardDetails;
    }
    
    
    
    /*
    *5th constructor
    *
    /
    */
     public Customer(int ssn, String name, char gender, Date dateOfBirth, Date registrationDate, String creditCardDetails) {
        this.ssn=ssn;
        this.name=name;
        this.gender=gender;
        this.dateOfBirth=dateOfBirth;
        this.registrationDate = registrationDate;
        this.creditCardDetails = creditCardDetails;
        this.bookings=new ArrayList<Booking>();
    }
//////////dddd

    @Override
    public String toString() {
        return "Customer{" + "ssn=" + ssn + ", name=" + name + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", registrationDate=" + registrationDate + ", bookings=" + bookings + ", onlineOrders=" + onlineOrders + ", creditCardDetails=" + creditCardDetails + '}';
    }

  
 
    /**
     * Get method for Bookings of a customer
     */
    public ArrayList<Booking> getBookings() {
        return bookings;
    }
    /**
     * Set method for Bookings of a customer
     */
    public void setBookings(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }


    /**
     * Get method for Date of Registration of a customer
     */
    public Date getRegistrationDate() {
        return registrationDate;
    }
    /**
     * Set method for Date of Registration of a customer
     */
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    /**
     * makebooking method is let user to create a booking in selected Customer
     */
    public void makeBooking(){
        try {
            Scanner scanner =new Scanner(System.in);  //Input taker mechanism
            System.out.println("\nWelcome to the Booking Screen of Mr/Ms: " + name);
            System.out.println("Which date do you want to book?(Type in this Format(dd/MM/yyyy)");
            String dateinput = scanner.nextLine();  //String Date Input Taker
            Date bookingDate =new SimpleDateFormat("dd/MM/yyyy").parse(dateinput);
            bookings.add(new Booking(bookingDate));

        } catch (ParseException ex) { //  catcher in case there is an exception
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*Get and Set Methods for our varibles*/
    public ArrayList<OnlineOrder> getOnlineOrders() {
        return onlineOrders;
    }

    public void setOnlineOrders(ArrayList<OnlineOrder> onlineOrders) {
        this.onlineOrders = onlineOrders;
    }

    public String getCreditCardDetails() {
        return creditCardDetails;
    }

    public void setCreditCardDetails(String creditCardDetails) {
        this.creditCardDetails = creditCardDetails;
    }
    
    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


}

