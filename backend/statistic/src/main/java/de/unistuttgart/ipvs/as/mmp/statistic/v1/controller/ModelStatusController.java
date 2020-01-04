package de.unistuttgart.ipvs.as.mmp.statistic.v1.controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import de.unistuttgart.ipvs.as.mmp.statistic.domain.DatabaseConnection;
import de.unistuttgart.ipvs.as.mmp.statistic.domain.ModelStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping(value = "/modelstatus")
public class ModelStatusController {

    @GetMapping
    public ResponseEntity<List<ModelStatus>> staticData() {
        List<ModelStatus> data=getData();
        return ResponseEntity.ok(data);
    }

    private List<ModelStatus> getData() {

        Connection conn= new DatabaseConnection().getConnection();
        List<ModelStatus> modelStatus = new ArrayList<ModelStatus>();
        String query="select count(*), status from modelmetadata group by status order by status";
        String status;
        int count;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                switch(rs.getString("status")){
                    case "0":
                        modelStatus.add(new ModelStatus("Planned", rs.getInt("count")));
                        break;
                    case "1":
                        modelStatus.add(new ModelStatus("Experimental", rs.getInt("count")));
                        break;
                    case "2":
                        modelStatus.add(new ModelStatus("Operation", rs.getInt("count")));
                        break;
                    case "3":
                        modelStatus.add(new ModelStatus("Maintenance", rs.getInt("count")));
                        break;
                    case "4":
                        modelStatus.add(new ModelStatus("Archived", rs.getInt("count")));
                        break;
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
        return modelStatus;
    }
}
