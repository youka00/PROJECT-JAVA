import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Person class for Contacts app.
 * This classes methods provides set, get and save contact details.
 * It includes details such as name, id, phone number etc.
 */
public class personInfo {
    private String fName;
    private String Lname;
    private String id;
    private int phoneNumber;
    private String address;
    private String eMail;

/**
 * Builds a personInfo object with given details.
 * @param fName first name of the person
 * @param Lname last name of the person.
 * @param id The ID of the person.
 * @param phoneNumber the phone number of the person.
 * @param address the address of the person
 * @param eMail the email of the person.
 */
    public personInfo(String fName, String Lname, String id, int phoneNumber, String address, String eMail) {
        this.fName = fName;
        this.Lname = Lname;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.eMail = eMail;
    }
    /**
     * Converts the person's infos into a csv formated string.
     * @return person's info as csv formated string.
     */

    public String getContactsCsv() {
        return this.id + ", " + this.fName + ", " + this.Lname + ", " + this.phoneNumber + ", " + this.address + ", " + this.eMail;

    }
    /**
     * Saves the person's details to the csv file.
     */

    public void saveInfo() {
    try {


        String csvPersonInfo = getContactsCsv();
        BufferedWriter bw = new BufferedWriter(new FileWriter("contacts.csv", true));
        bw.write(csvPersonInfo);
        bw.newLine();
        bw.close();
    } catch (IOException e) {
        System.out.println("Error");
        }
    }

/**
 * Getters and setters for personInfo fields.
 */

/**
 * returns the first name of the person
 * @return the first name of the person
 */
    public String getfName() {
        return fName;
    }
    /**
     * returns the last name of the person.
     * @return last name of the person.
     */

    public String getLname() {
        return Lname;
    }
    /**
     * return the id of the person.
     * @return the id of the person.
     */
    public String getid() {
        return id;
    }
    /**
     * return the phone number of the person.
     * @return the phone number of the person.
     */
    public int getphoneNumber() {
        return phoneNumber;
    }
    /**
     * return the address of the person.
     * @return the address of the person.
     */
    public String getaddress() {
        return address;
    }
    /**
     * return the email of the
     * @return the email of the person.
     */
    public String geteMail() {
        return eMail;
    }

    /**
     * sets the first name of the person.
     * @param fName  new first name
     */
    public void setfName(String fName) {
        this.fName = fName;
    }
    /**
     * Sets the last name of the person.
     * @param Lname new last name.
     */

    public void setLname(String Lname) {
        this.Lname = Lname;
    }
    /**
     * Sets the ID of the person.
     * @param id new ID.
     */

    public void setid(String id) {
        this.id = id;
    }
    /**
     * sets the phone number of the person.
     * @param phoneNumber new phone number.
     */

    public void setphoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * Sets the address of the person.
     * @param address new address
     */

    public void setaddress(String address) {
        this.address = address;
    }
    /**
     * Sets the email of the person
     * @param eMail new email.
     */
    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
}