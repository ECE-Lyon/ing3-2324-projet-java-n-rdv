package dao;

import model.Client ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    public void getClient(int id) throws SQLException {

    }
}
