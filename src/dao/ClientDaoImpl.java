package dao;

import model.Client ;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl implements ClientDao {

    private Connection connection;

    public ClientDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addClient(Client nouveauClient) throws SQLException {
        //Est ce qu'on stocke le mdp à chaque fois dans la classe ??? Est ce que c'est nécessaire
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO clients(nom, prenom, mail, mdp) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setString(1, nouveauClient.getNom());
            preparedStatement.setString(2, nouveauClient.getPrenom());
            preparedStatement.setString(3, nouveauClient.getMail());
            preparedStatement.setString(4, nouveauClient.getMdp());
            preparedStatement.execute();
        }
    }

    @Override
    public List<Client> getClient(String n, String p, String m, int idOperation) throws SQLException {
        List<Client> clients = new ArrayList<>() ;
        List<String> list = switchCase(n, p, m, idOperation) ;
        String sql = switchCasev2(n, p, m, idOperation) ;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            if(idOperation == 7) {
                preparedStatement.setString(1, n);
                preparedStatement.setString(2, p);
                preparedStatement.setString(3, m);
            }
            else if(idOperation==1 ||idOperation == 2 || idOperation==4){
                preparedStatement.setString(1, list.get(0));
            }
            else{
                preparedStatement.setString(1, list.get(0));
                preparedStatement.setString(2, list.get(1));
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int i = resultSet.getInt(1);
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String mail = resultSet.getString("mail");
                String mdp = resultSet.getString("mdp");
                clients.add(new Client(i, nom, prenom, mail, mdp));
            }
            return clients;
        }
    }

    @Override
    public void deleteClient(int id) throws SQLException{
        try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM clients where idClient = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }
    }
    @Override
    public void updateClient (Client client) throws SQLException{
        try(PreparedStatement preparedStatement = connection.prepareStatement("UPDATE clients SET nom = ?, prenom = ?," +
                "mail = ?, mdp = ?")){
            preparedStatement.setString(2, client.getNom());
            preparedStatement.setString(3,client.getPrenom());
            preparedStatement.setString(4, client.getMail());
            preparedStatement.setString(5, client.getMdp());
            preparedStatement.execute();
        }
    }

    public List<String> switchCase(String n, String p, String m, int idOperation){
        List<String> list = new ArrayList<>() ;
        switch (idOperation) {
            case 1 :
                list.add(n) ;
                break ;
            case 2 :
                list.add(p) ;
                break ;
            case 3 :
                list.add(n) ;
                list.add(p) ;
                break ;
            case 4 :
                list.add(m) ;
                break ;
            case 5 :
                list.add(n) ;
                list.add(m) ;
                break ;
            case 6 :
                list.add(p) ;
                list.add(m) ;
                break ;
        }
        return list ;
    }



    public String switchCasev2(String n, String p, String m, int idOperation){
       String sql = new String();
        switch (idOperation) {
            case 1 :
                sql = "SELECT * FROM clients WHERE nom = ?";
                break ;
            case 2 :
                sql = "SELECT * FROM clients WHERE prenom = ?" ;
                break ;
            case 3 :
                sql = "SELECT * FROM clients WHERE nom = ? AND prenom = ?";
                break ;
            case 4 :
                sql = "SELECT * FROM clients WHERE mail = ?" ;
                break ;
            case 5 :
                sql = "SELECT * FROM clients WHERE nom = ? AND mail = ?";
                break ;
            case 6 :
                sql = "SELECT * FROM clients WHERE prenom = ? AND mail = ?" ;
                break ;
            case 7 :
                sql = "SELECT * FROM clients WHERE nom = ? AND prenom = ? AND mail = ?" ;
                break ;
        }
        return sql ;
    }
}
