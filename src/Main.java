import controller.AffichageConnexionController;
import controller.AffichageMedecinController;
import model.Client;
import model.Medecin;
import model.Session;
import model.MySql ;
import view.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main {


    //// FAIRE AFFICHAGE T

    public static void main(String[] args) {
        //Initialisation du mot de passe
        if(args[0].equals("0")){
            MySql.setPassword("root") ;
        }
        

        //Creation de la session
        Session session = new Session() ;
        boolean run = true ;

        //Creation des controller
        AffichageConnexionController connexionController = new AffichageConnexionController(session) ;

        //Ouvrir la fenêtre de connexion
        AffichageConnexion affichagePrinciapel = new AffichageConnexion(connexionController) ;
        session.addObserver(affichagePrinciapel);

        while(run){
            //System.out.println();
            if(session.getTypeConnexion().equals(Session.TypeCompte.CLIENT)) {
                /** Affichage client, c'est ici (après connexion à un compte) */
                Client client = new Client() ;
                AffichageClient affichageclient = new AffichageClient() ;
                run = false ;
            }
            else if(session.getTypeConnexion().equals(Session.TypeCompte.MEDECIN)) {
                Medecin medecin = new Medecin() ;
                AffichageMedecinController medecinController = new AffichageMedecinController(session, medecin) ;
                PageMedecin affichagemedecin = new PageMedecin(medecinController);
                run = false ;

            }
        }


    }
}