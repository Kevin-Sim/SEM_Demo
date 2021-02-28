package com.napier.sem;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Country {
    private String code;
    private String name;
    private String continent;
    private String region;
    private int population;
    private City capital;
    private ArrayList<City> cities = new ArrayList<>();
    private ArrayList<CountryLanguage> countryLanguages = new ArrayList<>();

    public Country() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public City getCapital() {
        return capital;
    }

    public void setCapital(City capital) {
        this.capital = capital;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

    public ArrayList<CountryLanguage> getCountryLanguages() {
        return countryLanguages;
    }

    public void setCountryLanguages(ArrayList<CountryLanguage> countryLanguages) {
        this.countryLanguages = countryLanguages;
    }

    @Override
    public String toString() {
        String str = "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", region='" + region + '\'' +
                ", population=" + population;
        if (capital != null) {
            str += ", capital=" + capital.getName();
        } else {
            str += ", capital = null";
        }

        str += ", cities=" + cities.size() +
                ", countryLanguages=" + countryLanguages.size() +
                '}';
        return str;
    }

    /**
     *  * Code.
     *  * Name.
     *  * Continent.
     *  * Region.
     *  * Population.
     *  * Capital.
     * @return
     */
    public String toMarkdown() {
        String str = "";
        str += "|" + code + "|" + name + "|" + continent + "|" +  region +
                "|" + population + "|";
        if (capital != null) {
            str += capital.getName();
        } else {
            str += " ";
        }
        str += "|";
        return  str;
    }
}
