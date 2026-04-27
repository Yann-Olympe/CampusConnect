package controllers.Controllers_etudiant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;

public class InscriptionGroupesController {

    @FXML private ComboBox<String> typeGroupeComboBox;
    @FXML private ComboBox<String> statutComboBox;

    @FXML private TableView<Groupe> groupesTableView;
    @FXML private TableColumn<Groupe, String> colCours;
    @FXML private TableColumn<Groupe, String> colType;
    @FXML private TableColumn<Groupe, String> colGroupe;
    @FXML private TableColumn<Groupe, String> colEnseignant;
    @FXML private TableColumn<Groupe, Number> colPlaces;
    @FXML private TableColumn<Groupe, String> colStatut;
    @FXML private TableColumn<Groupe, String> colAction;

    @FXML private Label detailCours, detailType, detailGroupe, detailEnseignant, detailPlaces, detailSalle;
    @FXML private Label statusLabel;

    private ObservableList<Groupe> tousLesGroupes;
    private ObservableList<Groupe> groupesFiltres;

    public static class Groupe {
        private final SimpleStringProperty cours;
        private final SimpleStringProperty type;
        private final SimpleStringProperty groupe;
        private final SimpleStringProperty enseignant;
        private final SimpleIntegerProperty placesRestantes;
        private final SimpleStringProperty statut;
        private final SimpleStringProperty salle;

        public Groupe(String cours, String type, String groupe, String enseignant, int places, String statut, String salle) {
            this.cours = new SimpleStringProperty(cours);
            this.type = new SimpleStringProperty(type);
            this.groupe = new SimpleStringProperty(groupe);
            this.enseignant = new SimpleStringProperty(enseignant);
            this.placesRestantes = new SimpleIntegerProperty(places);
            this.statut = new SimpleStringProperty(statut);
            this.salle = new SimpleStringProperty(salle);
        }

        public SimpleStringProperty coursProperty() { return cours; }
        public SimpleStringProperty typeProperty() { return type; }
        public SimpleStringProperty groupeProperty() { return groupe; }
        public SimpleStringProperty enseignantProperty() { return enseignant; }
        public SimpleIntegerProperty placesRestantesProperty() { return placesRestantes; }
        public SimpleStringProperty statutProperty() { return statut; }
        public SimpleStringProperty salleProperty() { return salle; }

        public String getCours() { return cours.get(); }
        public String getType() { return type.get(); }
        public String getGroupe() { return groupe.get(); }
        public String getEnseignant() { return enseignant.get(); }
        public int getPlacesRestantes() { return placesRestantes.get(); }
        public String getStatut() { return statut.get(); }
        public String getSalle() { return salle.get(); }
    }

