package dao;

import model.Clinique;
import model.Medecin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JointureDaoImpl implements JointureDao {

    private Connection connection;
    public JointureDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addJointure(List<Clinique> list, Medecin med) throws SQLException {
        for(int i = 0 ; i < list.size() ; i++) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO medecin_clinique(idClinique, idMedecin, qualification)" +
                    " VALUES (?, ?, ?)")) {
                preparedStatement.setInt(1, list.get(i).getIdClinique());
                preparedStatement.setInt(2, med.getIdMedecin());
                preparedStatement.setString(3, med.getSpecification());
                preparedStatement.execute();
            }
        }
    }
}
