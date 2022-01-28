import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mert_
 * This class is a brain of our program where main and other vital funtions are defined.
 * The main purpose of this class is to connects different classes over this class ,and let them work together.
 *
 */

public class RestManApp {


    private static ArrayList<Staff> staffs;  /*Staff ArrayList to hold Staffs in our program*/
    private static ArrayList<Customer> customers; /*Customer ArrayList to hold Customers in our program*/

    public RestManApp() { /* Default constructor*/
    }

    /**
     *this method  will take the customer's orders. If the snn number or the date does not
     * exist, the method should provide an appropriate error message.
     * @param ssn is required to find Customer
     * @param bookingDate   is required to find Customer's Booking
     *
     */
    public static void receiveOrder(int ssn, Date bookingDate){
        int control=0; // control to check if ssn is okay
        for(Customer i:customers){
            if(i.getSsn()==ssn){
                control++;
                for(Booking a:i.getBookings()){
                    if(a.getBookingDate().equals(bookingDate)){
                        control++;
                        a.makeOrder();
                        break;
                    }
                }
                break;
            }
        }
        if(control==0)System.err.println("THE SSN IS NOT REGISTERED YET\n");
        else if(control==1) System.err.println("THE DATE IS NOT REGISTERED YET\n");

    }

    /**
     *this method will take the customer's booking information. If the snn number does not
     * exist, the method should provide an appropriate error message.
     * @param ssn is required to find Customer.
     */
    public static void receiveBooking(int ssn){
        boolean activeHigh=false;
        for(Customer i:customers){
            if(i.getSsn()==ssn){
                activeHigh=true;
                i.makeBooking();
                break;
            }
        }
        if(!activeHigh) System.err.println("\nTHE SSN IS NOT REGISTERED TO THE SYSTEM");

    }

    /**
     *this method will display a customer's  orders.
     * If the snn number or the booking date does not exist,
     * the program should provide an appropriate error message
     * @param ssn is required to find Customer.
     * @param bookingDate is required to find Customer's Booking
     */
    public static void getCustomerOrders(int ssn, Date bookingDate){
        int control=0;

        for(Customer i:customers){
            if( i.getSsn()==ssn){
                control++;
                for(Booking a:i.getBookings()){
                    if(a.getBookingDate().equals(bookingDate)){
                        control++;
                        for(Order x:a.getOrders()){
                            System.out.println("\nORDERS:\n");
                            System.out.println("Details:");
                            System.out.println(x.getDetails());
                            System.out.println("Description:");
                            System.out.println(x.getDescription()+"\n");
                        }
                        break; }
                }
                break;
            }
        }
        if(control==0)System.err.println("THE SSN IS NOT REGISTERED YET\n");
        else if(control==1) System.err.println("THE DATE IS NOT REGISTERED YET\n");

    }

    /**
     * this method will display a customer's bookings.
     * If the snn number does not exist,
     * the program should provide an appropriate error message.
     * @param ssn is required to find Customer.
     */
    public static void  getCustomerBooking(int ssn){
        boolean activeHigh=false;
        System.out.println("\nBOOKINGS:");
        for (Customer i:customers){

            if((ssn)==i.getSsn()){
                activeHigh=true;
                System.out.println("\nName: "+i.getName()+"   SSN: "+i.getSsn()+"   Gender: "+i.getGender()+"   Date Of Birth: "+i.getDateOfBirth()+"   Date of Registration: "+i.getRegistrationDate());


                for(Booking a:  i.getBookings()){
                    System.out.println("Booking Date:   "+a.getBookingDate() );
                    System.out.println("Booking Table:  "+a.getBookingTable());
                }
                break;
            }
        }


        if(!activeHigh){
            System.err.println("The SSN "+ssn+" Could not be Founded");
        }

    }

    /**
     *  This method will list all the customer.
     */
    public static void listCustomer(){

        for (Customer i:customers){
            System.out.println("Name: "+i.getName()+"   SSN: "+i.getSsn()+"   Gender: "+i.getGender()+"   Date Of Birth: "+i.getDateOfBirth()+"   Date of Registration: "+i.getRegistrationDate());
        }
    }

    /**
     * This method will list all the staff.
     */
    public static void listStaff(){

        for (Staff i:staffs){
            System.out.println("Name: "+i.getName()+"   SSN Number: "+i.getSsn() +"   Gender: "+i.getGender()+ "   Date of Birth: "+ i.getDateOfBirth());
        }
    }

