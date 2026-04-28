package controllers.Controllers_admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminNotesController {

    @FXML private ComboBox<String> filterCoursCombo;
    @FXML private ComboBox<String> filterGroupeCombo;
    @FXML private ComboBox<String> filterEvaluationCombo;

    @FXML private TableView<NoteItem> notesTable;
    @FXML private TableColumn<NoteItem, String> colMatricule;
    @FXML private TableColumn<NoteItem, String> colEtudiant;
    @FXML private TableColumn<NoteItem, String> colCours;
    @FXML private TableColumn<NoteItem, String> colGroupe;
    @FXML private TableColumn<NoteItem, String> colEvaluation;
    @FXML private TableColumn<NoteItem, Double> colNote;

    private final ObservableList<NoteItem> masterData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colMatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        colEtudiant.setCellValueFactory(new PropertyValueFactory<>("etudiant"));
        colCours.setCellValueFactory(new PropertyValueFactory<>("cours"));
        colGroupe.setCellValueFactory(new PropertyValueFactory<>("groupe"));
        colEvaluation.setCellValueFactory(new PropertyValueFactory<>("evaluation"));
        colNote.setCellValueFactory(new PropertyValueFactory<>("note"));

        filterCoursCombo.setItems(FXCollections.observableArrayList("Tous", "POO Avancée", "Réseaux WAN", "Bases de Données"));
        filterGroupeCombo.setItems(FXCollections.observableArrayList("Tous", "TD1", "TD2", "TP1", "CM1"));
        filterEvaluationCombo.setItems(FXCollections.observableArrayList("Toutes", "CC", "TP", "Examen"));

        filterCoursCombo.setValue("Tous");
        filterGroupeCombo.setValue("Tous");
        filterEvaluationCombo.setValue("Toutes");

        loadMockData();

        FilteredList<NoteItem> filtered = new FilteredList<>(masterData, p -> true);

        Runnable apply = () -> {
            String cours = filterCoursCombo.getValue();
            String groupe = filterGroupeCombo.getValue();
            String eval = filterEvaluationCombo.getValue();

            filtered.setPredicate(n -> {
                boolean mc = cours == null || cours.equals("Tous") || n.getCours().equalsIgnoreCase(cours);
                boolean mg = groupe == null || groupe.equals("Tous") || n.getGroupe().equalsIgnoreCase(groupe);
                boolean me = eval == null || eval.equals("Toutes") || n.getEvaluation().equalsIgnoreCase(eval);
                return mc && mg && me;
            });
        };

        filterCoursCombo.valueProperty().addListener((obs, o, n) -> apply.run());
        filterGroupeCombo.valueProperty().addListener((obs, o, n) -> apply.run());
        filterEvaluationCombo.valueProperty().addListener((obs, o, n) -> apply.run());

        notesTable.setItems(filtered);
    }

    private void loadMockData() {
        masterData.setAll(
                new NoteItem("CC2026001", "NGONO Paul", "POO Avancée", "TD1", "CC", 14.5),
                new NoteItem("CC2026004", "FOUDA Linda", "POO Avancée", "TD2", "CC", 16.0),
                new NoteItem("CC2026002", "MBOA Sarah", "Réseaux WAN", "TP1", "TP", 13.0),
                new NoteItem("CC2026003", "KAMDEM Kevin", "Bases de Données", "CM1", "Examen", 15.5)
        );
    }

    @FXML
    private void handleRefresh() {
        filterCoursCombo.setValue("Tous");
        filterGroupeCombo.setValue("Tous");
        filterEvaluationCombo.setValue("Toutes");
    }

    public static class NoteItem {
        private final String matricule, etudiant, cours, groupe, evaluation;
        private final Double note;

        public NoteItem(String matricule, String etudiant, String cours, String groupe, String evaluation, Double note) {
            this.matricule = matricule;
            this.etudiant = etudiant;
            this.cours = cours;
            this.groupe = groupe;
            this.evaluation = evaluation;
            this.note = note;
        }

        public String getMatricule() { return matricule; }
        public String getEtudiant() { return etudiant; }
        public String getCours() { return cours; }
        public String getGroupe() { return groupe; }
        public String getEvaluation() { return evaluation; }
        public Double getNote() { return note; }
    }
}