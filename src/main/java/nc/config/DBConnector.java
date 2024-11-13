/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author kraft
 */
public class DBConnector {

    private static String user = "root";
    private static String pass = "";
    private static String url = "jdbc:mysql://localhost:3306";
    private static String database = "diedTP";

    public static Connection instance;

    public static Connection getInstance() {
        if (instance == null) {

            try {
                instance = DriverManager.getConnection(url + "/" + database, user, pass);
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getLocalizedMessage());
            }
        }
        return instance;
    }

}
