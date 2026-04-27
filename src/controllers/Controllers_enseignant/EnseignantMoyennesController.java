package controllers.Controllers_enseignant;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class EnseignantMoyennesController {

    @FXML private TableView<EtudiantResultat> classementTable;
    @FXML private TableColumn<EtudiantResultat, Integer> colRang;
    @FXML private TableColumn<EtudiantResultat, String> colMatricule, colNom, colPrenom, colAppreciation;
    @FXML private TableColumn<EtudiantResultat, Double> colMoyenne;

    public static class EtudiantResultat {
        private final SimpleIntegerProperty rang;
        private final SimpleStringProperty matricule, nom, prenom, appreciation;
        private final SimpleDoubleProperty moyenne;
        public EtudiantResultat(int rang, String mat, String nom, String prenom, double moy, String app) {
            this.rang = new SimpleIntegerProperty(rang);
            this.matricule = new SimpleStringProperty(mat);
            this.nom = new SimpleStringProperty(nom);
            this.prenom = new SimpleStringProperty(prenom);
            this.moyenne = new SimpleDoubleProperty(moy);
            this.appreciation = new SimpleStringProperty(app);
        }
        public int getRang() { return rang.get(); }
        public String getMatricule() { return matricule.get(); }
        public String getNom() { return nom.get(); }
        public String getPrenom() { return prenom.get(); }
        public double getMoyenne() { return moyenne.get(); }
        public String getAppreciation() { return appreciation.get(); }
    }

    @FXML
    public void initialize() {
        colRang.setCellValueFactory(new PropertyValueFactory<>("rang"));
        colMatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colMoyenne.setCellValueFactory(new PropertyValueFactory<>("moyenne"));
        colAppreciation.setCellValueFactory(new PropertyValueFactory<>("appreciation"));

        classementTable.setItems(FXCollections.observableArrayList(
            new EtudiantResultat(1, "21INF1234", "Dupont", "Jean", 15.5, "Très bien"),
            new EtudiantResultat(2, "21INF5678", "Fokam", "Marie", 14.2, "Bien"),
            new EtudiantResultat(3, "21INF3456", "Kouam", "Lionel", 12.8, "Assez bien")
        ));
    }
}