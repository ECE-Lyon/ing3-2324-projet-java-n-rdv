package model;


import java.util.ArrayList;
import java.util.List;

public class Medecin extends Compte {
    private int idMedecin;
    private String specification;

    private List<Clinique> quelClinique ;


    public Medecin (int idMedecin, String specification, Compte compte){
        super(compte.getNom(), compte.getPrenom(), compte.getMail(), compte.getMdp()) ;
        this.idMedecin = idMedecin;
        this.specification = specification;
        this.quelClinique = new ArrayList<>();
    }
    public Medecin (int idMedecin, String specification, String n, String p, String mail, String mdp){
        super(n, p, mail, mdp) ;
        this.idMedecin = idMedecin;
        this.specification = specification;
        this.quelClinique = new ArrayList<>();
    }

    public Medecin (){
        super() ;
        this.idMedecin = -999;
        this.specification = null;
        this.quelClinique = new ArrayList<>();
    }

    public List<Clinique> getCliniques(){ return this.quelClinique ;}
    public void setClinique(List<Clinique> cliniques){ this.quelClinique = cliniques ;}
    public void addClinique(Clinique clinique){ this.quelClinique.add(clinique) ;}

    public int getIdMedecin() {
        return idMedecin;
    }
    public void setIdMedecin(int idMedecin) {
        this.idMedecin = idMedecin;
    }

    public String getSpecification() {
        return specification;
    }
    public void setSpecification(String specification) {
        this.specification = specification;
    }



}
