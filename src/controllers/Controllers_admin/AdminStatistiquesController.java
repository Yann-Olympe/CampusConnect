package controllers.Controllers_admin;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

public class AdminStatistiquesController {

    @FXML private BarChart<String, Number> filiereChart;
    @FXML private BarChart<String, Number> reussiteChart;

    @FXML
    public void initialize() {
        XYChart.Series<String, Number> filiereSeries = new XYChart.Series<>();
        filiereSeries.setName("Étudiants");
        filiereSeries.setData(FXCollections.observableArrayList(
                new XYChart.Data<>("GL", 400),
                new XYChart.Data<>("Réseaux", 350),
                new XYChart.Data<>("Info", 300),
                new XYChart.Data<>("SI", 198)
        ));

        XYChart.Series<String, Number> reussiteSeries = new XYChart.Series<>();
        reussiteSeries.setName("Taux (%)");
        reussiteSeries.setData(FXCollections.observableArrayList(
                new XYChart.Data<>("GL", 88),
                new XYChart.Data<>("Réseaux", 82),
                new XYChart.Data<>("Info", 79),
                new XYChart.Data<>("SI", 86)
        ));

        filiereChart.getData().clear();
        filiereChart.getData().add(filiereSeries);

        reussiteChart.getData().clear();
        reussiteChart.getData().add(reussiteSeries);

        filiereChart.setLegendVisible(false);
        reussiteChart.setLegendVisible(false);
    }
}