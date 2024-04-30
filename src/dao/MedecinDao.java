package dao;

import model.Medecin;

import java.sql.SQLException;
import java.util.List;

public interface MedecinDao {
    void addMedecin(Medecin newMedecin) throws SQLException;
    Medecin getMedecinById(int id) throws SQLException;
    int getIdMedecinByParameters(String mail, String nom, String prenom) throws SQLException;
    public List<String> getAllMedecin() throws SQLException ;
    String getSpeMedecin(int id) throws SQLException;
    public Medecin getMedecinByName(String nom) throws SQLException ;
    void deleteMedecin(int id) throws SQLException;
    void updateMedecin (Medecin medecin) throws SQLException;

}
