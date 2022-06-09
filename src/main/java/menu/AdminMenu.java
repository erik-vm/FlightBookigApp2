package menu;

import model.Admin;
import persistence.RepositoryAdmin;
import validation.Validation;

import java.util.List;
import java.util.Scanner;

public class AdminMenu {


    private int menuOptions(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the admin menu option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: Save new admin");
        System.out.println("2: Delete admin");
        System.out.println("3: Change your password");
        System.out.println("4: Admin list");
        System.out.println("100 - Return to Main Menu");
        System.out.println("\n/***************************************************/");
        return input.nextInt();
    }

    protected void menuChoice(Admin admin, Scanner input) {

        int userChoice;
        do {
            userChoice = menuOptions(input);
            switch (userChoice) {
                case 1:
                    saveAdmin(input);
                    break;
                case 2:
                    deleteAdmin(input);
                    break;
                case 3:
                    changePassword(admin,input);
                    break;
                case 4: adminList(input);
                break;
                case 100:
                    MainMenu.getMainMenu();
                    break;
                default:
                    System.out.println("\nSorry, please enter valid Option");
                    menuOptions(input);
                    break;
            }// End of switch statement
        } while (userChoice != 100);
    }

    private void saveAdmin(Scanner input) {
        try {
            Admin admin = new Admin();
            System.out.println("Enter username (3-20 characters. Can contain numbers.):");
            String username = input.next();
            if (new Validation().validUserName(username)) {
                System.out.println("Set password (minimum 8 characters long, has to contain at least on uppercase, one lowercase letter and one number):");
                String password = input.next();
                if (new Validation().validPassword(password)) {
                    admin.setUserName(username);
                    admin.setPassword(password);
                    new RepositoryAdmin().saveAdmin(admin);
                    System.out.println("New admin saved.");
                } else {
                    System.out.println("Invalid password input.");
                }
            } else {
                System.out.println("Invalid username input.");
            }
        } catch (Exception e) {
            System.out.println("Saving admin failed.");
        }
    }

    private void deleteAdmin(Scanner input) {
        try {
            System.out.println("Enter admin id: ");
            int adminId = input.nextInt();
            if (new RepositoryAdmin().getAdminById(adminId) != null) {
                new RepositoryAdmin().deleteAdmin(adminId);
                System.out.println("Admin deleted.");
            }
        } catch (Exception e) {
            System.out.println("Deleting admin failed.");
        }
    }

    protected boolean validPassword(Admin admin, Scanner input) {
        return admin.getPassword().equals(input.next());
    }

    private void changePassword(Admin admin, Scanner input) {
        try {
            System.out.println("Enter your old password:");
            String oldPassword = input.next();
            if (new RepositoryAdmin().getAdminByPassword(oldPassword) != null && admin.getPassword().equals(oldPassword)) {
                System.out.println("Enter new password (minimum 8 characters long, has to contain at least on uppercase, one lowercase letter and one number):");
                String newPassword = input.next();
                if (new Validation().validPassword(newPassword)) {
                    new RepositoryAdmin().modifyAdminPassword(admin.getAdminId(), input.next());
                    System.out.println("Password successfully changed.");
                }
            }
        } catch (Exception e) {
            System.out.println("Password change failed.");
        }
    }
    private void adminList(Scanner input){
        for (Admin admin : new RepositoryAdmin().adminList()){
            System.out.println(admin);
        }
    }

}
