package dao;

import model.Medecin;

import java.sql.SQLException;

public interface MedecinDao {
    void addMedecin(Medecin newMedecin) throws SQLException;
    Medecin getMedecin(int id) throws SQLException;
    String getSpeMedecin(int id) throws SQLException;
    void deleteMedecin(int id) throws SQLException;
    void updateMedecin (Medecin medecin) throws SQLException;

}
