package dao;

import model.Clinique;

import java.sql.*;

public class CliniqueDaoImpl implements CliniqueDao{
    private Connection connection;

    public CliniqueDaoImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void addClinique (Clinique newClinique) throws SQLException{
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO cliniques(nom, localisation) " +
                "VALUES (?, ?)")){
            preparedStatement.setString(1, newClinique.getNom());
            preparedStatement.setString(2, newClinique.getLocalisation());
            preparedStatement.execute();
        }
    }
    @Override
    public Clinique getClinique(int id) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cliniques where idClinique = ?")){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                int i = resultSet.getInt(1);
                String nom = resultSet.getString("nom");
                String localisation = resultSet.getString("localisation");
                return new Clinique(i, nom,localisation);
            }
            return null;
        }
    }
    @Override
    public void deleteClinique(int id) throws SQLException{
        try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM cliniques where idClient = ?")){
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }
    }
    @Override
    public void updateClinique (Clinique clinique) throws SQLException{
        try(PreparedStatement preparedStatement = connection.prepareStatement("UPDATE cliniques SET idClinique = ?, nom = ?, localisation = ?")){
            preparedStatement.setInt(1,clinique.getIdClinique());
            preparedStatement.setString(2, clinique.getNom());
            preparedStatement.setString(3,clinique.getLocalisation());
            preparedStatement.execute();
        }
    }


}
