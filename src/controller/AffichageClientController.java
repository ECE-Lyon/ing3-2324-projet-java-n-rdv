package controller;
import dao.*;
import model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
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

    public List<Rdv> getRdvReserve(Connection connection, String nomPrenom, String nomClinique, Timestamp date){
        int idClinique = -999, idMedecin = -999, nbNull = 0;
        String[] parts = nomPrenom.split(" ");
        String nomMedecin = parts[0];
        if(nomClinique != null){
            nbNull++ ;
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
            nbNull++ ;
            MedecinDao daoMedecin = new MedecinDaoImpl(connection) ;
            try {
                Medecin medecin = daoMedecin.getMedecinByName(nomPrenom) ;
                idMedecin = medecin.getIdMedecin() ;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        JointureDao daoJointure = new JointureDaoImpl(connection);
        List<Integer> idJointures = new ArrayList<>() ;
        try {
            idJointures = daoJointure.getIdJointures(idMedecin, idClinique) ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null ;
    }


}
