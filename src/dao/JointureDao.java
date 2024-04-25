package dao;

import model.Clinique;
import model.Medecin;

import java.sql.SQLException;
import java.util.List;

public interface JointureDao {

    public void addJointure(List<Clinique> list, Medecin med) throws SQLException;

}
