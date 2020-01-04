package de.unistuttgart.ipvs.as.mmp.statistic.v1.controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import de.unistuttgart.ipvs.as.mmp.statistic.domain.DatabaseConnection;
import de.unistuttgart.ipvs.as.mmp.statistic.domain.SizeByAlgorithm;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping(value = "/sizebyalgorithms")
public class SizeByAlgorithmController {

    @GetMapping
    public ResponseEntity<List<SizeByAlgorithm>> algorithms() {
        List<SizeByAlgorithm> algorithms=getAlgorithms();

        return ResponseEntity.ok(algorithms);
    }

    public List<SizeByAlgorithm> getAlgorithms(){
        Connection conn= new DatabaseConnection().getConnection();
        List<SizeByAlgorithm> algorithms= new ArrayList<>();
        String query = "select sum(model_file.file_size), count(modelmetadata.algorithm), modelmetadata.algorithm from model_file, modelmetadata group by modelmetadata.algorithm";
        int regression, classsification, clustering, neural_network, regression_sum, classsification_sum, clustering_sum, neural_network_sum;
        regression=classsification=clustering=neural_network=regression_sum=classsification_sum=clustering_sum=neural_network_sum=0;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                if(rs.getString("algorithm").equalsIgnoreCase("Hoeffding Tree") ||
                        rs.getString("algorithm").equalsIgnoreCase("Naive Bayes") ||
                        rs.getString("algorithm").equalsIgnoreCase("randomForest")){
                    classsification+=Integer.parseInt(rs.getString("count"));
                    classsification_sum+=Integer.parseInt(rs.getString("sum"));
                }
                else if(rs.getString("algorithm").equalsIgnoreCase("Linear Regression") ||
                        rs.getString("algorithm").equalsIgnoreCase("Simple Logistic")){
                    regression+=Integer.parseInt(rs.getString("count"));
                    regression_sum+=Integer.parseInt(rs.getString("sum"));
                }
                else if(rs.getString("algorithm").equalsIgnoreCase("RProp") ||
                        rs.getString("algorithm").equalsIgnoreCase("rpart")){
                    clustering+=Integer.parseInt(rs.getString("count"));
                    clustering_sum=Integer.parseInt(rs.getString("sum"));
                }
                else if(rs.getString("algorithm").equalsIgnoreCase("J48") ||
                        rs.getString("algorithm").equalsIgnoreCase("Multilayer Perceptron")){
                    neural_network+=Integer.parseInt(rs.getString("count"));
                    neural_network_sum+=Integer.parseInt(rs.getString("sum"));
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
        algorithms.add(new SizeByAlgorithm("Classification",String.valueOf(classsification_sum/1000000)+" MB",
                String.valueOf(classsification_sum/(1000*classsification))+" KB"));
        algorithms.add(new SizeByAlgorithm("Regression", String.valueOf(regression_sum/1000000)+" MB",
                String.valueOf(regression_sum/(1000*regression))+" KB"));
        algorithms.add(new SizeByAlgorithm("Clustering",String.valueOf(clustering_sum/1000000)+" MB",
                String.valueOf(clustering_sum/(1000*clustering))+" KB"));
        algorithms.add(new SizeByAlgorithm("Neural Network",String.valueOf(neural_network_sum/1000000)+" MB",
                String.valueOf(neural_network_sum/(1000*neural_network))+" KB"));

        return algorithms;
    }
}
