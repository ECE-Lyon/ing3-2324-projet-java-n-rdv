package dao;
import model.Clinique;
import model.Medecin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public Medecin getMedecinById(int id) throws SQLException {
        Medecin medecin = new Medecin() ;
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM medecins where idMedecin = ?")){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int i = resultSet.getInt(1);
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String mail = resultSet.getString("mail");
                String mpd = resultSet.getString("mdp");
                String specification = resultSet.getString("specification");
                medecin = new Medecin(i, specification, nom, prenom, mail, mpd);
            }
        }
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cliniques as c JOIN medecin_clinique as mc ON c.idClinique = mc.idClinique WHERE mc.idMedecin = ?")){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int idClinic = resultSet.getInt("idClinique");
                String localisation = resultSet.getString("localisation");
                String nom = resultSet.getString("nom");
                medecin.addClinique(new Clinique(idClinic, nom, localisation));
            }
            return medecin ;
        }
    }

    @Override
    public List<String> getAllMedecin() throws SQLException {
        List<String> list = new ArrayList<>( ) ;
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM medecins")){
            try(ResultSet result = preparedStatement.executeQuery()) {
                while(result.next()){
                    list.add(result.getString("nom") + " " + result.getString("prenom")) ;
                }
            }
        }
        return list;
    }

    @Override
    public int getIdMedecinByParameters(String mail, String nom, String prenom) throws SQLException {
        Medecin medecin = new Medecin() ;
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT idMedecin FROM medecins where mail = ? AND nom = ? AND prenom = ?")){
            preparedStatement.setString(1, mail);
            preparedStatement.setString(2, nom);
            preparedStatement.setString(3, prenom);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int i = resultSet.getInt(1);
                return i ;
            }

        }
        return -1;
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
    public Medecin getMedecinByName(String name) throws SQLException {
        Medecin medecin = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM medecins WHERE nom = ?")) {
            preparedStatement.setString(1, name);
            try (ResultSet result = preparedStatement.executeQuery()) {
                while (result.next()) {
                    medecin = new Medecin(result.getInt("idMedecin"), result.getString("specification"),result.getString("nom"), result.getString("prenom"), result.getString("mail"), result.getString("mdp"));
                }
            }
        }
        return medecin;
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
