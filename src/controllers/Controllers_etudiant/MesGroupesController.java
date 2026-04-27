package controllers.Controllers_etudiant;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;

public class MesGroupesController {

    @FXML private TableView<Groupe> groupesTable;
    @FXML private TableColumn<Groupe, String> colCours;
    @FXML private TableColumn<Groupe, String> colType;
    @FXML private TableColumn<Groupe, String> colGroupe;
    @FXML private TableColumn<Groupe, String> colEnseignant;
    @FXML private TableColumn<Groupe, String> colSalle;
    @FXML private TableColumn<Groupe, String> colCreneau;
    @FXML private TableColumn<Groupe, String> colStatut;
    @FXML private TableColumn<Groupe, String> colAction;
    @FXML private Label statusLabel;

    public static class Groupe {
        private final SimpleStringProperty cours, type, groupe, enseignant, salle, creneau, statut;

        public Groupe(String cours, String type, String groupe, String enseignant, String salle, String creneau, String statut) {
            this.cours = new SimpleStringProperty(cours);
            this.type = new SimpleStringProperty(type);
            this.groupe = new SimpleStringProperty(groupe);
            this.enseignant = new SimpleStringProperty(enseignant);
            this.salle = new SimpleStringProperty(salle);
            this.creneau = new SimpleStringProperty(creneau);
            this.statut = new SimpleStringProperty(statut);
        }

        public SimpleStringProperty coursProperty() { return cours; }
        public SimpleStringProperty typeProperty() { return type; }
        public SimpleStringProperty groupeProperty() { return groupe; }
        public SimpleStringProperty enseignantProperty() { return enseignant; }
        public SimpleStringProperty salleProperty() { return salle; }
        public SimpleStringProperty creneauProperty() { return creneau; }
        public SimpleStringProperty statutProperty() { return statut; }

        // Getters
        public String getCours() { return cours.get(); }
        public String getType() { return type.get(); }
        public String getGroupe() { return groupe.get(); }
        public String getEnseignant() { return enseignant.get(); }
        public String getSalle() { return salle.get(); }
        public String getCreneau() { return creneau.get(); }
        public String getStatut() { return statut.get(); }
    }

    @FXML
    public void initialize() {
        // Liaison des colonnes
        colCours.setCellValueFactory(cellData -> cellData.getValue().coursProperty());
        colType.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        colGroupe.setCellValueFactory(cellData -> cellData.getValue().groupeProperty());
        colEnseignant.setCellValueFactory(cellData -> cellData.getValue().enseignantProperty());
        colSalle.setCellValueFactory(cellData -> cellData.getValue().salleProperty());
        colCreneau.setCellValueFactory(cellData -> cellData.getValue().creneauProperty());
        colStatut.setCellValueFactory(cellData -> cellData.getValue().statutProperty());

        // Colonne Action avec bouton personnalisé
        colAction.setCellFactory(column -> new TableCell<Groupe, String>() {
            private final Button btn = new Button();
            {
                btn.setOnAction(e -> {
                    Groupe g = getTableView().getItems().get(getIndex());
                    seDesinscrire(g);
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
                if ("Inscrit".equals(g.getStatut())) {
                    btn.setText("Se désinscrire");
                    btn.setStyle("-fx-background-color: #ef4444; -fx-text-fill: white; -fx-background-radius: 20; -fx-padding: 4 12;");
                } else {
                    btn.setText("Complet");
                    btn.setDisable(true);
                    btn.setStyle("-fx-background-color: #cbd5e1; -fx-text-fill: #64748b; -fx-background-radius: 20; -fx-padding: 4 12;");
                }
                setGraphic(btn);
            }
        });

        // Données simulées
        ObservableList<Groupe> data = FXCollections.observableArrayList(
            new Groupe("POO Avancée", "CM", "CM-A", "Dr. Noulapeu", "A101", "Lun 08h-10h", "Inscrit"),
            new Groupe("POO Avancée", "TD", "TD-2", "M. Melvin", "B204", "Lun 10h-12h", "Inscrit"),
            new Groupe("Bases de données", "CM", "CM-A", "M. Dada Nelson", "A101", "Mer 08h-10h", "Inscrit"),
            new Groupe("Réseaux", "TP", "TP-1", "Dr. Malong", "Lab2", "Mar 14h-16h", "Inscrit"),
            new Groupe("Algorithmique", "TD", "TD-1", "Dr. Kikmo", "B205", "Jeu 10h-12h", "Inscrit")
        );
        groupesTable.setItems(data);
        statusLabel.setText(data.size() + " groupes chargés");
    }

    private void seDesinscrire(Groupe g) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Se désinscrire");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous vraiment vous désinscrire du groupe " + g.getGroupe() + " (" + g.getCours() + ") ?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // Logique de désinscription (supprimer de la base, rafraîchir le tableau, etc.)
                groupesTable.getItems().remove(g);
                statusLabel.setText(groupesTable.getItems().size() + " groupes chargés");
            }
        });
    }
}