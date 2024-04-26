package dao;

import model.Client ;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface ClientDao{

    void addClient(Client nouveauClient) throws SQLException ;

    List<Client> getClient(String n, String p, String m, int idOperation) throws SQLException ;

    void deleteClient(int id) throws SQLException;

    void updateClient (Client client) throws SQLException;

}
