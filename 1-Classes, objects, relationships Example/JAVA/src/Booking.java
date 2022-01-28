import java.util.Date;
import java.util.*;
/**
 *
 * @author mert_
 * This class is defined for Bookings.
 * The main purpose of this class is to collect details of Bookings.
 *
 **/
public class Booking {
    private Date bookingDate;
    private int bookingTable;
    private static int index=1;
    private ArrayList<Order> orders;

    /**
     * Default Constructor
     */
    public Booking() {

        this.orders = new ArrayList<Order>();
        bookingDate=new Date(0,0,0);
        this.bookingTable=index;
        index++;

    }

    /**
     * Second Constructor
     * @param bookingDate bookingDate is the variable that holds Booking Dates of Customers
     */
    public Booking(Date bookingDate) {
        this.orders = new ArrayList<Order>();
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
    public Booking(Date bookingDate, ArrayList<Order> orders) {
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
    public Booking(Date bookingDate, int bookingTable, ArrayList<Order> orders) {

        this.bookingDate = bookingDate;
        this.bookingTable = bookingTable;
        this.orders = orders;
        this.bookingTable=index;
        index++;
    }


    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
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

    public void makeOrder(){

        Scanner scanner =new Scanner(System.in); //Input taker mechanism
        System.out.println("\nWelcome to the Order Screen");
        System.out.println("It is a pleasure to serve you! (Table %d)"+bookingTable);
        System.out.println("Which size do you want your pizza (Big | Medium | Small)");
        String details = scanner.nextLine();
        System.out.println("What do you want on it? (You can type more than one ,Cheddar , Salami, Pineapple etc )");
        String description = scanner.nextLine();
        orders.add(new Order(details,description));

    }

}

