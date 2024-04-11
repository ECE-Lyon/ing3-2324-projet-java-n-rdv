package controller;

import dao.*;
import model.Clinique;
import model.Medecin;
import model.Session;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AffichageMedecinController {

    private Medecin medecin ;
    private Session session;
    public AffichageMedecinController(Session s, Medecin m){
        this.medecin = m ;
        this.session = s ;
    }

    public int getIdMedecin(){ return this.medecin.getIdMedecin() ; }

    public List<String> getMedecinClinique(Connection connection) throws SQLException {
        List<String> list = new ArrayList<>() ;
        for(int i = 0 ; i < this.medecin.getCliniques().size() ; i++){
            list.add(this.medecin.getCliniques().get(i).getNom()) ;
        }
        return list ;
    }

    public List<String> getAllClinique(Connection connection) throws SQLException {
        CliniqueDao dao = new CliniqueDaoImpl(connection) ;
        List<String> list = new ArrayList<>() ;
        return list = dao.getAllCliniquev2() ;
    }

    public String getSpeMedecin(Connection connection) throws SQLException{
        return this.medecin.getSpecification() ;
    }

    public Medecin getMedecin(Connection connection) throws SQLException{
        MedecinDao dao = new MedecinDaoImpl(connection) ;
        this.medecin = dao.getMedecin(session.getId()) ;
        return this.medecin ;
    }

    public boolean addMedecin(Connection connection, Medecin med, List<String> list) throws SQLException{
        MedecinDao dao = new MedecinDaoImpl(connection) ;
        dao.addMedecin(med);

        med.setIdMedecin(dao.getIdMedecin(med.getMail(), med.getNom(), med.getPrenom()));

        CliniqueDao daoClini = new CliniqueDaoImpl(connection) ;
        List<Clinique> cliniques = daoClini.getAllClinique(list);

        JointureDao daoJoint = new JointureDaoImpl(connection) ;
        daoJoint.addJointure(cliniques, med);
        return true ;
    }


}
