import controller.AffichageConnexionController;
import controller.AffichageMedecinController;
import model.Client;
import model.Medecin;
import model.Session;
import view.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main {



    public static void main(String[] args) throws SQLException {
        //Creation de la session
        Session session = new Session() ;
        boolean run = true ;


        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rdv_medical", "root", "root")) {
            //Creation des controller
            AffichageConnexionController connexionController = new AffichageConnexionController(session) ;

            //Ouvrir la fenêtre de connexion
           AffichageConnexion affichagePrinciapel = new AffichageConnexion(connection, connexionController) ;
           session.addObserver(affichagePrinciapel);

           while(run){
               System.out.println();
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
}