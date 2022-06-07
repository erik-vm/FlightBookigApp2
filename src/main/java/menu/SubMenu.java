package menu;

import java.util.Scanner;

public class SubMenu {
    boolean exit = false;
    private AdminMenu adminMenu;

    public SubMenu() {
        adminMenu = new AdminMenu();
    }

    private int menuOptions(Scanner input){
        System.out.println("\n-------------------------------------------------------");
        System.out.println("Select role. ");
        System.out.println("-------------------------------------------------------");
        System.out.println();
        System.out.println("1: - Admin");
        System.out.println("2: - Client");
        System.out.println("100 - Quit");
        System.out.println("***************************************************");

        System.out.println("Type the sub menu option: ");
        return input.nextInt();
    }
    public void menuChoice(Scanner input){
        while (!exit){
            int userChoice = menuOptions(input);;
            switch (userChoice){
                case 1:
                    break;
                case 2:
                    break;
                case 100: exit = true;
                    System.out.println("System closed.");
                break;
                default:
                    System.out.println("Invalid input");
                    menuChoice(input);
            }
        }
    }
}
