package dao;

import model.Rdv;

import java.sql.SQLException;

public interface RdvDao {
    void addRdv(Rdv newRdv) throws SQLException;
    Rdv getRdv(int id) throws SQLException;
    void deleteRdv(int id) throws SQLException;
    void updateRdv (Rdv rdv) throws SQLException;
}
