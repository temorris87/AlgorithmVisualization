package visualization;

import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public ComboBox <String> cbVisualizationType;
    public ComboBox <String> cbAlgorithm;
    public Button btnRunSimulation;
    public Canvas cnvAlgorithmCanvas;
    public ComboBox <Integer> cbSizeOfArray;

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
        cbAlgorithm.getSelectionModel().selectFirst();

        sim = new Simulation(cnvAlgorithmCanvas);
    }

    public void exeRunSimulation() {
        int size = cbSizeOfArray.getSelectionModel().getSelectedItem();
        sim.updateList(size);
        sim.updateCanvas();
    }
}
