package de.unistuttgart.ipvs.as.mmp.statistic.domain;

public class ModelStatus {
    private String status;

    public String getStatus() {
        return status;
    }

    public int getCount() {
        return count;
    }

    private int count;

    public ModelStatus(String status, int count){
        this.status=status;
        this.count=count;
    }

}
