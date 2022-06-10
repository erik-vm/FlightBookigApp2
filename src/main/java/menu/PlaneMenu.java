package menu;

import model.Admin;

import java.util.Scanner;

public class PlaneMenu {

    public int menuOptions(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the plane menu option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: Save plane");
        System.out.println("2: Plane list");
        System.out.println("3: Plane list by company");
        System.out.println("4: Delete plane");
        System.out.println("100 - Return to admin menu");
        System.out.println("\n/***************************************************/");
        return input.nextInt();
    }

    protected void menuChoice(Admin admin, Scanner input) {

        int userChoice;
        do {
            userChoice = menuOptions(input);
            switch (userChoice) {
                case 1:
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
                    new SubMenu().adminMenuChoice(admin, input);
                    break;
                default:
                    System.out.println("\nSorry, please enter valid Option");
                    menuOptions(input);
                    break;
            }// End of switch statement
        } while (userChoice != 100);
    }

    public void savePlane(Scanner input){
        try{
            System.out.println("Enter plane name");
        }catch (Exception e){
            System.out.println("Saving plane failed.");
        }
    }
}
