import java.io.Console;

public class Contact{
    public static void main(String[] args) {
            Contact app = new Contact();
            app.start();
    }

    public void start() {
        Console c = System.console();
    }

    personInfo person = addPerson();
    printPerson(person);

    while (true) {
        System.out.println("\nWhat do you want to do?");
        System.out.println("1. Wiew person's info");
        System.out.println("2. Edit");
        System.out.println("3. Delete");
        System.out.println("4. Exit");
        String choice = c.readLine();

        switch (choice) {
            case "1":
                printPerson(person);
                break;
            case "2":
                person = editPerson(person, c);
                break;
            case "3":
                person = deletePerson();
                return;
            case "4":
                return;
            default:
                System.out.println("Invalid choice!");


        }


    }

}

    public personInfo addPerson(Console c) {
        String fName = c.readLine("Enter first name: ");
        String Lname = c.readLine("Enter last name: ");
        String id = c.readLine("Enter id");
        int phoneNumber = Integer.parseInt(c.readLine("Enter phone number: "));
        String address = c.readLine("Enter address: ");
        String eMail = c.readLine("Enter email: ");

        return new personInfo(fName, Lname, id, phoneNumber, address, eMail);
    }