    /**
     * this method  will display the customer details.
     * If the snn number does not exist, the
     * method should provide an appropriate error message
     * @param ssn is required to find Customer.
     */
    public static void getCustomerDetails(int ssn){

        int holder,activeHigh=0;
        for (Customer i:customers){
            holder=i.getSsn();
            if((ssn)==holder){
                activeHigh++;
                System.out.println("Name: "+i.getName()+"   SSN: "+i.getSsn()+"   Gender: "+i.getGender()+"   Date Of Birth: "+i.getDateOfBirth()+"   Date of Registration: "+i.getRegistrationDate()+"\n");
                break;   }
        }
        if(activeHigh==0){
            System.err.println("The SSN "+ssn+" Could not be Founded\n");
        }
    }


    /**
     *this method will display the Staff details.
     * If the snn number does not exist, the program
     * should provide an appropriate error message.
     * @param ssn is required to find Staff.
     */

    public static void getStaffDetails(int ssn){
        int holder,activeHigh=0;
        for (Staff i:staffs){
            holder=i.getSsn();
            if((ssn)==holder){
                activeHigh++;
                System.out.println("Name: "+i.getName()+"   SSN Number: "+i.getSsn() +"   Gender: "+i.getGender()+ "   Date of Birth: "+ i.getDateOfBirth());
                break;
            }
        }
        if(activeHigh==0){
            System.err.println("The SSN "+ssn+" Could not be Founded\n");
        }

    }

    /**
     *  This method will add a customer by taking inputs from user.
     */

