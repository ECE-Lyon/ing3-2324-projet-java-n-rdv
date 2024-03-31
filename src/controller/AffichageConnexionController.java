package controller;

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


}
