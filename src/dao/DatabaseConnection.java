package dao;
// Classe utiliaire pour la connexion a la BD 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private static final String urlDB ="jdbc:mysql://localhost:3306/campus_connect";
	    private static final String userName = "root";
	    private static final String passwordDB = "Olympe404";
	    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

	    //  Charger le driver (une seule fois)
	    static {
	        try {
	            Class.forName(DRIVER);
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }

	    // Fournir la chaine connexion
	    public static Connection getConnection() {
	    	Connection connec = null;
	    	try {
	    		connec =  DriverManager.getConnection(urlDB,userName,passwordDB);
	    		
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    	return connec;	    }
	}