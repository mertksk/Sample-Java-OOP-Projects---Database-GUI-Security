import java.util.Date;

//Date will be added
/**
 *
 * @author mert_
 * This class is defined for Staffs.
 * The main purpose of this class is to collect details of Staffs.
 *
 */
public class Staff {


    private int ssn;
    private String name;
    private char gender;
    private Date dateOfBirth;

    /**
     * default Constructor
     */
    public Staff() {
        this.ssn = 00;
        this.name = "None";
        this.gender = 'X';
        dateOfBirth=null;

    }

    /**
     * Main Constructor
     * @param ssn is Social Security Number of Staff
     * @param name  is Name of Staff
     * @param gender is Gender of Staff
     * @param dateofBirth is Date of Birth of Staff
     */
    public Staff(int ssn, String name, char gender,Date dateofBirth) {
        this.ssn = ssn;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateofBirth;
    }

    /**
     * Get method for SSN variable
     */
    public int getSsn() {
        return ssn;
    }

    /**
     * Get method for Name variable
     */
    public String getName() {
        return name;
    }

    /**
     * Get method for Gender
     */
    public char getGender() {
        return gender;
    }

    /**
     * Get method for Date of Birth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }


    /**
     * Set method for Social Security Number
     */
    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    /**
     * Set method for Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set method for Gender
     */
    public void setGender(char gender) {
        this.gender = gender;
    }

    /**
     * Set method for Date of Birth
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }



}

