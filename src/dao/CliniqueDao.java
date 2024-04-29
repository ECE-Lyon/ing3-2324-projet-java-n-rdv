package dao;

import model.Clinique;

import java.sql.SQLException;
import java.util.List;

public interface CliniqueDao {
    void addClinique (Clinique newClinique) throws SQLException;
    Clinique getClinique(int id) throws SQLException;
    List<Clinique> getCliniqueByName(List<String> list) throws SQLException;

    List<String> getAllClinique() throws SQLException;


    void deleteClinique(int id) throws SQLException;
    void updateClinique (Clinique clinique) throws SQLException;
}
