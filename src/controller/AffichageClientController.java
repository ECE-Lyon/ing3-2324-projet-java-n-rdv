package controller;
import dao.ClientDao;
import dao.ClientDaoImpl;
import dao.RdvDao;
import dao.RdvDaoImpl;
import model.Client ;
import model.Medecin;
import model.Session;
import view.AffichageConnexion;

import java.sql.Connection;
import java.sql.Date;

public class AffichageClientController {

    Client client ;
    Medecin medecin ;
    Connection connection ;

    public AffichageClientController(Connection connection){
        this.connection = connection ;
    }


    public void creerRDV(Session s, Date date, Medecin medecin){
        RdvDao dao = new RdvDaoImpl(connection) ;
    }

}
