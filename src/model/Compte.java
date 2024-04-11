package model;

import java.util.Observable;

public class Compte extends Observable {
    protected String nom ;
    protected String prenom ;
    protected String mail ;
    protected String mdp ;

    // Pour l'instant, j'ai différencié le constructer avec/sans mdp car je ne sais pas si c'est bien de stocker le mdp dans la classe

    public Compte() {
        this.nom = null ;
        this.prenom = null ;
        this.mail = null ;
        this.mdp = null ;
    }

    public Compte(String n, String p, String mail) {
        this.nom = n ;
        this.prenom = p ;
        this.mail = mail ;
        this.mdp = "" ;
    }


    public Compte(String n, String p, String mail, String mdp) {
        this.nom = n ;
        this.prenom = p ;
        this.mail = mail ;
        this.mdp = mdp ;
    }


    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public String getMail() {
        return this.mail;
    }

    public String getMdp() {
        return this.mdp;
    }



}
