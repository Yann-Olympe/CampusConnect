package controllers.Controllers_admin;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class AdminNotificationsController {

    @FXML private ListView<String> notificationsList;

    @FXML
    public void initialize() {
        notificationsList.setItems(FXCollections.observableArrayList(
                "Conflit horaire détecté : salle Lab-02 occupée le 30/04 à 10:00",
                "Alerte capacité : groupe TP1 presque complet (24/25)",
                "Cours INF401 sans groupe TP associé",
                "3 notes manquantes pour le cours INF302 - Réseaux WAN",
                "Compte enseignant 'marc.lelouche' actuellement suspendu",
                "Nouvelle exportation du planning général effectuée"
        ));
    }
}