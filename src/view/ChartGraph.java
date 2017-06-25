package view;

import java.util.Iterator;
import java.util.Optional;

import cellularData.CellularDataCountry;
import cellularData.DataModelCD;
import cellularData.LinkedList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import lifeExpectancyAtBirth.DataModelLE;
import lifeExpectancyAtBirth.LifeExpectancyCountry;

/**
 * Instantiates an JavaFX application which creates a model of the data. Uses
 * the model to instantiate an object of type javafx.scene.chart.LineChart via
 * the GraphView class. Then sets up the scene with basic modification to the
 * stage.
 *
 * @author Foothill College, Zerong Li (Jerry), Qianli Li
 */
public class ChartGraph extends Application {
    private Stage theStage;
    private LinkedList<CellularDataCountry> selectedCDCountries;
    private lifeExpectancyAtBirth.LinkedList<LifeExpectancyCountry> selectedLECountries;

    /**
     * Called by launch method of Application
     *
     * @param stage: Stage
     */
    @Override
    public void start(Stage stage) {
        this.theStage = stage;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Choice data set");
        alert.setHeaderText("TEAM 03\nWelcome to data graph\t\t\t");
        alert.setContentText("Choose your option.");
        alert.setGraphic(null);

        ButtonType cellularDataButton = new ButtonType("Cellular Data");
        ButtonType lifeExpectancyButton = new ButtonType("Life Expectancy");

        alert.getButtonTypes().setAll(cellularDataButton, lifeExpectancyButton);
        stage.setTitle("GraphView Test");
        Optional<ButtonType> result1 = alert.showAndWait();
        if (result1.get() == cellularDataButton) {
            stage.setScene(subscriptionScene());
        } else if (result1.get() == lifeExpectancyButton) {
            stage.setScene(expectancyScene());
        }
    }

    /**
     * Method layout() creates a BorderPane that contains
     * all elements in the each graph should have
     *
     * @return borderPane   [a borderPane contains all elements]
     * @author ZerongLi, Qianli Li
     */
    public BorderPane layout() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20));
        borderPane.setLeft(LeftButton()); // places the switching buttons
        return borderPane;
    }

    /**
     * Method switchGraphButton() creates two buttons which are Subscription
     * and Birth Expectancy
     *
     * @return vBox     [a pane that contains two vertical buttons]
     * @author Zerong Li, Qianli Li
     */
    public VBox LeftButton() {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(30,10,10,10));
        Button btS = new Button("Subscription");
        Button btE = new Button("Birth Expectancy");
        vBox.getChildren().addAll(btS, btE);
        vBox.setSpacing(15);
        btS.setOnAction(e -> subscriptionScene());
        btE.setOnAction(e -> expectancyScene());
        Button btd = new Button("Data Analysis");
        vBox.getChildren().add(btd);
        btd.setOnAction(e -> new DataAnalysisWindow(this.selectedCDCountries, this.selectedLECountries));
        return vBox;
    }

    /**
     * Method subscriptionScene() returns the a scene that
     * contains the subscription graph
     *
     * @return scene
     * @author Zerong Li, Qianli Li
     */
    public Scene subscriptionScene() {
        DataModelCD model = new DataModelCD();
        GraphView graphView = new GraphView(model, UserInputWindow());
        graphView.update();

        if (this.selectedLECountries == null) { // random generate a Life expectancy chart for calculating
            DataModelLE model2 = new DataModelLE();
            GraphView2 graphView2 = new GraphView2(model2, 5);
            graphView2.update();
            this.selectedLECountries = graphView2.getSelectedCountries();
        }
        this.selectedCDCountries = graphView.getSelectedCountries();

        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(10));
        BorderPane borderPane = layout();
        borderPane.setCenter(graphView);
        borderPane.setRight(vBox);

        Iterator<CellularDataCountry> itr = selectedCDCountries.iterator();
        CheckBox[] allCheckBoxes = new CheckBox[selectedCDCountries.size()];
        int checkBoxCount = 0;
        while (itr.hasNext()) {
            CellularDataCountry current = itr.next(); // creates current object
            allCheckBoxes[checkBoxCount] = new CheckBox(current.getName()); // creates new CheckBox
            vBox.getChildren().add(allCheckBoxes[checkBoxCount]);
            checkBoxCount++;
        }
        new MouseClickGraphLine().CellularDataLine(borderPane, allCheckBoxes, selectedCDCountries);

        Scene scene = new Scene(borderPane, 1000, 600);
        this.theStage.setScene(scene);
        // Set the stage title
        this.theStage.setTitle("GraphView Test");
        // Display the stage
        this.theStage.show();
        return scene;
    }

    /**
     * Method expectancyScene() returns the a scene that
     * contains the life expectancy graph
     *
     * @return scene
     * @author Zerong Li, Qianli Li
     */
    public Scene expectancyScene() {
        DataModelLE model = new DataModelLE();
        GraphView2 graphView = new GraphView2(model, UserInputWindow());
        graphView.update();

        if (this.selectedCDCountries == null) { // random generate a subscription chart for calculating
            DataModelCD model2 = new DataModelCD();
            GraphView graphView2 = new GraphView(model2, 5);
            graphView2.update();
            this.selectedCDCountries = graphView2.getSelectedCountries();
        }
        this.selectedLECountries = graphView.getSelectedCountries();

        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(10));
        BorderPane borderPane = layout();
        borderPane.setCenter(graphView);    // places the graph
        borderPane.setRight(vBox);

        Iterator<LifeExpectancyCountry> itr = selectedLECountries.iterator();
        CheckBox[] allCheckBoxes = new CheckBox[selectedLECountries.size()];
        int checkBoxCount = 0;
        while (itr.hasNext()) {
            LifeExpectancyCountry current = itr.next(); // creates current object
            allCheckBoxes[checkBoxCount] = new CheckBox(current.getName()); // creates new CheckBox
            vBox.getChildren().add(allCheckBoxes[checkBoxCount]);
            checkBoxCount++;
        }
        new MouseClickGraphLine().LifeExpectancyLine(borderPane, allCheckBoxes, selectedLECountries);

        Scene scene = new Scene(borderPane, 1000, 600);
        this.theStage.setScene(scene);
        // Set the stage title
        this.theStage.setTitle("GraphView Test");
        // Display the stage
        this.theStage.show();
        return scene;
    }

    /**
     * Method UserInputWindow() creates a windows and
     * ask the number of countries on the graph
     *
     * @return userInput
     * @author Zerong Li, Qianli Li
     */
    public int UserInputWindow() {
        TextInputDialog dialog = new TextInputDialog("5");
        dialog.setContentText("Please enter the number of countries on the graph:");
        dialog.setGraphic(null);
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(number -> System.out.println("request: " + number));
        Integer userInput = Integer.valueOf(result.get());
        return userInput;
    }

    /**
     * Launches a standalone JavaFx App
     */
    public static void main(String[] args) {
        launch();
    }
}