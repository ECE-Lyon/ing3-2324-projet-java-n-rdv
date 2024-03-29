package dao;

import java.sql.SQLException;

public interface clientDao {

    // ENLEVER LES "DEFAULT"

    default void add() throws SQLException {}

    default void get(int id) throws SQLException {}


}
