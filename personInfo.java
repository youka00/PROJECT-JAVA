import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class personInfo {
    private String fName;
    private String Lname;
    private String id;
    private int phoneNumber;
    private String address;
    private String eMail;


    public personInfo(String fName, String Lname, String id, int phoneNumber, String address, String eMail) {
        this.fName = fName;
        this.Lname = Lname;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.eMail = eMail;
    }

    public String getContactsCsv() {
        return this.id + ", " + this.fName + ", " + this.Lname + ", " + this.phoneNumber + ", " + this.address + ", " + this.eMail;

    }

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

    public String getfName() {
        return fName;
    }
    public String getLname() {
        return Lname;
    }
    public String getid() {
        return id;
    }
    public int getphoneNumber() {
        return phoneNumber;
    }
    public String getaddress() {
        return address;
    }
    public String geteMail() {
        return eMail;
    }


    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setLname(String Lname) {
        this.Lname = Lname;
    }

    public void setid(String id) {
        this.id = id;
    }

    public void setphoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setaddress(String address) {
        this.address = address;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
}