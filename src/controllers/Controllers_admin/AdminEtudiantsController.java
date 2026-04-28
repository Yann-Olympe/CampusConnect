package controllers.Controllers_admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class AdminEtudiantsController {

    @FXML private TextField matriculeField;
    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private TextField emailField;
    @FXML private DatePicker dateNaissancePicker;
    @FXML private ComboBox<String> filiereCombo;
    @FXML private ComboBox<String> anneeCombo;
    @FXML private ComboBox<String> niveauCombo;

    @FXML private TextField searchField;
    @FXML private ComboBox<String> filterFiliereCombo;
    @FXML private ComboBox<String> filterNiveauCombo;

    @FXML private TableView<EtudiantItem> etudiantsTable;
    @FXML private TableColumn<EtudiantItem, String> colMatricule;
    @FXML private TableColumn<EtudiantItem, String> colNom;
    @FXML private TableColumn<EtudiantItem, String> colPrenom;
    @FXML private TableColumn<EtudiantItem, String> colEmail;
    @FXML private TableColumn<EtudiantItem, String> colDateNaissance;
    @FXML private TableColumn<EtudiantItem, String> colFiliere;
    @FXML private TableColumn<EtudiantItem, String> colAnnee;
    @FXML private TableColumn<EtudiantItem, String> colNiveau;

    private final ObservableList<EtudiantItem> masterData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colMatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDateNaissance.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        colFiliere.setCellValueFactory(new PropertyValueFactory<>("filiere"));
        colAnnee.setCellValueFactory(new PropertyValueFactory<>("annee"));
        colNiveau.setCellValueFactory(new PropertyValueFactory<>("niveau"));

        fillCombos();
        loadMockData();

        FilteredList<EtudiantItem> filtered = new FilteredList<>(masterData, p -> true);

        Runnable applyFilter = () -> {
            String keyword = searchField.getText() == null ? "" : searchField.getText().toLowerCase().trim();
            String filiere = filterFiliereCombo.getValue();
            String niveau = filterNiveauCombo.getValue();

            filtered.setPredicate(e -> {
                boolean matchSearch = keyword.isEmpty()
                        || e.getMatricule().toLowerCase().contains(keyword)
                        || e.getNom().toLowerCase().contains(keyword)
                        || e.getPrenom().toLowerCase().contains(keyword)
                        || e.getEmail().toLowerCase().contains(keyword);

                boolean matchFiliere = filiere == null || filiere.equals("Toutes") || e.getFiliere().equalsIgnoreCase(filiere);
                boolean matchNiveau = niveau == null || niveau.equals("Tous") || e.getNiveau().equalsIgnoreCase(niveau);

                return matchSearch && matchFiliere && matchNiveau;
            });
        };

        searchField.textProperty().addListener((obs, o, n) -> applyFilter.run());
        filterFiliereCombo.valueProperty().addListener((obs, o, n) -> applyFilter.run());
        filterNiveauCombo.valueProperty().addListener((obs, o, n) -> applyFilter.run());

        etudiantsTable.setItems(filtered);

        etudiantsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, selected) -> {
            if (selected != null) {
                matriculeField.setText(selected.getMatricule());
                nomField.setText(selected.getNom());
                prenomField.setText(selected.getPrenom());
                emailField.setText(selected.getEmail());
                dateNaissancePicker.setValue(LocalDate.parse(selected.getDateNaissance()));
                filiereCombo.setValue(selected.getFiliere());
                anneeCombo.setValue(selected.getAnnee());
                niveauCombo.setValue(selected.getNiveau());
            }
        });
    }

    private void fillCombos() {
        ObservableList<String> filieres = FXCollections.observableArrayList(
                "Génie Logiciel", "Réseaux", "Informatique", "Systèmes d'Information"
        );
        ObservableList<String> annees = FXCollections.observableArrayList(
                "1", "2", "3", "4", "5"
        );
        ObservableList<String> niveaux = FXCollections.observableArrayList(
                "L1", "L2", "L3", "M1", "M2"
        );

        filiereCombo.setItems(filieres);
        anneeCombo.setItems(annees);
        niveauCombo.setItems(niveaux);

        filterFiliereCombo.setItems(FXCollections.observableArrayList("Toutes", "Génie Logiciel", "Réseaux", "Informatique", "Systèmes d'Information"));
        filterFiliereCombo.setValue("Toutes");

        filterNiveauCombo.setItems(FXCollections.observableArrayList("Tous", "L1", "L2", "L3", "M1", "M2"));
        filterNiveauCombo.setValue("Tous");
    }

    private void loadMockData() {
        masterData.setAll(
                new EtudiantItem("CC2026001", "NGONO", "Paul", "paul.ngono@cc.edu", "2004-03-15", "Génie Logiciel", "3", "L3"),
                new EtudiantItem("CC2026002", "MBOA", "Sarah", "sarah.mboa@cc.edu", "2003-07-09", "Réseaux", "3", "L3"),
                new EtudiantItem("CC2026003", "KAMDEM", "Kevin", "kevin.kamdem@cc.edu", "2005-01-28", "Informatique", "2", "L2"),
                new EtudiantItem("CC2026004", "FOUDA", "Linda", "linda.fouda@cc.edu", "2002-11-12", "Génie Logiciel", "5", "M2"),
                new EtudiantItem("CC2026005", "ESSOMBA", "Bryan", "bryan.essomba@cc.edu", "2004-09-04", "Systèmes d'Information", "3", "L3")
        );
    }

    @FXML
    private void handleAjouter() {
        showInfo("Ajout étudiant", "Ajout simulé avec succès.");
    }

    @FXML
    private void handleModifier() {
        showInfo("Modification étudiant", "Modification simulée avec succès.");
    }

    @FXML
    private void handleSupprimer() {
        showInfo("Suppression étudiant", "Suppression simulée avec succès.");
    }

    @FXML
    private void handleReset() {
        matriculeField.clear();
        nomField.clear();
        prenomField.clear();
        emailField.clear();
        dateNaissancePicker.setValue(null);
        filiereCombo.setValue(null);
        anneeCombo.setValue(null);
        niveauCombo.setValue(null);
        etudiantsTable.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleRefresh() {
        searchField.clear();
        filterFiliereCombo.setValue("Toutes");
        filterNiveauCombo.setValue("Tous");
    }

    private void showInfo(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static class EtudiantItem {
        private final String matricule, nom, prenom, email, dateNaissance, filiere, annee, niveau;

        public EtudiantItem(String matricule, String nom, String prenom, String email,
                            String dateNaissance, String filiere, String annee, String niveau) {
            this.matricule = matricule;
            this.nom = nom;
            this.prenom = prenom;
            this.email = email;
            this.dateNaissance = dateNaissance;
            this.filiere = filiere;
            this.annee = annee;
            this.niveau = niveau;
        }

        public String getMatricule() { return matricule; }
        public String getNom() { return nom; }
        public String getPrenom() { return prenom; }
        public String getEmail() { return email; }
        public String getDateNaissance() { return dateNaissance; }
        public String getFiliere() { return filiere; }
        public String getAnnee() { return annee; }
        public String getNiveau() { return niveau; }
    }
}