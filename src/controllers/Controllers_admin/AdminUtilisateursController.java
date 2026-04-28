package controllers.Controllers_admin;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminUtilisateursController {

    @FXML private TextField identifiantField;
    @FXML private PasswordField passwordField;
    @FXML private ComboBox<String> roleCombo;
    @FXML private ComboBox<String> statutCompteCombo;
    @FXML private TextField acteurAssocieField;

    @FXML private TableView<UserItem> utilisateursTable;
    @FXML private TableColumn<UserItem, String> colIdentifiant;
    @FXML private TableColumn<UserItem, String> colRole;
    @FXML private TableColumn<UserItem, String> colActeur;
    @FXML private TableColumn<UserItem, String> colStatut;
    @FXML private TableColumn<UserItem, String> colDerniereConnexion;

    @FXML
    public void initialize() {
        colIdentifiant.setCellValueFactory(new PropertyValueFactory<>("identifiant"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colActeur.setCellValueFactory(new PropertyValueFactory<>("acteur"));
        colStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        colDerniereConnexion.setCellValueFactory(new PropertyValueFactory<>("derniereConnexion"));

        roleCombo.setItems(FXCollections.observableArrayList("ETUDIANT", "ENSEIGNANT", "ADMIN"));
        statutCompteCombo.setItems(FXCollections.observableArrayList("ACTIF", "SUSPENDU", "BLOQUÉ"));

        utilisateursTable.setItems(FXCollections.observableArrayList(
                new UserItem("admin.cc", "ADMIN", "Admin CampusConnect", "ACTIF", "27/04/2026 18:20"),
                new UserItem("cc2026001", "ETUDIANT", "NGONO Paul", "ACTIF", "27/04/2026 16:12"),
                new UserItem("kevin.tchoumi", "ENSEIGNANT", "Kevin Tchoumi", "ACTIF", "27/04/2026 14:45"),
                new UserItem("marc.lelouche", "ENSEIGNANT", "Marc Lelouche", "SUSPENDU", "25/04/2026 09:10")
        ));

        utilisateursTable.getSelectionModel().selectedItemProperty().addListener((obs, o, s) -> {
            if (s != null) {
                identifiantField.setText(s.getIdentifiant());
                roleCombo.setValue(s.getRole());
                statutCompteCombo.setValue(s.getStatut());
                acteurAssocieField.setText(s.getActeur());
                passwordField.clear();
            }
        });
    }

    @FXML private void handleCreer() { showInfo("Création compte", "Compte créé (simulation)."); }
    @FXML private void handleModifier() { showInfo("Modification compte", "Compte modifié (simulation)."); }
    @FXML private void handleResetPassword() { showInfo("Réinitialisation", "Mot de passe réinitialisé (simulation)."); }
    @FXML private void handleDesactiver() { showInfo("Désactivation", "Compte désactivé (simulation)."); }

    private void showInfo(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }

    public static class UserItem {
        private final String identifiant, role, acteur, statut, derniereConnexion;

        public UserItem(String identifiant, String role, String acteur, String statut, String derniereConnexion) {
            this.identifiant = identifiant;
            this.role = role;
            this.acteur = acteur;
            this.statut = statut;
            this.derniereConnexion = derniereConnexion;
        }

        public String getIdentifiant() { return identifiant; }
        public String getRole() { return role; }
        public String getActeur() { return acteur; }
        public String getStatut() { return statut; }
        public String getDerniereConnexion() { return derniereConnexion; }
    }
}