package controller;
import dao.*;
import model.Client ;
import model.Session;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AffichageClientController {

    Session session ;
    Client client ;

    public AffichageClientController(Session s, Client c){
        this.session = s ;
        this.client = c ;
    }


    public List<String> getAllClinique(Connection connection){
        CliniqueDao dao = new CliniqueDaoImpl(connection) ;
        try {
            return dao.getAllClinique() ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<String> getAllMedecin(Connection connection){
        MedecinDao dao = new MedecinDaoImpl(connection) ;
        try {
            return dao.getAllMedecin() ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




}
