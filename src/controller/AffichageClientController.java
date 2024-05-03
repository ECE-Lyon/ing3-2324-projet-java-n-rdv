package controller;
import dao.*;
import model.Client ;
import model.Medecin;
import model.Rdv;
import model.Session;
import model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class AffichageClientController {

    Session session ;
    Client client ;


    public AffichageClientController(Session s, Client c){
        this.session = s ;
        this.client = c ;

    }
    //Avoir le client actuellement connecté
    public Client getClientConnecte(Connection connection){
        ClientDao dao = new ClientDaoImpl(connection) ;
        try {
            this.client = dao.getClientById(session.getId()) ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return this.client ;
    }

    // AVOIR l'ensemble des cliniques pour les afficher dans le Combo Box
    public List<String> getAllClinique(Connection connection){
        CliniqueDao dao = new CliniqueDaoImpl(connection) ;
        try {
            return dao.getAllClinique() ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // AVOIR l'ensemble des médecins pour les afficher dans le Combo Box
    public List<String> getAllMedecin(Connection connection){
        MedecinDao dao = new MedecinDaoImpl(connection) ;
        try {
            return dao.getAllMedecin() ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getNote(Connection connection){
        ClientDao dao = new ClientDaoImpl(connection);
        try{
            //System.out.println(this.client.getIdClient());
            return dao.getNoteClient(this.client.getIdClient()) ;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public String getHeure(Connection connection){
        ClientDao dao =new ClientDaoImpl(connection);
        try{
            return dao.getHeureClient(this.client.getIdClient());
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    ///Avoir l'ensemble des rendez-vous libre pour qu'un client puisse prendre rdv ensuite
    public List<Creneau> getRdvLibre(Connection connection, String nomPrenom, String nomClinique, Date date){
        int idClinique = -999, idMedecin = -999, nbNull = 0;
        String nomMedecin = null;

        ///On vérifie que les critères du médecin et de la clinique ne sont pas vides (car elles peuvent l'être)
        if(nomClinique != null){
            List<String> nom = new ArrayList<>() ;
            nom.add(nomClinique) ;
            CliniqueDao daoClinique = new CliniqueDaoImpl(connection) ;
            try {
                List<Clinique> cliniques = daoClinique.getCliniqueByName(nom) ;
                idClinique = cliniques.get(0).getIdClinique() ;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(nomPrenom != null){
            String[] parts = nomPrenom.split(" ");
            nomMedecin = parts[0];
            MedecinDao daoMedecin = new MedecinDaoImpl(connection) ;
            try {
                Medecin medecin = daoMedecin.getMedecinByName(nomMedecin) ;
                idMedecin = medecin.getIdMedecin() ;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        //On cherche l'id (ou les ids) correspondant au medecin/clinique (ou toutes les cliniques d'un médecin ou tous les médecins d'une clinique)
        JointureDao daoJointure = new JointureDaoImpl(connection);
        AgendaDao daoAgenda = new AgendaDaoImpl(connection);

        List<Integer> idJointures = new ArrayList<>() ;
        List<Creneau> creneauLibre = new ArrayList<>() ;

        //Avec l'idJointure, on cherche ensuite les créneaux de libre (on prend la date et l'heure
        try {
            idJointures = daoJointure.getIdJointures(idMedecin, idClinique) ;
            creneauLibre = daoAgenda.getCreneauLibre(idJointures, date) ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Recuperer la liste de rdv libre pour les afficher
        return creneauLibre ;
    }

    //Avec l'idJointure, on récupère le medecin et la clinique correspondant à l'id jointure
    public List<Medecin> getMedecinByIdWithCreneauxLibre(Connection connection, List<Creneau>  cr){
        JointureDao dao = new JointureDaoImpl(connection) ;
        MedecinDao daoMed = new MedecinDaoImpl(connection) ;
        int[] tabId = new int[2] ;
        List<Medecin>  listMedecin = new ArrayList<>() ;
        //On fait ça pour l'ensemble des idJointures qui sont dans notre liste de Créneaux de rdv libre
        for(int i = 0 ; i < cr.size() ; i ++) {
            Medecin med ;
            try {
                tabId = dao.getMedecinByIdJointure(cr.get(i).getIdJointure());
                med = daoMed.getMedecinById(tabId[0]) ;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //De base, il y a l'ensemble des cliniques où bosse un médecin. Ducoup, on les remplace juste par la clinique qui correspond à l'idJointure
            for(int k = 0 ; k < med.getCliniques().size() ; k++) {
                if(med.getCliniques().get(k).getIdClinique() == tabId[1]){
                    List<Clinique> list = new ArrayList<>() ;
                    list.add(med.getCliniques().get(k)) ;
                    med.setClinique(list);
                    listMedecin.add(med) ;
                    break ;
                }
            }
        }
        return listMedecin ;
    }

}
