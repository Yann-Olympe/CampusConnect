package controllers.Controllers_enseignant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class EnseignantSaisieNotesController {

    @FXML private ComboBox<String> groupeCombo, evaluationCombo;
    @FXML private TableView<EtudiantNote> etudiantsTable;
    @FXML private TableColumn<EtudiantNote, String> colMatricule, colNom, colPrenom;
    @FXML private TableColumn<EtudiantNote, Double> colNote;
    @FXML private TableColumn<EtudiantNote, Void> colAction;
    @FXML private Label statusLabel;

    public static class EtudiantNote {
        private final SimpleStringProperty matricule, nom, prenom;
        private final SimpleDoubleProperty note;
        public EtudiantNote(String mat, String nom, String prenom, double note) {
            this.matricule = new SimpleStringProperty(mat);
            this.nom = new SimpleStringProperty(nom);
            this.prenom = new SimpleStringProperty(prenom);
            this.note = new SimpleDoubleProperty(note);
        }
        public String getMatricule() { return matricule.get(); }
        public String getNom() { return nom.get(); }
        public String getPrenom() { return prenom.get(); }
        public double getNote() { return note.get(); }
        public void setNote(double n) { note.set(n); }
    }

    @FXML
    public void initialize() {
        colMatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colNote.setCellValueFactory(new PropertyValueFactory<>("note"));
        colNote.setCellFactory(TextFieldTableCell.forTableColumn(new javafx.util.converter.DoubleStringConverter()));
        colNote.setOnEditCommit(event -> {
            EtudiantNote en = event.getRowValue();
            en.setNote(event.getNewValue());
            statusLabel.setText("Note modifiée pour " + en.getNom() + " " + en.getPrenom());
        });

        colAction.setCellFactory(col -> new TableCell<EtudiantNote, Void>() {
            private final Button btn = new Button("Enregistrer");
            { btn.setOnAction(e -> {
                EtudiantNote en = getTableView().getItems().get(getIndex());
                sauvegarderNote(en);
            }); }
            @Override protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });

        groupeCombo.setItems(FXCollections.observableArrayList("POO Avancée - TD2", "Réseaux - CM-A", "Bases de Données - TP1"));
        evaluationCombo.setItems(FXCollections.observableArrayList("Contrôle continu", "Examen final", "TP"));
        groupeCombo.getSelectionModel().selectedItemProperty().addListener((obs, old, val) -> chargerEtudiants());
    }

    private void chargerEtudiants() {
        ObservableList<EtudiantNote> data = FXCollections.observableArrayList(
            new EtudiantNote("21INF1234", "Dupont", "Jean", 0),
            new EtudiantNote("21INF5678", "Fokam", "Marie", 0),
            new EtudiantNote("21INF3456", "Kouam", "Lionel", 0)
        );
        etudiantsTable.setItems(data);
        statusLabel.setText("Étudiants chargés (" + data.size() + ")");
        etudiantsTable.setEditable(true);
    }

    private void sauvegarderNote(EtudiantNote en) {
        statusLabel.setText("Note sauvegardée pour " + en.getNom() + " : " + en.getNote());
    }
}