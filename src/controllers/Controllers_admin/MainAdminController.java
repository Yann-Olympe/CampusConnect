package controllers.Controllers_admin;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import java.io.IOException;

public class MainAdminController {

    @FXML private StackPane contentArea;
    @FXML private Label pageTitleLabel, adminNameLabel, adminRoleLabel, adminNameSidebar, adminRoleSidebar;
    @FXML private Button btnDashboard, btnEtudiants, btnEnseignants, btnCours, btnGroupes, btnSalles;
    @FXML private Button btnInscriptions, btnPlanning, btnSuivi, btnUtilisateurs, btnRapports, btnStatistiques, btnLogout;

    // ========== Champs pour DashboardAccueilAdminView ==========
    @FXML private Label currentDateLabel;
    @FXML private Label nbEtudiantsLabel, nbEnseignantsLabel, nbCoursLabel, nbGroupesLabel, nbSallesLabel;
    @FXML private Label nbConflitsLabel, nbSeancesLabel, tauxOccupationLabel;
    @FXML private PieChart pieChartFiliere;
    @FXML private BarChart<String, Number> barChartAnnee, barChartOccupation, barChartMoyennes;
    @FXML private ListView<String> conflitsListView, activitesListView;

    @FXML
    public void initialize() {
        // Mettre à jour la date
       
        // Charger la vue par défaut (tableau de bord)
        showDashboard();
    }

    private void loadView(String fxml) {
        try {
            contentArea.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/view/Dashboard_admin/" + fxml));
            contentArea.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
            // Optionnel : afficher une alerte à l'utilisateur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erreur de chargement : " + fxml + "\n" + e.getMessage());
            alert.show();
        }
    }

    @FXML public void showDashboard()   { loadView("DashboardAccueilAdminView.fxml"); pageTitleLabel.setText("Administration - Tableau de bord"); }
    @FXML public void showEtudiants()   { loadView("GestionEtudiantsView.fxml"); pageTitleLabel.setText("Administration - Gestion des étudiants"); }
    @FXML public void showEnseignants() { loadView("GestionEnseignantsView.fxml"); pageTitleLabel.setText("Administration - Gestion des enseignants"); }
    @FXML public void showCours()       { loadView("GestionCoursView.fxml"); pageTitleLabel.setText("Administration - Gestion des cours"); }
    @FXML public void showGroupes()     { loadView("GestionGroupesView.fxml"); pageTitleLabel.setText("Administration - Gestion des groupes"); }
    @FXML public void showSalles()      { loadView("GestionSallesView.fxml"); pageTitleLabel.setText("Administration - Gestion des salles"); }
    @FXML public void showInscriptions(){ loadView("InscriptionsView.fxml"); pageTitleLabel.setText("Administration - Inscriptions aux groupes"); }
    @FXML public void showPlanning()    { loadView("PlanningAdminView.fxml"); pageTitleLabel.setText("Administration - Planning général"); }
    @FXML public void showSuivi()       { loadView("SuiviAcademiqueView.fxml"); pageTitleLabel.setText("Administration - Suivi académique"); }
    @FXML public void showUtilisateurs(){ loadView("GestionUtilisateursView.fxml"); pageTitleLabel.setText("Administration - Gestion des comptes"); }
    @FXML public void showRapports()    { loadView("RapportsView.fxml"); pageTitleLabel.setText("Administration - Rapports exportables"); }
    @FXML public void showStatistiques(){ loadView("StatistiquesView.fxml"); pageTitleLabel.setText("Administration - Statistiques globales"); }

    @FXML
    public void logout() {
        try {
            javafx.scene.Parent root = FXMLLoader.load(getClass().getResource("/views/view/AdminLoginView.fxml"));
            contentArea.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}