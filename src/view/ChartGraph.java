package view;

import java.util.Optional;

import cellularData.DataModelCD;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import lifeExpectancyAtBirth.DataModelLE;

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

        ButtonType buttonTypeOne = new ButtonType("Cellular Data");
        ButtonType buttonTypeTwo = new ButtonType("Life Expectancy");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
        stage.setTitle("GraphView Test");
        Optional<ButtonType> result1 = alert.showAndWait();
        if (result1.get() == buttonTypeOne) {
            stage.setScene(subscriptionScene());
        } else if (result1.get() == buttonTypeTwo) {
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

        AnchorPane right = new AnchorPane();
        right.getChildren().add(dataAnalysisButton()); // places the data analysis switchGraphButton

        AnchorPane left = new AnchorPane();
        left.getChildren().add(switchGraphButton()); // places the switching buttons

        borderPane.setLeft(left);
        borderPane.setRight(right);
        return borderPane;
    }
    /**
     * Method dataAnalysisButton() creates two buttons which are Subscription
     * and Birth Expectancy
     *
     * @return hBox     [a pane that contains the data analysis button]
     * @author Zerong Li, Qianli Li
     */
    public HBox dataAnalysisButton() {
        HBox hBox = new HBox();
        hBox.setSpacing(5);
        hBox.setAlignment(Pos.CENTER);
        Button btd = new Button("Data Analysis");
        hBox.getChildren().add(btd);
        return hBox;
    }

    /**
     * Method switchGraphButton() creates two buttons which are Subscription
     * and Birth Expectancy
     *
     * @return vBox     [a pane that contains two vertical buttons]
     * @author Zerong Li, Qianli Li
     */
    public VBox switchGraphButton() {
        VBox vBox = new VBox();
        Button btS = new Button("Subscription");
        Button btE = new Button("Birth Expectancy");
        vBox.getChildren().addAll(btS, btE);
        vBox.setSpacing(10);
        btS.setOnAction(e -> subscriptionScene());
        btE.setOnAction(e -> expectancyScene());
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
        BorderPane borderPane = layout();
        borderPane.setCenter(graphView);
        Scene scene = new Scene(borderPane, 600, 600);
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
        BorderPane borderPane = layout();
        borderPane.setCenter(graphView);    // places the graph
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