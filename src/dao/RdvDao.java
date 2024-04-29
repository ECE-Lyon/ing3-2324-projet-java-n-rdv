package dao;

import model.Client;
import model.Clinique;
import model.Medecin;
import model.Rdv;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface RdvDao {
    void addRdv(Rdv newRdv, Medecin medecin, Client client, Clinique clinique, int idJointure) throws SQLException;
    Rdv getRdv(String etat) throws SQLException;
    List<Rdv> getRdvByClientId(int id) throws SQLException;

    List<Rdv> getRdvLibreAvecFiltre(String medecin, String clinique, Date date) throws SQLException ;

    void deleteRdv(int id) throws SQLException;
    void updateRdv (Rdv rdv) throws SQLException;
}
