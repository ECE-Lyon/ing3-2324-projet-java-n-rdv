package dao;

import model.Creneau;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import java.util.List;

public class AgendaDaoImpl implements AgendaDao{

    private Connection connection;
    public AgendaDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public List<Creneau> getCreneauLibre(List<Integer> idJointures, Date date) throws SQLException {
        List<Creneau> list = new ArrayList<>() ;
        for(int i = 0 ; i < idJointures.size() ; i++) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM emploi_du_temps WHERE idJointure = ? AND jour = ?")) {
                preparedStatement.setInt(1, idJointures.get(i));
                preparedStatement.setDate(2, date);

                try (ResultSet result = preparedStatement.executeQuery()) {
                    while (result.next()) {
                        Creneau cr = new Creneau(result.getDate("jour"), result.getInt("heure"), result.getInt("idJointure")) ;
                        list.add(cr);
                    }
                }
            }
        }
        return list ;
    }


}
