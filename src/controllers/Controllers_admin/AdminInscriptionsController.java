package controllers.Controllers_admin;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminInscriptionsController {

    @FXML private ComboBox<String> etudiantCombo;
    @FXML private ComboBox<String> coursCombo;
    @FXML private ComboBox<String> groupeCombo;

    @FXML private TableView<InscriptionItem> inscriptionsTable;
    @FXML private TableColumn<InscriptionItem, String> colEtudiant;
    @FXML private TableColumn<InscriptionItem, String> colMatricule;
    @FXML private TableColumn<InscriptionItem, String> colCours;
    @FXML private TableColumn<InscriptionItem, String> colGroupe;
    @FXML private TableColumn<InscriptionItem, String> colType;
    @FXML private TableColumn<InscriptionItem, String> colSalle;

    @FXML
    public void initialize() {
        colEtudiant.setCellValueFactory(new PropertyValueFactory<>("etudiant"));
        colMatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        colCours.setCellValueFactory(new PropertyValueFactory<>("cours"));
        colGroupe.setCellValueFactory(new PropertyValueFactory<>("groupe"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colSalle.setCellValueFactory(new PropertyValueFactory<>("salle"));

        etudiantCombo.setItems(FXCollections.observableArrayList(
                "CC2026001 - NGONO Paul",
                "CC2026002 - MBOA Sarah",
                "CC2026003 - KAMDEM Kevin",
                "CC2026004 - FOUDA Linda"
        ));

        coursCombo.setItems(FXCollections.observableArrayList(
                "POO Avancée", "Réseaux WAN", "Bases de Données"
        ));

        groupeCombo.setItems(FXCollections.observableArrayList(
                "TD1", "TD2", "TP1", "CM1"
        ));

        inscriptionsTable.setItems(FXCollections.observableArrayList(
                new InscriptionItem("NGONO Paul", "CC2026001", "POO Avancée", "TD1", "TD", "B-204"),
                new InscriptionItem("FOUDA Linda", "CC2026004", "POO Avancée", "TD2", "TD", "B-205"),
                new InscriptionItem("MBOA Sarah", "CC2026002", "Réseaux WAN", "TP1", "TP", "Lab-02"),
                new InscriptionItem("KAMDEM Kevin", "CC2026003", "Bases de Données", "CM1", "CM", "Amphi A")
        ));
    }

    @FXML
    private void handleInscrire() {
        showInfo("Inscription", "Inscription simulée avec vérification de capacité.");
    }

    @FXML
    private void handleRetirer() {
        showInfo("Retrait", "Retrait d'inscription simulé.");
    }

    private void showInfo(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }

    public static class InscriptionItem {
        private final String etudiant, matricule, cours, groupe, type, salle;

        public InscriptionItem(String etudiant, String matricule, String cours, String groupe, String type, String salle) {
            this.etudiant = etudiant;
            this.matricule = matricule;
            this.cours = cours;
            this.groupe = groupe;
            this.type = type;
            this.salle = salle;
        }

        public String getEtudiant() { return etudiant; }
        public String getMatricule() { return matricule; }
        public String getCours() { return cours; }
        public String getGroupe() { return groupe; }
        public String getType() { return type; }
        public String getSalle() { return salle; }
    }
}