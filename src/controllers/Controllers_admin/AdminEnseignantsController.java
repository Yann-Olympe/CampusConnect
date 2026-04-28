package controllers.Controllers_admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminEnseignantsController {

    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private TextField emailField;
    @FXML private ComboBox<String> statutCombo;
    @FXML private TextField departementField;
    @FXML private TextField specialiteField;
    @FXML private TextField telephoneField;

    @FXML private TextField searchField;
    @FXML private ComboBox<String> filterDepartementCombo;
    @FXML private ComboBox<String> filterStatutCombo;

    @FXML private TableView<EnseignantItem> enseignantsTable;
    @FXML private TableColumn<EnseignantItem, String> colNom;
    @FXML private TableColumn<EnseignantItem, String> colPrenom;
    @FXML private TableColumn<EnseignantItem, String> colEmail;
    @FXML private TableColumn<EnseignantItem, String> colDepartement;
    @FXML private TableColumn<EnseignantItem, String> colSpecialite;
    @FXML private TableColumn<EnseignantItem, String> colStatut;
    @FXML private TableColumn<EnseignantItem, Integer> colChargeHoraire;

    private final ObservableList<EnseignantItem> masterData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDepartement.setCellValueFactory(new PropertyValueFactory<>("departement"));
        colSpecialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        colStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        colChargeHoraire.setCellValueFactory(new PropertyValueFactory<>("chargeHoraire"));

        fillCombos();
        loadMockData();

        FilteredList<EnseignantItem> filtered = new FilteredList<>(masterData, p -> true);

        Runnable applyFilter = () -> {
            String keyword = searchField.getText() == null ? "" : searchField.getText().toLowerCase().trim();
            String dept = filterDepartementCombo.getValue();
            String statut = filterStatutCombo.getValue();

            filtered.setPredicate(e -> {
                boolean matchSearch = keyword.isEmpty()
                        || e.getNom().toLowerCase().contains(keyword)
                        || e.getPrenom().toLowerCase().contains(keyword)
                        || e.getEmail().toLowerCase().contains(keyword);

                boolean matchDept = dept == null || dept.equals("Tous") || e.getDepartement().equalsIgnoreCase(dept);
                boolean matchStatut = statut == null || statut.equals("Tous") || e.getStatut().equalsIgnoreCase(statut);

                return matchSearch && matchDept && matchStatut;
            });
        };

        searchField.textProperty().addListener((obs, o, n) -> applyFilter.run());
        filterDepartementCombo.valueProperty().addListener((obs, o, n) -> applyFilter.run());
        filterStatutCombo.valueProperty().addListener((obs, o, n) -> applyFilter.run());

        enseignantsTable.setItems(filtered);

        enseignantsTable.getSelectionModel().selectedItemProperty().addListener((obs, o, s) -> {
            if (s != null) {
                nomField.setText(s.getNom());
                prenomField.setText(s.getPrenom());
                emailField.setText(s.getEmail());
                statutCombo.setValue(s.getStatut());
                departementField.setText(s.getDepartement());
                specialiteField.setText(s.getSpecialite());
                telephoneField.setText(s.getTelephone());
            }
        });
    }

    private void fillCombos() {
        statutCombo.setItems(FXCollections.observableArrayList("Permanent", "Vacataire"));

        filterDepartementCombo.setItems(FXCollections.observableArrayList("Tous", "Informatique", "Télécoms", "Mathématiques"));
        filterDepartementCombo.setValue("Tous");

        filterStatutCombo.setItems(FXCollections.observableArrayList("Tous", "Permanent", "Vacataire"));
        filterStatutCombo.setValue("Tous");
    }

    private void loadMockData() {
        masterData.setAll(
                new EnseignantItem("TCHOUMI", "Kevin", "kevin.tchoumi@cc.edu", "Informatique", "Génie Logiciel", "Permanent", 18, "690000001"),
                new EnseignantItem("LELOUCHE", "Marc", "marc.lelouche@cc.edu", "Informatique", "Réseaux WAN", "Permanent", 16, "690000002"),
                new EnseignantItem("MENDO", "Alice", "alice.mendo@cc.edu", "Télécoms", "Sécurité Réseau", "Vacataire", 10, "690000003"),
                new EnseignantItem("NKOA", "Jean", "jean.nkoa@cc.edu", "Mathématiques", "Statistiques", "Permanent", 14, "690000004")
        );
    }

    @FXML private void handleAjouter() { showInfo("Ajout enseignant", "Ajout simulé avec succès."); }
    @FXML private void handleModifier() { showInfo("Modification enseignant", "Modification simulée avec succès."); }
    @FXML private void handleSupprimer() { showInfo("Suppression enseignant", "Suppression simulée avec succès."); }

    @FXML
    private void handleReset() {
        nomField.clear();
        prenomField.clear();
        emailField.clear();
        statutCombo.setValue(null);
        departementField.clear();
        specialiteField.clear();
        telephoneField.clear();
        enseignantsTable.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleRefresh() {
        searchField.clear();
        filterDepartementCombo.setValue("Tous");
        filterStatutCombo.setValue("Tous");
    }

    private void showInfo(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }

    public static class EnseignantItem {
        private final String nom, prenom, email, departement, specialite, statut, telephone;
        private final Integer chargeHoraire;

        public EnseignantItem(String nom, String prenom, String email, String departement,
                              String specialite, String statut, Integer chargeHoraire, String telephone) {
            this.nom = nom;
            this.prenom = prenom;
            this.email = email;
            this.departement = departement;
            this.specialite = specialite;
            this.statut = statut;
            this.chargeHoraire = chargeHoraire;
            this.telephone = telephone;
        }

        public String getNom() { return nom; }
        public String getPrenom() { return prenom; }
        public String getEmail() { return email; }
        public String getDepartement() { return departement; }
        public String getSpecialite() { return specialite; }
        public String getStatut() { return statut; }
        public Integer getChargeHoraire() { return chargeHoraire; }
        public String getTelephone() { return telephone; }
    }
}