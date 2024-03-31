package dao;

import model.Client ;
import java.sql.SQLException;

public interface ClientDao {


    void addClient(Client nouveauClient) throws SQLException ;

    void getClient(int id) throws SQLException ;


}
