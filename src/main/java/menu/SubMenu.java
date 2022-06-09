package menu;

import model.Admin;
import persistence.RepositoryAdmin;

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
                case 1:
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
                    break;
                case 2:
                    clientMenuChoice(input);
                    break;
                case 3: new ClientMenu().saveClient(input);
                break;
                case 100:
                    exit = true;
                    System.out.println("System closed.");
                    break;
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
                case 1:
                    new AdminMenu().menuChoice(admin, input);
                    break;
                case 2: new ClientMenu().menuChoice(input); ;
                break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 100:
                    MainMenu.getMainMenu();
                    break;
                default:
                    System.out.println("\nSorry, please enter valid Option");
                    menuOptions(input);
                    break;
            }// End of switch statement
        } while (!exit);
    }

    private int clientMenuOptions(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the admin option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: My info");
        System.out.println("2: Search for trips");
        System.out.println("3: Book a trip");
        System.out.println("4: Cancel a trip");
        System.out.println("5: Show my invoices");
        System.out.println("100 - Return to Main Menu");
        System.out.println("\n/***************************************************/");
        return input.nextInt();
    }

    private void clientMenuChoice(Scanner input) {
        int userChoice;
        do {
            userChoice = clientMenuOptions(input);
            switch (userChoice) {
                case 1:
                    ;
                    break;
                case 2:

                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 100:
                    MainMenu.getMainMenu();
                    break;
                default:
                    System.out.println("\nSorry, please enter valid Option");
                    menuOptions(input);
                    break;
            }// End of switch statement
        } while (!exit);
    }

}
