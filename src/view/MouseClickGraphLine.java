package view;

import cellularData.CellularDataCountry;
import cellularData.LinkedList;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import lifeExpectancyAtBirth.LifeExpectancyCountry;

import java.util.Iterator;

/**
 * Class MouseClickGraphLine enable user to hide and show
 * a specific data line on the graph
 *
 * @author Zerong Li (Jerry), Qianli Li
 */
public class MouseClickGraphLine {
    private CheckBox[] allCheckboxes;
    private LinkedList<CellularDataCountry> selectedCDCountries;
    private lifeExpectancyAtBirth.LinkedList<LifeExpectancyCountry> selectedLECountries;
    private BorderPane borderPane;

    public void CellularDataLine(BorderPane root, CheckBox[] newCheckBox, LinkedList<CellularDataCountry> newLinkedList) {
        this.allCheckboxes = newCheckBox;
        this.selectedCDCountries = newLinkedList;
        this.borderPane = root;

        for (int i = 0; i < allCheckboxes.length; i++) {
            allCheckboxes[i].setSelected(true);
            allCheckboxes[i].setOnAction(e -> new CheckBoxHandler().CDCheckBoxAction());
        }

    }

    public void LifeExpectancyLine(BorderPane root, CheckBox[] newCheckBox, lifeExpectancyAtBirth.LinkedList<LifeExpectancyCountry> newLinkedList) {
        this.allCheckboxes = newCheckBox;
        this.selectedLECountries = newLinkedList;
        this.borderPane = root;

        for (int i = 0; i < allCheckboxes.length; i++) {
            allCheckboxes[i].setSelected(true);
            allCheckboxes[i].setOnAction(e -> new CheckBoxHandler().LECheckBoxAction());
        }
    }


    class CheckBoxHandler {
        public void CDCheckBoxAction() {
            LinkedList<CellularDataCountry> visibleCountriesList = new LinkedList<>();
            for (CheckBox x : allCheckboxes) {
                if (x.isSelected()) {
                    String currentCountryName = x.getText();
                    Iterator<CellularDataCountry> itr = selectedCDCountries.iterator();
                    while (itr.hasNext()) {
                        CellularDataCountry current = itr.next();
                        if (current.getName() == currentCountryName) {
                            visibleCountriesList.add(current);
                            break;
                        }
                    }
                }
            }

            GraphView graphView = new GraphView(visibleCountriesList);
            graphView.update2();
            borderPane.setCenter(graphView);

        }

        public void LECheckBoxAction() {
            lifeExpectancyAtBirth.LinkedList<LifeExpectancyCountry> visibleCountriesList = new lifeExpectancyAtBirth.LinkedList<>();
            for (CheckBox x : allCheckboxes) {
                if (x.isSelected()) {
                    String currentCountryName = x.getText();
                    Iterator<LifeExpectancyCountry> itr = selectedLECountries.iterator();
                    while (itr.hasNext()) {
                        LifeExpectancyCountry current = itr.next();
                        if (current.getName() == currentCountryName) {
                            visibleCountriesList.add(current);
                            break;
                        }
                    }
                }

                GraphView2 graphView = new GraphView2(visibleCountriesList);
                graphView.update2();
                borderPane.setCenter(graphView);

            }
        }
    }
}
