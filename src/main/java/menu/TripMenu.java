package menu;

import model.*;
import persistence.RepositoryCity;
import persistence.RepositoryInvoice;
import persistence.RepositoryPlane;
import persistence.RepositoryTrip;

import java.sql.Time;
import java.util.Scanner;

public class TripMenu {

    public int menuOptions(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the admin menu option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: Save new trip");
        System.out.println("2: Delete trip");
        System.out.println("3: Show all trips");
        System.out.println("4: Show trips from city");
        System.out.println("5: Show trips to city");
        System.out.println("6: Show trips with maximum price");
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
                    saveTrip(input);
                    break;
                case 2: deleteTrip(input);
                    break;
                case 3:tripList();
                    break;
                case 4:getTripsFromCity(input);
                    break;
                case 5:getTripsToCity(input);
                    break;
                case 6: getTripsWithMaxPrice(input);
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

    private void saveTrip(Scanner input) {
        try {
            System.out.println("Enter plane id:");
            Plane plane = new RepositoryPlane().getPlaneById(input.nextInt());
            if (plane != null) {
                System.out.println("Enter departure city:");
                City depCity = new RepositoryCity().getCityByName(input.next());
                if (depCity != null)
                    System.out.println("Enter destination city:");
                City desCity = new RepositoryCity().getCityByName(input.next());
                if (desCity != null) {
                    System.out.println("Enter day plane departures");
                    String dayOfDep = input.next();
                    if (dayOfDep != null) {
                        System.out.println("Enter time of departure (HH:MM:SS)");
                        Time depTime = Time.valueOf(input.next());
                        if (depTime != null) {
                            System.out.println("Enter time of duration (HH:MM:SS)");
                            Time duration = Time.valueOf(input.next());
                            if (duration != null)
                                System.out.println("Enter price per person:");
                            new RepositoryTrip().saveTrip(new Trip(plane, depCity, desCity, dayOfDep, depTime, duration, input.nextDouble()));
                            System.out.println("Trip saved.");
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Saving trip failed.");
        }
    }

    private void deleteTrip(Scanner input){
        try{
            System.out.println("Enter trip id to delete:");
            new RepositoryTrip().deleteTrip(new RepositoryTrip().getTripById(input.nextInt()));
            System.out.println("Trip deleted.");
        }catch (Exception e){
            System.out.println("Deleting trip failed.");
        }
    }

    protected void tripList(){
        if (new RepositoryTrip().tripList().size() == 0){
            System.out.println("No trips in database.");
       return;
        }
        for (Trip trip : new RepositoryTrip().tripList()){
            System.out.println(trip);
        }
    }
    protected void getTripsFromCity(Scanner input){
        try{
            System.out.println("Enter departure city:");
            for (Trip trip : new RepositoryTrip().tripListByDepartureCity(new RepositoryCity().getCityByName(input.next()))){
                System.out.println(trip);
            }
        }catch (Exception e){
            System.out.println("No trips found.");
        }
    }
    protected void getTripsToCity(Scanner input){
        try{
            System.out.println("Enter destination city:");
            for (Trip trip : new RepositoryTrip().tripListByDestinationCity(new RepositoryCity().getCityByName(input.next()))){
                System.out.println(trip);
            }
        }catch (Exception e){
            System.out.println("No trips found.");
        }
    }
    protected void getTripsWithMaxPrice(Scanner input){
        try{
            System.out.println("Enter max price:");
            for (Trip trip : new RepositoryTrip().tripListMaxPrice(input.nextDouble())){
                System.out.println(trip);
            }
        }catch (Exception e){
            System.out.println("No trips found.");
        }
    }
    protected void bookATrip(Client client, Scanner input){
        try{
            System.out.println("Enter trip id:");
            Trip trip = new RepositoryTrip().getTripById(input.nextInt());
            if (trip != null){
                System.out.println("How many people are flying?");
                int passengers = input.nextInt();
                input.nextLine();
                if (passengers != 0 && trip.getPlane().getCurrentCapacity() >= passengers && client.getDebitCard().getBalance() >= (trip.getPricePerPassenger()*passengers)){
                    Invoice invoice = new Invoice(client, trip, passengers);
                    new RepositoryInvoice().saveInvoice(invoice);
                    System.out.println("Flight booked. Have a safe flight! Here is your invoice.");
                    System.out.println(invoice);
                    System.out.println("THANK YOU!");
                }
            }
        }catch (Exception e){
            System.out.println("Booking a flight failed.");
        }
    }

}
