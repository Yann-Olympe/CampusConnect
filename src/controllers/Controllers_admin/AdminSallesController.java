package controllers.Controllers_admin;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminSallesController {

    @FXML private TextField codeSalleField;
    @FXML private TextField nomSalleField;
    @FXML private TextField capaciteField;
    @FXML private ComboBox<String> typeSalleCombo;

    @FXML private TableView<SalleItem> sallesTable;
    @FXML private TableColumn<SalleItem, String> colCodeSalle;
    @FXML private TableColumn<SalleItem, String> colNomSalle;
    @FXML private TableColumn<SalleItem, Integer> colCapacite;
    @FXML private TableColumn<SalleItem, String> colTypeSalle;
    @FXML private TableColumn<SalleItem, String> colOccupation;

    @FXML
    public void initialize() {
        colCodeSalle.setCellValueFactory(new PropertyValueFactory<>("code"));
        colNomSalle.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colCapacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        colTypeSalle.setCellValueFactory(new PropertyValueFactory<>("type"));
        colOccupation.setCellValueFactory(new PropertyValueFactory<>("occupation"));

        typeSalleCombo.setItems(FXCollections.observableArrayList("Amphi", "Salle classique", "Salle TP"));

        sallesTable.setItems(FXCollections.observableArrayList(
                new SalleItem("A001", "Amphi A", 120, "Amphi", "85%"),
                new SalleItem("B204", "Salle B-204", 40, "Salle classique", "72%"),
                new SalleItem("B205", "Salle B-205", 40, "Salle classique", "64%"),
                new SalleItem("L002", "Lab-02", 25, "Salle TP", "93%"),
                new SalleItem("L003", "Lab-03", 25, "Salle TP", "88%")
        ));

        sallesTable.getSelectionModel().selectedItemProperty().addListener((obs, o, s) -> {
            if (s != null) {
                codeSalleField.setText(s.getCode());
                nomSalleField.setText(s.getNom());
                capaciteField.setText(String.valueOf(s.getCapacite()));
                typeSalleCombo.setValue(s.getType());
            }
        });
    }

    @FXML private void handleAjouter() { showInfo("Ajout salle", "Salle ajoutée (simulation)."); }
    @FXML private void handleModifier() { showInfo("Modification salle", "Salle modifiée (simulation)."); }
    @FXML private void handleSupprimer() { showInfo("Suppression salle", "Salle supprimée (simulation)."); }

    @FXML
    private void handleReset() {
        codeSalleField.clear();
        nomSalleField.clear();
        capaciteField.clear();
        typeSalleCombo.setValue(null);
        sallesTable.getSelectionModel().clearSelection();
    }

    private void showInfo(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }

    public static class SalleItem {
        private final String code, nom, type, occupation;
        private final Integer capacite;

        public SalleItem(String code, String nom, Integer capacite, String type, String occupation) {
            this.code = code;
            this.nom = nom;
            this.capacite = capacite;
            this.type = type;
            this.occupation = occupation;
        }

        public String getCode() { return code; }
        public String getNom() { return nom; }
        public Integer getCapacite() { return capacite; }
        public String getType() { return type; }
        public String getOccupation() { return occupation; }
    }
}