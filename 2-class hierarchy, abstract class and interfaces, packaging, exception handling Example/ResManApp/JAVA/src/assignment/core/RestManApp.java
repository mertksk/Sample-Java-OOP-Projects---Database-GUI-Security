package assignment.core;

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
    public static void addOrderofBooking(int ssn, Date bookingDate){
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
     *This methods lists all orders in our system
     */

    public static void listAllOrder(  ){
        for (Customer i:customers){
            for(OnlineOrder z:i.getOnlineOrders()){
                System.out.println("OrderID: "+z.getOrderID());
                System.out.println("Order Type: ONLINE");
                System.out.println("Order Date: "+z.getOrderDate());
                System.out.println("Order Details: "+z.getOrderDetails());
                System.out.println("Extra Notes: "+z.getExtraNotes()+"\n");

            }
            for(Booking a:i.getBookings()){
                for(inRestOrder x:a.getOrders()){
                    System.out.println("OrderID: "+x.getOrderID());
                    System.out.println("Order Type: IN RESTAURANT");
                    System.out.println("Order Date: "+x.getOrderDate());
                    System.out.println("Order Details: "+x.getOrderDetails());
                    System.out.println("Extra Notes: "+x.getExtraNotes()+"\n");


                }
            }

        }


    }

    /**
     * This method is to add Order
     * @param ssn is used to find customer in the list
     * @param orderType is used to select the type of Order
     */
    public static void addOrder(int orderType,int ssn){
        Scanner scanner =new Scanner(System.in); // To get input
        int activeHigh=0; // control to check if ssn is okay
        String orderDetails ,extraNotes,paymentType;
        for(Customer i:customers){
            if(i.getSsn()==ssn){
                if(orderType==1){ //Online Order

                    ArrayList<OnlineOrder> order1=i.getOnlineOrders();

                    System.out.println("Enter the Details of Online Order");
                    orderDetails = scanner.nextLine();

                    System.out.println("Which Food do You want to Order?(Pizza,Pide etc.)");
                    scanner.nextLine(); // dummy

                    System.out.println("Extra Notes:");
                    extraNotes = scanner.nextLine();

                    System.out.println("Payment Method(Credit Card , Cash or others): ");
                    scanner.nextLine(); // dummy
                    paymentType = scanner.nextLine();


                    order1.add(new OnlineOrder(orderDetails, extraNotes,paymentType));



                }
                else if(orderType==2){ //InRestaurantOrder
                    Date bookingDate = new Date();
                    String dateInput;
                    System.out.print("\nEnter Booking Date (Format: \"dd/MM/yyyy\" ):");
                    dateInput = scanner.nextLine();
                    try {
                        bookingDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateInput);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    addOrderofBooking(ssn,bookingDate);

                }
                else{
                    System.out.println("Unknown type");
                }
                activeHigh++;
                break;
            }      }
        if(activeHigh==0)System.err.println("THE SSN IS NOT REGISTERED YET\n");





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
                        for(inRestOrder x:a.getOrders()){
                            System.out.println("\nORDERS:\n");
                            System.out.println("Details:");
                            System.out.println(x.getOrderDetails());
                            System.out.println("Description:");
                            System.out.println(x.getExtraNotes()+"\n");
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
     * This method will display the most
     * recent booking done by the customer.
     */


    public static void displayCustomerLastBooking(int ssn){
        int control=0;

        for(Customer i:customers){
            if( i.getSsn()==ssn){
                control++;
                if (i.getBookings() != null && !i.getBookings().isEmpty()) {
                    Booking item = i.getBookings().get(i.getBookings().size()-1);

                        System.out.println("Last Booking Details of ID("+i.getSsn()+") : \n");
                        System.out.println("Last Booking ID: " + i.getSsn() );
                        System.out.println("Date -> "+item.getBookingDate());
                        System.out.println("Table Number -> "+item.getBookingTable() + "\n");
                        ArrayList<inRestOrder> lastOrder=item.getOrders();
                        int counter=1;
                        for(inRestOrder x:lastOrder){

                            System.out.println("ID of Order: " + x.getOrderID());
                            System.out.println("Details of Order: " + counter );
                            System.out.println("Details: "+x.getOrderDetails());
                            System.out.println("Extra Notes " + x.getExtraNotes());
                            counter++;

                        }
                }

            }}
        if(control==0)System.err.println("THE SSN IS NOT REGISTERED YET\n");


    }

    /**
     * This method is to list salaries of all staff
     */
   public static void listAllStafSalary(){

       for (Staff i:staffs){
           System.out.println("\nName: "+i.getName() );
           i.getSalary(i);
       }
    }

    /**
     * this method will display a customer's bookings.
     * If the snn number does not exist,
     * the program should provide an appropriate error message.
     * @param ssn is required to find Customer.
     */
    public static void  addBooking(int ssn){
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
        }    }

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

            customers.add(new Customer(ssn,name,gender,dateofBirth,registrationDate,null,null)); // will be updated
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
            int salary;

            System.out.println("Enter Name");
            String name = scanner.nextLine();

            System.out.println("Enter SSN:");
            int ssn = scanner.nextInt();

            System.out.println("Staff Type (1)Junior / (2)Senior");
            int selection = scanner.nextInt();

            System.out.println("Enter Gender (F for Female /M for Male / O for others)");
            char gender=scanner.next().charAt(0);

            scanner.nextLine(); // dummy

            System.out.println("Enter Date of Birth (dd/mm/yyyy)");
            String dateinput = scanner.nextLine();
            Date dateofBirth =new SimpleDateFormat("dd/MM/yyyy").parse(dateinput);



            System.out.println("Enter Start Date (dd/mm/yyyy):");
            String strDate = scanner.nextLine();
            Date startDate =new SimpleDateFormat("dd/MM/yyyy").parse(strDate);
            if(selection==1){



                System.out.println("Enter Salary for Junior:");
                salary = scanner.nextInt();

                scanner.nextLine(); // dummy

                System.out.println("Enter Expected End Date (dd/mm/yyyy):");
                String Edate = scanner.nextLine();
                Date expectedEndDate =new SimpleDateFormat("dd/MM/yyyy").parse(Edate);


                staffs.add(new Junior(ssn,name,gender,dateofBirth,startDate,salary,expectedEndDate));
            }
            else if (selection==2){

                System.out.println("Enter Salary for Senior");
                salary = scanner.nextInt();

                staffs.add(new Senior(ssn,name,gender,dateofBirth,startDate,salary));
            }
            else{
                staffs.add(new Staff(ssn,name,gender,dateofBirth,null));
            }


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
        System.out.println("6) Add support.Order of Booking");
        System.out.println("7) Get Booking");
        System.out.println("8) Customer Details");
        System.out.println("9) Add Booking");
        System.out.println("10) Customer Order");
        System.out.println("11) List Staff");
        System.out.println("12) List Customer");
        System.out.println("13) Display Customer's Last Booking");
        System.out.println("14) Add Order");
        System.out.println("15) List All Staff Salary");
        System.out.println("16)List All Orders");
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


        //noinspection InfiniteLoopStatement
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
                    addOrderofBooking(ssn, Date1);
                }
                case 8 -> {
                    System.out.print("\nEnter a SSN :");
                    ssn = scanner.nextInt();
                    getCustomerDetails(ssn);
                }
                case 9 -> {
                    System.out.println("Enter a SSN to Get Customer Booking");
                    ssn = scanner.nextInt();
                    addBooking(ssn);
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
                case 13 ->{
                    System.out.print("\nEnter SSN :");
                    ssn = scanner.nextInt();
                    displayCustomerLastBooking(ssn);}

                case 14->{
                    System.out.println("Order Adder");
                    System.out.println("-------------------------");
                    System.out.println("\nOrder Type ((1)Online order (2)In Restaurant Order)):");
                    select = scanner.nextInt();
                    System.out.print("\nEnter SSN :");
                    ssn = scanner.nextInt();
                    addOrder(select,ssn);
                }
                case 15->listAllStafSalary();
                case 16->listAllOrder();
                case -1 -> exit();
                default -> System.out.println("Undefined Option");
            }





        }

    }}
