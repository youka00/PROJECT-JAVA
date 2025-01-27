import java.io.IOException;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class ContactsApp{
private List<personInfo> contacts = new ArrayList<>();
    public static void main(String[] args) {
        ContactsApp app = new ContactsApp();
            app.start();
    }

    public void start() {
        loadContact();

        Console c = System.console();

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
            case "1":
               if (contacts.isEmpty()) {
                System.out.println("No contacts available");
               } else {
                for (personInfo p : contacts) {
                    printPerson(p);
                }
               }
                break;
            case "2":
                personInfo newPerson = addPerson(c);
                contacts.add(newPerson);
                newPerson.saveInfo();
                System.out.println("Person is added!");

                break;
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
            case "5":
                System.out.println("Exiting from the app");
                return;
            default:
                System.out.println("Invalid choice!");
                break;


        }


    }

}

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

public personInfo findPersonById(String id) {
    for(personInfo person : contacts) {
        if(person.getid().equals(id)) {
            return person;
        }
    }
        return null;

}
    public personInfo addPerson(Console c) {
        String fName = c.readLine("Enter first name: ");
        String Lname = c.readLine("Enter last name: ");
        String id = c.readLine("Enter id: ");
        int phoneNumber = Integer.parseInt(c.readLine("Enter phone number: " + " +"));
        String address = c.readLine("Enter address: ");
        String eMail = c.readLine("Enter email: ");



        return new personInfo(fName, Lname, id, phoneNumber, address, eMail);
    }



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


