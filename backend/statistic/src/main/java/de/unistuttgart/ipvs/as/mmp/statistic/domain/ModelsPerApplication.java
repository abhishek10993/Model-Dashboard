package de.unistuttgart.ipvs.as.mmp.statistic.domain;

public class ModelsPerApplication
{
    private String name;
    private int count;

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public ModelsPerApplication(String name, int count){
        this.name=name;
        this.count=count;
    }
}