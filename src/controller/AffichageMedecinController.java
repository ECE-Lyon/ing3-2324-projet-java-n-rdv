package controller;

import dao.*;
import model.*;

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
            return list = dao.getAllClinique() ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Avoir la spécialisation du médecin actuellement connecté
    public String getSpeMedecin(){
        return this.medecin.getSpecification() ;
    }





    //Avoir le médecin actuellement connecté
    public Medecin getMedecinAndAllClinique(Connection connection){
        MedecinDao dao = new MedecinDaoImpl(connection) ;
        try {
            this.medecin = dao.getMedecinById(session.getId()) ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return this.medecin ;
    }

    //Avoir juste 1 clinique d'un seul médecin (dans tabId, il y a idMedecin et idClinique)
    public Medecin getMedecinAndOneClinique(Connection connection, int[] tabId){
        MedecinDao dao = new MedecinDaoImpl(connection) ;
        Medecin med ;
        try {
            med = dao.getMedecinById(tabId[0]) ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for(int i = 0 ; i < med.getCliniques().size() ; i++) {
            if(med.getCliniques().get(i).getIdClinique() == tabId[1]){
                List<Clinique> list = new ArrayList<>() ;
                list.add(med.getCliniques().get(i)) ;
                med.setClinique(list);
                break ;
            }
        }
        return med ;
    }


    //Ajouter un nouveau médecin
    public boolean addMedecin(Connection connection, Medecin med, List<String> list){
        MedecinDao dao = new MedecinDaoImpl(connection) ;

        try {
            dao.addMedecin(med);


        //mettre le bonne id pour l'inserer dans la jointure medecin_clinique
        med.setIdMedecin(dao.getIdMedecinByParameters(med.getMail(), med.getNom(), med.getPrenom()));

        CliniqueDao daoClini = new CliniqueDaoImpl(connection) ;
        List<Clinique> cliniques = daoClini.getCliniqueByName(list);

        JointureDao daoJoint = new JointureDaoImpl(connection) ;
        daoJoint.addJointure(cliniques, med);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true ;
    }

    //Recherche de clients avec des filtres
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
             clients = dao.getListClients(nom, prenom, mail, idOp) ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clients ;
    }

    //Recherche de l'historique de rendez vous d'un client
    public List<Rdv> getRdvsClient(Connection connection, int idClient){
        RdvDao daoRdv = new RdvDaoImpl(connection) ;
        ClientDao daoClient = new ClientDaoImpl(connection) ;
        List<Rdv> rdv = new ArrayList<>() ;
        try {
            rdv = daoRdv.getRdvByClientId(idClient) ;
            for(int i = 0 ; i < rdv.size() ;i++){
                rdv.get(i).setRdvClient(daoClient.getClientById(idClient));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rdv ;
    }



    //Avoir l'id du medecin et de la clinique avec l'id de la Jointure medecin_clinique
    public int[] getIdMedecinCliniqueByIdJointure(Connection connection, int idJointure){
        JointureDao dao = new JointureDaoImpl(connection) ;
        int[] tabId = new int[2] ;
        try {
            tabId = dao.getMedecinByIdJointure(idJointure) ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tabId;
    }


}
