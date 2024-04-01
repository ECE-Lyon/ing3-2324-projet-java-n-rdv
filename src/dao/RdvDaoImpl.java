package dao;

import model.Medecin;
import model.Rdv;
import model.Client;

import java.sql.*;
import java.time.LocalDateTime;

public class RdvDaoImpl {
    private Connection connection;
    public RdvDaoImpl(Connection connection){this.connection = connection;}
    public void addRdv(Rdv newRdv) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO rdv(note, heure, idClient, idJointure)" +
                " VALUES (?, ?, ?, ?)")) {
            preparedStatement.setString(1, newRdv.getNote());
            preparedStatement.setDate(2, newRdv.getDate());
            preparedStatement.execute();
        }
    }
    public Rdv getRdv(int id) throws SQLException {
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM rdv where idRdv =" + id);
            if (resultSet.next()){
                int i = resultSet.getInt(1);
                String note = resultSet.getString("note");
                Date date = resultSet.getDate("heure");
                return new Rdv(i, note, date);
            }
            return null;
        }
    }
    public void deleteRdv(int id) throws SQLException{
        try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM rdv where idRdv ="+ id)) {
            preparedStatement.execute();
        }
    }
    public void updateRdv (Rdv rdv) throws SQLException{
        try(PreparedStatement preparedStatement = connection.prepareStatement("UPDATE rdv SET idRdv = ?, note = ?, heure = ?,")){
            preparedStatement.setInt(1,rdv.getIdRdv());
            preparedStatement.setString(2, rdv.getNote());
            preparedStatement.setDate(3,rdv.getDate());
            preparedStatement.execute();
        }
    }
}
