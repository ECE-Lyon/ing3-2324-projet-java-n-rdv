package dao;

import model.Clinique;
import model.Compte;
import model.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public Clinique getCliniqueById(int id) throws SQLException {
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
    public List<Clinique> getCliniqueByName(List<String> cliniques) throws SQLException {
        List<Clinique> list = new ArrayList<>() ;
        for(int i = 0 ; i < cliniques.size() ; i++) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cliniques WHERE nom = ?")) {
                preparedStatement.setString(1, cliniques.get(i));
                try (ResultSet result = preparedStatement.executeQuery()) {
                    while (result.next()) {
                        Clinique clinique = new Clinique(result.getInt("idClinique"), result.getString("nom"), result.getString("localisation"));
                        list.add(clinique);
                    }
                }
            }
        }
        return list;
    }

    @Override
    public int getIdCliniqueByName(String nom) throws SQLException {
        int idClinique = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT idClinique FROM cliniques WHERE nom = ?")) {
            preparedStatement.setString(1, nom);
            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {
                    idClinique = result.getInt("idClinique");
                }
            }
        }
        return idClinique;
    }

    @Override
    public List<String> getAllClinique() throws SQLException {
        List<String> list = new ArrayList<>( ) ;
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT nom FROM cliniques")){
            try(ResultSet result = preparedStatement.executeQuery()) {
                while(result.next()){
                    list.add(result.getString("nom")) ;
                }
            }
        }
        return list;
    }
    public List<String> getCliniqueNom(int idClinique) throws SQLException {
        List<String> list = new ArrayList<>( ) ;
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT nom FROM cliniques " +
                "where idClinique = ?")){
            preparedStatement.setInt(1, idClinique);
            try(ResultSet result = preparedStatement.executeQuery()) {
                while(result.next()){
                    list.add(result.getString("nom")) ;
                }
            }
        }
        return list;
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
