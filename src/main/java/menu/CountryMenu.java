package menu;

import model.Country;
import persistence.RepositoryCountry;
import validation.Validation;

import java.util.Scanner;

public class CountryMenu {

    RepositoryCountry repositoryCountry = new RepositoryCountry();
    Validation validation = new Validation();

    public int menuOptions(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the admin menu option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: Country list");
        System.out.println("2: Save new country");
        System.out.println("3: Change country name");
        System.out.println("4: Delete country");
        System.out.println("100 - Return to Main Menu");
        System.out.println("\n/***************************************************/");
        return input.nextInt();
    }

    protected void menuChoice(Scanner input) {

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
                    MainMenu.getMainMenu();
                    break;
                default:
                    System.out.println("\nSorry, please enter valid Option");
                    menuOptions(input);
                    break;
            }// End of switch statement
        } while (userChoice != 100);
    }

    public void countryList(Scanner input){
        if (repositoryCountry.countryList().size() == 0){
            System.out.println("No countries available.");
            return;
        }
        for (Country country : repositoryCountry.countryList()){
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
                if (validation.validName(countryName)){
                    country.setName(countryName);
                    repositoryCountry.saveCountry(country);
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
            repositoryCountry.deleteCountryById(input.nextInt());
        }catch (Exception e){
            System.out.println("Failed to delete country. Check for correct id.");
        }
    }
    public void changeCountryName(Scanner input){
        try {
            System.out.println("Enter country id to change name:");
            int countryId = input.nextInt();
            if (repositoryCountry.getCountryById(countryId) != null){
                System.out.println("Enter new name:");
                String newName = input.next();
                if (validation.validName(newName)) {
                    repositoryCountry.changeNameById(countryId, newName);
                    System.out.println("Name changed");
                }
            }
        }catch (Exception e){
            System.out.println("Failed to change name. Check for correct id.");
        }
    }
}
