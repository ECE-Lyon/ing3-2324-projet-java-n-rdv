package dao;

import model.Client ;

import java.sql.SQLException;
import java.util.List;

public interface ClientDao{

    void addClient(Client nouveauClient) throws SQLException ;

    List<Client> getListClients(String n, String p, String m, int idOperation) throws SQLException ;

    Client getClientById(int idClient) throws SQLException ;
    public List<Client> getListClientById(int idClient) throws SQLException;

    List<Integer> getListIdClientByIdRdv(int idClient) throws SQLException;

    void deleteClient(int id) throws SQLException;

    void updateClient (Client client) throws SQLException;



    //public String getHeureClient(int id) throws SQLException;
    List<String> getHeureClient(int idClient) throws SQLException;

    List<String> getNoteClient(int idClient) throws SQLException;


}
