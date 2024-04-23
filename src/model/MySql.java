package model;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySql {

    private static String url = "jdbc:mysql://127.0.0.1:3306/" ;
    private static String database = "rdv_medical" ;
    private static String driver = "com.mysql.jdbc.Driver" ;
    private static String user = "root" ;
    private static String password = null ;

    private static Connection connection = null ;

    public static Connection getConnection(){
        if(MySql.connection == null){
            try {
                Class.forName(driver).newInstance();
                MySql.connection = DriverManager.getConnection(url + database, user, password) ;
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            catch (Exception ex){
                System.err.println(ex);
            }
        }
        return MySql.connection ;
    }

    public static void setPassword(String pass) { MySql.password = pass; }
    public static String getPassword() { return MySql.password ; }

}












