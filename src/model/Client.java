package model;

import java.util.Observable;

public class Client extends Compte {


    private int idClient ;

    //Compte test pour nous (c-a-d pas besoin d'avoir ni mdp ni mail pour se connecter (pour les phases de test))
    public Client(){
        super() ;
        this.idClient = -1 ;
    }

    public Client(int id, Compte compte){
        super(compte.getNom(), compte.getPrenom(), compte.getMail(), compte.getMdp()) ;
        this.idClient = id ;
    }

    public Client(int id, String nom, String prenom, String mail){
        super(nom, prenom, mail) ;
        this.idClient = id ;
    }


    public int getIdClient() {
        return this.idClient;
    }

}
