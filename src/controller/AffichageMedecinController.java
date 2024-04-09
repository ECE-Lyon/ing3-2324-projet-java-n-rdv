package controller;

import dao.CliniqueDao;
import dao.CliniqueDaoImpl;
import model.Clinique;
import model.Medecin;
import model.Session;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AffichageMedecinController {

    private Medecin medecin ;

    public AffichageMedecinController(){

    }

    public List<String> getAllClinique(Connection connection) throws SQLException {
        CliniqueDao dao = new CliniqueDaoImpl(connection) ;
        List<String> list = new ArrayList<>() ;
        return list= dao.getAllCliniquev2() ;
    }



}
