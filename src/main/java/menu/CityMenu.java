package menu;

import model.Admin;
import model.City;
import model.Country;
import persistence.RepositoryCity;
import persistence.RepositoryCountry;
import validation.Validation;

import java.util.List;
import java.util.Scanner;

public class CityMenu {

    public int menuOptions(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the admin menu option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: City list");
        System.out.println("2: City list by country");
        System.out.println("3: Save new city");
        System.out.println("4: Delete city");
        System.out.println("5: Change city name");
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
                    cityList();
                    break;
                case 2:
                    cityListByCountry(input);
                    break;
                case 3:
                    saveCity(input);
                    break;
                case 4:
                    deleteCity(input);
                    break;
                case 5:
                    changeCityName(input);
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

    private void cityList() {
        if (new RepositoryCity().cityList().size() == 0) {
            System.out.println("No cities in database.");
        }
        Long cityCount = new RepositoryCity().totalCityCount();
        List<City> cityList = new RepositoryCity().cityList();
        System.out.println();
        System.out.println("Total cities(" + cityCount + ")" + "\n---------------------------------------------------");
        for (City city : cityList) {
            System.out.println(city);
        }
    }

    public void cityListByCountry(Scanner input) {
        try {
            System.out.println("Enter country to get cities from:");
            Country country = new RepositoryCountry().getCountryByName(input.next());
            if (country != null) {
                Long cityCount = new RepositoryCity().cityCountByCountry(country);
                List<City> cityList = new RepositoryCity().cityListByCountry(country);
                System.out.println();
                System.out.println(country.getName()+ " | Total cities(" + cityCount + ")" + "\n---------------------------------------------------");
                for (City city :cityList ) {
                    System.out.println(city.getName());
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid country name.");
        }
    }

    private void saveCity(Scanner input) {
        try {
            System.out.println("Enter name of a country where city locates:");
            Country country = new RepositoryCountry().getCountryByName(input.next());
            if (country != null) {
                System.out.println("Enter name of the city:");
                new RepositoryCity().saveCity(new City(input.next(), country));
                System.out.println("City saved.");
            }
        } catch (Exception e) {
            System.out.println("Saving city failed.");
        }
    }

    private void deleteCity(Scanner input) {
        try {
            System.out.println("Enter city id to be deleted:");
            new RepositoryCity().deleteCity(input.nextInt());
        } catch (Exception e) {
            System.out.println("Deleting city failed.");
        }
    }

    private void changeCityName(Scanner input) {
        try {
            System.out.println("Enter city id:");
            int ciyId = input.nextInt();
            if (new RepositoryCity().getCityById(ciyId) != null) {
                System.out.println("Enter new name:");
                String newName = input.next();
                if (new Validation().validName(newName)) {
                    new RepositoryCity().changeCityNameById(ciyId, newName);
                    System.out.println("City name changed.");
                }
            }
        } catch (Exception e) {
            System.out.println("City name change failed.");
        }
    }
}