    public static void addCustomer(){

        try {
            System.out.println("Welcome to Customer Adder");
            Scanner scanner =new Scanner(System.in);

            System.out.println("Enter Name"); //1
            String name = scanner.nextLine();

            System.out.println("Enter SSN:"); //2
            int ssn = scanner.nextInt();

            System.out.println("Enter Gender (F for Female /M for Male / O for others)");
            char gender=scanner.next().charAt(0);
            scanner.nextLine(); // dummy

            System.out.println("Enter Date of Birth (dd/mm/yyyy)");
            String dateinput = scanner.nextLine();
            Date dateofBirth =new SimpleDateFormat("dd/MM/yyyy").parse(dateinput);


            System.out.println("Enter Date of Registration (dd/mm/yyyy)");
            dateinput = scanner.nextLine();
            Date registrationDate =new SimpleDateFormat("dd/MM/yyyy").parse(dateinput);

            customers.add(new Customer(ssn,name,gender,dateofBirth,registrationDate));
        } catch (ParseException ex) {
            Logger.getLogger(RestManApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method will add new staff to the
     * list of Staff maintained.
     */
    public static void addStaff(){

        try {
            System.out.println("Welcome to Staff Adder");
            Scanner scanner =new Scanner(System.in);

            System.out.println("Enter Name");
            String name = scanner.nextLine();
            System.out.println("Enter SSN:");
            int ssn = scanner.nextInt();
            System.out.println("Enter Gender (F for Female /M for Male / O for others)");
            char gender=scanner.next().charAt(0);
            scanner.nextLine(); // dummy
            System.out.println("Enter Date of Birth (dd/mm/yyyy)");
            String dateinput = scanner.nextLine();
            Date dateofBirth =new SimpleDateFormat("dd/MM/yyyy").parse(dateinput);
            staffs.add(new Staff(ssn,name,gender,dateofBirth));

        }
        catch (ParseException ex) {
            Logger.getLogger(RestManApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *This method will read an snn number of a
     * Staff, and delete the corresponding staff object. If the snn number does
     * not exist, the program should provide an appropriate error message.
     * @param ssn is required to find Staff.
     */
    public static void deleteStaff(int ssn){
        int holder,activeHigh=0; //Holder to hold value , activeHigh is an control mechanism
        int counter=0; //counts the loop
        for (Staff i:staffs){      //Travel in Arraylist one by one
            holder=i.getSsn();
            if((ssn)==holder){      //SSN Check
                activeHigh++;
                System.out.println("SNN:"+i.getSsn()+" was Removed from the Staffs\n");
                staffs.remove(counter);
                break;
            }
            counter++;
        }

        if(activeHigh==0){  //Error condition
            System.err.println("The SSN "+ssn+ " Could not be Founded\n");
        }


    }

    /**
     *This method will read an snn number of a
     * Customer, and delete the corresponding Customer object. If the snn number does
     * not exist, the program should provide an appropriate error message.
     * @param ssn is required to find Customer.
     */

    public static void deleteCustomer(int ssn){

        int holder,activeHigh=0; //Holder to hold value , activeHigh is an control mechanism
        int counter=0; //counts the loop
        for (Customer i:customers){ //Travel in Arraylist one by one
            holder=i.getSsn();
            if((ssn)==holder){
                activeHigh++;
                System.out.println("SNN:"+i.getSsn()+" was Removed from the Customers\n");
                customers.remove(counter);
                break;
            }
            counter++;
        }
        if(activeHigh==0){ //Error condition
            System.err.println("The SSN "+ssn+ " Could not be Founded\n");
        }

    }


    /**
     * This method will display the interaction menu to the user.
     */
    public static void menu(){
        System.out.println("1) Add Staff");
        System.out.println("2) Delete Staff");
        System.out.println("3) Get Staff Details");
        System.out.println("4) Add Customer");
        System.out.println("5) Delete Customer");
        System.out.println("6) Receive Booking");
        System.out.println("7) Receive Order");
        System.out.println("8) Customer Details");
        System.out.println("9) Customer Booking");
        System.out.println("10) Customer Order");
        System.out.println("11) List Staff");
        System.out.println("12) List Customer");
        System.out.println("-1) For Exit");



    }

    /**
     * This method should terminate the program.
     */
    public static void exit(){
        System.out.println("GOOD LUCK!");
        System.exit(0); //exits
    }

    /**
     *
     *The main method where operation part is held.
     */
    public static void main(String[] args){
        int select;     //select for menu
        /*Parameters may be sent to functionalities*/
        int ssn;
        String cnvDate1;
        Date Date1=null;

        //Input taker
        Scanner scanner =new Scanner(System.in);
        /*Initial Data Load*/
        PopulateData a=new PopulateData();
        customers=a.update1();
        staffs=a.update2();


        while(true){
            menu();
            System.out.print("\nYour Choice: ");
            select=scanner.nextInt();


            switch (select) {
                case 1 -> addStaff();
                case 2 -> {
                    System.out.println("Enter a SSN to delete Staff");
                    ssn = scanner.nextInt();
                    deleteStaff(ssn);
                }
                case 3 -> {
                    System.out.println("Enter a SSN to get Staff Details");
                    ssn = scanner.nextInt();
                    getStaffDetails(ssn);
                }
                case 4 -> addCustomer();
                case 5 -> {
                    System.out.println("Enter a SSN to delete a Customer");
                    ssn = scanner.nextInt();
                    deleteCustomer(ssn);
                }
                case 6 -> {
                    System.out.println("Enter a SSN to Receive a Booking:");
                    ssn = scanner.nextInt();
                    receiveBooking(ssn);
                }
                case 7 -> {
                    System.out.println("Enter a SSN to Receive a Order:");
                    ssn = scanner.nextInt();
                    scanner.nextLine(); // dummy
                    System.out.print("\nEnter Booking Date (Format: \"dd/MM/yyyy\" ):");
                    cnvDate1 = scanner.nextLine();
                    try {
                        Date1 = new SimpleDateFormat("dd/MM/yyyy").parse(cnvDate1);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    receiveOrder(ssn, Date1);
                }
                case 8 -> {
                    System.out.print("\nEnter a SSN :");
                    ssn = scanner.nextInt();
                    getCustomerDetails(ssn);
                }
                case 9 -> {
                    System.out.println("Enter a SSN to Get Customer Booking");
                    ssn = scanner.nextInt();
                    getCustomerBooking(ssn);
                }
                case 10 -> {
                    System.out.print("\nEnter SSN :");
                    ssn = scanner.nextInt();
                    scanner.nextLine(); // dummy
                    System.out.print("\nEnter Booking Date (Format: \"dd/MM/yyyy\" ):");
                    cnvDate1 = scanner.nextLine();
                    try {
                        Date1 = new SimpleDateFormat("dd/MM/yyyy").parse(cnvDate1);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    getCustomerOrders(ssn, Date1);
                }
                case 11 -> listStaff();
                case 12 -> listCustomer();
                case -1 -> exit();
            }





        }

    }}
