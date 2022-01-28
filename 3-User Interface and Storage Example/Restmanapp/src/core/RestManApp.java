package core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

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
    
    /*Holds  path of text Databases*/
    private static String staffFile;
    private static String customerFile;
    

    public RestManApp() { /* Default constructor*/
    }

    /**
     * This method updates the Staff Data in Database when it is called
     * @throws IOException 
     */
    public static void updateStaffDatabase() throws IOException{
        if(staffs==null){
            
        }
        else{
                File f=new File(staffFile);
            try (FileWriter fw = new FileWriter(f)) {
                fw.write(""); 
                fw.close();
            }
            FileWriter fw=new FileWriter(f,true);
            for(Staff a:staffs){
                Date date1=a.getDateOfBirth();
                DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy"); 
                String printer=dateFormat.format(date1);

                Date date2=a.getDateOfBirth();
                DateFormat dateFormat2 = new SimpleDateFormat("dd/mm/yyyy"); 
                String printer2=dateFormat2.format(date2);

                fw.write("Senior"+" "+a.getName()+" "+a.getGender()+" "+a.getSsn()+" "+printer+" "+"3500"+" "+printer2+" ");
                } 
            fw.close();
        }
        
        /*
         for(Staff a:staffs){
                fw.write(saka);
            }
        */
        
        
    }
    /**
     * This method updates the Customer Data in Database when it is called
     * @throws IOException 
     */
    public static void updateCustomerDatabase() throws IOException{
        if(customers==null){
            
        }
        else{
            
            File f=new File(customerFile);
            try (FileWriter fw = new FileWriter(f)) {
                fw.write(""); 
                fw.close();
            }
            FileWriter fw=new FileWriter(f,true);
            for(Customer i:customers){
                if(i.getOnlineOrders()!=null){
                    
                
                    for(OnlineOrder cnt:i.getOnlineOrders()){
                        fw.write("onlineorder"+","+cnt.getOrderDetails()+","+cnt.getExtraNotes()+","+cnt.getPaymentType()+",");

                }}
                if(i.getBookings()!=null){
                    
                
                    for(Booking imp:i.getBookings()){
                        if(imp.getOrders()!=null){

                            for(inRestOrder zip:imp.getOrders()){
                                fw.write("inrestaurant"+","+zip.getOrderDetails()+","+zip.getExtraNotes()+",");
                       }}
                    }
                }
                
                if(i.getBookings()!=null){
                    
                
                for(Booking imp:i.getBookings()){
                    
                    Date date1=imp.getBookingDate();
                    DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
                    String printer=dateFormat.format(date1);
                    fw.write("booking"+","+printer+",");
                }
                }
                Date date1=i.getDateOfBirth();
                DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
                String printer=dateFormat.format(date1);
                
                Date date2=i.getRegistrationDate();
                String printer2=dateFormat.format(date1);
                
                fw.write("customer"+","+i.getSsn()+","+i.getName()+","+i.getGender()+","+printer+","+printer2+","+i.getCreditCardDetails()+",");
            }
        
               
            fw.close();
    }
        } 
     /**
     * This method gets staffFile's path 
     * @throws IOException 
     */
    public static void setStaffFile(String staffFile) {
        RestManApp.staffFile = staffFile;
    }
  /**
     * This method gets staffFile's path 
     * @throws IOException 
     */
    public static void setCustomerFile(String customerFile) {
        RestManApp.customerFile = customerFile;
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
   /*public static void listAllStafSalary(){

       for (Staff i:staffs){
           System.out.println("\nName: "+i.getName() );
           i.getSalary(i);
       }
    }*/

    /**
     * this method will display a customer's bookings.
     * If the snn number does not exist,
     * the program should provide an appropriate error message.
     * @param ssn is required to find Customer.
     */
    public static Customer  getCustomer(int ssn){
        boolean activeHigh=false;
        System.out.println("\nBOOKINGS:");
        for (Customer i:customers){

            if((ssn)==i.getSsn()){
                activeHigh=true;
                //System.out.println("\nName: "+i.getName()+"   SSN: "+i.getSsn()+"   Gender: "+i.getGender()+"   Date Of Birth: "+i.getDateOfBirth()+"   Date of Registration: "+i.getRegistrationDate());
                return i;    
                
                
            }
        
        
        }


        if(!activeHigh){
            System.err.println("The SSN "+ssn+" Could not be Founded");
          
        }   return null;
 }

    /**
     *  This method will list all the customer.
     */
    public static ArrayList<Customer> listCustomer(){
    
        return customers;
        
    }

    /**
     * This method will list all the staff.
     */
    public static ArrayList<Staff> listStaff(){

        
            return staffs;
        }
    /**
     * this method  will display the customer details.
     * If the snn number does not exist, the
     * method should provide an appropriate error message
     * @param ssn is required to find Customer.
     */
   /* public static void getCustomerDetails(int ssn){

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
    }*/


    /**
     *this method will display the Staff details.
     * If the snn number does not exist, the program
     * should provide an appropriate error message.
     * @param ssn is required to find Staff.
     */
    
    public static void systemCustomerAdd(int ssn, String name, char gender, Date dateOfBirth, Date registrationDate, ArrayList<Booking> bookings, ArrayList<OnlineOrder> onlineOrders, String creditCardDetails){
        
         if(customers==null){
            customers =new ArrayList<>();
         }
        customers.add(new Customer(ssn,name,gender,dateOfBirth,registrationDate,bookings,onlineOrders,creditCardDetails ));
         
       //Customer()
    }
    public static Staff getStaffDetails(int ssn){
      
        int holder,activeHigh=0;
        for (Staff i:staffs){
            holder=i.getSsn();
            if((ssn)==holder){
                activeHigh++;
               // System.out.println("Name: "+i.getName()+"   SSN Number: "+i.getSsn() +"   Gender: "+i.getGender()+ "   Date of Birth: "+ i.getDateOfBirth());
                return i;
                
            }
        }

    
        return null;
    
    
    }

    /**
     *  This method will add a customer by taking inputs from user.
     */

    public static void addCustomer(String name,int ssn,char gender,Date dateofBirth,Date registrationDate){
        
        if(customers==null){
            customers=new ArrayList<>();
        }
        
        customers.add(new Customer(ssn,name,gender,dateofBirth,registrationDate,null,null)); // will be updated
        
        
     
    }

    /**
     * This method will add new staff to the
     * list of Staff maintained.
     * @param type
     * @param ssn
     * @param gender
     * @param name
     * @param dob
     * @param salary
     * @param endDate
     */
     public static void addStaff(int ssn,char gender,String name,Date dob,float salary,Date startDate ){

         if(staffs==null){
            staffs =new ArrayList<>();
         }
         staffs.add(new Senior(ssn, name, gender,dob,startDate,salary));       

     }
    public static void addStaff(int ssn,char gender,String name,Date dob,float salary,Date startDate,Date endDate){
        if(staffs==null){
            staffs =new ArrayList<>();
         }
        staffs.add(new Junior(ssn, name,gender ,dob,startDate ,salary,endDate));  
    }
   

   
    public static boolean deleteStaff(int ssn){
        int holder,activeHigh=0; //Holder to hold value , activeHigh is an control mechanism
        int counter=0; //counts the loop
        for (Staff i:staffs){      //Travel in Arraylist one by one
            holder=i.getSsn();
            if((ssn)==holder){      //SSN Check
                activeHigh++;
                staffs.remove(counter);
                break;
            }
            counter++;
        }

        if(activeHigh==0){  //Error condition
            return false;
        }
        return true;

    }

    /**
     *This method will read an snn number of a
     * Customer, and delete the corresponding Customer object. If the snn number does
     * not exist, the program should provide an appropriate error message.
     * @param ssn is required to find Customer.
     */

    public static boolean deleteCustomer(int ssn){

        int holder,activeHigh=0; //Holder to hold value , activeHigh is an control mechanism
        int counter=0; //counts the loop
        for (Customer i:customers){ //Travel in Arraylist one by one
            holder=i.getSsn();
            if((ssn)==holder){
                activeHigh++;
                customers.remove(counter);
                break;
            }
            counter++;
        }
        if(activeHigh==0){ //Error condition
            return false;
        }
        return true;
    }


    /**
     * This method will display the interaction menu to the user.
     */
    public static void menu(){
        int option=0;
          
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                StartPoint x=new StartPoint();
                x.setVisible(true);
            }
        }); 
         //çalıştuırr
         
       

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
        ArrayList<Staff> staffs =new ArrayList<>();
        ArrayList<Customer> customers =new ArrayList<>(); 
        menu();
           

            



        

    }
    
    }
