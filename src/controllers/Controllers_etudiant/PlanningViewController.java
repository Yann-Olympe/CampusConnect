package controllers.Controllers_etudiant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.DayOfWeek;

public class PlanningViewController {

    @FXML private Label semaineLabel;
    @FXML private TableView<Seance> planningTable;
    @FXML private TableColumn<Seance, String> colJour, colHeure, colCours, colType, colGroupe, colSalle, colEnseignant, colStatut, colAction;
    @FXML private Label detailCours, detailType, detailGroupe, detailSalle, detailEnseignant, detailDate, detailHeure, detailStatut;

    private ObservableList<Seance> toutesLesSeances;
    private ObservableList<Seance> seancesSemaine;
    private LocalDate debutSemaine = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

    // Modèle de données avec date réelle
    public static class Seance {
        private final SimpleObjectProperty<LocalDate> date;
        private final SimpleStringProperty heure, cours, type, groupe, salle, enseignant, statut;

        public Seance(LocalDate date, String heure, String cours, String type, String groupe, String salle, String enseignant, String statut) {
            this.date = new SimpleObjectProperty<>(date);
            this.heure = new SimpleStringProperty(heure);
            this.cours = new SimpleStringProperty(cours);
            this.type = new SimpleStringProperty(type);
            this.groupe = new SimpleStringProperty(groupe);
            this.salle = new SimpleStringProperty(salle);
            this.enseignant = new SimpleStringProperty(enseignant);
            this.statut = new SimpleStringProperty(statut);
        }

        public SimpleObjectProperty<LocalDate> dateProperty() { return date; }
        public SimpleStringProperty heureProperty() { return heure; }
        public SimpleStringProperty coursProperty() { return cours; }
        public SimpleStringProperty typeProperty() { return type; }
        public SimpleStringProperty groupeProperty() { return groupe; }
        public SimpleStringProperty salleProperty() { return salle; }
        public SimpleStringProperty enseignantProperty() { return enseignant; }
        public SimpleStringProperty statutProperty() { return statut; }

        public LocalDate getDate() { return date.get(); }
        public String getHeure() { return heure.get(); }
        public String getCours() { return cours.get(); }
        public String getType() { return type.get(); }
        public String getGroupe() { return groupe.get(); }
        public String getSalle() { return salle.get(); }
        public String getEnseignant() { return enseignant.get(); }
        public String getStatut() { return statut.get(); }
    }

