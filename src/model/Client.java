package model;

import java.util.Observable;

public class Client extends Compte {


    private int idClient ;

    public Client(){
        super() ;
        this.idClient = -1 ;
    }

    public Client(int id, Compte compte){
        super(compte.getNom(), compte.getPrenom(), compte.getMail(), compte.getMdp()) ;
        this.idClient = id ;
    }

    public Client(int id, String nom, String prenom, String mail, String mdp){
        super(nom, prenom, mail, mdp) ;
        this.idClient = id ;
    }


    public int getIdClient() {
        return this.idClient;
    }

}
