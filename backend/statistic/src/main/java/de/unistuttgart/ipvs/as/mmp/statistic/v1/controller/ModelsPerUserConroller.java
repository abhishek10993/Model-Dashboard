package de.unistuttgart.ipvs.as.mmp.statistic.v1.controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import de.unistuttgart.ipvs.as.mmp.statistic.domain.DatabaseConnection;
import de.unistuttgart.ipvs.as.mmp.statistic.domain.ModelsPerUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping(value = "/modelsperuser")
public class ModelsPerUserConroller {

    @GetMapping
    public ResponseEntity<List<ModelsPerUser>> staticData() {
        List<ModelsPerUser> data=getData();
        return ResponseEntity.ok(data);
    }

    private List<ModelsPerUser> getData() {
        Connection conn= new DatabaseConnection().getConnection();
        List<ModelsPerUser> modelsPerUsers = new ArrayList<ModelsPerUser>();
        String query="select count(modelmetadata.name), mmpuser.name from modelmetadata, mmpuser where modelmetadata.author_id=mmpuser.id group by mmpuser.id";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                modelsPerUsers.add(new ModelsPerUser(rs.getString("name"),rs.getInt("count")));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelsPerUsers;
    }
}
