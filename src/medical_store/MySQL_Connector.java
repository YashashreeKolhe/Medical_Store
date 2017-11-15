/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medical_store;

import com.mysql.jdbc.Connection;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abhishek
 */
public class MySQL_Connector {
    
    public static Connection getConnection() throws  SQLException {
        Connection conn = null;
        try(FileInputStream f = new FileInputStream("/home/dell/NetBeansProjects/Medical_Store/src/medical_store/db.properties")) {
            Properties pros = new Properties();
            pros.load(f);
            
            String url = pros.getProperty("url");
            String user = pros.getProperty("user");
            String password = pros.getProperty("password");
            
            conn = (Connection) DriverManager.getConnection(url, user, password);
        } catch (IOException ex) {
            Logger.getLogger(MySQL_Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
    public static ResultSet runQuery(Connection conn, String query) throws SQLException {
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            return rs;
            
        }catch(SQLException ex) {
            Logger.getLogger(MySQL_Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public static void runUpdateQuery(Connection conn, String query) throws SQLException {
       
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);   
        }catch(SQLException ex) {
            Logger.getLogger(MySQL_Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
