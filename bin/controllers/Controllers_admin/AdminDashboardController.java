package controllers.Controllers_admin;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class AdminDashboardController {

    @FXML private Label totalEtudiantsLabel;
    @FXML private Label totalEnseignantsLabel;
    @FXML private Label totalCoursLabel;
    @FXML private Label totalConflitsLabel;

    @FXML private ListView<String> recentActivitiesList;

    @FXML private Label resumeFiliereLabel;
    @FXML private Label resumeNiveauLabel;
    @FXML private Label occupationSallesLabel;
    @FXML private Label tauxReussiteLabel;

    @FXML
    public void initialize() {
        totalEtudiantsLabel.setText("1 248");
        totalEnseignantsLabel.setText("86");
        totalCoursLabel.setText("54");
        totalConflitsLabel.setText("7");

        recentActivitiesList.setItems(FXCollections.observableArrayList(
                "Nouvel étudiant ajouté : CC20261248 - NANA Boris",
                "Cours INF401 créé : Architecture Logicielle",
                "Enseignant affecté au groupe TP2 - Réseaux",
                "Conflit détecté : salle Lab-02 occupée à 10h00",
                "Planning général mis à jour pour la semaine",
                "Rapport 'Liste étudiants par filière' généré"
        ));

        resumeFiliereLabel.setText("Répartition par filière : GL (32%), Réseaux (28%), Info (24%), SI (16%)");
        resumeNiveauLabel.setText("Répartition par niveau : L1 (25%), L2 (23%), L3 (21%), M1 (18%), M2 (13%)");
        occupationSallesLabel.setText("Occupation des salles : 78%");
        tauxReussiteLabel.setText("Taux de réussite global : 84.6%");
    }
}