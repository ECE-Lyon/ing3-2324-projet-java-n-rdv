package dao;

import model.Session;
import model.Compte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompteDaoImpl implements CompteDao {

    private Connection connection ;

    public CompteDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean connexion(Session s, String login, String mdp) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM clients")) {
            try(ResultSet result = preparedStatement.executeQuery()) {
                while(result.next()){
                    if(result.getString("mail").equals(login) && result.getString("mdp").equals(mdp)){
                        Compte compte = new Compte(result.getString("nom"),  result.getString("prenom"), result.getString("mail"), result.getString("mdp")) ;
                        s.setConnexionActuelle(compte, Session.TypeCompte.CLIENT);
                        return true ;
                    }
                }
            }

        }
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM medecins")) {
            try(ResultSet result = preparedStatement.executeQuery()) {
                while(result.next()){
                    if(result.getString("mail").equals(login) && result.getString("mdp").equals(mdp)){
                        Compte compte = new Compte(result.getString("nom"),  result.getString("prenom"), result.getString("mail"), result.getString("mdp")) ;
                        s.setConnexionActuelle(compte, Session.TypeCompte.MEDECIN);
                        return true ;
                    }
                }
            }

        }
        return false;
    }
}
