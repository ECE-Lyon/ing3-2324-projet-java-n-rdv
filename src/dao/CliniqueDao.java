package dao;

import model.Clinique;

import java.sql.SQLException;
import java.util.List;

public interface CliniqueDao {
    void addClinique (Clinique newClinique) throws SQLException;
    Clinique getCliniqueById(int id) throws SQLException;
    public List<String> getCliniqueNom(int idClinique) throws SQLException;
    List<Clinique> getCliniqueByName(List<String> list) throws SQLException;

    List<String> getAllClinique() throws SQLException;
    public int getIdCliniqueByName(String nom) throws SQLException;


    void deleteClinique(int id) throws SQLException;
    void updateClinique (Clinique clinique) throws SQLException;
}
