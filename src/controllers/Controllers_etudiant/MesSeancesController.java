package controllers.Controllers_etudiant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MesSeancesController {

    @FXML private DatePicker dateDebutPicker;
    @FXML private DatePicker dateFinPicker;
    @FXML private ComboBox<String> coursComboBox;

    @FXML private TableView<Seance> seancesTable;
    @FXML private TableColumn<Seance, String> colDate, colHeureDebut, colHeureFin, colCours, colType, colGroupe, colSalle, colEnseignant;
    @FXML private Label paginationLabel;

    private ObservableList<Seance> toutesLesSeances;
    private ObservableList<Seance> seancesFiltrees;

    public static class Seance {
        private final SimpleObjectProperty<LocalDate> date;
        private final SimpleStringProperty heureDebut, heureFin, cours, type, groupe, salle, enseignant;

        public Seance(LocalDate date, String heureDebut, String heureFin, String cours, String type, String groupe, String salle, String enseignant) {
            this.date = new SimpleObjectProperty<>(date);
            this.heureDebut = new SimpleStringProperty(heureDebut);
            this.heureFin = new SimpleStringProperty(heureFin);
            this.cours = new SimpleStringProperty(cours);
            this.type = new SimpleStringProperty(type);
            this.groupe = new SimpleStringProperty(groupe);
            this.salle = new SimpleStringProperty(salle);
            this.enseignant = new SimpleStringProperty(enseignant);
        }

        public LocalDate getDate() { return date.get(); }
        public String getHeureDebut() { return heureDebut.get(); }
        public String getHeureFin() { return heureFin.get(); }
        public String getCours() { return cours.get(); }
        public String getType() { return type.get(); }
        public String getGroupe() { return groupe.get(); }
        public String getSalle() { return salle.get(); }
        public String getEnseignant() { return enseignant.get(); }
    }

    @FXML
    public void initialize() {
        // Liaison des colonnes
        colDate.setCellValueFactory(cellData -> {
            LocalDate d = cellData.getValue().getDate();
            String dateStr = d.format(DateTimeFormatter.ofPattern("EEE. dd/MM/yyyy"));
            return new SimpleStringProperty(dateStr);
        });
        colHeureDebut.setCellValueFactory(cellData -> cellData.getValue().heureDebut);
        colHeureFin.setCellValueFactory(cellData -> cellData.getValue().heureFin);
        colCours.setCellValueFactory(cellData -> cellData.getValue().cours);
        colType.setCellValueFactory(cellData -> cellData.getValue().type);
        colGroupe.setCellValueFactory(cellData -> cellData.getValue().groupe);
        colSalle.setCellValueFactory(cellData -> cellData.getValue().salle);
        colEnseignant.setCellValueFactory(cellData -> cellData.getValue().enseignant);

        // Données simulées
        toutesLesSeances = FXCollections.observableArrayList(
            new Seance(LocalDate.of(2026, 4, 8), "10h00", "12h00", "POO", "TD", "TD-2", "B204", "M. Kouam"),
            new Seance(LocalDate.of(2026, 4, 9), "08h00", "10h00", "Bases de Données", "CM", "CM-A", "A101", "Mme Fokam"),
            new Seance(LocalDate.of(2026, 4, 9), "14h00", "16h00", "Réseaux", "TP", "TP-1", "Lab2", "M. Ngujiamtsia"),
            new Seance(LocalDate.of(2026, 4, 10), "10h00", "12h00", "Algorithmique", "TD", "TD-1", "B205", "M. Kouam"),
            new Seance(LocalDate.of(2026, 4, 11), "14h00", "16h00", "Anglais Technique", "CM", "CM-A", "A103", "Mme Abega")
        );
        seancesFiltrees = FXCollections.observableArrayList(toutesLesSeances);
        seancesTable.setItems(seancesFiltrees);
        mettreAJourPagination();

        // Filtres
        dateDebutPicker.setValue(LocalDate.of(2026, 4, 1));
        dateFinPicker.setValue(LocalDate.of(2026, 4, 11));
        coursComboBox.setItems(FXCollections.observableArrayList("Tous", "POO", "Bases de Données", "Réseaux", "Algorithmique", "Anglais Technique"));
        coursComboBox.getSelectionModel().selectFirst();

        // Écouteurs
        dateDebutPicker.valueProperty().addListener((obs, old, val) -> filtrer());
        dateFinPicker.valueProperty().addListener((obs, old, val) -> filtrer());
        coursComboBox.valueProperty().addListener((obs, old, val) -> filtrer());
    }

    private void filtrer() {
        LocalDate debut = dateDebutPicker.getValue();
        LocalDate fin = dateFinPicker.getValue();
        String cours = coursComboBox.getValue();

        seancesFiltrees.clear();
        for (Seance s : toutesLesSeances) {
            boolean dateOk = (debut == null || !s.getDate().isBefore(debut)) && (fin == null || !s.getDate().isAfter(fin));
            boolean coursOk = (cours == null || cours.equals("Tous") || s.getCours().equals(cours));
            if (dateOk && coursOk) {
                seancesFiltrees.add(s);
            }
        }
        mettreAJourPagination();
    }

    @FXML
    private void resetFilters() {
        dateDebutPicker.setValue(LocalDate.of(2026, 4, 1));
        dateFinPicker.setValue(LocalDate.of(2026, 4, 11));
        coursComboBox.getSelectionModel().selectFirst();
        filtrer();
    }

    private void mettreAJourPagination() {
        int size = seancesFiltrees.size();
        paginationLabel.setText("Affichage 1 à " + size + " sur " + size + " séances");
    }
}