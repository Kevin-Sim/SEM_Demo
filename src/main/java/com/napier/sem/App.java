package com.napier.sem;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * 1	All the countries in the world organised by largest population to smallest.
 * 2	All the countries in a continent organised by largest population to smallest.
 * 3	All the countries in a region organised by largest population to smallest.
 * 4	The top N populated countries in the world where N is provided by the user.
 * 5	The top N populated countries in a continent where N is provided by the user.
 * 6	The top N populated countries in a region where N is provided by the user.
 * 7	All the cities in the world organised by largest population to smallest.
 * 8	All the cities in a continent organised by largest population to smallest.
 * 9	All the cities in a region organised by largest population to smallest.
 * 10	All the cities in a country organised by largest population to smallest.
 * 11	All the cities in a district organised by largest population to smallest.
 * 12	The top N populated cities in the world where N is provided by the user.
 * 13	The top N populated cities in a continent where N is provided by the user.
 * 14	The top N populated cities in a region where N is provided by the user.
 * 15	The top N populated cities in a country where N is provided by the user.
 * 16	The top N populated cities in a district where N is provided by the user.
 * 17	All the capital cities in the world organised by largest population to smallest.
 * 18	All the capital cities in a continent organised by largest population to smallest.
 * 19	All the capital cities in a region organised by largest to smallest.
 * 20	The top N populated capital cities in the world where N is provided by the user.
 * 21	The top N populated capital cities in a continent where N is provided by the user.
 * 22	The top N populated capital cities in a region where N is provided by the user.
 * 23	The population of people, people living in cities, and people not living in cities in each continent.
 * 24	The population of people, people living in cities, and people not living in cities in each region.
 * 25	The population of people, people living in cities, and people not living in cities in each country.
 * 26	The population of the world.
 * 27	The population of a continent.
 * 28	The population of a region.
 * 29	The population of a country.
 * 30	The population of a district.
 * 31	The population of a city.
 * 32	Number of people that speak Chinese, English, Hindi, Spanish, Arabic including % of world population from smallest to largest
 * <p>
 * Country Report
 * A country report requires the following columns:
 * <p>
 * Code.
 * Name.
 * Continent.
 * Region.
 * Population.
 * Capital.
 * <p>
 * City Report
 * A city report requires the following columns:
 * <p>
 * Name.
 * Country.
 * District.
 * Population.
 * <p>
 * Capital City Report
 * A capital city report requires the following columns:
 * <p>
 * Name.
 * Country.
 * Population.
 * <p>
 * Population Report
 * For the population reports, the following information is requested:
 * <p>
 * The name of the continent/region/country.
 * The total population of the continent/region/country.
 * The total population of the continent/region/country living in cities (including a %).
 * The total population of the continent/region/country not living in cities (including a %).
 */
public class App {
    String[] regions = {"Caribbean", "Southern and Central Asia", "Central Africa", "Southern Europe", "Middle East",
            "South America", "Polynesia", "Antarctica", "Australia and New Zealand", "Western Europe", "Eastern Africa",
            "Western Africa", "Eastern Europe", "Central America", "North America", "Southeast Asia", "Southern Africa",
            "Eastern Asia", "Nordic Countries", "Northern Africa", "Baltic Countries", "Melanesia", "Micronesia",
            "British Islands", "Micronesia/Caribbean"};

    String[] continents = {"North America", "Asia", "Africa", "Europe", "South America", "Oceania", "Antarctica"};

    public static void main(String[] args) {
        // Create new Application
        App a = new App();
        // Connect to database
        a.connect();

        ArrayList<Country> countries = a.getCountries();

        for(Country c : countries){
            System.out.println(c);
        }
//        report1(countries);
//        for(String continent : a.continents){
//            report2(countries, continent);
//        }
//        for(String region : a.regions){
//            report3(countries, region);
//        }
        // Disconnect from database
        a.disconnect();
    }

    /**
     * All the countries in the world organised by largest population to smallest.
     *
     * @param countries
     */

