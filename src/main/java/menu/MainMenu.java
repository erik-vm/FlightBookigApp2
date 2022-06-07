package menu;

import java.util.Scanner;

public class MainMenu {

    private static Scanner input;
    private static SubMenu subMenu;

    public static void main(String[] args) {
        input = new Scanner(System.in).useDelimiter("\n");
        subMenu = new SubMenu();
        subMenu.menuChoice(input);
    }
    public static void getMainMenu(){
        subMenu.menuChoice(input);
    }
}
