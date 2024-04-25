package controller;

import dao.*;
import model.Client;
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

    //Avoir toutes les cliniques où travaille le médecin actuellement connecté
    public List<String> getMedecinClinique() {
        List<String> list = new ArrayList<>() ;
        for(int i = 0 ; i < this.medecin.getCliniques().size() ; i++){
            list.add(this.medecin.getCliniques().get(i).getNom()) ;
        }
        return list ;
    }

    //Avoir toutes les cliniques
    public List<String> getAllClinique(Connection connection)  {
        CliniqueDao dao = new CliniqueDaoImpl(connection) ;
        List<String> list = new ArrayList<>() ;
        try {
            return list = dao.getAllCliniquev2() ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Avoir la spécialisation du médecin actuellement connecté
    public String getSpeMedecin(){
        return this.medecin.getSpecification() ;
    }

    //Avoir le médecin actuellement connecté
    public Medecin getMedecin(Connection connection){
        MedecinDao dao = new MedecinDaoImpl(connection) ;
        try {
            this.medecin = dao.getMedecin(session.getId()) ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return this.medecin ;
    }

    //Ajouter un nouveau médecin
    public boolean addMedecin(Connection connection, Medecin med, List<String> list){
        MedecinDao dao = new MedecinDaoImpl(connection) ;

        try {
            dao.addMedecin(med);


        //mettre le bonne id pour l'inserer dans la jointure medecin_clinique
        med.setIdMedecin(dao.getIdMedecin(med.getMail(), med.getNom(), med.getPrenom()));

        CliniqueDao daoClini = new CliniqueDaoImpl(connection) ;
        List<Clinique> cliniques = daoClini.getAllClinique(list);

        JointureDao daoJoint = new JointureDaoImpl(connection) ;
        daoJoint.addJointure(cliniques, med);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true ;
    }

    public List<Client> getClientsRecherche(Connection connection, String nom, String prenom, String mail){
        int[] tab = {1, 1, 1} ;
        if(nom == null){
            tab[0] = 0 ;
        }
        if(prenom == null){
            tab[1] = 0 ;
        }
        if(mail == null){
            tab[2] = 0 ;
        }
        int idOp = tab[0] + 2*tab[1] + 4*tab[2] ;
        ClientDao dao = new ClientDaoImpl(connection) ;
        List<Client> clients = new ArrayList<>();
        try {
             clients = dao.getClient(nom, prenom, mail, idOp) ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clients ;
    }


}
