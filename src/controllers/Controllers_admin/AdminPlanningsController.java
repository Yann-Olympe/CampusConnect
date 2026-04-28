package controllers.Controllers_admin;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminPlanningsController {

    @FXML private ComboBox<String> coursCombo;
    @FXML private ComboBox<String> groupeCombo;
    @FXML private ComboBox<String> enseignantCombo;
    @FXML private ComboBox<String> salleCombo;
    @FXML private DatePicker datePicker;
    @FXML private TextField heureDebutField;
    @FXML private TextField heureFinField;
    @FXML private ComboBox<String> typeSeanceCombo;

    @FXML private TableView<PlanningItem> planningsTable;
    @FXML private TableColumn<PlanningItem, String> colDate;
    @FXML private TableColumn<PlanningItem, String> colHoraire;
    @FXML private TableColumn<PlanningItem, String> colCours;
    @FXML private TableColumn<PlanningItem, String> colGroupe;
    @FXML private TableColumn<PlanningItem, String> colEnseignant;
    @FXML private TableColumn<PlanningItem, String> colSalle;
    @FXML private TableColumn<PlanningItem, String> colTypeSeance;

    @FXML
    public void initialize() {
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colHoraire.setCellValueFactory(new PropertyValueFactory<>("horaire"));
        colCours.setCellValueFactory(new PropertyValueFactory<>("cours"));
        colGroupe.setCellValueFactory(new PropertyValueFactory<>("groupe"));
        colEnseignant.setCellValueFactory(new PropertyValueFactory<>("enseignant"));
        colSalle.setCellValueFactory(new PropertyValueFactory<>("salle"));
        colTypeSeance.setCellValueFactory(new PropertyValueFactory<>("typeSeance"));

        coursCombo.setItems(FXCollections.observableArrayList("POO Avancée", "Réseaux WAN", "Bases de Données"));
        groupeCombo.setItems(FXCollections.observableArrayList("TD1", "TD2", "TP1", "CM1"));
        enseignantCombo.setItems(FXCollections.observableArrayList("Kevin Tchoumi", "Marc Lelouche", "Jean Nkoa"));
        salleCombo.setItems(FXCollections.observableArrayList("B-204", "B-205", "Lab-02", "Amphi A"));
        typeSeanceCombo.setItems(FXCollections.observableArrayList("CM", "TD", "TP"));

        planningsTable.setItems(FXCollections.observableArrayList(
                new PlanningItem("29/04/2026", "08:00 - 10:00", "POO Avancée", "TD1", "Kevin Tchoumi", "B-204", "TD"),
                new PlanningItem("30/04/2026", "10:00 - 12:00", "Réseaux WAN", "TP1", "Marc Lelouche", "Lab-02", "TP"),
                new PlanningItem("02/05/2026", "14:00 - 16:00", "Bases de Données", "CM1", "Jean Nkoa", "Amphi A", "CM")
        ));
    }

    @FXML private void handleCreer() { showInfo("Création séance", "Séance créée (simulation)."); }
    @FXML private void handleModifier() { showInfo("Modification séance", "Séance modifiée (simulation)."); }
    @FXML private void handleSupprimer() { showInfo("Suppression séance", "Séance supprimée (simulation)."); }

    private void showInfo(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }

    public static class PlanningItem {
        private final String date, horaire, cours, groupe, enseignant, salle, typeSeance;

        public PlanningItem(String date, String horaire, String cours, String groupe, String enseignant, String salle, String typeSeance) {
            this.date = date;
            this.horaire = horaire;
            this.cours = cours;
            this.groupe = groupe;
            this.enseignant = enseignant;
            this.salle = salle;
            this.typeSeance = typeSeance;
        }

        public String getDate() { return date; }
        public String getHoraire() { return horaire; }
        public String getCours() { return cours; }
        public String getGroupe() { return groupe; }
        public String getEnseignant() { return enseignant; }
        public String getSalle() { return salle; }
        public String getTypeSeance() { return typeSeance; }
    }
}