package menu;

import model.Admin;
import model.Client;
import model.DebitCard;
import persistence.RepositoryClient;
import persistence.RepositoryDebitCard;
import validation.Validation;

import java.util.List;
import java.util.Scanner;

public class ClientMenu {


    private int menuOptions(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the admin menu option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: Save new client");
        System.out.println("2: Delete client");
        System.out.println("3: Show invoices by client id.");
        System.out.println("4: Client list");
        System.out.println("100 - admin menu");
        System.out.println("\n/***************************************************/");
        return input.nextInt();
    }

    protected void menuChoice(Admin admin,Scanner input) {

        int userChoice;
        do {
            userChoice = menuOptions(input);
            switch (userChoice) {
                case 1: saveClient(input);
                    break;
                case 2: deleteClient(input);
                    break;
                case 3:
                    break;
                case 4: clientList();
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

    protected void saveClient(Scanner input){
        try{
            Client client = new Client();
            System.out.println("Please enter first name:");
            String firstName = input.next();
            if (new Validation().validName(firstName)){
                System.out.println("Please enter last name:");
                String lastName = input.next();
                if (new Validation().validName(lastName)){
                    System.out.println("Please enter phone number (6-20 digits): ");
                    String number = input.next();
                    if (new Validation().validNumber(number)){
                        System.out.println("Please enter email (name@mail.com):");
                        String email = input.next();
                        if (new Validation().validEmail(email) && !new Validation().doesEmailExist(email)){
                            System.out.println("Please set password for your account (minimum 8 characters long, has to contain at least on uppercase, one lowercase letter and one number): ");
                            String password = input.next();
                            if (new Validation().validPassword(password)){
                                System.out.println("Enter debit card number (8 digits)");
                                int cardNumber =input.nextInt();
                                if (new Validation().validDebitCard(cardNumber) && !new Validation().doesDebitCardExist(cardNumber)){
                                    DebitCard debitCard = new DebitCard();
                                    debitCard.setCardNumber(cardNumber);
                                    new RepositoryDebitCard().saveDebitCard(debitCard);
                                    client.setFirstName(firstName);
                                    client.setLastName(lastName);
                                    client.setPhoneNumber(number);
                                    client.setEmail(email);
                                    client.setPassword(password);
                                    client.setDebitCard(debitCard);
                                    new RepositoryClient().saveClient(client);
                                    System.out.println("New client saved.");
                                }
                            }
                        }
                    }
                }
            }

        }catch (Exception e){
            System.out.println("Failed to save new client.");
        }
    }

    private void deleteClient(Scanner input){
        try{
            System.out.println("Enter client id to delete:");
                new RepositoryClient().deleteClientById(input.nextInt());
            System.out.println("Client successfully deleted.");
        }catch (Exception e){
            System.out.println("Deleting client failed.");
        }
    }

    private void clientList(){
        for (Client client : new RepositoryClient().clientList()){
            System.out.println(client);
        }
    }
}
