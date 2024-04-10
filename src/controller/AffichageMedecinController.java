package controller;

import dao.*;
import model.Clinique;
import model.Medecin;
import model.Rdv;
import model.Session;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AffichageMedecinController {

    private Medecin medecin ;
    private Session session;
    private String specialisation;
    public AffichageMedecinController(Session s){
        this.session = s ;
    }

    public List<String> getAllClinique(Connection connection) throws SQLException {
        CliniqueDao dao = new CliniqueDaoImpl(connection) ;
        List<String> list = new ArrayList<>() ;
        return list= dao.getAllCliniquev2() ;
    }

    public String getSpeMedecin(Connection connection) throws SQLException{
        MedecinDao dao = new MedecinDaoImpl(connection);
        return specialisation = dao.getSpeMedecin(session.getId());
    }
}
