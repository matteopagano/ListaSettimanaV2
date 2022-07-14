package com.company;

import java.util.ArrayList;
import java.util.List;

public class CarBuilderProperty {
    private List<Person> peopleOwnCar = new ArrayList<>();
    private List<Person> peoplePizzeriaCar = new ArrayList<>();
    private List<Person> peopleMotorbike = new ArrayList<>();
    private final int numberOfPizzeriaCar;

    public CarBuilderProperty(List<Person> peopleOwnCar, List<Person> peoplePizzeriaCar, List<Person> peopleMotorbike, int numberOfPizzeriaCar) {
        this.peopleOwnCar = peopleOwnCar;
        this.peoplePizzeriaCar = peoplePizzeriaCar;
        this.peopleMotorbike = peopleMotorbike;
        this.numberOfPizzeriaCar = numberOfPizzeriaCar;
    }

    public List<Person> getPeopleOwnCar() {
        return peopleOwnCar;
    }

    public List<Person> getPeoplePizzeriaCar() {
        return peoplePizzeriaCar;
    }

    public List<Person> getPeopleMotorbike() {
        return peopleMotorbike;
    }

    public int getNumberOfPizzeriaCar() {
        return numberOfPizzeriaCar;
    }
}
