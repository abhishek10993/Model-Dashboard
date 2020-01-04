package de.unistuttgart.ipvs.as.mmp.statistic.v1.controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import de.unistuttgart.ipvs.as.mmp.statistic.domain.DatabaseConnection;
import de.unistuttgart.ipvs.as.mmp.statistic.domain.Algorithms;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping(value = "/algorithms")
public class AlgorithmController {

    @GetMapping
    public ResponseEntity<List<Algorithms>> algorithms(@RequestParam(value="from_date", defaultValue="All") String from_date,
                                                       @RequestParam(value="to_date", defaultValue="All") String to_date) {
        List<Algorithms> algorithms=getAlgorithms(from_date, to_date);

        return ResponseEntity.ok(algorithms);
    }

    public List<Algorithms> getAlgorithms(String from_date, String to_date){

        Connection conn= new DatabaseConnection().getConnection();
        List<Algorithms> algorithms=new ArrayList<Algorithms>();
        String query;
        if (from_date.equals("All") && to_date.equals("All"))
            query="select algorithm, count (algorithm) from modelmetadata group by algorithm";
        else
            query="select algorithm, count (algorithm) from modelmetadata where  CAST(date_of_creation AS VARCHAR) >= '"+from_date+"' " +
                    "and CAST(date_of_creation AS VARCHAR) <= '"+to_date+"' group by algorithm";
        int regression, classsification, clustering, neural_network;
        regression=classsification=clustering=neural_network=0;
        try {
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                if(rs.getString("algorithm").equalsIgnoreCase("Hoeffding Tree") ||
                        rs.getString("algorithm").equalsIgnoreCase("Naive Bayes") ||
                        rs.getString("algorithm").equalsIgnoreCase("randomForest")){
                    classsification+=Integer.parseInt(rs.getString("count"));
                }
                else if(rs.getString("algorithm").equalsIgnoreCase("Linear Regression") ||
                        rs.getString("algorithm").equalsIgnoreCase("Simple Logistic")){
                    regression+=Integer.parseInt(rs.getString("count"));
                }
                else if(rs.getString("algorithm").equalsIgnoreCase("RProp") ||
                        rs.getString("algorithm").equalsIgnoreCase("rpart")){
                    clustering+=Integer.parseInt(rs.getString("count"));
                }
                else if(rs.getString("algorithm").equalsIgnoreCase("J48") ||
                        rs.getString("algorithm").equalsIgnoreCase("Multilayer Perceptron")){
                    neural_network+=Integer.parseInt(rs.getString("count"));
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        algorithms.add(new Algorithms("Classification",classsification));
        algorithms.add(new Algorithms("Regression", regression));
        algorithms.add(new Algorithms("Clustering",clustering));
        algorithms.add(new Algorithms("Neural Network",neural_network));

        return algorithms;
    }
}