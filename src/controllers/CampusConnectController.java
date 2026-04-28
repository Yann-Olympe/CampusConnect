// CampusConnectController.java
package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class CampusConnectController implements Initializable {

    @FXML private Canvas heroRingCanvas;
    @FXML private Label avgGradeLabel;
    @FXML private Label sessionsTodayLabel;
    @FXML private Label assignmentsLabel;
    @FXML private Label generalAvgLabel;

    // Progress bars for notes
    @FXML private ProgressBar pooProgress;
    @FXML private ProgressBar algoProgress;
    @FXML private ProgressBar bddProgress;
    @FXML private ProgressBar reseauxProgress;
    @FXML private ProgressBar mathsProgress;

    @FXML private Label pooScore;
    @FXML private Label algoScore;
    @FXML private Label bddScore;
    @FXML private Label reseauxScore;
    @FXML private Label mathsScore;

    // Progress bars for groups
    @FXML private ProgressBar reseauxGroupProgress;
    @FXML private ProgressBar bddGroupProgress;
    @FXML private ProgressBar mathsGroupProgress;

    @FXML private Label reseauxGroupCapacity;
    @FXML private Label bddGroupCapacity;
    @FXML private Label mathsGroupCapacity;

    @FXML private VBox navContainer;

    private double currentAverage = 13.4;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        drawHeroRing(67); // 67% progress
        setupGrades();
        setupGroups();
        setupNavigation();
    }

    private void drawHeroRing(int percent) {
        GraphicsContext gc = heroRingCanvas.getGraphicsContext2D();
        double size = heroRingCanvas.getWidth();
        double center = size / 2;
        double radius = 26;
        double strokeWidth = 5;

        gc.clearRect(0, 0, size, size);
        gc.setStroke(Color.rgb(56, 139, 253, 0.1));
        gc.setLineWidth(strokeWidth);
        gc.strokeOval(center - radius, center - radius, radius * 2, radius * 2);

        double angle = 360 * percent / 100.0;
        double startAngle = -90;
        double endAngle = startAngle + angle;

        gc.setStroke(Color.rgb(88, 166, 255));
        gc.strokeArc(center - radius, center - radius, radius * 2, radius * 2, startAngle, angle, false);
    }

    private void setupGrades() {
        // POO: 15.2/20 = 0.76
        pooProgress.setProgress(0.76);
        pooScore.setText("15.2");
        // Algo: 14.4/20 = 0.72
        algoProgress.setProgress(0.72);
        algoScore.setText("14.4");
        // BDD: 13.6/20 = 0.68
        bddProgress.setProgress(0.68);
        bddScore.setText("13.6");
        // Réseaux: 12.4/20 = 0.62
        reseauxProgress.setProgress(0.62);
        reseauxScore.setText("12.4");
        // Maths: 11.5/20 = 0.575
        mathsProgress.setProgress(0.575);
        mathsScore.setText("11.5");

        avgGradeLabel.setText("13.4");
        generalAvgLabel.setText("13.4");
    }

    private void setupGroups() {
        // Réseaux TD1: 18/25 = 0.72
        reseauxGroupProgress.setProgress(0.72);
        reseauxGroupCapacity.setText("18/25");
        // BDD TP2: 10/25 = 0.40
        bddGroupProgress.setProgress(0.40);
        bddGroupCapacity.setText("10/25");
        // Maths CM: 106/120 ≈ 0.883
        mathsGroupProgress.setProgress(0.883);
        mathsGroupCapacity.setText("106/120");
    }

    private void setupNavigation() {
        for (javafx.scene.Node node : navContainer.getChildren()) {
            if (node instanceof Label) {
                Label navItem = (Label) node;
                navItem.setOnMouseClicked(event -> {
                    clearNavSelection();
                    navItem.getStyleClass().add("nav-item-selected");
                });
            }
        }
        // Sélection par défaut: Vue d'ensemble
        if (!navContainer.getChildren().isEmpty()) {
            ((Label) navContainer.getChildren().get(0)).getStyleClass().add("nav-item-selected");
        }
    }

    private void clearNavSelection() {
        for (javafx.scene.Node node : navContainer.getChildren()) {
            if (node instanceof Label) {
                node.getStyleClass().remove("nav-item-selected");
            }
        }
    }

    @FXML
    private void handleInscriptionReseaux() {
        showAlert("Inscription", "Inscription au groupe Réseaux TD1 confirmée !");
    }

    @FXML
    private void handleInscriptionBDD() {
        showAlert("Inscription", "Inscription au groupe BDD TP2 confirmée !");
    }

    @FXML
    private void handleInscriptionMaths() {
        showAlert("Inscription", "Inscription au groupe Mathématiques CM confirmée !");
    }

    @FXML
    private void handleLogout() {
        showAlert("Déconnexion", "Vous avez été déconnecté.");
    }

    @FXML
    private void handleNotification() {
        showAlert("Notifications", "Aucune nouvelle notification.");
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}