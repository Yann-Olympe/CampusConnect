package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import application.model.Role;
import application.model.Utilisateur;
import application.session.Session;

public class DashboardEtudiantController {

    // ========== ÉLÉMENTS DU FXML ==========
    @FXML private Label lblBienvenue;
    @FXML private Label lblCredits;
    @FXML private Label lblMoyenne;
    @FXML private Label lblCoursCount;
    @FXML private VBox vboxProchainsCours;
    @FXML private VBox vboxDernieresNotes;

    // Boutons du menu
    @FXML private Button btnDashboard;
    @FXML private Button btnCours;
    @FXML private Button btnEmploi;
    @FXML private Button btnNotes;
    @FXML private Button btnProfil;
    @FXML private Button btnLogout;

    // ========== INITIALISATION ==========
    @FXML
    public void initialize() {
        // Vérification de la session
        if (!Session.isLoggedIn() || Session.getCurrentUser().getRole() != Role.ETUDIANT) {
            redirectToLogin();
            return;
        }

        // Chargement des données utilisateur
        Utilisateur etudiant = Session.getCurrentUser();
        lblBienvenue.setText("Bienvenue, " + etudiant.getIdentifiant());

        // Chargement des données simulées (à remplacer par appel BDD)
        chargerCredits();
        chargerMoyenne();
        chargerCoursCount();
        chargerProchainsCours();
        chargerDernieresNotes();

        // Configuration des actions des boutons du menu
        configurerMenu();
    }

    // ========== CHARGEMENT DES DONNÉES (SIMULÉES) ==========
    private void chargerCredits() {
        lblCredits.setText("42");
    }

    private void chargerMoyenne() {
        lblMoyenne.setText("14.5");
    }

    private void chargerCoursCount() {
        lblCoursCount.setText("6");
    }

    private void chargerProchainsCours() {
        // Simulation d'une liste de cours
        String[] cours = {
            "Mathématiques - Salle 101 - Lundi 10h",
            "Physique - Salle 205 - Mardi 14h",
            "Informatique - Labo 3 - Mercredi 9h",
            "Anglais - Salle 12 - Jeudi 8h",
            "Réseaux - Salle 8 - Vendredi 13h"
        };
        for (String c : cours) {
            Label label = new Label(c);
            label.getStyleClass().add("cours-item");
            vboxProchainsCours.getChildren().add(label);
        }
    }

    private void chargerDernieresNotes() {
        // Simulation de notes
        String[] notes = {
            "Mathématiques : 15/20 (DS)",
            "Physique : 12/20 (TP)",
            "Informatique : 18/20 (Projet)",
            "Anglais : 14/20 (Devoir)",
            "Réseaux : 16/20 (Examen)"
        };
        for (String n : notes) {
            Label label = new Label(n);
            label.getStyleClass().add("note-item");
            vboxDernieresNotes.getChildren().add(label);
        }
    }

    // ========== CONFIGURATION DU MENU ==========
    private void configurerMenu() {
        // Par défaut, le bouton Tableau de bord est actif (style à gérer)
        // Les autres boutons chargeront les différentes vues
        btnDashboard.setOnAction(e -> refreshDashboard());
        btnCours.setOnAction(e -> chargerVue("/application/view/EtudiantCoursView.fxml"));
        btnEmploi.setOnAction(e -> chargerVue("/application/view/EtudiantEmploiView.fxml"));
        btnNotes.setOnAction(e -> chargerVue("/application/view/EtudiantNotesView.fxml"));
        btnProfil.setOnAction(e -> chargerVue("/application/view/EtudiantProfilView.fxml"));
    }

    private void refreshDashboard() {
        // Recharge les données (peut être vide pour l'instant)
        vboxProchainsCours.getChildren().clear();
        vboxDernieresNotes.getChildren().clear();
        chargerCredits();
        chargerMoyenne();
        chargerCoursCount();
        chargerProchainsCours();
        chargerDernieresNotes();
    }

    private void chargerVue(String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = (Stage) btnDashboard.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("CampusConnect - Étudiant");
        } catch (Exception e) {
            e.printStackTrace();
            // Option : afficher une alerte
        }
    }

    // ========== DÉCONNEXION ==========
    @FXML
    private void handleLogout() {
        Session.logout();
        redirectToLogin();
    }

    // ========== REDIRECTION VERS LA PAGE DE CONNEXION ==========
    private void redirectToLogin() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/application/view/LoginView.fxml"));
            Stage stage = (Stage) btnLogout.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("CampusConnect - Connexion");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}