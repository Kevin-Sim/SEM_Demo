package com.napier.sem;

import javax.swing.*;

public class Population {

    String area;
    int totalPopulation;
    int populationInCities;
    int populationNotInCities;

    public String toMarkdown() {
        String str = "";
        str += "|" + area + "|" + totalPopulation + "|" + populationInCities +
                "(" + new Double(populationInCities) * 100 / totalPopulation + "%)" +
                "|" + populationNotInCities + "(" + new Double(populationNotInCities) * 100 / totalPopulation + "%)|";
        return str;
    }
}
