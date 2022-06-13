package menu;

import model.Admin;
import model.Plane;
import model.PlaneCompany;
import persistence.RepositoryPlane;
import persistence.RepositoryPlaneCompany;
import validation.Validation;

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
                case 1: savePlane(input);
                    break;
                case 2: planeList();
                    break;
                case 3:planeListByCompany(input);
                    break;
                case 4:deletePlane(input);
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
            String name = input.next();
            if (new Validation().validName(name)){
                System.out.println("Enter company name:");
                PlaneCompany planeCompany = new RepositoryPlaneCompany().getCompanyByName(input.next());
                if (planeCompany != null){
                    System.out.println("Enter planes max capacity:");
                    new RepositoryPlane().savePlane(new Plane(name, planeCompany, input.nextInt()));
                    System.out.println("Plane saved.");
                }
            }
        }catch (Exception e){
            System.out.println("Saving plane failed.");
        }
    }
    public void deletePlane(Scanner input){
        try {
            System.out.println("Enter plane id:");
            new RepositoryPlane().deletePlaneById(input.nextInt());
        }catch (Exception e){
            System.out.println("Deleting plane failed.");
        }
    }
    public void planeList(){
        if (new RepositoryPlane().planeList().size() == 0){
            System.out.println("No planes in database");
        return;
        }
        for (Plane plane : new RepositoryPlane().planeList()){
            System.out.println(plane);
        }
    }
    public void planeListByCompany(Scanner input){
        try{
            System.out.println("Enter company name:");
            PlaneCompany planeCompany = new RepositoryPlaneCompany().getCompanyByName(input.next());
            for (Plane plane : new RepositoryPlane().planeList()){
                if (plane.getPlaneCompany() == planeCompany){
                    System.out.println(plane);
                }
            }
        }catch (Exception e){
            System.out.println("No planes found.");
        }
    }
}
