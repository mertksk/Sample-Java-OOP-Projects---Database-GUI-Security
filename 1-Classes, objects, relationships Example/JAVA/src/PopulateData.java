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
        ArrayList<Order> orders =new ArrayList<>();

        //order add
        orders.add(new Order("big", "Olive and Sesame"));
        orders.add(new Order("small" ,"sausage without sauce"));
        orders.add(new Order("small" ,"no additional ingredients"));

        //booking add
        bookings.add(new Booking(new Date(120,10,19), orders));

        //customer add
        customers.add(new Customer(214398,"Mert Özçelik",'M',new Date(98,05,19),new Date(120,10,03),bookings)); //1

        //clear orders
        orders =new ArrayList<Order>();

        //clear bookings
        bookings=new ArrayList<Booking>();

        //order add
        orders.add(new Order("medium", "no additional ingredients"));
        orders.add(new Order("big" ,"additional tomate sauce"));

        //booking add
        bookings.add(new Booking(new Date(120,10,18), orders));
        //customer add
        customers.add(new Customer(114358,"Batuhan Sandıkci",'M',new Date(98,10,8),new Date(120,10,12),bookings)); //2

        //clear orders
        orders =new ArrayList<Order>();
        //clear bookings
        bookings=new ArrayList<Booking>();

        //order add
        orders.add(new Order("medium", "more salami"));

        //booking add
        bookings.add(new Booking(new Date(120,10,18), orders));

        //clear orders
        orders =new ArrayList<Order>();

        //order add
        orders.add(new Order("small","more cheddar"));

        //booking add
        bookings.add(new Booking(new Date(120,11,18), orders));//Formula of Date type (1900+x,1+y,z);

        //customer add
        customers.add(new Customer(125479,"George Washington",'M',new Date(80,00,2),new Date(120,10,17),bookings)); //3




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
        staffs.add(new Staff(953245, "Furkan", 'M',new Date(97,9,4)));
        staffs.add(new Staff(115012, "Xsenia", 'F',new Date(97,3,5)));
        staffs.add(new Staff(142656, "Ayda", 'F',new Date(98,11,12)));



        return staffs;  //returns staffs list
    }


}

