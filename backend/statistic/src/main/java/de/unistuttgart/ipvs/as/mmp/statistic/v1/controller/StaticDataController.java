package de.unistuttgart.ipvs.as.mmp.statistic.v1.controller;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import de.unistuttgart.ipvs.as.mmp.statistic.domain.DatabaseConnection;
import de.unistuttgart.ipvs.as.mmp.statistic.domain.StaticData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping(value = "/staticdata")
public class StaticDataController {

    @GetMapping
    public ResponseEntity<StaticData> staticData() {
        StaticData data=getData();
        return ResponseEntity.ok(data);
    }

    public StaticData getData(){
        Connection conn= new DatabaseConnection().getConnection();
        String total_models_query="select count(*) from modelmetadata";
        String total_size_query="select sum(file_size) from model_file";
        String added_lastmonth_query="select count(*) from modelmetadata where CAST(date_of_creation as text) > ?";
        String increased_lastmonth_query="select sum(model_file.file_size) from model_file, modelmetadata  " +
                "where model_file.model_id=modelmetadata.id-1 and CAST(modelmetadata.date_of_creation as text) > ?";
        String total_models, total_size, added_lastmonth, percentage_change;
        total_models=total_size=added_lastmonth=percentage_change="0";
        LocalDate date=LocalDate.now();
        String date_lastmonth=String.valueOf(date.minusDays(date.getDayOfMonth()));
        double change=0;
        long sum=0;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(total_models_query);
            while (rs.next()){
                total_models=rs.getString("count");
                System.out.println(total_models);
            }
            rs=stmt.executeQuery(total_size_query);
            while(rs.next()){
                sum=Long.parseLong(rs.getString("sum"));
                total_size=String.valueOf(sum/1000000)+" MB";
                System.out.println(total_size);
            }
            PreparedStatement preparedStatement=conn.prepareStatement(added_lastmonth_query);
            preparedStatement.setString(1,date_lastmonth);
            rs=preparedStatement.executeQuery();
            while (rs.next()){
                added_lastmonth=rs.getString("count");
                System.out.println(added_lastmonth);
            }
            preparedStatement=conn.prepareStatement(increased_lastmonth_query);
            preparedStatement.setString(1,date_lastmonth);
            rs=preparedStatement.executeQuery();
            while (rs.next()){
                try{
                    change=Double.parseDouble(rs.getString("sum"));
                    percentage_change=String.valueOf((int)((change/(sum-change))*100));
                }
                catch (Exception e){
                    e.printStackTrace();
                    percentage_change="0";
                }
                System.out.println(percentage_change);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new StaticData(total_models, added_lastmonth, total_size, percentage_change);

    }
}
