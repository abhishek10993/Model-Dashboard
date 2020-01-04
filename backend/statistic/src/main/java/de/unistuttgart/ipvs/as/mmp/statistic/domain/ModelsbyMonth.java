package de.unistuttgart.ipvs.as.mmp.statistic.domain;

public class ModelsbyMonth {

    private final int value;
    private final String name;

    public int getvalue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public ModelsbyMonth( int value, String name) {
        this.value=value;
        this.name=name;
    }
}
