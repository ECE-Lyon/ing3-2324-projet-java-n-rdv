package dao;

import model.Session;

import java.sql.SQLException;

public interface CompteDao {


    boolean connexion(Session s, String login, String mdp) throws SQLException;

}
