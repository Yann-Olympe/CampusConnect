package dao;

import java.sql.*;

import util.DatabaseConnection;

public class SeanceDao {

    Connection con = DatabaseConnection.getConnection();

    public void createSeance(Date date, Time debut, Time fin,
                             int idGroupe, int idEns, int idSalle) throws SQLException {

        String sql = "INSERT INTO Seance VALUES (NULL, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDate(1, date);
        ps.setTime(2, debut);
        ps.setTime(3, fin);
        ps.setInt(4, idGroupe);
        ps.setInt(5, idEns);
        ps.setInt(6, idSalle);

        ps.executeUpdate();
    }
}