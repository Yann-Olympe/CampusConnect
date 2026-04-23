package controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

public class InscriptionGroupesController {

    @FXML private ListView<String> coursListView;
    @FXML private ComboBox<String> semestreComboBox;
    @FXML private ComboBox<String> typeGroupeComboBox;
    @FXML private ComboBox<String> statutComboBox;

    @FXML
    public void initialize() {
        initializeFilters();
        loadCours();
    }

    private void initializeFilters() {
        semestreComboBox.setItems(FXCollections.observableArrayList(
                "Semestre 1", "Semestre 2"
        ));

        typeGroupeComboBox.setItems(FXCollections.observableArrayList(
                "CM", "TD", "TP"
        ));

        statutComboBox.setItems(FXCollections.observableArrayList(
                "Ouvert", "Complet", "Fermé"
        ));
    }

    private void loadCours() {
        coursListView.setItems(FXCollections.observableArrayList(
                "POO101 - Programmation Orientée Objet",
                "BDD201 - Bases de Données",
                "ALG301 - Algorithmique Avancée",
                "RES210 - Réseaux Informatiques",
                "WEB220 - Développement Web",
                "MTH110 - Mathématiques appliquées"
        ));
    }
}