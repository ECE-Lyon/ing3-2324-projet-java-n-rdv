package model;

public class Clinique {
    private int idClinique;
    private String localisation;
    private String nom;
    public Clinique(int idClinique, String nom, String localisation){
        this.idClinique = idClinique;
        this.nom = nom;
        this.localisation = localisation;
    }

    public int getIdClinique() {
        return idClinique;
    }

    public void setIdClinique(int idClinique) {
        this.idClinique = idClinique;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }
}
