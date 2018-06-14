package visualization;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import visualization.sorting.InsertionSortSimulation;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML Slider sldScansPerSecond;
    @FXML ComboBox <String> cbVisualizationType;
    @FXML ComboBox <String> cbAlgorithm;
    @FXML Button btnRunSimulation;
    @FXML Canvas cnvAlgorithmCanvas;
    @FXML ComboBox <Integer> cbSizeOfArray;

    private Simulation sim;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbVisualizationType.getItems().clear();
        cbVisualizationType.getItems().addAll("Sorting");
        cbVisualizationType.getSelectionModel().selectFirst();

        cbAlgorithm.getItems().clear();
        cbAlgorithm.getItems().addAll("Selection Sort");
        cbAlgorithm.getSelectionModel().selectFirst();

        cbSizeOfArray.getItems().clear();
        cbSizeOfArray.getItems().addAll(10, 25, 50, 100, 200, 400, 800);
        cbSizeOfArray.getSelectionModel().selectFirst();

        sim = new InsertionSortSimulation(cnvAlgorithmCanvas);
        sim.clearCanvas();
    }

    public void exeRunSimulation() {
        int size = cbSizeOfArray.getSelectionModel().getSelectedItem();
        sim.setScansPerSecond((int) sldScansPerSecond.getValue());
        sim.initializeData(size);
        sim.shuffleData();
        sim.start();
    }
}
