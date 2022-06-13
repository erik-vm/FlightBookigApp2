package main;

import menu.CityMenu;
import menu.CountryMenu;
import model.Admin;
import model.Client;
import model.PlaneCompany;
import model.Trip;
import persistence.*;
import validation.Validation;

import java.sql.Time;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);


        Client client = new RepositoryClient().getClientById(1);

        new RepositoryDebitCard().setNewBalance(client.getDebitCard().getDebitCardId(), (client.getDebitCard().getBalance()+input.nextDouble()));

    }
}
