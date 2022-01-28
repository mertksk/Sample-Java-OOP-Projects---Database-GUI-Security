package assignment.support; /**
 *
 * @author mert_
 * This is abstract class for all assignment.support.Person Types.
 * The main purpose is to create a Roof for all kind of assignment.support.Person's will be occured in our system.
 **/

import java.util.Date;

public abstract class Person {
    private int ssn;
    private String name;
    private char gender;
    private Date dateOfBirth;

    public Person() {
        this.ssn = 00;
        this.name = "None";
        this.gender = 'X';
        dateOfBirth=null;
    }

    public Person(int ssn, String name, Date dateOfBirth) {
        this.ssn = ssn;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender='U';
    }

    public Person(int ssn, String name, char gender, Date dateOfBirth) {
        this.ssn = ssn;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Get method for SSN variable
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
     * Get method for Name variable
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
     * Get method for Gender
     */
    public char getGender() {
        return gender;
    }
    /**
     * Set method for Gender
     */
    public void setGender(char gender) {
        this.gender = gender;
    }
    /**
     * Get method for Date of Birth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    /**
     * Set method for Date of Birth
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
