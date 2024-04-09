package controller;

import model.Client;
import model.Compte;
import model.Session;
import dao.* ;

import java.sql.Connection;
import java.sql.SQLException;

public class AffichageConnexionController {

    private Session session ;


    public AffichageConnexionController(Session s){
        this.session = s ;
    }

    public void entrerConnexion(Connection connections, String email, String mdp) throws SQLException {
        CompteDao comptedao = new CompteDaoImpl(connections) ;
        session.updatePageDeConnexion(comptedao.connexion(this.session, email, mdp)) ;
    }

    public void creerClient(Connection connections, Compte compte) throws SQLException {
        ClientDao dao = new ClientDaoImpl(connections) ;
        dao.addClient(new Client(-1, compte));
    }

}
