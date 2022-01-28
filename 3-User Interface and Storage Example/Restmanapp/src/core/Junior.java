package core; /**
 *
 * @author mert_
 * This class is defined for Juniors.
 * The main purpose of this class is to collect details of Junior Staffs working in our restaurants.
 *This class extends "Staff" Class
 **/

import java.util.Date;


public class Junior extends Staff{
    private float monthlySalary;
    private Date expectedEndDate;


    /*Default Constructor*/
    public Junior() {
        super();
        this.expectedEndDate=null;
        this.monthlySalary=0;

    }
    /*Second Constructor*/
    public Junior(int ssn, String name, char gender, Date dateofBirth, Date startDate, float monthlySalary, Date expectedEndDate) {
        super(ssn, name, gender, dateofBirth, startDate);
        this.monthlySalary = monthlySalary;
        this.expectedEndDate = expectedEndDate;
    }
    /*Get method for MonthlySalary*/
    public float getMonthlySalary() {
        return monthlySalary;
    }
    /*Set method for MonthlySalary*/
    public void setMonthlySalary(float monthlySalary) {
        this.monthlySalary = monthlySalary;
    }
    /*Get method for ExpectedEndDate*/
    public Date getExpectedEndDate() {
        return expectedEndDate;
    }
    /*Set method for ExpectedEndDate*/
    public void setExpectedEndDate(Date expectedEndDate) {
        this.expectedEndDate = expectedEndDate;
    }

}
