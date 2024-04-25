package dao;

import model.Client ;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ClientDao{

    void addClient(Client nouveauClient) throws SQLException ;

    Client getClient(int id) throws SQLException ;

    void deleteClient(int id) throws SQLException;

    void updateClient (Client client) throws SQLException;

}