    @FXML
    public void initialize() {
        // Colonnes
        colJour.setCellValueFactory(cellData -> {
            LocalDate d = cellData.getValue().getDate();
            String jour = d.format(DateTimeFormatter.ofPattern("EEEE dd/MM"));
            return new SimpleStringProperty(jour);
        });
        colHeure.setCellValueFactory(cellData -> cellData.getValue().heureProperty());
        colCours.setCellValueFactory(cellData -> cellData.getValue().coursProperty());
        colType.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        colGroupe.setCellValueFactory(cellData -> cellData.getValue().groupeProperty());
        colSalle.setCellValueFactory(cellData -> cellData.getValue().salleProperty());
        colEnseignant.setCellValueFactory(cellData -> cellData.getValue().enseignantProperty());
        colStatut.setCellValueFactory(cellData -> cellData.getValue().statutProperty());

        // Colonne Action : bouton icône
        colAction.setCellFactory(column -> new TableCell<Seance, String>() {
            private final Button btn = new Button("📅");
            {
                btn.setOnAction(e -> {
                    Seance s = getTableView().getItems().get(getIndex());
                    ajouterRappel(s);
                });
                btn.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
                setAlignment(Pos.CENTER);
            }
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) setGraphic(null);
                else setGraphic(btn);
            }
        });

        // Données simulées avec dates réelles (semaine du 07/04/2025)
        toutesLesSeances = FXCollections.observableArrayList(
            new Seance(LocalDate.of(2025, 4, 7), "10h00-12h00", "POO Avancée", "TD", "TD-2", "B204", "M. Nzambwe", "Planifiée"),
            new Seance(LocalDate.of(2025, 4, 7), "14h00-16h00", "Réseaux", "CM", "CM-A", "A101", "M. Ngulmatia", "Planifiée"),
            new Seance(LocalDate.of(2025, 4, 9), "08h00-10h00", "Bases de données", "CM", "CM-A", "A101", "Mme Fokam", "Confirmée"),
            new Seance(LocalDate.of(2025, 4, 9), "14h00-16h00", "Réseaux", "TP", "TP-1", "Lab2", "M. Ngulmatia", "En cours"),
            new Seance(LocalDate.of(2025, 4, 10), "10h00-12h00", "Algorithmique", "TD", "TD-1", "B205", "M. Kouam", "Planifiée"),
            new Seance(LocalDate.of(2025, 4, 11), "08h00-10h00", "Mathématiques", "CM", "CM-A", "A103", "Dr. Kikmo", "Planifiée")
        );
        seancesSemaine = FXCollections.observableArrayList();
        planningTable.setItems(seancesSemaine);

        // Afficher la semaine courante
        mettreAJourSemaine();
        filtrerSeances();

        // Sélection d'une ligne
        planningTable.getSelectionModel().selectedItemProperty().addListener((obs, old, selected) -> {
            if (selected != null) {
                detailCours.setText(selected.getCours());
                detailType.setText(selected.getType());
                detailGroupe.setText(selected.getGroupe());
                detailSalle.setText(selected.getSalle());
                detailEnseignant.setText(selected.getEnseignant());
                detailDate.setText(selected.getDate().format(DateTimeFormatter.ofPattern("EEEE dd/MM/yyyy")));
                detailHeure.setText(selected.getHeure());
                detailStatut.setText(selected.getStatut());
                detailStatut.setStyle(getStatutStyle(selected.getStatut()));
            } else {
                detailCours.setText("-"); detailType.setText("-"); detailGroupe.setText("-");
                detailSalle.setText("-"); detailEnseignant.setText("-"); detailDate.setText("-");
                detailHeure.setText("-"); detailStatut.setText("-");
            }
        });
    }

    private void filtrerSeances() {
        seancesSemaine.clear();
        LocalDate finSemaine = debutSemaine.plusDays(6);
        for (Seance s : toutesLesSeances) {
            LocalDate d = s.getDate();
            if (!d.isBefore(debutSemaine) && !d.isAfter(finSemaine)) {
                seancesSemaine.add(s);
            }
        }
        if (seancesSemaine.isEmpty()) {
            planningTable.setPlaceholder(new Label("Aucune séance cette semaine"));
        }
    }

    private void mettreAJourSemaine() {
        LocalDate finSemaine = debutSemaine.plusDays(6);
        String texte = String.format("Semaine %d (%s - %s)",
                debutSemaine.get(java.time.temporal.WeekFields.ISO.weekOfWeekBasedYear()),
                debutSemaine.format(DateTimeFormatter.ofPattern("dd/MM")),
                finSemaine.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        semaineLabel.setText(texte);
        filtrerSeances();
    }

    @FXML
    private void previousWeek() {
        debutSemaine = debutSemaine.minusWeeks(1);
        mettreAJourSemaine();
    }

    @FXML
    private void nextWeek() {
        debutSemaine = debutSemaine.plusWeeks(1);
        mettreAJourSemaine();
    }

    @FXML
    private void goToToday() {
        debutSemaine = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        mettreAJourSemaine();
    }

    @FXML
    private void ajouterRappel() {
        Seance selected = planningTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            ajouterRappel(selected);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune séance");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une séance dans le planning.");
            alert.showAndWait();
        }
    }

    private void ajouterRappel(Seance s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rappel");
        alert.setHeaderText(null);
        alert.setContentText("Rappel ajouté pour la séance " + s.getCours() + " (" + s.getHeure() + ")");
        alert.showAndWait();
    }

    private String getStatutStyle(String statut) {
        switch (statut) {
            case "Planifiée": return "-fx-text-fill: #3b82f6; -fx-font-weight: bold;";
            case "Confirmée": return "-fx-text-fill: #10b981; -fx-font-weight: bold;";
            case "En cours": return "-fx-text-fill: #f59e0b; -fx-font-weight: bold;";
            case "Terminée": return "-fx-text-fill: #64748b; -fx-font-weight: bold;";
            case "Annulée": return "-fx-text-fill: #ef4444; -fx-font-weight: bold;";
            case "Reportée": return "-fx-text-fill: #8b5cf6; -fx-font-weight: bold;";
            default: return "";
        }
    }
}