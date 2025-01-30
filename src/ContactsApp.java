import java.io.IOException;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
Made by melisa
/**
 * A simple contacts manager aplication.
 * You can add, edit, view and delete contacts
 * This aplicatipn doesn't have graphical user interface. Instead it is made
 * for command line user interface.
 */
public class ContactsApp{

 /**
  * A list for saving contact information.
  */
private List<personInfo> contacts = new ArrayList<>();
/**
 * The main methos, which starts the COntactsApp.
 * @param args Command line argument.
 */
    public static void main(String[] args) {
        ContactsApp app = new ContactsApp();
            app.start();
    }

    /**
     * Starts the app and displays a menu for user actions.
     * It loads contacts and then provides a console interface for user input.
     */

    public void start() {
        loadContact();

        Console c = System.console();
/**
 * Asks what the user wants to do.
 */
    while (true) {
        System.out.println("----------------------------------");
        System.out.println("What do you want to do?");
        System.out.println("1. Wiew person's info");
        System.out.println("2. Add a person");
        System.out.println("3. Edit");
        System.out.println("4. Delete");
        System.out.println("5. Exit");
        System.out.println("----------------------------------");
        String choice = c.readLine();

        switch (choice) {
            /**
             * Case1
             * Prints all contacts from contacts.CSV file if there is any contacts
             * Calls printPerson method.
             */
            case "1":
               if (contacts.isEmpty()) {
                System.out.println("No contacts available");
               } else {
                for (personInfo p : contacts) {
                    printPerson(p);
                }
               }

               /**
                * Case 2
                * Calls addPerson method to add a new person.
                */
                break;
            case "2":
                personInfo newPerson = addPerson(c);
                contacts.add(newPerson);
                newPerson.saveInfo();
                System.out.println("Person is added!");

                break;

                /**
                 * case 3
                 * Asks the user to specify a contacts AD to edit.
                 * Edits the contact if ID exist
                 */
            case "3":
                String editId = c.readLine ("Enter the ID of the person so you can edit: ");
                personInfo personEdit = findPersonById(editId);
                if (personEdit != null) {
                    editPerson(personEdit, c);
                    saveAllContacts();
                    System.out.println("Person is edited!");
                } else {
                    System.out.println("Person not found");
                }
                break;

                /**
                 * Asks the user to specify ID.
                 * Deletes contact's from CSV file is ID exist.
                 */
            case "4":
                String deleteThisPerson = c.readLine("Enter the ID of the person so you can delete it: ");
                personInfo deletePerson = findPersonById(deleteThisPerson);
                if (deletePerson != null) {
                    contacts.remove(deletePerson);
                    saveAllContacts();
                    System.out.println("Person is deleted!");
                } else {
                    System.out.println("Person has not found");
                }
                break;
                /**
                 * Exits the application.
                 */
            case "5":
                System.out.println("Exiting from the app");
                return;
            default:
                System.out.println("Invalid choice!");
                break;


        }


    }

}
/**
 * Loads contact's from the csv file into the contacts list.
 */
public void loadContact() {
    try (BufferedReader br = new BufferedReader(new FileReader("contacts.csv"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] fields = line.split(", ");
            if (fields.length == 6) {
                String id = fields[0];
                String fName = fields[1];
                String Lname = fields[2];
                int phoneNumber = Integer.parseInt(fields[3]);
                String address = fields[4];
                String eMail = fields[5];

                personInfo person = new personInfo(fName, Lname, id, phoneNumber, address, eMail);
                contacts.add(person);

            }
        }
    } catch (IOException e) {
        System.out.println("Error");
    }
}

/**
 * Saves all the contacts from the csv file.
 */

public void saveAllContacts() {
    try(BufferedWriter bw = new BufferedWriter(new FileWriter("contacts.csv", false))) {
        for (personInfo person : contacts) {
            bw.write(person.getContactsCsv());
            bw.newLine();
    }
    } catch (IOException e) {
        System.out.println("Error");
    }

}

/**
 * Finds person by theis ID.
 * @param id the ID of the person to find.
 * @return personInfo if there is somebody by that ID. Otherwise null.
 */

public personInfo findPersonById(String id) {
    for(personInfo person : contacts) {
        if(person.getid().equals(id)) {
            return person;
        }
    }
        return null;

}

/**
 * Adds a new person.
 * @param c the console object for user input.
 * @return a new personInfo.
 */

    public personInfo addPerson(Console c) {
        String fName = c.readLine("Enter first name: ");
        String Lname = c.readLine("Enter last name: ");
        String id = c.readLine("Enter id: ");
        int phoneNumber = Integer.parseInt(c.readLine("Enter phone number: " + " +"));
        String address = c.readLine("Enter address: ");
        String eMail = c.readLine("Enter email: ");



        return new personInfo(fName, Lname, id, phoneNumber, address, eMail);
    }

/**
 * Prints the details of the person.
 * @param person object to print personInfo.
 */

public void printPerson(personInfo person) {
    Console c = System.console();
    System.out.println("Person: ");
    System.out.println("---------------------------------------------");
    System.out.println("First name: " + person.getfName());
    System.out.println("Last name: " + person.getLname());
    System.out.println("Id: " + person.getid());
    System.out.println("Phone number: " + person.getphoneNumber());
    System.out.println("Address: " + person.getaddress());
    System.out.println("Email: " + person.geteMail());
    System.out.println("---------------------------------------------");



}
/**
 * Edist an existing contact
 * @param person the personInfo object to edit
 * @param c the console object for user input.
 * @return the uppdated personInf object.
 */



public personInfo editPerson(personInfo person, Console c) {

    String fName = c.readLine("Current first name: " + person.getfName() + " Enter new first name: ");
    String Lname = c.readLine("Current last name: " + person.getLname() + " Enter new last name: " );
    String id = c.readLine("Current id: " + person.getid() + " Enter new Id: " );
    int phoneNumber = Integer.parseInt(c.readLine("Current phone number: " + person.getphoneNumber() + " Enter new phone number: " + " +"));
    String address = c.readLine("Current addres: " + person.getaddress() + " Enter new address: ");
    String eMail = c.readLine("Current Email: " + person.geteMail() + " Enter new Email: ");

    person.setfName(fName);
    person.setLname(Lname);
    person.setid(id);
    person.setphoneNumber(phoneNumber);
    person.setaddress(address);
    person.seteMail(eMail);

    return person;

    }


}