    private static void report1(ArrayList<Country> countries) {
        Collections.sort(countries, new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                if (o1.getPopulation() > o2.getPopulation()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        printCountryReport(countries, "All the countries in the world organised by largest population to smallest", "report1.md");
    }

    /**
     * All the countries in a continent organised by largest population to smallest.
     * <p>
     * "North America,Asia,Africa,Europe,South America,Oceania,Antarctica"
     */
    private static void report2(ArrayList<Country> countries, String continent) {
        ArrayList<Country> countries1 = new ArrayList<>();
        for (Country c : countries) {
            if (c.getContinent().equals(continent)) {
                countries1.add(c);
            }
        }
        Collections.sort(countries1, new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                if (o1.getPopulation() > o2.getPopulation()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        printCountryReport(countries1, "All the countries in a continent (" + continent +
                ") organised by largest population to smallest", "report2_" + continent + ".md");
    }

    /**
     * All the countries in a region organised by largest population to smallest.
     * <p>
     * "Caribbean", "Southern and Central Asia", "Central Africa", "Southern Europe", "Middle East",
     *             "South America", "Polynesia", "Antarctica", "Australia and New Zealand", "Western Europe", "Eastern Africa",
     *             "Western Africa", "Eastern Europe", "Central America", "North America", "Southeast Asia", "Southern Africa",
     *             "Eastern Asia", "Nordic Countries", "Northern Africa", "Baltic Countries", "Melanesia", "Micronesia",
     *             "British Islands", "Micronesia/Caribbean"
     */
    private static void report3(ArrayList<Country> countries, String region) {
        ArrayList<Country> countries1 = new ArrayList<>();
        for (Country c : countries) {
            if (c.getRegion().equals(region)) {
                countries1.add(c);
            }
        }
        Collections.sort(countries1, new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                if (o1.getPopulation() > o2.getPopulation()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        printCountryReport(countries1, "All the countries in a region (" + region +
                ") organised by largest population to smallest", "report3_" + region.replace("/", "_") + ".md");
    }

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start needed for travis but can be removed locally if db running
                Thread.sleep(30000);

                // Connect to database locally
//                con = DriverManager.getConnection("jdbc:mysql://localhost:33060/world?useSSL=true", "root", "example");

                // Connect to database inside docker
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");

                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                // print
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * Used to create OO model originally has a dummy Country
     * Relationships set in @getCountries()
     *
     * @return
     */
    private HashMap<Integer, City> getCities() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement

            String strSelect = "SELECT * from city ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            HashMap<Integer, City> cities = new HashMap<>();
            while (rset.next()) {
                City city = new City();
                city.setId(rset.getInt("ID"));
                city.setName(rset.getString("Name"));
                Country country = new Country();
                country.setCode(rset.getString("CountryCode"));
                city.setCountry(country);
                city.setDistrict(rset.getString("District"));
                city.setPopulation(rset.getInt("Population"));
                cities.put(city.getId(), city);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    private HashMap<Country, CountryLanguage> getCountryLanguages() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement

            String strSelect = "SELECT * from countrylanguage ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            HashMap<Country, CountryLanguage> countryLanguages = new HashMap<>();
            while (rset.next()) {
                CountryLanguage countryLanguage = new CountryLanguage();
                Country country = new Country();
                country.setCode(rset.getString("CountryCode"));
                countryLanguage.setCountry(country);
                countryLanguage.setLanguage(rset.getString("Language"));
                countryLanguage.setIsOfficial(rset.getString("IsOfficial"));
                countryLanguage.setPercentage(rset.getDouble("Percentage"));
                countryLanguages.put(countryLanguage.getCountry(), countryLanguage);
            }
            return countryLanguages;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    public ArrayList<Country> getCountries() {
        //country set to null
        HashMap<Integer, City> cities = getCities();
        //country set to null
        HashMap<Country, CountryLanguage> countryLanguages = getCountryLanguages();
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement

            String strSelect = "SELECT * from country ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country> countries = new ArrayList<>();
            while (rset.next()) {
                Country country = new Country();
                country.setCode(rset.getString("Code"));
                country.setName(rset.getString("Name"));
                country.setContinent(rset.getString("Continent"));
                country.setRegion(rset.getString("Region"));
                country.setPopulation(rset.getInt("Population"));
                country.setCapital(cities.get(rset.getInt("Capital")));
                for (City city : cities.values()) {
                    if (city.getCountry().getCode().equals(country.getCode())) {
                        country.getCities().add(city);
                        city.setCountry(country);
                    }
                }
                countries.add(country);

                for (CountryLanguage cl : countryLanguages.values()) {
                    if (cl.getCountry().getCode().equals(country.getCode())) {
                        country.getCountryLanguages().add(cl);
                        cl.setCountry(country);
                    }
                }
            }
            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }


    public static void printCityReport(ArrayList<City> cities) {
        StringBuilder sb = new StringBuilder();
        sb.append("| ID | Name | CountryCode | District | Population |\r\n");
        sb.append("| :--- | :--- | :--- | :--- | :---: |\r\n");
        for (City city : cities) {
            sb.append(city.toString() + "\r\n");
        }
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(new File("cities.md")));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * * Country Report
     * * A country report requires the following columns:
     * *
     * * Code.
     * * Name.
     * * Continent.
     * * Region.
     * * Population.
     * * Capital.
     *
     * @param countries
     */
    public static void printCountryReport(ArrayList<Country> countries, String heading, String filename) {
        StringBuilder sb = new StringBuilder();
        sb.append("# " + heading + "\r\n\r\n");
        sb.append("| Code | Name | Continent | Region | Population | Capital |\r\n");
        sb.append("| :--- | :--- | :--- | :--- | :--- | :--- |\r\n");
        for (Country country : countries) {
            sb.append(country.toMarkdown() + "\r\n");
        }
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(new File(filename)));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void printPopulationReport(ArrayList<Population> populations) {
        StringBuilder sb = new StringBuilder();
        sb.append("| Area Name | Total Population | Population In Cities | Population Not In Cities |\r\n");
        sb.append("| :--- | :--- | :--- | :--- | \r\n");
        for (Population population : populations) {
            sb.append(population.toMarkdown() + "\r\n");
        }
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(new File("population.md")));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}