package de.unistuttgart.ipvs.as.mmp.statistic.v1.controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import de.unistuttgart.ipvs.as.mmp.statistic.domain.DatabaseConnection;
import de.unistuttgart.ipvs.as.mmp.statistic.domain.ModelsPerApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping(value = "/modelsperapplication")
public class ModelsPerApplicationController {

    @GetMapping
    public ResponseEntity<List<ModelsPerApplication>> staticData() {
        List<ModelsPerApplication> data=getData();
        return ResponseEntity.ok(data);
    }

    private List<ModelsPerApplication> getData() {
        Connection conn= new DatabaseConnection().getConnection();
        List<ModelsPerApplication> modelsPerApplication = new ArrayList<ModelsPerApplication>();
        String query="select count(*), application_name from pmmlmetadata group by application_name";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                modelsPerApplication.add(new ModelsPerApplication(rs.getString("application_name"),rs.getInt("count")));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelsPerApplication;
    }
}
