package dao;
import model.Clinique;
import model.Medecin;

import java.sql.*;
public class MedecinDaoImpl {
    private Connection connection;
    public MedecinDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public void addMedecin(Medecin newMedecin) throws SQLException {
        //Est ce qu'on stocke le mdp à chaque fois dans la classe ??? Est ce que c'est nécessaire
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
    public Medecin getMedecin(int id) throws SQLException {
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM medecins where idMedecin =" + id);
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
    public void deleteMedecin(int id) throws SQLException{
        try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM medecins where idMedecin ="+ id)) {
            preparedStatement.execute();
        }
    }
    public void updateMedecin (Medecin medecin) throws SQLException{
        try(PreparedStatement preparedStatement = connection.prepareStatement("UPDATE medecins SET idMedecin = ?, nom = ?, prenom = ?," +
                "mail = ?, mdp = ?, specification = ?")){
            preparedStatement.setInt(1,medecin.getIdMedecin());
            preparedStatement.setString(2, medecin.getNom());
            preparedStatement.setString(3,medecin.getPrenom());
            preparedStatement.setString(4,medecin.getMail());
            preparedStatement.setString(5,medecin.getMdp());
            preparedStatement.setString(6,medecin.getSpecification());
            preparedStatement.execute();
        }
    }
}
