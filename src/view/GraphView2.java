package view;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.Iterator;

import lifeExpectancyAtBirth.*;

/**
 * Define Class GraphView to create a data series for each Country object
 *
 * @author Zerong Li (Jerry), Qianli Li
 */

public class GraphView2 extends LineChart<Number, Number> {
    private DataModelLE model;
    private final NumberAxis xAxis;
    private final NumberAxis yAxis;
    private int size;
    private LinkedList<LifeExpectancyCountry> selectedCountries;

    /**
     * Constructor of Class GraphView which takes the Country linked list as parameter
     * and setup the XY Axis of the chart
     *
     * @param newModel [accessor to the parsed data]
     * @author Zerong Li, Qianli Li
     */
    public GraphView2(DataModelLE newModel, int newSize) {
        super(new NumberAxis(), new NumberAxis());
        super.setTitle("TEAM 03");
        this.xAxis = (NumberAxis) getXAxis();
        this.yAxis = (NumberAxis) getYAxis();
        this.xAxis.setLabel("Year");
        this.yAxis.setLabel("Life Expectancy At Birth");
        this.xAxis.setForceZeroInRange(false);
        this.model = newModel;
        this.size = newSize;
    }

    /**
     * Constructor of Class GraphView which takes the Country linked list as parameter
     * and setup the XY Axis of the chart
     *
     * @param newList [a list contains selected LifeExpectancyCountry]
     * @author Zerong Li, Qianli Li
     */
    public GraphView2(LinkedList<LifeExpectancyCountry> newList) {
        super(new NumberAxis(), new NumberAxis());
        super.setTitle("TEAM 03");
        this.xAxis = (NumberAxis) getXAxis();
        this.yAxis = (NumberAxis) getYAxis();
        this.xAxis.setLabel("Year");
        this.yAxis.setLabel("Life Expectancy At Birth");
        this.xAxis.setForceZeroInRange(false);
        this.selectedCountries = newList;
    }

    /**
     * Method update() will goes through the list of Country objects and
     * creates a series from each element
     *
     * @author Zerong Li, Qianli Li
     */
    public void update() {
        LifeExpectancyCountry[] lifeExpectancy = this.model.getLifeExpectancy(); // get all country data
        CountrySelector2 countrySelector = new CountrySelector2(lifeExpectancy, this.size); // select some countries
        LinkedList<LifeExpectancyCountry> selectedCountryList = countrySelector.selectCountries();
        this.selectedCountries = selectedCountryList;
        Iterator<LifeExpectancyCountry> itr = selectedCountryList.iterator();
        while (itr.hasNext()) {
            LifeExpectancyCountry selectCountry = itr.next();
            Series<Number, Number> currentSeries = this.seriesFromCountry(selectCountry);
            this.getData().add(currentSeries);
        }
    }

    /**
     * Method update2() will goes through the list of Country objects and
     * creates a series from each element
     *
     * @author Zerong Li, Qianli Li
     */
    public void update2() {
        Iterator<LifeExpectancyCountry> itr = selectedCountries.iterator();
        while (itr.hasNext()) {
            LifeExpectancyCountry selectedCountry = itr.next();
            Series<Number, Number> currentSeries = this.seriesFromCountry(selectedCountry);
            this.getData().add(currentSeries);
        }
    }

    /**
     * Method Series() takes one Country object as parameter and
     * returns a Series<Number,Number> object
     *
     * @param newCountry [the country object the will be added on the graph]
     * @return countrySeries    [the line that has been drawn]
     * @author Zerong Li, Qianli Li
     */
    public Series<Number, Number> seriesFromCountry(LifeExpectancyCountry newCountry) {
        LifeExpectancyCountry currentCountry = newCountry;
        String name = currentCountry.getName();
        XYChart.Series<Number, Number> countrySeries = new XYChart.Series<Number, Number>();
        countrySeries.setName(name);
        LinkedList<ExpectancyYear> SubscriptionsLinkedList = currentCountry.getSubscription();
        Iterator<ExpectancyYear> itr = SubscriptionsLinkedList.iterator(); // Traversing in the SubscriptionYear list
        while (itr.hasNext()) {
            ExpectancyYear currentSubscriptionObject = itr.next();
            // draw a line with data set
            XYChart.Data<Number, Number> currentData = new XYChart.Data<Number, Number>(currentSubscriptionObject.getYear(), currentSubscriptionObject.getExpectancies());
            countrySeries.getData().add(currentData);
        }
        return countrySeries;
    }

    /**
     * Accessor method for selected list of countries.
     *
     * @return LinkedList of Country objects
     * @author Zerong Li
     */
    public LinkedList<LifeExpectancyCountry> getSelectedCountries() {
        return this.selectedCountries;
    }
}
