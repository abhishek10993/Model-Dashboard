package de.unistuttgart.ipvs.as.mmp.statistic.domain;

public class ModelsPerUser {
    private String name;
    private int count;

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public ModelsPerUser(String name, int count){
        this.name=name;
        this.count=count;
    }
}
