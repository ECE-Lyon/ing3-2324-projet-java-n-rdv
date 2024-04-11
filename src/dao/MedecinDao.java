package dao;

import model.Medecin;

import java.sql.SQLException;
import java.util.List;

public interface MedecinDao {
    void addMedecin(Medecin newMedecin) throws SQLException;
    Medecin getMedecin(int id) throws SQLException;
    int getIdMedecin(String mail, String nom, String prenom) throws SQLException;
    String getSpeMedecin(int id) throws SQLException;
    void deleteMedecin(int id) throws SQLException;
    void updateMedecin (Medecin medecin) throws SQLException;

}
