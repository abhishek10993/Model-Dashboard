package de.unistuttgart.ipvs.as.mmp.statistic.domain;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnection {

    Connection conn=null;
    public Connection getConnection(){
        Properties props = new Properties();
        String project_path=new File("").getAbsolutePath();
        try {
            props.load(new FileInputStream(project_path+"/src/main/resources/application-local.properties"));
            String url= props.getProperty("spring.datasource.url");
            String username= props.getProperty("spring.datasource.username");
            String password= props.getProperty("spring.datasource.password");
            conn = DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
