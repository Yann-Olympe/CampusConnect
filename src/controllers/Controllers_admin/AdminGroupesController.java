package controllers.Controllers_admin;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminGroupesController {

    @FXML private ComboBox<String> coursCombo;
    @FXML private TextField nomGroupeField;
    @FXML private ComboBox<String> typeCombo;
    @FXML private TextField capaciteField;
    @FXML private ComboBox<String> enseignantCombo;
    @FXML private ComboBox<String> salleCombo;

    @FXML private TableView<GroupeItem> groupesTable;
    @FXML private TableColumn<GroupeItem, String> colCours;
    @FXML private TableColumn<GroupeItem, String> colNomGroupe;
    @FXML private TableColumn<GroupeItem, String> colType;
    @FXML private TableColumn<GroupeItem, Integer> colCapacite;
    @FXML private TableColumn<GroupeItem, Integer> colEffectif;
    @FXML private TableColumn<GroupeItem, String> colEnseignant;
    @FXML private TableColumn<GroupeItem, String> colSalle;

    @FXML
    public void initialize() {
        colCours.setCellValueFactory(new PropertyValueFactory<>("cours"));
        colNomGroupe.setCellValueFactory(new PropertyValueFactory<>("nomGroupe"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colCapacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        colEffectif.setCellValueFactory(new PropertyValueFactory<>("effectif"));
        colEnseignant.setCellValueFactory(new PropertyValueFactory<>("enseignant"));
        colSalle.setCellValueFactory(new PropertyValueFactory<>("salle"));

        coursCombo.setItems(FXCollections.observableArrayList("POO Avancée", "Réseaux WAN", "Bases de Données", "Architecture Logicielle"));
        typeCombo.setItems(FXCollections.observableArrayList("CM", "TD", "TP"));
        enseignantCombo.setItems(FXCollections.observableArrayList("Kevin Tchoumi", "Marc Lelouche", "Alice Mendo", "Jean Nkoa"));
        salleCombo.setItems(FXCollections.observableArrayList("Amphi A", "B-204", "B-205", "Lab-02", "Lab-03"));

        groupesTable.setItems(FXCollections.observableArrayList(
                new GroupeItem("POO Avancée", "TD1", "TD", 40, 38, "Kevin Tchoumi", "B-204"),
                new GroupeItem("POO Avancée", "TD2", "TD", 40, 35, "Kevin Tchoumi", "B-205"),
                new GroupeItem("Réseaux WAN", "TP1", "TP", 25, 24, "Marc Lelouche", "Lab-02"),
                new GroupeItem("Bases de Données", "CM1", "CM", 100, 85, "Jean Nkoa", "Amphi A")
        ));

        groupesTable.getSelectionModel().selectedItemProperty().addListener((obs, o, s) -> {
            if (s != null) {
                coursCombo.setValue(s.getCours());
                nomGroupeField.setText(s.getNomGroupe());
                typeCombo.setValue(s.getType());
                capaciteField.setText(String.valueOf(s.getCapacite()));
                enseignantCombo.setValue(s.getEnseignant());
                salleCombo.setValue(s.getSalle());
            }
        });
    }

    @FXML private void handleAjouter() { showInfo("Ajout groupe", "Groupe ajouté (simulation)."); }
    @FXML private void handleModifier() { showInfo("Modification groupe", "Groupe modifié (simulation)."); }
    @FXML private void handleSupprimer() { showInfo("Suppression groupe", "Groupe supprimé (simulation)."); }

    @FXML
    private void handleReset() {
        coursCombo.setValue(null);
        nomGroupeField.clear();
        typeCombo.setValue(null);
        capaciteField.clear();
        enseignantCombo.setValue(null);
        salleCombo.setValue(null);
        groupesTable.getSelectionModel().clearSelection();
    }

    private void showInfo(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }

    public static class GroupeItem {
        private final String cours, nomGroupe, type, enseignant, salle;
        private final Integer capacite, effectif;

        public GroupeItem(String cours, String nomGroupe, String type, Integer capacite,
                          Integer effectif, String enseignant, String salle) {
            this.cours = cours;
            this.nomGroupe = nomGroupe;
            this.type = type;
            this.capacite = capacite;
            this.effectif = effectif;
            this.enseignant = enseignant;
            this.salle = salle;
        }

        public String getCours() { return cours; }
        public String getNomGroupe() { return nomGroupe; }
        public String getType() { return type; }
        public Integer getCapacite() { return capacite; }
        public Integer getEffectif() { return effectif; }
        public String getEnseignant() { return enseignant; }
        public String getSalle() { return salle; }
    }
}