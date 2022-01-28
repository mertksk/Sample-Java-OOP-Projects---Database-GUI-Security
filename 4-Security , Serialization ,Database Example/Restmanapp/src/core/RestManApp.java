package core;

import Forms.MainMenu;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import static java.lang.System.in;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mert_
 * This class is a brain of our program where main and other vital funtions are defined.
 *
 */

public class RestManApp {

    
    private static ArrayList<Staff> staffs;  /*Staff ArrayList to hold Staffs in our program*/
    private static ArrayList<Customer> customers; /*Customer ArrayList to hold Customers in our program*/
    
    /*Holds  path of text Databases*/

    private static DataStorage dataBase;

    public RestManApp() { /* Default constructor*/
    }
    /*
    * This method takes the outcome of our objects and creates a Unique Password.
    */
    public static String createMd5(String input) 
    { 
        try { 
  
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
           
            byte[] messageDigest = md.digest(input.getBytes()); 
  
  
            BigInteger point = new BigInteger(1, messageDigest); 
  
            String temp = point.toString(16); 
            while (temp.length() < 32) { 
                temp = "0" + temp; 
            } 
            return temp; 
        }  
  
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 
  
    
    /*It is called at the beggining of the program ,and it takes the file where customer objects
     *are stored and controls if there is a change or not
     */
    public static void controlData() throws IOException{
        
        ArrayList<Customer> cst = null;  
        String controlData1=null , controlData2=null;
       try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("CustomerBooking.bin"))){
           
       
        
           try {
               
               cst = (ArrayList<Customer> )in.readObject();
             
           } catch (ClassNotFoundException ex) {
               Logger.getLogger(RestManApp.class.getName()).log(Level.SEVERE, null, ex);
           }
           }catch (FileNotFoundException ex) {
            System.out.println("File cannot found or the first time and not created yet...");
       
        } 
        System.out.println("************************************");
    if(cst!=null){
    

        for (Customer o : cst) {
            controlData1=controlData1+o.toString();
            }
            controlData1=createMd5(controlData1);        
            }
          File file = new File("md5.txt"); 
          Scanner sc = new Scanner(file); 
  
        
          controlData2=sc.nextLine();
          
          if(controlData1.equals(controlData2)){
               System.out.println("All Datas are Safe!");
          }
          else{
              System.err.println("We are Under Attack , our Database has been changed!");
          }
  } 
    
    /*This method is called at the end of the program ,it saves all datas ,and creates, stores a MD5 password for them*/
    public static void serializeandMd5(){
        
        PrintWriter writer=null;
        try{

            
            String temp=null;
            try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("CustomerBooking.bin"))){
                
                
                out.writeObject(customers);
                
            } catch (FileNotFoundException ex) {
                System.out.println("File cannot found...");
            } catch (IOException ex) {
                System.out.println("an Error occured druing file operation...");
            }
            if(customers!=null){
                for(Customer x:customers){
                    temp=temp+x.toString();
                }
            }
            temp=createMd5(temp);
            writer = new PrintWriter("md5.txt");
            writer.print(temp);   
            writer.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RestManApp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            writer.close();
        }
            
        
    }
    
    
    
  
  /*Returns the datastorage variable in Main Class*/
    public static DataStorage getdataBase(){
        
        return dataBase;
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
     * this method will display a customer's bookings.
     * If the snn number does not exist,
     * the program should provide an appropriate error message.
     * @param ssn is required to find Customer.
     */
    public static Customer  getCustomer(int ssn){
        boolean activeHigh=false;
        if(customers!=null){
            for (Customer i:customers){
                if((ssn)==i.getSsn()){
                    activeHigh=true;
                    return i;       
                }}
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

    public static void addCustomer(String name,int ssn,char gender,Date dateofBirth,Date registrationDate,String paymentType){
        
        if(customers==null){
            customers=new ArrayList<>();
        }
        
        customers.add(new Customer(ssn,name,gender,dateofBirth,registrationDate,paymentType,null)); // will be updated
        
        
     
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
        dataBase.deleteCustomerDBMS(ssn);
        return true;
    }


    /**
     * This method will display the interaction menu to the user.
     */
    public static void menu(){
        int option=0;
        
        customers=dataBase.initialCustomerLoad();
        
        try {
            controlData();
        } catch (IOException ex) {
            Logger.getLogger(RestManApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                 MainMenu a=new MainMenu();   
                a.setVisible(true);
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
       
        //Input taker
        Scanner scanner =new Scanner(System.in);
        ArrayList<Staff> staffs =new ArrayList<>();
        ArrayList<Customer> customers =new ArrayList<>(); 
        dataBase=new DataStorage();
        menu();
        serializeandMd5(); 

            



        

    }
    
    }
