package assignment.core;

import java.util.ArrayList;
import java.util.Date;

/**
 * PopulateData class is to upload information in initial position
 */
public class PopulateData {

    /**
     * Default constructor
     */
    PopulateData() {

    }

    /**
     * update 1 method is to load Customer information in inital position.
     * It creates an ArrayList<Customer> variable ,and returns it to RestManApp Class
     * @return returns loaded customer ArrayList
     */

    /*Date formula(1900+x,01+y,z)*/
    public ArrayList<Customer> update1(){
        ArrayList<Customer> customers =new ArrayList<>(); // creates an empty List then returns it
        ArrayList<Booking> bookings = new ArrayList<>();   //// creates subLists
        ArrayList<inRestOrder> inRestaurant =new ArrayList<>();
        ArrayList<OnlineOrder> onlineOrders =new ArrayList<>();


        //Online order add
        onlineOrders.add(new OnlineOrder("Levrek", "Mezes", "1 installment "));
        //In restaurant order add
        inRestaurant.add(new inRestOrder("big pizza", "Olive and Sesame"));
        inRestaurant.add(new inRestOrder("small pizza" ,"sausage without sauce"));
        inRestaurant.add(new inRestOrder("small pizza" ,"no additional ingredients"));

        //booking add
        bookings.add(new Booking(new Date(120,10,19), inRestaurant));


        //customer add 1
        customers.add(new Customer(214398,"Mert Özçelik",'M',new Date(98,05,19),new Date(120,10,03),bookings,onlineOrders,"Isbank Debit Card")); //1

        //clear inRestaurant orders
        inRestaurant =new ArrayList<inRestOrder>();
        //clear online orders
        onlineOrders =new ArrayList<>();



        //clear bookings
        bookings=new ArrayList<Booking>();

        //order add
        inRestaurant.add(new inRestOrder("medium pizza", "no additional ingredients"));
        inRestaurant.add(new inRestOrder("big pizza" ,"additional tomate sauce"));

        //booking add
        bookings.add(new Booking(new Date(120,10,18), inRestaurant));
        //customer add 2
        customers.add(new Customer(114358,"Batuhan Sandıkci",'M',new Date(98,10,8),new Date(120,10,12),bookings,onlineOrders,"Ziraat Bank Debit Card")); //2

        //clear inRestaurant
        inRestaurant =new ArrayList<inRestOrder>();
        //clear bookings
        bookings=new ArrayList<Booking>();

        //order add
        inRestaurant.add(new inRestOrder("medium pizza", "more salami"));

        //booking add
        bookings.add(new Booking(new Date(120,10,18), inRestaurant));

        //clear inRestaurant
        inRestaurant =new ArrayList<inRestOrder>();

        //order add
        inRestaurant.add(new inRestOrder("small pizza","more cheddar"));

        //booking add
        bookings.add(new Booking(new Date(120,11,18), inRestaurant));//Formula of Date type (1900+x,1+y,z);

        //customer add 3
        customers.add(new Customer(125479,"George Washington",'M',new Date(80,00,2),new Date(120,10,17),bookings,onlineOrders,"Isbank Credit Card")); //3




        return customers; //returns customer list
    }

    /**
     * update 1 method is to load Customer information in inital position.
     * It creates an ArrayList<Staff> variable ,and returns it to RestManApp Class
     * @return returns loaded Staff ArrayList
     */

    /*Date formula(1900+x,01+y,z)*/
    public ArrayList<Staff> update2(){
        ArrayList<Staff> staffs =new ArrayList<>();  // creates an empty List then returns it

        //Adds Staff
        staffs.add(new Senior(953245, "Furkan", 'M',new Date(97,9,4),new Date(108,10,4),2683));
        staffs.add(new Senior(115012, "Xsenia", 'F',new Date(97,3,5),new Date(115,2,6),750));
        staffs.add(new Junior(142656, "Ayda", 'F',new Date(98,11,12),new Date(115,3,3),350,new Date(123,3,5)));


        return staffs;  //returns staffs list
    }


}

