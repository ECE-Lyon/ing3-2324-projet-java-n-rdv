package model;

import model.Compte ;

import java.util.Observable;

/** Pour avoir accès à la personne actuellement connecté ainsi que ces informations
 * A chaque déconnexion, changer la session
 * A chaque connexion, activé une session
  */

public class Session extends Observable {

    public enum TypeCompte {NULL, CLIENT, MEDECIN} ;

    private Compte connecte ;
    private TypeCompte typeCompte ;


    public Session() {
        this.connecte = new Compte() ;
        this.typeCompte = TypeCompte.NULL ;
    }

    public Session(Compte connexionActuelle, TypeCompte type) {
        this.connecte = connexionActuelle ;
        this.typeCompte = type ;
    }

    public void setConnexionActuelle(Compte compte, TypeCompte type){
        this.connecte = compte ;
        this.typeCompte = type ;
    }

    public TypeCompte getTypeConnexion() { return this.typeCompte ;}

    public void updatePageDeConnexion(boolean result){
        setChanged();
        notifyObservers(result);
    }

}
