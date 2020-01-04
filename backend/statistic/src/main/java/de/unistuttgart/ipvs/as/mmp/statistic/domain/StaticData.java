package de.unistuttgart.ipvs.as.mmp.statistic.domain;

public class StaticData {
    private String total_models;
    private String added_last_month;
    private String total_size;
    private String percentage_change;

    public String getTotal_models() {
        return total_models;
    }

    public String getAdded_last_month() {
        return added_last_month;
    }

    public String getTotal_size() {
        return total_size;
    }

    public String getIncreased_last_month() {
        return percentage_change;
    }

    public StaticData(String total_models,String added_last_month, String total_size, String percentage_change){
        this.total_models=total_models;
        this.added_last_month=added_last_month;
        this.total_size=total_size;
        this.percentage_change=percentage_change;
    }

}
