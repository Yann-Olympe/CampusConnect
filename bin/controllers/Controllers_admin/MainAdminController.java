package controllers.Controllers_admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import java.io.IOException;

public class MainAdminController {

    @FXML private StackPane contentArea;
    @FXML private Label topTitleLabel;
    @FXML private Label topSubtitleLabel;

    // Boutons du menu
    @FXML private Button btnDashboard, btnEtudiants, btnEnseignants, btnUtilisateurs;
    @FXML private Button btnCours, btnGroupes, btnSalles, btnInscriptions;
    @FXML private Button btnPlannings, btnConflits, btnNotes, btnMoyennes;
    @FXML private Button btnRapports, btnStatistiques, btnNotifications, btnParametres;
    @FXML private Button btnProfil, btnLogout;

    @FXML
    public void initialize() {
        // Charger la vue par défaut
        showDashboard();
    }

    private void loadView(String fxml, String titre) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/view/Dashboard_admin/" + fxml));
            Parent view = loader.load();
            contentArea.getChildren().clear();
            contentArea.getChildren().add(view);
            if (topTitleLabel != null) topTitleLabel.setText(titre);
        } catch (IOException e) {
            e.printStackTrace();
            // Option : afficher un message d'erreur dans la zone centrale
            contentArea.getChildren().clear();
            Label error = new Label("Erreur de chargement : " + fxml);
            error.setStyle("-fx-text-fill: red; -fx-padding: 20;");
            contentArea.getChildren().add(error);
        }
    }

    // ========== ACTIONS DU MENU ==========
    @FXML public void showDashboard()    { loadView("DashboardAccueilAdminView.fxml", "Espace administrateur"); }
    @FXML public void showEtudiants()    { loadView("GestionEtudiantsView.fxml", "Espace administrateur"); }
    @FXML public void showEnseignants()  { loadView("GestionEnseignantsView.fxml", "Espace administrateur"); }
    @FXML public void showUtilisateurs() { loadView("GestionUtilisateursView.fxml", "Espace administrateur"); }
    @FXML public void showCours()        { loadView("GestionCoursView.fxml", "Espace administrateur"); }
    @FXML public void showGroupes()      { loadView("GestionGroupesView.fxml", "Espace administrateur"); }
    @FXML public void showSalles()       { loadView("GestionSallesView.fxml", "Espace administrateur"); }
    @FXML public void showInscriptions() { loadView("InscriptionsView.fxml", "Espace administrateur"); }
    @FXML public void showPlannings()    { loadView("PlanningsView.fxml", "Espace administrateur"); }
    @FXML public void showConflits()     { loadView("ConflitsView.fxml", "Espace administrateur"); }
    @FXML public void showNotes()        { loadView("NotesView.fxml", "Suivi des notes"); }
    @FXML public void showMoyennes()     { loadView("MoyennesView.fxml", "Espace administrateur"); }
    @FXML public void showRapports()     { loadView("RapportsView.fxml", "Espace administrateur"); }
    @FXML public void showStatistiques() { loadView("StatistiquesView.fxml", "Espace administrateur"); }
    @FXML public void showNotifications(){ loadView("NotificationsView.fxml", "Espace administrateur"); }
    @FXML public void showParametres()   { loadView("ParametresView.fxml", "Espace administrateur"); }
    @FXML public void showProfil()       { loadView("ProfilAdminView.fxml", "Espace administrateur"); }

    @FXML
    public void logout() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/view/AdminLoginView.fxml"));
            contentArea.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}