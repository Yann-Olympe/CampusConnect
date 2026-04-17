package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Charger le fichier FXML (chemin absolu depuis le classpath)
        Parent root = FXMLLoader.load(getClass().getResource("/application/view/LoginView.fxml"));
        
        // Créer la scène avec la racine chargée
        Scene scene = new Scene(root);
        
        // Configurer la fenêtre
        primaryStage.setTitle("CampusConnect - Connexion");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}