package controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class PlanningViewController {

    @FXML private ComboBox<String> planningTypeGroupeComboBox;
    @FXML private ComboBox<String> planningStatutComboBox;

    @FXML
    public void initialize() {
        initializeFilters();
    }

    private void initializeFilters() {
        planningTypeGroupeComboBox.setItems(FXCollections.observableArrayList(
                "Tous", "CM", "TD", "TP"
        ));

        planningStatutComboBox.setItems(FXCollections.observableArrayList(
                "Tous", "Planifiée", "Confirmée", "En cours", "Terminée", "Annulée", "Reportée"
        ));

        planningTypeGroupeComboBox.setValue("Tous");
        planningStatutComboBox.setValue("Tous");
    }
}