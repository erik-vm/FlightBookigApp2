package menu;

import model.Admin;
import model.Client;
import model.Invoice;
import model.Trip;
import persistence.RepositoryClient;
import persistence.RepositoryInvoice;
import persistence.RepositoryTrip;

import java.util.Scanner;

public class InvoiceMenu {
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
                case 1:saveInvoice(input);
                    break;
                case 2:invoiceList();
                    break;
                case 3: invoiceListByClient(input);
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

    private void saveInvoice(Scanner input){
        try{
            Invoice invoice;
            System.out.println("Enter client id:");
            Client client = new RepositoryClient().getClientById(input.nextInt());
            if (client!=null){
                System.out.println("Enter trip id:");
                Trip trip = new RepositoryTrip().getTripById(input.nextInt());
                if (trip != null){
                    System.out.println("Enter how many passengers:");
                    int passengers = input.nextInt();
                    invoice = new Invoice(client, trip, passengers, (passengers*trip.getPricePerPassenger()));
                    new RepositoryInvoice().saveInvoice(invoice);
                    System.out.println("Invoice created.\n" + invoice);
                }

            }
        }catch (Exception e){
            System.out.println("Saving invoice failed.");
        }
    }
    protected void saveInvoice(Client client, Trip trip, int passengers){
        new RepositoryInvoice().saveInvoice(new Invoice(client, trip, passengers, (passengers* trip.getPricePerPassenger())));
    }
    private void invoiceList(){
       if (new RepositoryInvoice().invoiceList().size() == 0){
           System.out.println("No invoices in database");
           return;
       }
       for (Invoice invoice : new RepositoryInvoice().invoiceList()){
           System.out.println(invoice);
       }
    }
    protected void invoiceListByClient(Scanner input){
        try {
            System.out.println("Enter client id:");
            for (Invoice invoice : new RepositoryInvoice().invoiceListByClient(new RepositoryClient().getClientById(input.nextInt()))){
                System.out.println(invoice);
            }
        }catch (Exception e){
            System.out.println("No invoices for this client in database.");
        }
    }
    protected void invoiceListByClient(Client client){
        if (new RepositoryInvoice().invoiceListByClient(client).size() == 0){
            System.out.println(client.getFirstName() + " " + client.getLastName() + " don't have any invoices.");
            return;
        }
        for (Invoice invoice : new RepositoryInvoice().invoiceListByClient(client)){
            System.out.println(invoice);
        }
    }
}
