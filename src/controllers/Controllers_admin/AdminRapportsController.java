package controllers.Controllers_admin;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class AdminRapportsController {

    @FXML
    private void handleReleves() {
        showInfo("Rapport", "Génération du rapport 'Relevés de notes globaux' (simulation).");
    }

    @FXML
    private void handlePlanning() {
        showInfo("Rapport", "Export du planning général (simulation).");
    }

    @FXML
    private void handleEtudiantsFiliere() {
        showInfo("Rapport", "Export de la liste des étudiants par filière (simulation).");
    }

    @FXML
    private void handleEtudiantsGroupe() {
        showInfo("Rapport", "Export de la liste des étudiants par groupe (simulation).");
    }

    private void showInfo(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}