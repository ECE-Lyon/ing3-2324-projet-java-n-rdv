import java.sql.Connection;
import java.sql.DriverManager;


public class Main {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/tp6", "root", "root")) {


        }
    }
}