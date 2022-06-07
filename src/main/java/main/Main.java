package main;

import menu.CountryMenu;
import model.Admin;
import persistence.RepositoryAdmin;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        CountryMenu countryMenu = new CountryMenu();
        countryMenu.countryList(scanner);
        countryMenu.saveCountry(scanner);
        countryMenu.countryList(scanner);
        countryMenu.changeCountryName(scanner);
        countryMenu.countryList(scanner);
        countryMenu.deleteCountry(scanner);
        countryMenu.countryList(scanner);


    }
}
