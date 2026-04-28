package controllers.Controllers_enseignant;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class EnseignantNotesEnregistreesController {

    @FXML private TableView<Evaluation> notesTable;
    @FXML private TableColumn<Evaluation, String> colCours, colGroupe, colEvaluation, colDate, colEtudiantsEvalues;
    @FXML private TableColumn<Evaluation, Double> colCoefficient, colMoyenne;
    @FXML private TableColumn<Evaluation, Void> colActions;

    public static class Evaluation {
        private final SimpleStringProperty cours, groupe, evaluation, date, etudiants;
        private final SimpleDoubleProperty coeff, moyenne;
        public Evaluation(String cours, String groupe, String evaluation, double coeff, String date, String etudiants, double moyenne) {
            this.cours = new SimpleStringProperty(cours);
            this.groupe = new SimpleStringProperty(groupe);
            this.evaluation = new SimpleStringProperty(evaluation);
            this.coeff = new SimpleDoubleProperty(coeff);
            this.date = new SimpleStringProperty(date);
            this.etudiants = new SimpleStringProperty(etudiants);
            this.moyenne = new SimpleDoubleProperty(moyenne);
        }
        public String getCours() { return cours.get(); }
        public String getGroupe() { return groupe.get(); }
        public String getEvaluation() { return evaluation.get(); }
        public double getCoeff() { return coeff.get(); }
        public String getDate() { return date.get(); }
        public String getEtudiants() { return etudiants.get(); }
        public double getMoyenne() { return moyenne.get(); }
    }

    @FXML
    public void initialize() {
        colCours.setCellValueFactory(new PropertyValueFactory<>("cours"));
        colGroupe.setCellValueFactory(new PropertyValueFactory<>("groupe"));
        colEvaluation.setCellValueFactory(new PropertyValueFactory<>("evaluation"));
        colCoefficient.setCellValueFactory(new PropertyValueFactory<>("coeff"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colEtudiantsEvalues.setCellValueFactory(new PropertyValueFactory<>("etudiants"));
        colMoyenne.setCellValueFactory(new PropertyValueFactory<>("moyenne"));

        colActions.setCellFactory(col -> new TableCell<Evaluation, Void>() {
            private final Button btn = new Button("Détails");
            { btn.setOnAction(e -> {}); }
            @Override protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });

        notesTable.setItems(FXCollections.observableArrayList(
            new Evaluation("POO Avancée", "TD2", "Contrôle continu", 0.4, "15/04/2025", "28", 14.5),
            new Evaluation("Réseaux", "CM-A", "Examen final", 0.6, "20/04/2025", "65", 12.3)
        ));
    }
}