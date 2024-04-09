package model;


import java.util.ArrayList;
import java.util.List;

public class Medecin {
    private int idMedecin;
    private String nom;
    private String prenom;
    private String mail;
    private String mdp;
    private String specification;
    private String qualification;

    private List<Clinique> quelClinique ;


    public Medecin (int idMedecin, String nom, String prenom, String mail, String mdp, String specification){
        this.idMedecin = idMedecin;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.mdp = mdp;
        this.specification = specification;
        this.qualification = "Podologue" ;
        this.quelClinique = new ArrayList<>();
    }
    public int getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(int idMedecin) {
        this.idMedecin = idMedecin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }
}
