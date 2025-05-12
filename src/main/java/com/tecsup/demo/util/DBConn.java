package com.tecsup.demo.util;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;


public class DBConn {
    private static String driver= null;
    private static String usuario = null;
    private static String password = null;
    private static String url = null;
    static PropertyResourceBundle properties;

    static {
        try {
            ResourceBundle properties = ResourceBundle.getBundle("config");
            url = properties.getString("URL");
            driver = properties.getString("DRIVER");
            usuario = properties.getString("USER");
            password = properties.getString("PASSWORD");

            System.out.println("URL: " + url);
            System.out.println("USER: " + usuario);
            System.out.println("PASSWORD: " + password);
            System.out.println("DRIVER: " + driver);

            Class.forName(driver);
        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }

    public static Connection getConnection() {
        Connection connection=null;
        try{
            connection = DriverManager.getConnection(url,usuario,password);
        }
        catch(SQLException e){
            //Error en base de datos no se puede lograr la conexion
            System.out.println("Error al conectar a la base de datos: " + e);
        }
        return connection;
    }

}