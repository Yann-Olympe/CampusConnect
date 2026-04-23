package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.beans.property.SimpleStringProperty;

public class MonReleveController {
    @FXML private TableView<NoteDetail> releveTable;
    @FXML private Label moyenneGeneraleLabel;

    public static class NoteDetail {
        private final SimpleStringProperty cours, cc, tp, examen, finale, credits;
        public NoteDetail(String c, String cc, String tp, String ex, String fin, String cred) {
            this.cours = new SimpleStringProperty(c);
            this.cc = new SimpleStringProperty(cc);
            this.tp = new SimpleStringProperty(tp);
            this.examen = new SimpleStringProperty(ex);
            this.finale = new SimpleStringProperty(fin);
            this.credits = new SimpleStringProperty(cred);
        }
        // getters...
    }

    @FXML public void initialize() {
        releveTable.setItems(FXCollections.observableArrayList(
            new NoteDetail("POO Avancée", "14.5", "15.0", "16.0", "15.2", "4"),
            new NoteDetail("Algorithmique", "13.0", "14.5", "15.0", "14.4", "4"),
            new NoteDetail("Bases de données", "14.0", "13.5", "13.0", "13.6", "3"),
            new NoteDetail("Réseaux", "12.0", "12.5", "12.5", "12.4", "3"),
            new NoteDetail("Mathématiques", "10.5", "11.0", "12.0", "11.5", "4")
        ));
        moyenneGeneraleLabel.setText("14.25");
    }
}