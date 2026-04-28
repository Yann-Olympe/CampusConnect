package controllers.Controllers_admin;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminConflitsController {

    @FXML private TableView<ConflitItem> conflitsTable;
    @FXML private TableColumn<ConflitItem, String> colTypeConflit;
    @FXML private TableColumn<ConflitItem, String> colDate;
    @FXML private TableColumn<ConflitItem, String> colHoraire;
    @FXML private TableColumn<ConflitItem, String> colRessource;
    @FXML private TableColumn<ConflitItem, String> colSeance1;
    @FXML private TableColumn<ConflitItem, String> colSeance2;
    @FXML private TableColumn<ConflitItem, String> colStatut;

    @FXML
    public void initialize() {
        colTypeConflit.setCellValueFactory(new PropertyValueFactory<>("typeConflit"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colHoraire.setCellValueFactory(new PropertyValueFactory<>("horaire"));
        colRessource.setCellValueFactory(new PropertyValueFactory<>("ressource"));
        colSeance1.setCellValueFactory(new PropertyValueFactory<>("seance1"));
        colSeance2.setCellValueFactory(new PropertyValueFactory<>("seance2"));
        colStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));

        loadMockData();
    }

    @FXML
    private void handleAnalyser() {
        showInfo("Analyse des conflits", "Analyse simulée terminée : 7 conflits détectés.");
    }

    @FXML
    private void handleRefresh() {
        loadMockData();
    }

    private void loadMockData() {
        conflitsTable.setItems(FXCollections.observableArrayList(
                new ConflitItem("Salle occupée", "30/04/2026", "10:00 - 12:00", "Lab-02",
                        "Réseaux WAN / TP1", "Sécurité Réseau / TP2", "À résoudre"),
                new ConflitItem("Enseignant occupé", "02/05/2026", "14:00 - 16:00", "Kevin Tchoumi",
                        "POO Avancée / TD2", "Architecture Logicielle / CM1", "À résoudre"),
                new ConflitItem("Groupe occupé", "03/05/2026", "08:00 - 10:00", "TD1",
                        "POO Avancée / TD1", "Bases de Données / TD1", "Résolu")
        ));
    }

    private void showInfo(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }

    public static class ConflitItem {
        private final String typeConflit, date, horaire, ressource, seance1, seance2, statut;

        public ConflitItem(String typeConflit, String date, String horaire, String ressource,
                           String seance1, String seance2, String statut) {
            this.typeConflit = typeConflit;
            this.date = date;
            this.horaire = horaire;
            this.ressource = ressource;
            this.seance1 = seance1;
            this.seance2 = seance2;
            this.statut = statut;
        }

        public String getTypeConflit() { return typeConflit; }
        public String getDate() { return date; }
        public String getHoraire() { return horaire; }
        public String getRessource() { return ressource; }
        public String getSeance1() { return seance1; }
        public String getSeance2() { return seance2; }
        public String getStatut() { return statut; }
    }
}