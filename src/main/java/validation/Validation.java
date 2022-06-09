package validation;

import model.City;
import persistence.RepositoryCity;
import persistence.RepositoryClient;
import persistence.RepositoryCountry;
import persistence.RepositoryDebitCard;


public class Validation {

    public boolean validName(String name) {
        return name != null && name.matches("[A-Za-z\\s]{3,20}$");
    }

    public boolean validNumber(String number) {
        return number != null && number.matches("^\\d{6,20}");
    }

    public boolean validEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
    public boolean doesEmailExist(String email){
        boolean result = false;
        try {
            if (new RepositoryClient().getClientByEmail(email) != null){
                result = true;
            }
        }catch (Exception e){

        }
         return result;
    }

    public boolean validCountry(String name) {
        return new RepositoryCountry().getCountryByName(name) != null;
    }

    public boolean validCity(String name) {
        return new RepositoryCity().getCityByName(name) != null;
    }

    public boolean validUserName(String name){
        return name.matches("[A-Za-z0-9]{3,20}$");
    }
    public boolean validPassword(String password){
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$");
    }

    public boolean validDebitCard(int debitCardNumber){
        return String.valueOf(debitCardNumber).matches("^\\d{8}");
    }
    public boolean doesDebitCardExist(int debitCardNumber){
        boolean result = false;
        try {
            if (new RepositoryDebitCard().getDebitCardByNumber(debitCardNumber) != null){
                result = true;
            }
        }catch (Exception e){

        }
        return result;
    }
}
