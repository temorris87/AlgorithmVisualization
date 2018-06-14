package visualization;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import visualization.sorting.BubbleSortSimulation;
import visualization.sorting.SelectionSortSimulation;

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
        cbAlgorithm.getItems().addAll("Bubble Sort", "Selection Sort");
        cbAlgorithm.getSelectionModel().selectFirst();

        cbSizeOfArray.getItems().clear();
        cbSizeOfArray.getItems().addAll(10, 25, 50, 100, 200, 400, 800);
        cbSizeOfArray.getSelectionModel().selectFirst();
    }

    public void exeRunSimulation() {
        int size = cbSizeOfArray.getSelectionModel().getSelectedItem();

        if (this.sim != null && this.sim.isSimulationStarted())
            this.sim.stop();

        this.sim = getSimulation(cbVisualizationType.getValue(), cbAlgorithm.getValue());
        if (this.sim != null) {
            this.sim.clearCanvas();

            this.sim.setScansPerSecond((int) sldScansPerSecond.getValue());
            this.sim.initializeData(size);
            this.sim.shuffleData();
            this.sim.start();
        }
    }

    private Simulation getSimulation(String category, String algorithm) {
        switch (category) {
            case "Sorting":
                return getSortingSimulation(algorithm);
            default:
                return null;
        }
    }

    private Simulation getSortingSimulation(String algorithm) {
        switch (algorithm) {
            case "Bubble Sort":
                return new BubbleSortSimulation(cnvAlgorithmCanvas);
            case "Selection Sort":
                return new SelectionSortSimulation(cnvAlgorithmCanvas);
            default:
                return null;
        }
    }
}
