package main;

import menu.CityMenu;
import menu.CountryMenu;
import model.Admin;
import persistence.RepositoryAdmin;
import persistence.RepositoryClient;
import validation.Validation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

    new CityMenu().cityListByCountry(input);

    }
}
