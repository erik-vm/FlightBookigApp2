package menu;

import model.Admin;
import model.City;
import model.Plane;
import model.PlaneCompany;
import persistence.RepositoryCity;
import persistence.RepositoryPlaneCompany;
import validation.Validation;

import java.util.Scanner;

public class PlaneCompanyMenu {
    public int menuOptions(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the plane company menu option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: Save plane company");
        System.out.println("2: Plane company list");
        System.out.println("3: Plane company list by city");
        System.out.println("4: Delete plane company");
        System.out.println("100 - Return to admin menu");
        System.out.println("\n/***************************************************/");
        return input.nextInt();
    }

    protected void menuChoice(Admin admin, Scanner input) {

        int userChoice;
        do {
            userChoice = menuOptions(input);
            switch (userChoice) {
                case 1: savePlaneCompany(input);
                    break;
                case 2: planeCompanyList(input);
                    break;
                case 3: planeCompaniesByCity(input);
                    break;
                case 4:deletePlaneCompany(input);
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

    private void savePlaneCompany(Scanner input){
        try {
            System.out.println("Enter name for plane company:");
            String planeCompanyName = input.next();
            if (new Validation().validName(planeCompanyName)){
                System.out.println("Enter city company is located in:");
                new RepositoryPlaneCompany().savePlaneCompany(new PlaneCompany(planeCompanyName, new RepositoryCity().getCityByName(input.next())));
                System.out.println("Company saved.");
            }
        }catch (Exception e){
            System.out.println("Saving plane company failed.");
        }
    }
    private void planeCompanyList(Scanner input){
        if (new RepositoryPlaneCompany().planeCompanyList().size() == 0){
            System.out.println("No plane companies in database.");
            return;
        }
        try {
            for (PlaneCompany planeCompany : new RepositoryPlaneCompany().planeCompanyList()){
                System.out.println(planeCompany);
            }
        }catch (Exception e){
        }
    }
    private void planeCompaniesByCity(Scanner input){
        try{
            System.out.println("Enter city name");
            for (PlaneCompany planeCompany : new RepositoryPlaneCompany().planeCompanyListByCity(new RepositoryCity().getCityByName(input.next()))){
                System.out.println(planeCompany);
            }
        }catch (Exception e){
            System.out.println("No companies found in entered city.");
        }
    }
    private void deletePlaneCompany(Scanner input){
        try {
            System.out.println("Enter id of plane company to delete:");
            new RepositoryPlaneCompany().deletePlaneCompany(input.nextInt());
        }catch (Exception e){
            System.out.println("Deleting company failed.");
        }
    }
}
