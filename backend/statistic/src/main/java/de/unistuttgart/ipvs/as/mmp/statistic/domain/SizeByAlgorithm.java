package de.unistuttgart.ipvs.as.mmp.statistic.domain;

public class SizeByAlgorithm {
    String name;
    String total_size;
    String avg_size;

    public String getName() {
        return name;
    }

    public String getTotal_size() {
        return total_size;
    }

    public String getAvg_size() {
        return avg_size;
    }
    public SizeByAlgorithm(String name, String total_size, String avg_size){
        this.name=name;
        this.total_size=total_size;
        this.avg_size=avg_size;
    }


}
