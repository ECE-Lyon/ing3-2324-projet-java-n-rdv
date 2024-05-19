package dao;

import model.*;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface RdvDao {
    int getIdJointure(Rdv rdv, int idRdv) throws SQLException;
    void addRdv(Creneau creneau, int idClient) throws SQLException;
    Rdv getRdv(String etat) throws SQLException;
    List<Rdv> getRdvByClientId(int id) throws SQLException;

    List<Rdv> getRdvFiltres(String etat, Date date, List<Integer> listIdJointure) throws SQLException;

    void deleteRdv(int id) throws SQLException;
    void updateRdv (Rdv rdv) throws SQLException;

    List<Integer> getIdRDV(int idClient) throws SQLException;

}
