
module CampusConnect {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;
	requires java.sql;

    // Ouvre le package contenant vos contrôleurs FXML
    opens controllers to javafx.fxml;
    opens controllers.Controllers_etudiant to javafx.fxml , javafx.base, javafx.controls;
    opens controllers.Controllers_admin to javafx.fxml, javafx.base, javafx.controls;
    opens controllers.Controllers_enseignant to javafx.fxml, javafx.base, javafx.controls;
    
    // Si d'autres packages contiennent des FXML ou sont nécessaires :
    opens views to javafx.fxml;
    opens views.view to javafx.fxml;

    // Exports si besoin (par ex. pour la classe Main)
    exports views;
    
    
}