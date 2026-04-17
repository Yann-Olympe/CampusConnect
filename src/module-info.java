
module CampusConnect {
    requires javafx.controls;
    requires javafx.fxml;

    // Ouvre le package contenant vos contrôleurs FXML
    opens controllers to javafx.fxml;

    // Si d'autres packages contiennent des FXML ou sont nécessaires :
    opens views to javafx.fxml;
    opens views.view to javafx.fxml;

    // Exports si besoin (par ex. pour la classe Main)
    exports views;
}