package de.unistuttgart.ipvs.as.mmp.statistic.domain;

public class Algorithms {


    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public Algorithms( String name, int value) {
        this.name = name;
        this.value = value;

    }
}
