package core; /**
 *
 * @author mert_
 * This class is specialized for our Senior staff.
 *
 */


import java.util.ArrayList;
import java.util.Date;

public class Senior extends Staff {
    private float grossSalaryYearly;
    private ArrayList<Junior> responsibleFrom;

    /*Default Constructor*/
    public Senior() {
        super();
        this.responsibleFrom=null;
        this.grossSalaryYearly=0;
    }

    /*Second Constructor*/
    public Senior(int ssn, String name, char gender, Date dateofBirth, Date startDate, float grossSalaryYearly) {
        super(ssn, name, gender, dateofBirth, startDate);
        this.grossSalaryYearly = grossSalaryYearly;
    }

    /*Third Constructor*/
    public Senior(int ssn, String name, char gender, Date dateofBirth, Date startDate, float grossSalaryYearly, ArrayList<Junior> responsibleFrom) {
        super(ssn, name, gender, dateofBirth, startDate);
        this.grossSalaryYearly = grossSalaryYearly;
        this.responsibleFrom = responsibleFrom;
    }

    /*Get and set methods of our variables*/
    public float getGrossSalaryYearly() {
        return grossSalaryYearly;
    }

    public ArrayList<Junior> getResponsibleFrom() {
        return responsibleFrom;
    }

    public void setGrossSalaryYearly(float grossSalaryYearly) {
        this.grossSalaryYearly = grossSalaryYearly;
    }

    public void setResponsibleFrom(ArrayList<Junior> responsibleFrom) {
        this.responsibleFrom = responsibleFrom;
    }
}
