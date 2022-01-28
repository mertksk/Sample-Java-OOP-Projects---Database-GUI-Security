package core;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

 
public class DataStorage {
    
    /*Here is details of our database*/
    private String kullanici_adi = "cng443user";
    private String parola = "1234";
    
    private String db_ismi = "hms";
    
    private String host =  "localhost";
    
    private int port = 3306;
    
    private Connection con = null;
    
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    
    
    
    //This method adds new booking for a Customer
    public void addBookingDMBS(int Customer_id,int Booking_table,String Booking_date) {
        
         
        
        try {
            statement = con.createStatement();
           String query = "INSERT INTO booking(Customer_id,Booking_table,Booking_date) VALUES (?, ?, ?)";
           PreparedStatement pstmt = con.prepareStatement(query);
           pstmt.setInt(1,Customer_id); 
           pstmt.setInt(2,Booking_table);
           pstmt.setString(3,Booking_date);
           pstmt.execute();
            
   
        } catch (SQLException ex) {
            Logger.getLogger(DataStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
    
    
     public void addCustomerDMBS(int ssn,String name,char gender,String DOB,String DateOfRegistration,String paymentType) {
        
         
        
        try {           
         
            statement = con.createStatement();
           String query = "INSERT INTO customer(ssn,name,gender,DOB,DateOfRegistration,Payment_Type) VALUES (?, ?, ? ,? ,? , ?)";
           PreparedStatement pstmt = con.prepareStatement(query);
           pstmt.setInt(1,ssn); 
           pstmt.setString(2,name);
           pstmt.setObject(3 , gender, java.sql.Types.CHAR) ;
           pstmt.setString(4,DOB);
           pstmt.setString(5,DateOfRegistration);
           pstmt.setString(6,paymentType);
           pstmt.execute();
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DataStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void deleteCustomerDBMS(int ssn) {
        
        try {
            statement = con.createStatement();
            
            String query = "Delete from customer where ssn = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1,ssn); 
            pstmt.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(DataStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
 
    
    /*
    
    initialBookingLoad takes loaded Customers and brings their bookings to the system from the Database
    
    */
    public void initialBookingLoad(ArrayList<Customer> customerList) {
     
        ArrayList<Booking> temp;
        int ssn=0;
        Date date1=null;
        int Booking_table;
        String Booking_date;
        String query="Select * From booking";
        try {
            statement = con.createStatement();           
            ResultSet rs = statement.executeQuery(query);
            
            while (rs.next()) {
                
                ssn = rs.getInt("Customer_id");
                Booking_table = rs.getInt("Booking_table");               
                Booking_date = rs.getString("Booking_date");                                         
                if(customerList!=null){
                    
                    for(Customer i:customerList){
                        if(ssn==i.getSsn()){
                            temp=i.getBookings();
                            try {
                                date1 = new SimpleDateFormat("dd/MM/yyyy").parse(Booking_date);
                            } catch (ParseException ex) {
                                Logger.getLogger(DataStorage.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            temp.add(new Booking(date1));
                            i.setBookings(temp);
                            
                        }
                        
                    }

             }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DataStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }
    /*It loads the Customers from the Database*/
    public ArrayList<Customer> initialCustomerLoad() {
        
     
        ArrayList<Customer> temp=new ArrayList<>() ; 
        Date date1=null,date2=null;
        int Booking_table;
        String Booking_date;
        String name;
        String gender ;     
        String DOB;
        String DateOfRegistration;
        String paymentType;
        String query = "Select * From customer";
        int ssn=0;
        try {
            statement = con.createStatement();
            
            ResultSet rs = statement.executeQuery(query);
            
            while (rs.next()) {
                
                ssn = rs.getInt("ssn");
                name = rs.getString("name");               
                gender = rs.getString("gender");                
                DOB = rs.getString("DOB");             
                DateOfRegistration = rs.getString("DateOfRegistration");
                paymentType=rs.getString("Payment_Type");
               
                try {
                    date1 = new SimpleDateFormat("dd/MM/yyyy").parse(DOB);
                    date2 = new SimpleDateFormat("dd/MM/yyyy").parse(DateOfRegistration); 

                } catch (ParseException ex) {
                    Logger.getLogger(DataStorage.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                temp.add(new Customer(ssn,name,gender.charAt(0),date1,date2,paymentType));
          
            }
            
           
     
        initialBookingLoad(temp);   
        
            
        } catch (SQLException ex) {
            Logger.getLogger(DataStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return temp;
        
    }
    
    /*Constructor where the Database constructed.It takes the predefined values ,and connects to the Database*/
    public DataStorage() {
        
      
        String url = "jdbc:mysql://" + host + ":" + port + "/" + db_ismi+ "?useUnicode=true&characterEncoding=utf8";
        
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver cannot be found....");
        }
        
        
        try {
            con = DriverManager.getConnection(url, kullanici_adi, parola);
           // System.out.println("Connection is Succesfull");
            
            
        } catch (SQLException ex) {
            System.out.println("Connection is not Succesfull...");
            //ex.printStackTrace();
        }
        
    } 

    
}