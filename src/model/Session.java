package model;

import model.Compte ;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Observable;

/** Pour avoir accès à la personne actuellement connecté ainsi que ces informations
 * A chaque déconnexion, changer la session
 * A chaque connexion, activé une session
 *
 * Ajouter un ID client/medecin en variable, pour pouvoir appeler si jamais le client/medecin en question dans la bdd
  */


public class Session extends Observable {

    public enum TypeCompte {NULL, CLIENT, MEDECIN} ;

    private Compte connecte ;
    private TypeCompte typeCompte ;

    private int id ;


    public Session() {
        this.connecte = new Compte() ;
        this.typeCompte = TypeCompte.NULL ;
        this.id = -999 ;
    }

    public Session(Compte connexionActuelle, TypeCompte type) {
        this.connecte = connexionActuelle ;
        this.typeCompte = type ;

    }

    public void setConnexionActuelle(Compte compte, TypeCompte type, int id ){
        this.connecte = compte ;
        this.typeCompte = type ;
        this.id = id ;
    }

    public TypeCompte getTypeConnexion() { return this.typeCompte ;}

    public void updatePageDeConnexion(boolean result){
        setChanged();
        notifyObservers(result);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}




/*public JPanel createPanel(String nomClinique, Timestamp dateRdv, Client client, String etat, int i) {
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.gridx = 0;
    constraints.gridy = i;
    constraints.weightx = 1;
    constraints.weighty = 4;
    constraints.fill = GridBagConstraints.BOTH;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dateString = sdf.format(dateRdv);

    JPanel panel = new JPanel();
    this.rdvsMedecin.setLayout(new FlowLayout(FlowLayout.CENTER, 130, 0));

    JLabel label1 = new JLabel(nomClinique);
    panel.add(label1, CENTER_ALIGNMENT);

    JLabel label2 = new JLabel(dateString);
    panel.add(label2,CENTER_ALIGNMENT);

    if (client != null){
        JLabel label3 = new JLabel(client.getPrenom() + " " + client.getNom());
        panel.add(label3, CENTER_ALIGNMENT);
    }
    JLabel label4 = new JLabel(etat);
    panel.add(label4, CENTER_ALIGNMENT);

    Border bord = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED) ;
    panel.setBorder(bord);
    return panel;
}*/