    @FXML
    public void initialize() {
        // Liaison des colonnes
        colCours.setCellValueFactory(cellData -> cellData.getValue().coursProperty());
        colType.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        colGroupe.setCellValueFactory(cellData -> cellData.getValue().groupeProperty());
        colEnseignant.setCellValueFactory(cellData -> cellData.getValue().enseignantProperty());
        colPlaces.setCellValueFactory(cellData -> cellData.getValue().placesRestantesProperty());
        colStatut.setCellValueFactory(cellData -> cellData.getValue().statutProperty());

        // Colonne Action avec bouton centré
        colAction.setCellFactory(column -> new TableCell<Groupe, String>() {
            private final Button btn = new Button();
            {
                btn.setOnAction(e -> {
                    Groupe g = getTableView().getItems().get(getIndex());
                    inscrire(g);
                });
                setAlignment(Pos.CENTER);
            }
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getIndex() < 0 || getIndex() >= getTableView().getItems().size()) {
                    setGraphic(null);
                    return;
                }
                Groupe g = getTableView().getItems().get(getIndex());
                if ("Complet".equals(g.getStatut())) {
                    btn.setDisable(true);
                    btn.setText("Complet");
                    btn.setStyle("-fx-background-color: #cbd5e1; -fx-text-fill: #64748b; -fx-background-radius: 20; -fx-padding: 4 12;");
                } else {
                    btn.setDisable(false);
                    btn.setText("S'inscrire");
                    btn.setStyle("-fx-background-color: #1E63D5; -fx-text-fill: white; -fx-background-radius: 20; -fx-padding: 4 12;");
                }
                setGraphic(btn);
            }
        });

        // Données simulées (sans créneau)
        tousLesGroupes = FXCollections.observableArrayList(
            new Groupe("POO Avancée", "CM", "CM-A", "Dr. Noulapeu", 15, "Ouvert", "A101"),
            new Groupe("POO Avancée", "CM", "CM-B", "Dr. Noulapeu", 0, "Complet", "A102"),
            new Groupe("POO Avancée", "TD", "TD-2", "M. Melvin", 12, "Ouvert", "B204"),
            new Groupe("Bases de données", "CM", "CM-A", "M. Dada Nelson", 8, "Ouvert", "A101"),
            new Groupe("Réseaux", "TP", "TP-1", "Dr. Malong", 5, "Ouvert", "Lab2"),
            new Groupe("Algorithmique", "TD", "TD-1", "Dr. Kikmo", 2, "Ouvert", "B205"),
            new Groupe("Mathématiques", "CM", "CM-A", "Dr. Kikmo", 20, "Ouvert", "A103")
        );
        groupesFiltres = FXCollections.observableArrayList(tousLesGroupes);
        groupesTableView.setItems(groupesFiltres);
        statusLabel.setText(groupesFiltres.size() + " groupes disponibles");

        // Filtres
        typeGroupeComboBox.setItems(FXCollections.observableArrayList("Tous", "CM", "TD", "TP"));
        statutComboBox.setItems(FXCollections.observableArrayList("Tous", "Ouvert", "Complet"));
        typeGroupeComboBox.getSelectionModel().selectFirst();
        statutComboBox.getSelectionModel().selectFirst();
        typeGroupeComboBox.valueProperty().addListener((obs, old, val) -> filtrer());
        statutComboBox.valueProperty().addListener((obs, old, val) -> filtrer());

        // Sélection d'une ligne -> détails
        groupesTableView.getSelectionModel().selectedItemProperty().addListener((obs, old, selected) -> {
            if (selected != null) {
                detailCours.setText(selected.getCours());
                detailType.setText(selected.getType());
                detailGroupe.setText(selected.getGroupe());
                detailEnseignant.setText(selected.getEnseignant());
                detailPlaces.setText(String.valueOf(selected.getPlacesRestantes()));
                detailSalle.setText(selected.getSalle());
            } else {
                detailCours.setText("-");
                detailType.setText("-");
                detailGroupe.setText("-");
                detailEnseignant.setText("-");
                detailPlaces.setText("-");
                detailSalle.setText("-");
            }
        });
    }

    private void filtrer() {
        String type = typeGroupeComboBox.getValue();
        String statut = statutComboBox.getValue();
        groupesFiltres.clear();
        for (Groupe g : tousLesGroupes) {
            boolean typeOk = type.equals("Tous") || g.getType().equals(type);
            boolean statutOk = statut.equals("Tous") || g.getStatut().equals(statut);
            if (typeOk && statutOk) {
                groupesFiltres.add(g);
            }
        }
        statusLabel.setText(groupesFiltres.size() + " groupes affichés");
    }

    @FXML
    private void resetFilters() {
        typeGroupeComboBox.getSelectionModel().selectFirst();
        statutComboBox.getSelectionModel().selectFirst();
        filtrer();
    }

    private void inscrire(Groupe g) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Inscription");
        alert.setHeaderText(null);
        alert.setContentText("Vous êtes inscrit au groupe " + g.getGroupe() + " (" + g.getCours() + ")");
        alert.showAndWait();
    }
}