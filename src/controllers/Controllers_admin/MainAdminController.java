package controllers.Controllers_admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainAdminController {

    @FXML private StackPane contentArea;
    @FXML private Label topTitleLabel;
    @FXML private Label topSubtitleLabel;

    @FXML private Button btnDashboard;
    @FXML private Button btnEtudiants;
    @FXML private Button btnEnseignants;
    @FXML private Button btnUtilisateurs;
    @FXML private Button btnCours;
    @FXML private Button btnGroupes;
    @FXML private Button btnSalles;
    @FXML private Button btnInscriptions;
    @FXML private Button btnPlannings;
    @FXML private Button btnConflits;
    @FXML private Button btnNotes;
    @FXML private Button btnMoyennes;
    @FXML private Button btnRapports;
    @FXML private Button btnStatistiques;
    @FXML private Button btnNotifications;
    @FXML private Button btnParametres;
    @FXML private Button btnProfil;
    @FXML private Button btnLogout;

    private List<Button> navButtons;

    @FXML
    public void initialize() {
        navButtons = new ArrayList<>();
        navButtons.add(btnDashboard);
        navButtons.add(btnEtudiants);
        navButtons.add(btnEnseignants);
        navButtons.add(btnUtilisateurs);
        navButtons.add(btnCours);
        navButtons.add(btnGroupes);
        navButtons.add(btnSalles);
        navButtons.add(btnInscriptions);
        navButtons.add(btnPlannings);
        navButtons.add(btnConflits);
        navButtons.add(btnNotes);
        navButtons.add(btnMoyennes);
        navButtons.add(btnRapports);
        navButtons.add(btnStatistiques);
        navButtons.add(btnNotifications);
        navButtons.add(btnParametres);
        navButtons.add(btnProfil);

        showDashboard();
    }

    private void setActiveButton(Button activeButton) {
        for (Button btn : navButtons) {
            if (btn == null) continue;
            btn.getStyleClass().removeAll("nav-btn-active");
            if (!btn.getStyleClass().contains("nav-btn")) {
                btn.getStyleClass().add("nav-btn");
            }
        }

        if (activeButton != null) {
            activeButton.getStyleClass().remove("nav-btn");
            if (!activeButton.getStyleClass().contains("nav-btn-active")) {
                activeButton.getStyleClass().add("nav-btn-active");
            }
        }
    }

    private void loadView(String fxmlName, String title, String subtitle, Button activeButton) {
        try {
            URL resource = getClass().getResource("/views/view/Dashboard_admin/" + fxmlName);

            if (resource == null) {
                System.err.println("FXML introuvable : /views/view/Dashboard_admin/" + fxmlName);
                return;
            }

            FXMLLoader loader = new FXMLLoader(resource);
            Parent view = loader.load();

            contentArea.getChildren().clear();
            contentArea.getChildren().add(view);

            if (topTitleLabel != null) topTitleLabel.setText(title);
            if (topSubtitleLabel != null) topSubtitleLabel.setText(subtitle);

            setActiveButton(activeButton);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showDashboard() {
        loadView("AdminDashboardView.fxml", "Tableau de bord administrateur",
                "Pilotage global de l'établissement", btnDashboard);
    }

    @FXML
    public void showEtudiants() {
        loadView("AdminEtudiantsView.fxml", "Gestion des étudiants",
                "Création, modification, suppression et consultation", btnEtudiants);
    }

    @FXML
    public void showEnseignants() {
        loadView("AdminEnseignantsView.fxml", "Gestion des enseignants",
                "Administration du corps enseignant et affectations", btnEnseignants);
    }

    @FXML
    public void showUtilisateurs() {
        loadView("AdminUtilisateursView.fxml", "Utilisateurs / Comptes",
                "Gestion des accès et des rôles système", btnUtilisateurs);
    }

    @FXML
    public void showCours() {
        loadView("AdminCoursView.fxml", "Gestion des cours",
                "Offre de formation et responsables pédagogiques", btnCours);
    }

    @FXML
    public void showGroupes() {
        loadView("AdminGroupesView.fxml", "Gestion des groupes",
                "Organisation CM / TD / TP et affectations", btnGroupes);
    }

    @FXML
    public void showSalles() {
        loadView("AdminSallesView.fxml", "Gestion des salles",
                "Capacité, type et occupation des salles", btnSalles);
    }

    @FXML
    public void showInscriptions() {
        loadView("AdminInscriptionsView.fxml", "Gestion des inscriptions",
                "Affectation des étudiants aux groupes", btnInscriptions);
    }

    @FXML
    public void showPlannings() {
        loadView("AdminPlanningsView.fxml", "Gestion des plannings",
                "Planification des séances académiques", btnPlannings);
    }

    @FXML
    public void showConflits() {
        loadView("AdminConflitsView.fxml", "Gestion des conflits",
                "Détection et résolution des conflits horaires", btnConflits);
    }

    @FXML
    public void showNotes() {
        loadView("AdminNotesView.fxml", "Consultation des notes",
                "Suivi des évaluations et résultats", btnNotes);
    }

    @FXML
    public void showMoyennes() {
        loadView("AdminMoyennesView.fxml", "Moyennes / Résultats",
                "Analyse académique globale des étudiants", btnMoyennes);
    }

    @FXML
    public void showRapports() {
        loadView("AdminRapportsView.fxml", "Rapports exportables",
                "Production de documents académiques", btnRapports);
    }

    @FXML
    public void showStatistiques() {
        loadView("AdminStatistiquesView.fxml", "Statistiques académiques",
                "Indicateurs et graphiques de pilotage", btnStatistiques);
    }

    @FXML
    public void showNotifications() {
        loadView("AdminNotificationsView.fxml", "Notifications / Alertes",
                "Suivi des événements critiques du système", btnNotifications);
    }

    @FXML
    public void showParametres() {
        loadView("AdminParametresView.fxml", "Paramètres système",
                "Configuration institutionnelle et académique", btnParametres);
    }

    @FXML
    public void showProfil() {
        loadView("AdminProfilView.fxml", "Mon profil administrateur",
                "Informations personnelles et activité", btnProfil);
    }

    @FXML
    public void logout() {
        try {
            URL resource = getClass().getResource("/views/view/LoginView.fxml");
            if (resource == null) {
                System.err.println("LoginView.fxml introuvable");
                return;
            }

            Parent root = FXMLLoader.load(resource);
            contentArea.getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}