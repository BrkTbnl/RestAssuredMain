package models;

import java.util.List;

public class Location {
    private String postcode;
    private String country;
    private String countryAbbreviation;
    private List<Place> places;

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryAbbreviation() {
        return countryAbbreviation;
    }

    public void setCountryAbbreviation(String countryAbbreviation) {
        this.countryAbbreviation = countryAbbreviation;
    }

    public List<Place> getPlaces() {
        return places;
    }


    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
