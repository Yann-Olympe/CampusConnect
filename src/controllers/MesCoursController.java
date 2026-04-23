package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;

public class MesCoursController {

    @FXML private TableView<Cours> coursTable;
    @FXML private TableColumn<Cours, String> colCode;
    @FXML private TableColumn<Cours, String> colIntitule;
    @FXML private TableColumn<Cours, String> colDescription;
    @FXML private TableColumn<Cours, String> colHeures;
    @FXML private Label statusLabel;

    // Modèle de données
    public static class Cours {
        private final SimpleStringProperty code;
        private final SimpleStringProperty intitule;
        private final SimpleStringProperty description;
        private final SimpleStringProperty heures;

        public Cours(String code, String intitule, String description, String heures) {
            this.code = new SimpleStringProperty(code);
            this.intitule = new SimpleStringProperty(intitule);
            this.description = new SimpleStringProperty(description);
            this.heures = new SimpleStringProperty(heures);
        }
        public String getCode() { return code.get(); }
        public String getIntitule() { return intitule.get(); }
        public String getDescription() { return description.get(); }
        public String getHeures() { return heures.get(); }
    }

    @FXML
    public void initialize() {
        // Lier les colonnes aux propriétés
        colCode.setCellValueFactory(cellData -> cellData.getValue().code);
        colIntitule.setCellValueFactory(cellData -> cellData.getValue().intitule);
        colDescription.setCellValueFactory(cellData -> cellData.getValue().description);
        colHeures.setCellValueFactory(cellData -> cellData.getValue().heures);

        // Données simulées (exemple)
        ObservableList<Cours> data = FXCollections.observableArrayList(
            new Cours("INF301", "POO Avancée", "Programmation orientée objet en Java", "45h"),
            new Cours("INF302", "Réseaux", "Fondamentaux des réseaux informatiques", "30h"),
            new Cours("INF305", "Bases de données", "SQL, conception et optimisation", "36h"),
            new Cours("MATH301", "Mathématiques", "Algèbre et analyse pour l'informatique", "60h"),
            new Cours("INF304", "Algorithmique", "Structures de données avancées", "45h")
        );
        coursTable.setItems(data);

        // Mettre à jour le statut
        statusLabel.setText(data.size() + " cours chargés");
    }
}