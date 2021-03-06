package view;

import cellularData.*;
import cellularData.LinkedList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import lifeExpectancyAtBirth.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.util.Iterator;

/**
 * Class DataAnalysisWindow calculates enable user to
 * calculate the average life expectancy within a period and
 * the total number of subscriptions within a period
 *
 * @author Zerong Li (Jerry), Qianli Li
 */
public class DataAnalysisWindow {
    private LinkedList<CellularDataCountry> selectedCDCountries;
    private lifeExpectancyAtBirth.LinkedList<LifeExpectancyCountry> selectedLECountries;
    private CellularDataCountry[] allCDCountries;
    private LifeExpectancyCountry[] allLECountries;
    private int startYear = 0;
    private int endYear = 0;
    private int minimumYear = 0;
    private int maximumYear = 0;
    private ChoiceBox<String> CDorLE;
    private ChoiceBox<String> countryChoiceBox1;
    private ChoiceBox<String> countryChoiceBox2;
    private ChoiceBox<String> countryChoiceBox3;
    private ChoiceBox<String> countryChoiceBox4;

    /**
     * Construct the Data Analysis Window
     *
     * @author Zerong Li, Qianli Li
     */
    public DataAnalysisWindow(LinkedList<CellularDataCountry> newCDList, lifeExpectancyAtBirth.LinkedList<LifeExpectancyCountry> newLEList) {
        Stage stage = new Stage();
        this.selectedCDCountries = newCDList;
        this.selectedLECountries = newLEList;
        AnchorPane root = new AnchorPane();
        root.setPadding(new Insets(20d));

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20d));

        Text title = new Text("Select the period that you want to calculate the data: ");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vbox.getChildren().add(title);

        HBox firstRow = new HBox(10);
        HBox secondRow = new HBox(10);
        HBox thirdRow = new HBox(10d);
        firstRow.setPadding(new Insets(10));
        secondRow.setPadding(new Insets(10));
        thirdRow.setPadding(new Insets(10));
        Separator separator = new Separator();
        Text title2 = new Text("I would like to calculate: ");
        Text title3 = new Text("Country: ");
        Text from = new Text("From");
        Text to = new Text("to");


        ObservableList<?> elements = FXCollections.observableArrayList("Total Subscription (All Countries)", "Average Life Expectancy (All Countries)", separator,
                "Total Subscription (Graph Only)", "Average Life Expectancy (Graph Only)");
        ChoiceBox<String> CDorLE = new ChoiceBox<>((ObservableList<String>) elements);
        this.CDorLE = CDorLE;

        ChoiceBox<String> empty = new ChoiceBox<>();
        empty.getItems().add("*Please make selection first*");
        empty.setValue("*Please make selection first*");
        secondRow.getChildren().addAll(title3, empty);
        ChoiceBox<String> empty2 = new ChoiceBox<>();
        empty2.getItems().add("****");
        empty2.setValue("****");
        ChoiceBox<String> empty3 = new ChoiceBox<>();
        empty3.getItems().add("****");
        empty3.setValue("****");
        thirdRow.getChildren().addAll(from, empty2, to, empty3);

        ChoiceBox<String> countryChoiceBox1 = new ChoiceBox<>();
        DataModelCD dataModelCD = new DataModelCD();
        CellularDataCountry[] allCDCountries = dataModelCD.getCellularData();
        this.allCDCountries = allCDCountries;
        for (int i = 0; i < allCDCountries.length; i++) {
            countryChoiceBox1.getItems().add(allCDCountries[i].getName());
        }
        this.countryChoiceBox1 = countryChoiceBox1;

        ChoiceBox<String> countryChoiceBox2 = new ChoiceBox<>();
        DataModelLE dataModelLE = new DataModelLE();
        LifeExpectancyCountry[] allLECountries = dataModelLE.getLifeExpectancy();
        this.allLECountries = allLECountries;
        for (int i = 0; i < allLECountries.length; i++) {
            countryChoiceBox2.getItems().add(allLECountries[i].getName());
        }
        this.countryChoiceBox2 = countryChoiceBox2;


        ChoiceBox<String> countryChoiceBox3 = new ChoiceBox<>();
        Iterator<CellularDataCountry> itr = this.selectedCDCountries.iterator();
        while (itr.hasNext()) {
            CellularDataCountry currentCountry = itr.next();
            countryChoiceBox3.getItems().add(currentCountry.getName());
        }
        this.countryChoiceBox3 = countryChoiceBox3;

        ChoiceBox<String> countryChoiceBox4 = new ChoiceBox<>();
        Iterator<LifeExpectancyCountry> itr2 = this.selectedLECountries.iterator();
        while (itr2.hasNext()) {
            LifeExpectancyCountry currentCountry = itr2.next();
            countryChoiceBox4.getItems().add(currentCountry.getName());
        }
        this.countryChoiceBox4 = countryChoiceBox4;

        ChoiceBox<Integer> fromYearChoiceBox = new ChoiceBox<>();
        ChoiceBox<Integer> toYearChoiceBox = new ChoiceBox<>();

        CDorLE.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (CDorLE.getSelectionModel().isSelected(0)) {
                    secondRow.getChildren().removeAll(empty, countryChoiceBox2, countryChoiceBox3, countryChoiceBox4);
                    thirdRow.getChildren().removeAll(from, empty2, to, empty3, fromYearChoiceBox, toYearChoiceBox);
                    thirdRow.getChildren().addAll(from, empty2, to, empty3);
                    secondRow.getChildren().add(countryChoiceBox1);
                    countryChoiceBox1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable2, String oldValue, String newValue) {
                            int miniYear = 9999;
                            int maxYear = 0;
                            int index = 0;

                            fromYearChoiceBox.getItems().clear();
                            for (; index < allCDCountries.length; index++) {
                                if (allCDCountries[index].getName() == newValue) {
                                    break;
                                }
                            }

                            if (allCDCountries[index].getMinYear() < miniYear) {
                                miniYear = allCDCountries[index].getMinYear();
                                minimumYear = miniYear;
                            }
                            if (allCDCountries[index].getMaxYear() > maxYear) {
                                maxYear = allCDCountries[index].getMaxYear();
                                maximumYear = maxYear;
                            }
                            for (int j = miniYear; j <= maxYear; j++) {
                                fromYearChoiceBox.getItems().add(j);
                            }
                            thirdRow.getChildren().removeAll(from, fromYearChoiceBox, to, toYearChoiceBox, empty2, empty3);
                            thirdRow.getChildren().addAll(from, fromYearChoiceBox, to, empty3);
                            fromYearChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
                                @Override
                                public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                                    toYearChoiceBox.getItems().clear();
                                    if (newValue != null) {
                                        for (int i = newValue; i <= maximumYear; i++) {
                                            toYearChoiceBox.getItems().add(i);
                                        }
                                        startYear = newValue;
                                        if (toYearChoiceBox.getItems().size() != 0) {
                                            toYearChoiceBox.getSelectionModel().selectLast();
                                            endYear = toYearChoiceBox.getSelectionModel().getSelectedItem();
                                        }
                                        thirdRow.getChildren().removeAll(empty3, toYearChoiceBox);
                                        thirdRow.getChildren().add(toYearChoiceBox);
                                    }
                                    toYearChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
                                        @Override
                                        public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                                            if (newValue != null) {
                                                endYear = newValue;
                                            }
                                        }
                                    });
                                }
                            });
                        }
                    });
                } else if (CDorLE.getSelectionModel().isSelected(1)) {
                    secondRow.getChildren().removeAll(empty, countryChoiceBox1, countryChoiceBox3, countryChoiceBox4);
                    thirdRow.getChildren().removeAll(from, empty2, to, empty3, fromYearChoiceBox, toYearChoiceBox);
                    thirdRow.getChildren().addAll(from, empty2, to, empty3);
                    secondRow.getChildren().add(countryChoiceBox2);
                    countryChoiceBox2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                            int miniYear = 9999;
                            int maxYear = 0;
                            int index = 0;

                            fromYearChoiceBox.getItems().clear();
                            for (; index < allLECountries.length; index++) {
                                if (allLECountries[index].getName() == newValue) {
                                    break;
                                }
                            }

                            if (allLECountries[index].getMinYear() < miniYear) {
                                miniYear = allLECountries[index].getMinYear();
                                minimumYear = miniYear;
                            }
                            if (allLECountries[index].getMaxYear() > maxYear) {
                                maxYear = allLECountries[index].getMaxYear();
                                maximumYear = maxYear;
                            }
                            for (int j = miniYear; j <= maxYear; j++) {
                                fromYearChoiceBox.getItems().add(j);
                            }
                            thirdRow.getChildren().removeAll(from, fromYearChoiceBox, to, toYearChoiceBox, empty2, empty3);
                            thirdRow.getChildren().addAll(from, fromYearChoiceBox, to, empty3);
                            fromYearChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
                                @Override
                                public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                                    toYearChoiceBox.getItems().clear();
                                    if (newValue != null) {
                                        for (int i = newValue; i <= maximumYear; i++) {
                                            toYearChoiceBox.getItems().add(i);
                                        }
                                        startYear = newValue;
                                        if (toYearChoiceBox.getItems().size() != 0) {
                                            toYearChoiceBox.getSelectionModel().selectLast();
                                            endYear = toYearChoiceBox.getSelectionModel().getSelectedItem();
                                        }
                                        thirdRow.getChildren().removeAll(empty3, toYearChoiceBox);
                                        thirdRow.getChildren().add(toYearChoiceBox);
                                    }
                                    toYearChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
                                        @Override
                                        public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                                            if (newValue != null) {
                                                endYear = newValue;
                                            }
                                        }
                                    });
                                }
                            });
                        }
                    });
                } else if (CDorLE.getSelectionModel().isSelected(3)) {
                    secondRow.getChildren().removeAll(empty, countryChoiceBox1, countryChoiceBox2, countryChoiceBox4);
                    thirdRow.getChildren().removeAll(from, empty2, to, empty3, fromYearChoiceBox, toYearChoiceBox);
                    thirdRow.getChildren().addAll(from, empty2, to, empty3);
                    secondRow.getChildren().add(countryChoiceBox3);
                    countryChoiceBox3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable2, String oldValue, String newValue) {
                            int miniYear = 9999;
                            int maxYear = 0;
                            Iterator<CellularDataCountry> itr = selectedCDCountries.iterator();
                            fromYearChoiceBox.getItems().clear();

                            while (itr.hasNext()) {
                                CellularDataCountry currentCountry = itr.next();
                                if (currentCountry.getName() == newValue) {
                                    miniYear = currentCountry.getMinYear();
                                    maxYear = currentCountry.getMaxYear();
                                    minimumYear = miniYear;
                                    maximumYear = maxYear;
                                }
                            }

                            for (int j = miniYear; j <= maxYear; j++) {
                                fromYearChoiceBox.getItems().add(j);
                            }
                            thirdRow.getChildren().removeAll(from, fromYearChoiceBox, to, toYearChoiceBox, empty2, empty3);
                            thirdRow.getChildren().addAll(from, fromYearChoiceBox, to, empty3);
                            fromYearChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
                                @Override
                                public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                                    toYearChoiceBox.getItems().clear();
                                    if (newValue != null) {
                                        for (int i = newValue; i <= maximumYear; i++) {
                                            toYearChoiceBox.getItems().add(i);
                                        }
                                        startYear = newValue;
                                        if (toYearChoiceBox.getItems().size() != 0) {
                                            toYearChoiceBox.getSelectionModel().selectLast();
                                            endYear = toYearChoiceBox.getSelectionModel().getSelectedItem();
                                        }
                                        thirdRow.getChildren().removeAll(empty3, toYearChoiceBox);
                                        thirdRow.getChildren().add(toYearChoiceBox);
                                    }
                                    toYearChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
                                        @Override
                                        public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                                            if (newValue != null) {
                                                endYear = newValue;
                                            }
                                        }
                                    });
                                }
                            });
                        }
                    });
                } else if (CDorLE.getSelectionModel().isSelected(4)) {
                    secondRow.getChildren().removeAll(empty, countryChoiceBox1, countryChoiceBox2, countryChoiceBox3);
                    thirdRow.getChildren().removeAll(from, empty2, to, empty3, fromYearChoiceBox, toYearChoiceBox);
                    thirdRow.getChildren().addAll(from, empty2, to, empty3);
                    secondRow.getChildren().add(countryChoiceBox4);
                    countryChoiceBox4.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable2, String oldValue, String newValue) {
                            int miniYear = 9999;
                            int maxYear = 0;
                            Iterator<LifeExpectancyCountry> itr = selectedLECountries.iterator();
                            fromYearChoiceBox.getItems().clear();

                            while (itr.hasNext()) {
                                LifeExpectancyCountry currentCountry = itr.next();
                                if (currentCountry.getName() == newValue) {
                                    miniYear = currentCountry.getMinYear();
                                    maxYear = currentCountry.getMaxYear();
                                    minimumYear = miniYear;
                                    maximumYear = maxYear;
                                }
                            }

                            for (int j = miniYear; j <= maxYear; j++) {
                                fromYearChoiceBox.getItems().add(j);
                            }
                            thirdRow.getChildren().removeAll(from, fromYearChoiceBox, to, toYearChoiceBox, empty2, empty3);
                            thirdRow.getChildren().addAll(from, fromYearChoiceBox, to, empty3);
                            fromYearChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
                                @Override
                                public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                                    toYearChoiceBox.getItems().clear();
                                    if (newValue != null) {
                                        for (int i = newValue; i <= maximumYear; i++) {
                                            toYearChoiceBox.getItems().add(i);
                                        }
                                        startYear = newValue;
                                        if (toYearChoiceBox.getItems().size() != 0) {
                                            toYearChoiceBox.getSelectionModel().selectLast();
                                            endYear = toYearChoiceBox.getSelectionModel().getSelectedItem();
                                        }
                                        thirdRow.getChildren().removeAll(empty3, toYearChoiceBox);
                                        thirdRow.getChildren().add(toYearChoiceBox);
                                    }
                                    toYearChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
                                        @Override
                                        public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                                            if (newValue != null) {
                                                endYear = newValue;
                                            }
                                        }
                                    });
                                }
                            });
                        }
                    });
                }
            }
        });

        firstRow.getChildren().addAll(title2, CDorLE);
        vbox.getChildren().addAll(firstRow, secondRow, thirdRow);
        secondRow.prefWidthProperty().bind(firstRow.widthProperty());
        thirdRow.prefWidthProperty().bind(firstRow.widthProperty());

        HBox lastRow = new HBox(10);
        Button btOK = new Button("OK (or press ENTER key)");
        Button btCancel = new Button("Close");
        btOK.setOnAction(e -> new resultWindow().result());
        btCancel.setOnAction(event -> stage.close());
        lastRow.getChildren().addAll(btOK, btCancel);

        root.getChildren().addAll(vbox, lastRow);
        vbox.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                new resultWindow().result();
            }
        });
        root.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ESCAPE)) {
               stage.close();
            }
        });

        AnchorPane.setTopAnchor(vbox, 5d);
        AnchorPane.setLeftAnchor(vbox, 10d);
        AnchorPane.setRightAnchor(vbox, 10d);
        AnchorPane.setBottomAnchor(vbox, 5d);
        AnchorPane.setBottomAnchor(lastRow, 50d);
        AnchorPane.setRightAnchor(lastRow, 75d);

        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Data Analysis");
        stage.show();
    }

    /**
     * Calculates the total number of subscriptions
     *
     * @author Zerong Li, Qianli Li
     */
    class getNumSubscriptionsForPeriod {
        public double getNumSubscription(CellularDataCountry newCountry, int yearI, int yearII) {
            double subsCount = 0;
            Iterator<SubscriptionYear> itr = newCountry.getSubscription().iterator();
            while (itr.hasNext()) {
                SubscriptionYear subYear = itr.next();
                int year = subYear.getYear();
                if (year >= yearI && year <= yearII)
                    subsCount += subYear.getSubscriptions();
                if (year > yearII)
                    break;
            }
            return subsCount;
        }
    }

    /**
     * Calculates the average life expectancy
     *
     * @author Zerong Li, Qianli Li
     */
    class getAverageNumExpectanciesForPeriod {
        public double getAvgLifeExpectancy(LifeExpectancyCountry newCountry, int yearI, int yearII) {
            double totalLifeExpectancy = 0;
            double averageLifeExpectancy;
            Iterator<ExpectancyYear> itr = newCountry.getSubscription().iterator();
            int yearCount = yearII - yearI + 1;
            while (itr.hasNext()) {
                ExpectancyYear subYear = itr.next();
                int year = subYear.getYear();
                if (year >= yearI && year <= yearII)
                    totalLifeExpectancy += subYear.getExpectancies();
                if (year > yearII)
                    break;
            }
            averageLifeExpectancy = totalLifeExpectancy / yearCount;
            return averageLifeExpectancy;
        }
    }

    /**
     * Constructs the window that shows result
     *
     * @author Zerong Li, Qianli Li
     */
    class resultWindow {
        public void result() {
            Stage stage = new Stage();
            double result = 0;
            String countryName = "";

            if (CDorLE.getSelectionModel().isSelected(0)) {
                CellularDataCountry selectedCountry = null;
                String selectedCountryName = countryChoiceBox1.getSelectionModel().selectedItemProperty().getValue();
                for (int i = 0; i < allCDCountries.length; i++) {
                    if (allCDCountries[i].getName() == selectedCountryName) {
                        selectedCountry = allCDCountries[i];
                        countryName = selectedCountry.getName();
                        break;
                    }
                }
                result = new getNumSubscriptionsForPeriod().getNumSubscription(selectedCountry, startYear, endYear);
            } else if (CDorLE.getSelectionModel().isSelected(1)) {
                LifeExpectancyCountry selectedCountry = null;
                String selectedCountryName = countryChoiceBox2.getSelectionModel().selectedItemProperty().getValue();
                for (int i = 0; i < allLECountries.length; i++) {
                    if (allLECountries[i].getName() == selectedCountryName) {
                        selectedCountry = allLECountries[i];
                        countryName = selectedCountry.getName();
                        break;
                    }
                }
                result = new getAverageNumExpectanciesForPeriod().getAvgLifeExpectancy(selectedCountry, startYear, endYear);
            } else if (CDorLE.getSelectionModel().isSelected(3)) {
                CellularDataCountry selectedCountry = null;
                String selectedCountryName = countryChoiceBox3.getSelectionModel().selectedItemProperty().getValue();
                Iterator<CellularDataCountry> itr = selectedCDCountries.iterator();
                while (itr.hasNext()) {
                    CellularDataCountry currentCountry = itr.next();
                    if (currentCountry.getName() == selectedCountryName) {
                        selectedCountry = currentCountry;
                        countryName = selectedCountry.getName();
                        break;
                    }
                }
                result = new getNumSubscriptionsForPeriod().getNumSubscription(selectedCountry, startYear, endYear);
            } else if (CDorLE.getSelectionModel().isSelected(4)) {
                LifeExpectancyCountry selectedCountry = null;
                String selectedCountryName = countryChoiceBox4.getSelectionModel().selectedItemProperty().getValue();
                Iterator<LifeExpectancyCountry> itr = selectedLECountries.iterator();
                while (itr.hasNext()) {
                    LifeExpectancyCountry currentCountry = itr.next();
                    if (currentCountry.getName() == selectedCountryName) {
                        selectedCountry = currentCountry;
                        countryName = selectedCountry.getName();
                        break;
                    }
                }
                result = new getAverageNumExpectanciesForPeriod().getAvgLifeExpectancy(selectedCountry, startYear, endYear);
            }

            AnchorPane root = new AnchorPane();
            HBox hBox1 = new HBox(10);
            HBox hBox2 = new HBox(10);
            VBox vbox = new VBox(10);
            hBox1.setPadding(new Insets(10d));
            hBox2.setPadding(new Insets(10d));
            vbox.setPadding(new Insets(20d));

            Text t0 = new Text("The total Subscription of ");
            Text t1 = new Text("The Average Life Expectancy of ");
            Text t2 = new Text(countryName);
            t2.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            t2.setFill(Color.BLUE);
            if (CDorLE.getSelectionModel().isSelected(0) || CDorLE.getSelectionModel().isSelected(3))
                hBox1.getChildren().addAll(t0, t2);
            else if (CDorLE.getSelectionModel().isSelected(1) || CDorLE.getSelectionModel().isSelected(4))
                hBox1.getChildren().addAll(t1, t2);


            Text from = new Text("from ");
            int start = startYear;
            String startYear = Integer.toString(start);
            Text yearI = new Text(startYear);
            int end = endYear;
            String endYear = Integer.toString(end);
            Text yearII = new Text(endYear);
            Text to = new Text(" to ");
            Text is = new Text(" is: ");

            String resultInString = String.format("%.2f", result);
            Text resultText = new Text(resultInString);
            hBox2.getChildren().addAll(from, yearI, to, yearII, is, resultText);
            Button BtClose = new Button("Close (or press ENTER key)");

            root.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ENTER)) {
                   stage.close();
                }
            });

            vbox.getChildren().addAll(hBox1, hBox2);
            root.getChildren().addAll(vbox, BtClose);
            BtClose.setOnAction(event -> stage.close());
            AnchorPane.setRightAnchor(BtClose, 8d);
            AnchorPane.setBottomAnchor(BtClose, 10d);

            Scene scene = new Scene(root, 550, 150);
            stage.setScene(scene);
            stage.setTitle("Result");
            stage.show();
        }
    }
}
