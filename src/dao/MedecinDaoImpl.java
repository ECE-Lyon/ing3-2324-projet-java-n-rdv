package dao;
import model.Clinique;
import model.Medecin;

import java.sql.*;
public class MedecinDaoImpl implements MedecinDao{
    private Connection connection;
    public MedecinDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addMedecin(Medecin newMedecin) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO medecins(nom, prenom, mail, mdp, specification)" +
                " VALUES (?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, newMedecin.getNom());
            preparedStatement.setString(2, newMedecin.getPrenom());
            preparedStatement.setString(3, newMedecin.getMail());
            preparedStatement.setString(4, newMedecin.getMdp());
            preparedStatement.setString(5, newMedecin.getSpecification());
            preparedStatement.execute();
        }
    }
    @Override
    public Medecin getMedecin(int id) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM medecins where idMedecin = ?")){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                int i = resultSet.getInt(1);
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String mail = resultSet.getString("mail");
                String mpd = resultSet.getString("mdp");
                String specification = resultSet.getString("specification");
                return new Medecin(i, nom, prenom, mail, mpd, specification);
            }
            return null;
        }
    }
    @Override
    public String getSpeMedecin(int id) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT specification FROM medecins where idMedecin = ?")){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getString("specification");
            }
            return null;
        }
    }
    @Override
    public void deleteMedecin(int id) throws SQLException{
        try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM medecins where idMedecin = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }
    }
    @Override
    public void updateMedecin (Medecin medecin) throws SQLException{
        try(PreparedStatement preparedStatement = connection.prepareStatement("UPDATE medecins SET nom = ?, prenom = ?," +
                "mail = ?, mdp = ?, specification = ?")){
            preparedStatement.setString(2, medecin.getNom());
            preparedStatement.setString(3,medecin.getPrenom());
            preparedStatement.setString(4,medecin.getMail());
            preparedStatement.setString(5,medecin.getMdp());
            preparedStatement.setString(6,medecin.getSpecification());
            preparedStatement.execute();
        }
    }
}
