package dao;

import model.Clinique;

import java.sql.SQLException;

public interface CliniqueDao {
    void addClinique (Clinique newClinique) throws SQLException;
    Clinique getClinique(int id) throws SQLException;
    void deleteClinique(int id) throws SQLException;
    void updateClinique (Clinique clinique) throws SQLException;
}
