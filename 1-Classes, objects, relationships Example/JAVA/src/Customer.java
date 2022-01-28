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
public class Customer {
    private int ssn;
    private String name;
    private char gender;
    private Date dateOfBirth;
    private Date registrationDate;
    private ArrayList<Booking> bookings;

    /**
     * Default Constructor
     */
    public Customer() {

        this.ssn = 00;
        this.name = "None";
        this.gender = 'X';
        this.dateOfBirth = null;
        this.registrationDate =null;
        this.bookings=new ArrayList<Booking>();
    }

    /**
     * Second Constructor
     * @param ssn stands for Social Security Number of Customer
     * @param name is Name of Customer
     * @param gender is Gender of Customer
     * @param dateOfBirth is Date of Birth of Customer
     * @param registrationDate is Date of Registration of Customer
     */
    public Customer(int ssn, String name, char gender, Date dateOfBirth, Date registrationDate) {
        this.ssn = ssn;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.registrationDate = registrationDate;
        this.bookings=new ArrayList<Booking>();
    }

    /**
     *
     * Second Constructor
     * @param ssn stands for Social Security Number of Customer
     * @param name is Name of Customer
     * @param gender is Gender of Customer
     * @param dateOfBirth is Date of Birth of Customer
     * @param registrationDate is Date of Registration of Customer
     * @param bookings represents Bookings of a Customer
     */
    public Customer(int ssn, String name, char gender, Date dateOfBirth, Date registrationDate, ArrayList<Booking> bookings) {
        this.ssn = ssn;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.registrationDate = registrationDate;
        this.bookings = bookings;
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
     * Get method for Social Security Number
     */
    public int getSsn() {
        return ssn;
    }
    /**
     * Set method for Social Security Number
     */
    public void setSsn(int ssn) {
        this.ssn = ssn;
    }
    /**
     * Get method for Name
     */
    public String getName() {
        return name;
    }
    /**
     * Set method for Name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Get method for Gender of a customer
     */
    public char getGender() {
        return gender;
    }
    /**
     * Set method for Gender of a customer
     */
    public void setGender(char gender) {
        this.gender = gender;
    }

    /**
     * Get method for Date of Birth of a customer
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    /**
     * Set method for Date of Birth of a customer
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
            System.out.println("\nWelcome to the Booking Screen of Mr/Mrs: "+name);
            System.out.println("Which date do you want to book?(Type in this Format(dd/MM/yyyy)");
            String dateinput = scanner.nextLine();  //String Date Input Taker
            Date bookingDate =new SimpleDateFormat("dd/MM/yyyy").parse(dateinput);
            bookings.add(new Booking(bookingDate));

        } catch (ParseException ex) { //  catcher in case there is an exception
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


}

