package controllers.Controllers_enseignant;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class EnseignantEtudiantsController {

    @FXML private TableView<Etudiant> etudiantsTable;
    @FXML private TableColumn<Etudiant, String> colMatricule, colNom, colPrenom, colEmail, colAnnee;
    @FXML private TableColumn<Etudiant, Double> colMoyenne;
    @FXML private ComboBox<String> coursCombo, groupeCombo;
    @FXML private TextField searchField;

    public static class Etudiant {
        private final SimpleStringProperty matricule, nom, prenom, email, annee;
        private final SimpleDoubleProperty moyenne;
        public Etudiant(String mat, String nom, String prenom, String email, String annee, double moy) {
            this.matricule = new SimpleStringProperty(mat);
            this.nom = new SimpleStringProperty(nom);
            this.prenom = new SimpleStringProperty(prenom);
            this.email = new SimpleStringProperty(email);
            this.annee = new SimpleStringProperty(annee);
            this.moyenne = new SimpleDoubleProperty(moy);
        }
        public String getMatricule() { return matricule.get(); }
        public String getNom() { return nom.get(); }
        public String getPrenom() { return prenom.get(); }
        public String getEmail() { return email.get(); }
        public String getAnnee() { return annee.get(); }
        public double getMoyenne() { return moyenne.get(); }
    }

    @FXML
    public void initialize() {
        colMatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAnnee.setCellValueFactory(new PropertyValueFactory<>("annee"));
        colMoyenne.setCellValueFactory(new PropertyValueFactory<>("moyenne"));

        etudiantsTable.setItems(FXCollections.observableArrayList(
            new Etudiant("21INF1234", "Dupont", "Jean", "jean.dupont@campus.com", "L3", 14.2),
            new Etudiant("21INF5678", "Fokam", "Marie", "marie.fokam@campus.com", "L3", 15.5),
            new Etudiant("21MAT9012", "Ngana", "Paul", "paul.ngana@campus.com", "L2", 12.8),
            new Etudiant("21INF3456", "Kouam", "Lionel", "lionel.kouam@campus.com", "L3", 16.0)
        ));
    }
}