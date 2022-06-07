package validation;

import model.City;
import persistence.RepositoryCity;
import persistence.RepositoryCountry;

public class Validation {
    RepositoryCountry repositoryCountry = new RepositoryCountry();
    RepositoryCity repositoryCity = new RepositoryCity();

    public boolean validName(String name) {
        return name != null && name.matches("[A-Za-z\\s]*$");
    }

    public boolean validNumber(String number) {
        return number != null && number.matches("^\\d+");
    }

    public boolean validEmail(String email) {
        return email != null && email.matches("^(.+)@(.+)$");
    }

    public boolean validCountry(String name) {
        return repositoryCountry.getCountryByName(name) != null;
    }

    public boolean validCity(String name) {
        return repositoryCity.getCityByName(name) != null;
    }
}
