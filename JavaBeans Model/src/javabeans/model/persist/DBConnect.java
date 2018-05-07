/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans.model.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DanielMoralesGonzale
 */
public class DBConnect {

    private static final DBConnect instance = null;

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String BD_URL = "jdbc:mysql://" + "localhost/shopbeandb";
    private static final String USUARI = "root";
    private static final String PASSWORD = "";

    public DBConnect() {
        try {
            Class.forName(this.DRIVER);
        } catch (ClassNotFoundException ex) {
            System.out.println("DBConnect:" + ex.getMessage());
        }

    }

    /**
     * Generate the conection
     *
     * @return a connection
     * @throws SQLException if a connection error occurs
     */
    public Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(BD_URL, USUARI, PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            conn = null;
        }
        return conn;
    }

}
