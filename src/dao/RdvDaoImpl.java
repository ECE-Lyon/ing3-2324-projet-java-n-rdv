package dao;

import model.Clinique;
import model.Medecin;
import model.Rdv;
import model.Client;

import java.sql.*;
import java.time.LocalDateTime;

public class RdvDaoImpl implements RdvDao{
    private Connection connection;
    public RdvDaoImpl(Connection connection){this.connection = connection;}
    @Override
    public int getIdJointure(Rdv rdv, int idRdv) throws SQLException{
        int idJointure = 0;
        try(PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT idJointure FROM medecin_clinique " +
                "where idRdv = ?")){
            preparedStatement1.setInt(1, rdv.getIdRdv());
            ResultSet resultSet = preparedStatement1.executeQuery();
            if (resultSet.next()){
                idJointure = resultSet.getInt(5);
            }
        }
        return idJointure;
    }
    @Override
    public void addRdv(Rdv newRdv, Medecin medecin, Client client, Clinique clinique, int idJointure) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO rdv(note, heure, idClient, idJointure)" +
                " VALUES (?, ?, ?, ?)")) {
            preparedStatement.setString(1, newRdv.getNote());
            preparedStatement.setDate(2, newRdv.getDate());
            preparedStatement.setInt(3,client.getIdClient());
            preparedStatement.setInt(4, idJointure);
            preparedStatement.execute();
        }
    }
    @Override
    public Rdv getRdv(String etat) throws SQLException {
        // je récupère l'idJointure pour avoir la clinique du rdv et je récupère l'heure aussi
        // je trie par l'état de du rdv aussi que j'ai rajouté dans la bdd
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT (idJointure, heure) FROM rdv where Etat = ?")){
            preparedStatement.setString(1, etat);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                int i = resultSet.getInt(5);
                Date date = resultSet.getDate("heure");
                return new Rdv(i, date);
            }
            return null;
        }
    }
    @Override
    public void deleteRdv(int id) throws SQLException{
        try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM rdv where idRdv = ?")){
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }
    }
    @Override
    public void updateRdv (Rdv rdv) throws SQLException{
        try(PreparedStatement preparedStatement = connection.prepareStatement("UPDATE rdv SET idRdv = ?, note = ?, heure = ?")){
            preparedStatement.setInt(1,rdv.getIdRdv());
            preparedStatement.setString(2, rdv.getNote());
            preparedStatement.setDate(3,rdv.getDate());
            preparedStatement.execute();
        }
    }
}
