package de.unistuttgart.ipvs.as.mmp.statistic.v1.controller;

import java.sql.*;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import de.unistuttgart.ipvs.as.mmp.statistic.domain.DatabaseConnection;
import de.unistuttgart.ipvs.as.mmp.statistic.domain.ModelsbyMonth;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping(value = "/modelsbymonth")
public class ModelsbyMonthController {

    @GetMapping
    public ResponseEntity<List<ModelsbyMonth>> models(@RequestParam(value="name", defaultValue="All") String name) {

        List<ModelsbyMonth> modelsbyMonths=getModels(name);
        return ResponseEntity.ok(modelsbyMonths);
    }

    @GetMapping(value = "/total")
    public ResponseEntity<List<ModelsbyMonth>> totalmodels(@RequestParam(value="name", defaultValue="All") String name) {

        List<ModelsbyMonth> modelsbyMonths=getTotalModels(name);
        return ResponseEntity.ok(modelsbyMonths);
    }


    public int[] getData(String name) {
        int[] values=new int[12];
        Connection conn= new DatabaseConnection().getConnection();
        String query;
        switch (name)
        {
            case "Regression":
                query = "select count(*), date_part('month',date_of_creation) from modelmetadata where algorithm in" +
                        " ('Linear Regression','Simple Logistic') group by date_part('month',date_of_creation)";
                break;
            case "Classification":
                query = "select count(*), date_part('month',date_of_creation) from modelmetadata where algorithm in" +
                        " ('Hoeffding Tree','Naive Bayes','randomForest') group by date_part('month',date_of_creation)";
                break;
            case "Clustering":
                query = "select count(*), date_part('month',date_of_creation) from modelmetadata where algorithm in" +
                        " ('RProp','rpart') group by date_part('month',date_of_creation)";
                break;
            case "Neural Network":
                query = "select count(*), date_part('month',date_of_creation) from modelmetadata where algorithm in" +
                        " ('J48','Multilayer Perceptron') group by date_part('month',date_of_creation)";
                break;
            default:
                query="select count(*), date_part('month',date_of_creation) from modelmetadata group by date_part('month',date_of_creation)";

        }
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                values[Integer.parseInt(rs.getString("date_part"))-1]=Integer.parseInt(rs.getString("count"));
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return values;
    }

    public List<ModelsbyMonth> getTotalModels(String name) {
        int[] values=getData(name);
        String month;
        int sum = 0;
        List<ModelsbyMonth> modelsbyMonths=new ArrayList<ModelsbyMonth>();
        for(int i=0;i<values.length;i++){
            month=new DateFormatSymbols().getMonths()[i].substring(0,3);
            sum+=values[i];
            modelsbyMonths.add(new ModelsbyMonth(sum,month));
        }
        return modelsbyMonths;
    }

    public List<ModelsbyMonth> getModels(String name) {
        int[] values=getData(name);
        String month;
        List<ModelsbyMonth> modelsbyMonths=new ArrayList<ModelsbyMonth>();
        for(int i=0;i<values.length;i++){
            month=new DateFormatSymbols().getMonths()[i].substring(0,3);
            modelsbyMonths.add(new ModelsbyMonth(values[i],month));
        }
        return modelsbyMonths;
    }
}
