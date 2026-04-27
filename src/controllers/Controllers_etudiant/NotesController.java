package controllers.Controllers_etudiant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class NotesController {

    @FXML private ComboBox<String> semestreCombo;
    @FXML private ComboBox<String> statutCombo;
    @FXML private TableView<Note> notesTable;
    @FXML private TableColumn<Note, String> colCode;
    @FXML private TableColumn<Note, String> colIntitule;
    @FXML private TableColumn<Note, String> colCC;
    @FXML private TableColumn<Note, Number> colCredits;

    private ObservableList<Note> toutesLesNotes;

    public static class Note {
        private final SimpleStringProperty code, intitule, cc;
        private final SimpleDoubleProperty credits;

        public Note(String code, String intitule, double credits, String cc) {
            this.code = new SimpleStringProperty(code);
            this.intitule = new SimpleStringProperty(intitule);
            this.credits = new SimpleDoubleProperty(credits);
            this.cc = new SimpleStringProperty(cc);
        }

        public String getCode() { return code.get(); }
        public String getIntitule() { return intitule.get(); }
        public double getCredits() { return credits.get(); }
        public String getCc() { return cc.get(); }
    }

    @FXML
    public void initialize() {
        // Liaison des colonnes
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colIntitule.setCellValueFactory(new PropertyValueFactory<>("intitule"));
        colCredits.setCellValueFactory(new PropertyValueFactory<>("credits"));
        colCC.setCellValueFactory(new PropertyValueFactory<>("cc"));

        // Données simulées
        toutesLesNotes = FXCollections.observableArrayList(
            new Note("POO101", "Programmation Orientée Objet", 6, "15.00"),
            new Note("BDD102", "Bases de Données", 5, "14.50"),
            new Note("RES103", "Réseaux Informatiques", 5, "12.00"),
            new Note("ALG104", "Algorithmique Avancée", 4, "-"),
            new Note("ENG105", "Anglais Technique", 2, "-"),
            new Note("MAT106", "Mathématiques Discrètes", 4, "-")
        );
        notesTable.setItems(toutesLesNotes);

        // Filtres
        semestreCombo.setItems(FXCollections.observableArrayList("S1 2025-2026", "S2 2024-2025"));
        statutCombo.setItems(FXCollections.observableArrayList("Tous", "Publiées", "En attente"));
        semestreCombo.getSelectionModel().selectFirst();
        statutCombo.getSelectionModel().selectFirst();
    }

    @FXML
    private void actualiser() {
        // Recharge les données (simulé)
        // Ici vous pourriez rafraîchir depuis la base de données
        notesTable.refresh();
    }
}