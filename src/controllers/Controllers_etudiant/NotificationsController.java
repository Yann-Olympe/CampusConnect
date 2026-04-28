package controllers.Controllers_etudiant;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class NotificationsController {

    @FXML private ComboBox<String> categorieCombo;
    @FXML private ComboBox<String> dateCombo;
    @FXML private ComboBox<String> statutCombo;
    @FXML private ListView<NotificationItem> notificationsList;
    @FXML private Label statusLabel;

    private ObservableList<NotificationItem> toutesNotifications;
    private ObservableList<NotificationItem> notificationsFiltrees;

    public static class NotificationItem {
        private final String titre;
        private final String message;
        private final String date;
        private final String categorie;
        private boolean lu;

        public NotificationItem(String titre, String message, String date, String categorie) {
            this.titre = titre;
            this.message = message;
            this.date = date;
            this.categorie = categorie;
            this.lu = false;
        }

        public String getTitre() { return titre; }
        public String getMessage() { return message; }
        public String getDate() { return date; }
        public String getCategorie() { return categorie; }
        public boolean isLu() { return lu; }
        public void setLu(boolean lu) { this.lu = lu; }
    }

    @FXML
    public void initialize() {
        // Données simulées
        toutesNotifications = FXCollections.observableArrayList(
            new NotificationItem("Note publiée", "Votre note de POO (TD) a été publiée. Note CC : 13.50/20", "Aujourd'hui 10:15", "Notes"),
            new NotificationItem("Séance déplacée", "La séance de Réseaux (TP-1) du 10/04 est déplacée. Nouvelle salle : Lab3", "Hier 16:40", "Planning"),
            new NotificationItem("Inscription confirmée", "Inscription confirmée au groupe TP-1 (Bases de Données).", "Hier 09:22", "Inscriptions"),
            new NotificationItem("Relevé disponible", "Relevé de notes du semestre S1 disponible. Vous pouvez le consulter.", "Hier 09:20", "Académiques"),
            new NotificationItem("Clôture des inscriptions", "Clôture des inscriptions dans 2 jours. Date limite : 10/04/2024", "Hier 09:18", "Inscriptions")
        );

        // CellFactory améliorée avec icône, survol et design moderne
        notificationsList.setCellFactory(lv -> new ListCell<NotificationItem>() {
            @Override
            protected void updateItem(NotificationItem item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                    setStyle("");
                } else {
                    VBox vbox = new VBox(8);
                    vbox.setPadding(new javafx.geometry.Insets(12));
                    vbox.setStyle("-fx-background-color: white; -fx-border-color: #E2E8F0; -fx-border-width: 0 0 1 0;");

                    // Effet de survol
                    this.setOnMouseEntered(e -> vbox.setStyle("-fx-background-color: #F8FAFC; -fx-border-color: #E2E8F0; -fx-border-width: 0 0 1 0;"));
                    this.setOnMouseExited(e -> vbox.setStyle("-fx-background-color: white; -fx-border-color: #E2E8F0; -fx-border-width: 0 0 1 0;"));

                    // Ligne d'en-tête : icône, titre, date
                    HBox header = new HBox(10);
                    header.setAlignment(Pos.CENTER_LEFT);

                    // Icône selon catégorie
                    ImageView icone = new ImageView();
                    icone.setFitWidth(20);
                    icone.setFitHeight(20);
                    switch (item.getCategorie()) {
                        case "Notes": icone.setImage(loadImage("📘")); break;
                        case "Planning": icone.setImage(loadImage("📅")); break;
                        case "Inscriptions": icone.setImage(loadImage("✏️")); break;
                        default: icone.setImage(loadImage("🔔"));
                    }
                    Label lblTitre = new Label(item.getTitre());
                    lblTitre.setStyle("-fx-font-weight: bold; -fx-text-fill: #1E293B; -fx-font-size: 14px;");
                    Region spacer = new Region();
                    HBox.setHgrow(spacer, Priority.ALWAYS);
                    Label lblDate = new Label(item.getDate());
                    lblDate.setStyle("-fx-text-fill: #64748B; -fx-font-size: 11px;");
                    header.getChildren().addAll(icone, lblTitre, spacer, lblDate);

                    // Message
                    Label lblMessage = new Label(item.getMessage());
                    lblMessage.setWrapText(true);
                    lblMessage.setStyle("-fx-text-fill: #475569; -fx-font-size: 13px;");

                    vbox.getChildren().addAll(header, lblMessage);

                    // Surbrillance si non lu
                    if (!item.isLu()) {
                        vbox.setStyle("-fx-background-color: #EFF6FF; -fx-border-color: #BFDBFE; -fx-border-width: 0 0 1 0;");
                        this.setOnMouseEntered(e -> vbox.setStyle("-fx-background-color: #DBEAFE; -fx-border-color: #BFDBFE; -fx-border-width: 0 0 1 0;"));
                        this.setOnMouseExited(e -> vbox.setStyle("-fx-background-color: #EFF6FF; -fx-border-color: #BFDBFE; -fx-border-width: 0 0 1 0;"));
                    }
                    setGraphic(vbox);
                }
            }
        });

        // Filtres
        categorieCombo.setItems(FXCollections.observableArrayList("Toutes", "Notes", "Planning", "Inscriptions", "Académiques"));
        dateCombo.setItems(FXCollections.observableArrayList("Toutes", "Aujourd'hui", "Cette semaine", "Ce mois"));
        statutCombo.setItems(FXCollections.observableArrayList("Tous", "Lu", "Non lu"));
        categorieCombo.getSelectionModel().selectFirst();
        dateCombo.getSelectionModel().selectFirst();
        statutCombo.getSelectionModel().selectFirst();

        notificationsFiltrees = FXCollections.observableArrayList();
        notificationsList.setItems(notificationsFiltrees);
        filtrer();
    }

    // Helper pour convertir un émoji en Image (alternative simple)
    private Image loadImage(String emoji) {
        // Dans une vraie application, utilisez de vraies images ; ici on fait un placeholder
        return new Image("https://emoji.gg/assets/emoji/" + emoji + ".png", true);
        // Si vous n'avez pas de connexion, utilisez une image locale
        // return new Image(getClass().getResourceAsStream("/icons/" + emoji + ".png"));
    }

    @FXML
    private void filtrer() {
        String categorie = categorieCombo.getValue();
        String statutFiltre = statutCombo.getValue();

        notificationsFiltrees.clear();
        for (NotificationItem n : toutesNotifications) {
            boolean catOk = categorie.equals("Toutes") || n.getCategorie().equals(categorie);
            boolean statutOk = statutFiltre.equals("Tous") ||
                    (statutFiltre.equals("Lu") && n.isLu()) ||
                    (statutFiltre.equals("Non lu") && !n.isLu());
            if (catOk && statutOk) notificationsFiltrees.add(n);
        }
        mettreAJourStatut();
    }

    @FXML
    private void marquerToutLu() {
        for (NotificationItem n : notificationsFiltrees) {
            n.setLu(true);
        }
        notificationsList.refresh();
        filtrer();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notifications");
        alert.setHeaderText(null);
        alert.setContentText("Toutes les notifications affichées ont été marquées comme lues.");
        alert.showAndWait();
    }

    private void mettreAJourStatut() {
        int total = notificationsFiltrees.size();
        long nonLus = notificationsFiltrees.stream().filter(n -> !n.isLu()).count();
        statusLabel.setText(total + " notification(s) - " + nonLus + " non lue(s)");
    }
}