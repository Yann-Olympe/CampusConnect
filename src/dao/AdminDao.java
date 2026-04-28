package dao;

import java.sql.*;

import util.DatabaseConnection;

public class AdminDao {

    Connection con = DatabaseConnection.getConnection();

    public void save(String role, int idPersonne) throws SQLException {
        String sql = "INSERT INTO Admin (role, id_personne) VALUES (?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, role);
        ps.setInt(2, idPersonne);
        ps.executeUpdate();
    }
}