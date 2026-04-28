package dao;

import java.sql.*;

import util.DatabaseConnection;

public class NoteDao {

    Connection con = DatabaseConnection.getConnection();

    public void addNote(float valeur, float coef, int idInscription) throws SQLException {
        String sql = "INSERT INTO Note VALUES (NULL, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setFloat(1, valeur);
        ps.setFloat(2, coef);
        ps.setInt(3, idInscription);
        ps.executeUpdate();
    }
}