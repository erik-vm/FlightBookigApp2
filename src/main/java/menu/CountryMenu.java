package menu;

import model.Admin;
import model.Country;
import persistence.RepositoryCountry;
import validation.Validation;

import java.util.List;
import java.util.Scanner;

public class CountryMenu {

    public int menuOptions(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the admin menu option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: Country list");
        System.out.println("2: Save new country");
        System.out.println("3: Change country name");
        System.out.println("4: Delete country");
        System.out.println("100 - Return to admin menu");
        System.out.println("\n/***************************************************/");
        return input.nextInt();
    }

    protected void menuChoice(Admin admin, Scanner input) {

        int userChoice;
        do {
            userChoice = menuOptions(input);
            switch (userChoice) {
                case 1:countryList(input);
                    break;
                case 2:saveCountry(input);
                    break;
                case 3:changeCountryName(input);
                    break;
                case 4: deleteCountry(input);
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

    public void countryList(Scanner input){
        if (new RepositoryCountry().countryList().size() == 0){
            System.out.println("No countries available.");
            return;
        }
        Long countryCount = new RepositoryCountry().totalCountryCount();
        List <Country> countryList= new RepositoryCountry().countryList();
        System.out.println();
        System.out.println("Total countries(" +countryCount +")" +"\n---------------------------------------------------");
        for (Country country : countryList){
            System.out.println(country);
        }
    }
    public void saveCountry(Scanner input){
        Country country = new Country();
        boolean correctInput = false;
        while (!correctInput){
            try {
                System.out.println("Enter name of the country:");
                String countryName = input.next();
                if (new Validation().validName(countryName)){
                    country.setName(countryName);
                    new RepositoryCountry().saveCountry(country);
                    System.out.println("Country saved");
                    correctInput = true;
                }
            }catch (Exception e){
                System.out.println("Invalid input. Name can only contain letters.");
            }
        }
    }

    public void deleteCountry(Scanner input){
        try {
            System.out.println("Enter country id to delete:");
            new RepositoryCountry().deleteCountryById(input.nextInt());
            System.out.println("Country deleted successfully.");
        }catch (Exception e){
            System.out.println("Failed to delete country. Check for correct id.");
        }
    }
    public void changeCountryName(Scanner input){
        try {
            System.out.println("Enter country id to change name:");
            int countryId = input.nextInt();
            if (new RepositoryCountry().getCountryById(countryId) != null){
                System.out.println("Enter new name:");
                String newName = input.next();
                if (new Validation().validName(newName)) {
                    new RepositoryCountry().changeNameById(countryId, newName);
                    System.out.println("Name changed");
                }
            }
        }catch (Exception e){
            System.out.println("Failed to change name. Check for correct id.");
        }
    }
}
