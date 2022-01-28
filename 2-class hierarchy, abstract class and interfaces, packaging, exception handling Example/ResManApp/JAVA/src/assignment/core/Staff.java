package assignment.core;

import assignment.support.Employee;
import assignment.support.Person;

import java.util.Date;

//Date will be added
/**
 *
 * @author mert_
 * This class is defined for Staffs.
 * The main purpose of this class is to collect details of Staffs.
 *
 */
public class Staff extends Person implements Employee {

    private Date startDate;



    /**
     * default Constructor
     */
    public Staff() {
       super();
       this.startDate=null;

    }

    /**
     * Main Constructor
     */
    public Staff(int ssn, String name, char gender,Date dateofBirth,Date startDate) {
         super(ssn,name,gender,dateofBirth);
         this.startDate=startDate;
    }


    /*Get and Set Methods for our variables */
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    /**
     *This method is to display salaries of all kind of staffs
     * @param anyStaff object is non type variable .We check instance of ,and categorize after that.
     */


    @Override
    public void getSalary(Object anyStaff) { //x
        float salary = 0;

        if(anyStaff instanceof Senior){

            java.util.Date date=new java.util.Date();
            int year1=date.getYear();
            salary=((Senior) anyStaff).getGrossSalaryYearly();
            int year2=((Senior) anyStaff).getStartDate().getYear();

            year1=year1-year2;
            for(int i=0;i<year1;i++){
                salary=salary+(salary/10);
            }
            System.out.println("SSN: "+((Senior) anyStaff).getSsn()+" Has "+salary+" as His/Her new Salary");
        }
        else if(anyStaff instanceof  Junior){

            salary=((Junior) anyStaff).getMonthlySalary();
            System.out.println("SSN: "+(((Junior) anyStaff).getSsn())+" Has "+ salary+" as His/Her new Salary" );
        }
        else{
            System.out.println("Unknown Staff Type");
        }


    }
}

