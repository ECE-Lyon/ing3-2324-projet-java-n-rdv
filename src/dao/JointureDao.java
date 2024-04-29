package dao;

import model.Clinique;
import model.Medecin;

import java.sql.SQLException;
import java.util.List;

public interface JointureDao {

    public void addJointure(List<Clinique> list, Medecin med) throws SQLException;

    public int[] getMedecinByIdJointure(int idJointure)  throws SQLException;
    public List<Integer> getIdJointures(int idMedecin, int idClinique) throws SQLException ;
}
