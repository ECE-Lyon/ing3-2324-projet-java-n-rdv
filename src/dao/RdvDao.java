package dao;

import model.Client;
import model.Clinique;
import model.Medecin;
import model.Rdv;

import java.sql.SQLException;

public interface RdvDao {
    int getIdJointure(Rdv rdv, int idRdv) throws SQLException;
    void addRdv(Rdv newRdv, Medecin medecin, Client client, Clinique clinique, int idJointure) throws SQLException;
    Rdv getRdv(int id) throws SQLException;
    void deleteRdv(int id) throws SQLException;
    void updateRdv (Rdv rdv) throws SQLException;
}
