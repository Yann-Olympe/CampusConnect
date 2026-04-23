package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;

public class NotificationsController {
    @FXML private ListView<String> notificationsList;
    @FXML private ComboBox<String> categorieCombo, dateCombo, statutCombo;

    @FXML public void initialize() {
        notificationsList.setItems(FXCollections.observableArrayList(
            "📢 Votre note de POO (TD) a été publiée. Note CC : 13.50 / 20",
            "📅 La séance de Réseaux (TP-1) du 10/04 est déplacée. Nouvelle salle : Lab3",
            "✅ Inscription confirmée au groupe TP-1 (Bases de Données).",
            "📄 Relevé de notes du semestre S1 disponible. Vous pouvez le consulter.",
            "⚠️ Clôture des inscriptions dans 2 jours. Date limite : 10/04/2024 à 23h59"
        ));
        categorieCombo.setItems(FXCollections.observableArrayList("Toutes", "Notes", "Planning", "Inscriptions", "Académiques"));
        dateCombo.setItems(FXCollections.observableArrayList("Toutes", "Aujourd'hui", "Cette semaine", "Ce mois"));
        statutCombo.setItems(FXCollections.observableArrayList("Tous", "Lu", "Non lu"));
    }
}