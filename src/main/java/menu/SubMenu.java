package menu;

import model.Admin;
import model.Client;
import persistence.RepositoryAdmin;
import persistence.RepositoryClient;
import persistence.RepositoryDebitCard;
import persistence.RepositoryTrip;
import validation.Validation;

import java.util.Scanner;

public class SubMenu {
    private boolean exit = false;

    protected int menuOptions(Scanner input) {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("Select role or create client account. ");
        System.out.println("-------------------------------------------------------");
        System.out.println();
        System.out.println("1: - Admin");
        System.out.println("2: - Client");
        System.out.println("3: - Create client account");
        System.out.println("100 - Quit");
        System.out.println("***************************************************");

        System.out.println("Type the sub menu option: ");
        return input.nextInt();
    }

    public void menuChoice(Scanner input) {
        while (!exit) {
            int userChoice = menuOptions(input);
            ;
            switch (userChoice) {
                case 1 -> {
                    if (new RepositoryAdmin().adminList().size() == 0) {
                         System.out.println("No admin registered in database. Create new admin and log in.");
                         new AdminMenu().saveAdmin(input);
                     } else {
                         try {
                             System.out.println("Enter username: ");
                             String username = input.next();
                             if (new RepositoryAdmin().getAdminByUserName(username) != null) {
                                 Admin admin = new RepositoryAdmin().getAdminByUserName(username);
                                 System.out.println("Enter password");
                                 String password = input.next();
                                 if (new RepositoryAdmin().getPasswordByUserName(admin.getUserName()).equals(password)) {
                                     adminMenuChoice(admin, input);
                                 } else {
                                     System.out.println("Incorrect password.");
                                 }
                             }
                         } catch (Exception e) {
                             System.out.println("Login failed.");
                         }
                     }
                }
                case 2 -> {
                    if (new RepositoryClient().clientList().size() == 0) {
                        System.out.println("No client registered in database. Create new client and log in.");
                        new ClientMenu().saveClient(input);
                    } else {
                        try {
                            System.out.println("Enter email: ");
                            String email = input.next();
                            if (new RepositoryClient().getClientByEmail(email) != null) {
                                Client client = new RepositoryClient().getClientByEmail(email);
                                System.out.println("Enter password");
                                String password = input.next();
                                if (client.getPassword().equals(password)) {
                                    clientMenuChoice(client, input);
                                } else {
                                    System.out.println("Incorrect password.");
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Login failed.");
                        }
                    }
                }
                case 3 -> new ClientMenu().saveClient(input);
                case 100 -> {
                    exit = true;
                    System.out.println("System closed.");
                }
//                default:
//                    System.out.println("Invalid input");
//                    menuChoice(input);
            }
        }
    }

    private int adminMenuOptions(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the admin option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: Admin");
        System.out.println("2: Client");
        System.out.println("3: Country");
        System.out.println("4: City");
        System.out.println("5: Trip");
        System.out.println("6: Plane");
        System.out.println("7: Plane company");
        System.out.println("8: Invoice");
        System.out.println("100 - Return to Main Menu");
        System.out.println("\n/***************************************************/");
        return input.nextInt();
    }

    public void adminMenuChoice(Admin admin, Scanner input) {

        int userChoice;
        do {
            userChoice = adminMenuOptions(input);
            switch (userChoice) {
                case 1 -> new AdminMenu().menuChoice(admin, input);
                case 2 -> new ClientMenu().menuChoice(admin, input);
                case 3 -> new CountryMenu().menuChoice(admin, input);
                case 4 -> new CityMenu().menuChoice(admin, input);
                case 5 -> new TripMenu().menuChoice(admin, input);
                case 6 -> new PlaneMenu().menuChoice(admin, input);
                case 7 -> new PlaneCompanyMenu().menuChoice(admin, input);
                case 8 -> new InvoiceMenu().menuChoice(admin, input);
                case 100 -> MainMenu.getMainMenu();
                default -> {
                    System.out.println("\nSorry, please enter valid Option");
                    menuOptions(input);
                }
            }// End of switch statement
        } while (!exit);
    }

    private int clientMenuOptions(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the client menu option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: My info");
        System.out.println("2: Trip menu");
        System.out.println("3: Show my invoices");
        System.out.println("4: My debit card info");
        System.out.println("100 - Return to Main Menu");
        System.out.println("\n/***************************************************/");
        return input.nextInt();
    }

    private void clientMenuChoice(Client client, Scanner input) {
        int userChoice;
        do {
            userChoice = clientMenuOptions(input);
            switch (userChoice) {
                case 1 -> {
                    System.out.println(client);
                    ;
                }
                case 2 -> clientTripMenu(client, input);
                case 3 -> new InvoiceMenu().invoiceListByClient(client);
                case 4 -> debitCardMenu(client, input);
                case 100 -> MainMenu.getMainMenu();
                default -> {
                    System.out.println("\nSorry, please enter valid Option");
                    menuOptions(input);
                }
            }// End of switch statement
        } while (!exit);
    }

    private void clientTripMenu(Client client, Scanner input) {

        boolean exit = false;
        int userChoice= 0;
        do {
            System.out.println("\n/***************************************************/");
            System.out.println("Select the trip menu option: ");
            System.out.println("-------------------------\n");
            System.out.println();
            System.out.println("1: Show all trips");
            System.out.println("2: Show trips from city");
            System.out.println("3: Show trip to city");
            System.out.println("4: Show trip with maximum price.");
            System.out.println("5: Book a trip");
            System.out.println("100 - Return to home Menu");
            System.out.println("\n/***************************************************/");
            userChoice = input.nextInt();
            switch (userChoice) {
                case 1 -> new TripMenu().tripList();
                case 2 -> new TripMenu().getTripsFromCity(input);
                case 3 -> new TripMenu().getTripsToCity(input);
                case 4 -> new TripMenu().getTripsWithMaxPrice(input);
                case 5 -> new TripMenu().bookATrip(client, input);
                case 100 -> exit= true;
                default -> {
                    System.out.println("\nSorry, please enter valid Option");
                    menuOptions(input);
                }
        }}while (!exit);

    }

    private void debitCardMenu(Client client, Scanner input) {
        System.out.println(client.getDebitCard());
        System.out.println("Do you want make a deposit? (Y/N)");
        String response = input.next().toUpperCase();
        if (response.equals("Y")) {
            depositMoney(client, input);
        }
    }

    private void depositMoney(Client client, Scanner input) {
        try {
            System.out.println("Enter amount to deposit:");
           new RepositoryDebitCard().setNewBalance(client.getDebitCard().getDebitCardId(), (client.getDebitCard().getBalance()+input.nextDouble()));
            System.out.println("Deposit successful! New balance: " + new RepositoryDebitCard().getBalanceById(client.getDebitCard().getDebitCardId()));
        } catch (Exception e) {
            System.out.println("Ups, something went wrong...");
        }
    }
}