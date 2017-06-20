package view;

import cellularData.*;
import cellularData.LinkedList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lifeExpectancyAtBirth.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.*;
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
    private int maximumYear;

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

        ChoiceBox<String> empty = new ChoiceBox<>();
        empty.getItems().add("Please make selection first");
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
        for (int i = 0; i < allCDCountries.length; i++) {
            countryChoiceBox1.getItems().add(allCDCountries[i].getName());
        }

        ChoiceBox<String> countryChoiceBox2 = new ChoiceBox<>();
        DataModelLE dataModelLE = new DataModelLE();
        LifeExpectancyCountry[] allLECountries = dataModelLE.getLifeExpectancy();
        for (int i = 0; i < allLECountries.length; i++) {
            countryChoiceBox2.getItems().add(allLECountries[i].getName());
        }

        ChoiceBox<String> countryChoiceBox3 = new ChoiceBox<>();
        Iterator<CellularDataCountry> itr = this.selectedCDCountries.iterator();
        while(itr.hasNext()) {
            CellularDataCountry currentCountry = itr.next();
            countryChoiceBox3.getItems().add(currentCountry.getName());
        }

        ChoiceBox<String> countryChoiceBox4 = new ChoiceBox<>();
        Iterator<LifeExpectancyCountry> itr2 = this.selectedLECountries.iterator();
        while(itr2.hasNext()) {
            LifeExpectancyCountry currentCountry = itr2.next();
            countryChoiceBox4.getItems().add(currentCountry.getName());
        }

        ChoiceBox<Integer> fromYearChoiceBox = new ChoiceBox<>();
        ChoiceBox<Integer> toYearChoiceBox = new ChoiceBox<>();
        CDorLE.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue == "Total Subscription (All Countries)") {
                    secondRow.getChildren().removeAll(empty, countryChoiceBox2, countryChoiceBox3, countryChoiceBox4);
                    thirdRow.getChildren().removeAll(from, empty2, to, empty3, fromYearChoiceBox, toYearChoiceBox);
                    thirdRow.getChildren().addAll(from, empty2, to, empty3);
                    secondRow.getChildren().add(countryChoiceBox1);
                    countryChoiceBox1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable2, String oldValue, String newValue) {
                            int miniYear = 9999;
                            int maxYear = 0;
                            int i = 0;

                            for (; i < allCDCountries.length; i++) {
                                if (allCDCountries[i].getName() == newValue) {
                                    break;
                                }
                            }

                            if (allCDCountries[i].getMinYear() < miniYear) {
                                miniYear = allCDCountries[i].getMinYear();
                            }
                            if (allCDCountries[i].getMaxYear() > maxYear) {
                                maxYear = allCDCountries[i].getMaxYear();
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
                                    if (oldValue == null) {
                                        for (int i = newValue; i <= maximumYear; i++) {
                                            toYearChoiceBox.getItems().add(i);
                                        }
                                        thirdRow.getChildren().removeAll(empty3, toYearChoiceBox);
                                        thirdRow.getChildren().add(toYearChoiceBox);

                                    } else if (toYearChoiceBox.getItems().size() == 1) {
                                        toYearChoiceBox.getItems().remove(0);
                                        for (int i = newValue; i <= maximumYear; i++) {
                                            toYearChoiceBox.getItems().add(i);
                                        }
                                        thirdRow.getChildren().removeAll(empty3, toYearChoiceBox);
                                        thirdRow.getChildren().add(toYearChoiceBox);
                                    } else if (oldValue != null) {
                                        thirdRow.getChildren().removeAll(toYearChoiceBox, empty3);
                                        thirdRow.getChildren().add(empty3);
                                        if (oldValue > newValue || newValue > oldValue) {
                                            for (int i = oldValue; i < maximumYear; i++) {
                                                int size = toYearChoiceBox.getItems().size();
                                                toYearChoiceBox.getItems().remove(0, size);
                                            }
                                            for (int i = newValue; i <= maximumYear; i++) {
                                                toYearChoiceBox.getItems().add(i);
                                            }
                                            thirdRow.getChildren().remove(empty3);
                                            thirdRow.getChildren().add(toYearChoiceBox);
                                        }
                                    }
                                }
                            });
                        }
                    });
                } else if (newValue == "Average Life Expectancy (All Countries)") {
                    secondRow.getChildren().removeAll(empty, countryChoiceBox1, countryChoiceBox3, countryChoiceBox4);
                    thirdRow.getChildren().removeAll(from, empty2, to, empty3, fromYearChoiceBox, toYearChoiceBox);
                    thirdRow.getChildren().addAll(from, empty2, to, empty3);
                    secondRow.getChildren().add(countryChoiceBox2);
                    countryChoiceBox2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                            int miniYear = 9999;
                            int maxYear = 0;
                            int i = 0;

                            for (; i < allLECountries.length; i++) {
                                if (allLECountries[i].getName() == newValue) {
                                    break;
                                }
                            }

                            if (allLECountries[i].getMinYear() < miniYear) {
                                miniYear = allLECountries[i].getMinYear();
                            }
                            if (allLECountries[i].getMaxYear() > maxYear) {
                                maxYear = allLECountries[i].getMaxYear();
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
                                    if (oldValue == null) {
                                        for (int i = newValue; i <= maximumYear; i++) {
                                            toYearChoiceBox.getItems().add(i);
                                        }
                                        thirdRow.getChildren().removeAll(empty3, toYearChoiceBox);
                                        thirdRow.getChildren().add(toYearChoiceBox);

                                    } else if (toYearChoiceBox.getItems().size() == 1) {
                                        toYearChoiceBox.getItems().remove(0);
                                        for (int i = newValue; i <= maximumYear; i++) {
                                            toYearChoiceBox.getItems().add(i);
                                        }
                                        thirdRow.getChildren().removeAll(empty3, toYearChoiceBox);
                                        thirdRow.getChildren().add(toYearChoiceBox);
                                    } else if (oldValue != null) {
                                        thirdRow.getChildren().removeAll(toYearChoiceBox, empty3);
                                        thirdRow.getChildren().add(empty3);
                                        if (oldValue > newValue || newValue > oldValue) {
                                            for (int i = oldValue; i < maximumYear; i++) {
                                                int size = toYearChoiceBox.getItems().size();
                                                toYearChoiceBox.getItems().remove(0, size);
                                            }
                                            for (int i = newValue; i <= maximumYear; i++) {
                                                toYearChoiceBox.getItems().add(i);
                                            }
                                            thirdRow.getChildren().remove(empty3);
                                            thirdRow.getChildren().add(toYearChoiceBox);
                                        }
                                    }
                                }
                            });
                        }
                    });
                } else if (newValue == "Total Subscription (Graph Only)") {
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

                            while(itr.hasNext()) {
                                CellularDataCountry currentCountry = itr.next();
                                if (currentCountry.getName() == newValue) {
                                    miniYear = currentCountry.getMinYear();
                                    maxYear = currentCountry.getMaxYear();
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
                                    if (oldValue == null) {
                                        for (int i = newValue; i <= maximumYear; i++) {
                                            toYearChoiceBox.getItems().add(i);
                                        }
                                        thirdRow.getChildren().removeAll(empty3, toYearChoiceBox);
                                        thirdRow.getChildren().add(toYearChoiceBox);

                                    } else if (toYearChoiceBox.getItems().size() == 1) {
                                        toYearChoiceBox.getItems().remove(0);
                                        for (int i = newValue; i <= maximumYear; i++) {
                                            toYearChoiceBox.getItems().add(i);
                                        }
                                        thirdRow.getChildren().removeAll(empty3, toYearChoiceBox);
                                        thirdRow.getChildren().add(toYearChoiceBox);
                                    } else if (oldValue != null) {
                                        thirdRow.getChildren().removeAll(toYearChoiceBox, empty3);
                                        thirdRow.getChildren().add(empty3);
                                        if (oldValue > newValue || newValue > oldValue) {
                                            for (int i = oldValue; i < maximumYear; i++) {
                                                int size = toYearChoiceBox.getItems().size();
                                                toYearChoiceBox.getItems().remove(0, size);
                                            }
                                            for (int i = newValue; i <= maximumYear; i++) {
                                                toYearChoiceBox.getItems().add(i);
                                            }
                                            thirdRow.getChildren().remove(empty3);
                                            thirdRow.getChildren().add(toYearChoiceBox);
                                        }
                                    }
                                }
                            });
                        }
                    });
                } else if (newValue == "Average Life Expectancy (Graph Only)") {
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

                            while(itr.hasNext()) {
                                LifeExpectancyCountry currentCountry = itr.next();
                                if (currentCountry.getName() == newValue) {
                                    miniYear = currentCountry.getMinYear();
                                    maxYear = currentCountry.getMaxYear();
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
                                    if (oldValue == null) {
                                        for (int i = newValue; i <= maximumYear; i++) {
                                            toYearChoiceBox.getItems().add(i);
                                        }
                                        thirdRow.getChildren().removeAll(empty3, toYearChoiceBox);
                                        thirdRow.getChildren().add(toYearChoiceBox);

                                    } else if (toYearChoiceBox.getItems().size() == 1) {
                                        toYearChoiceBox.getItems().remove(0);
                                        for (int i = newValue; i <= maximumYear; i++) {
                                            toYearChoiceBox.getItems().add(i);
                                        }
                                        thirdRow.getChildren().removeAll(empty3, toYearChoiceBox);
                                        thirdRow.getChildren().add(toYearChoiceBox);
                                    } else if (oldValue != null) {
                                        thirdRow.getChildren().removeAll(toYearChoiceBox, empty3);
                                        thirdRow.getChildren().add(empty3);
                                        if (oldValue > newValue || newValue > oldValue) {
                                            for (int i = oldValue; i < maximumYear; i++) {
                                                int size = toYearChoiceBox.getItems().size();
                                                toYearChoiceBox.getItems().remove(0, size);
                                            }
                                            for (int i = newValue; i <= maximumYear; i++) {
                                                toYearChoiceBox.getItems().add(i);
                                            }
                                            thirdRow.getChildren().remove(empty3);
                                            thirdRow.getChildren().add(toYearChoiceBox);
                                        }
                                    }
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
        Button btOK = new Button("OK");
        Button btCancel = new Button("Close");
        btOK.setOnAction(e -> new resultWindow().result());
        btCancel.setOnAction(event -> stage.close());
        lastRow.getChildren().addAll(btOK, btCancel);

        root.getChildren().addAll(vbox, lastRow);

        AnchorPane.setTopAnchor(vbox, 5d);
        AnchorPane.setLeftAnchor(vbox, 10d);
        AnchorPane.setBottomAnchor(lastRow, 100d);
        AnchorPane.setRightAnchor(lastRow, 50d);

        Scene scene = new Scene(root, 600, 500);
        stage.setScene(scene);
        stage.setTitle("Data Analysis");
        stage.show();
    }

    class getNumSubscriptionsForPeriod {

    }

    class getAverageNumExpectanciesForPeriod {

    }

    class resultWindow {
        public void result() {
            Stage stage = new Stage();
            AnchorPane root = new AnchorPane();

            Scene scene = new Scene(root, 500, 200);
            stage.setScene(scene);
            stage.setTitle("Result");
            stage.show();
        }
    }
}