package dao;

import model.Clinique;

import java.sql.*;

public class CliniqueDaoImpl {
    private Connection connection;

    public CliniqueDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public void addClinique (Clinique newClinique) throws SQLException{
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO cliniques(nom, localisation) " +
                "VALUES (?, ?)")){
            preparedStatement.setString(1, newClinique.getNom());
            preparedStatement.setString(2, newClinique.getLocalisation());
            preparedStatement.execute();
        }
    }

    public Clinique getClinique(int id) throws SQLException {
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cliniques where idClinique =" + id);
            if (resultSet.next()){
                int i = resultSet.getInt(1);
                String nom = resultSet.getString("nom");
                String localisation = resultSet.getString("localisation");
                return new Clinique(i, nom,localisation);
            }
            return null;
        }
    }

    public void deleteClinique(int id) throws SQLException{
        try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM cliniques where idClient ="+ id)) {
            preparedStatement.execute();
        }
    }

    public void updateClinique (Clinique clinique) throws SQLException{
        try(PreparedStatement preparedStatement = connection.prepareStatement("UPDATE cliniques SET idClinique = ?, nom = ?, localisation = ?")){
            preparedStatement.setInt(1,clinique.getIdClinique());
            preparedStatement.setString(2, clinique.getNom());
            preparedStatement.setString(3,clinique.getLocalisation());
            preparedStatement.execute();
        }
    }


}
