package dao;

import java.sql.*;

import util.DatabaseConnection;

public class CoursDao {

    Connection con = DatabaseConnection.getConnection();

    public void save(String code, String intitule, String desc, int volume, int idEns) throws SQLException {
        String sql = "INSERT INTO Cours VALUES (NULL, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, code);
        ps.setString(2, intitule);
        ps.setString(3, desc);
        ps.setInt(4, volume);
        ps.setInt(5, idEns);
        ps.executeUpdate();
    }

    public ResultSet findAll() throws SQLException {
        return con.prepareStatement("SELECT * FROM Cours").executeQuery();
    }
}