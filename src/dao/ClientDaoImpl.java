package dao;

import model.Client ;

import java.sql.*;

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
    public Client getClient(int id) throws SQLException {
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM clients where idClient =" + id);
            if (resultSet.next()){
                int i = resultSet.getInt(1);
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String mail = resultSet.getString("mail");
                String mdp = resultSet.getString("mdp");
                return new Client(i, nom, prenom, mail, mdp);
            }
            return null;
        }
    }

    @Override
    public void deleteClient(int id) throws SQLException{
        try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM clients where idClient ="+ id)) {
            preparedStatement.execute();
        }
    }

    public void updateClient (Client client) throws SQLException{
        try(PreparedStatement preparedStatement = connection.prepareStatement("UPDATE clients SET idClient = ?, nom = ?, prenom = ?," +
                "mail = ?, mdp = ?")){
            preparedStatement.setInt(1,client.getIdClient());
            preparedStatement.setString(2, client.getNom());
            preparedStatement.setString(3,client.getPrenom());
            preparedStatement.setString(4, client.getMail());
            preparedStatement.setString(5, client.getMdp());
            preparedStatement.execute();
        }
    }
}
