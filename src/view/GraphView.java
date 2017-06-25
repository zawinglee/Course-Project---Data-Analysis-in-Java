package view;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.Iterator;

import cellularData.*;

/**
 * Define Class GraphView to create a data series for each Country object
 *
 * @author Zerong Li (Jerry), Qianli Li
 */

public class GraphView extends LineChart<Number, Number> {
    private DataModelCD model;
    private final NumberAxis xAxis;
    private final NumberAxis yAxis;
    private int size;
    private LinkedList<CellularDataCountry> selectedCountries;

    /**
     * Constructor of Class GraphView which takes the Country linked list as parameter
     * and setup the XY Axis of the chart
     *
     * @param newModel [accessor to the parsed data]
     * @author Zerong Li, Qianli Li
     */
    public GraphView(DataModelCD newModel, int newSize) {
        super(new NumberAxis(), new NumberAxis());
        super.setTitle("TEAM 03");
        this.xAxis = (NumberAxis) getXAxis();
        this.yAxis = (NumberAxis) getYAxis();
        this.xAxis.setLabel("Year");
        this.yAxis.setLabel("Subscription Rate");
        this.xAxis.setForceZeroInRange(false);
        this.model = newModel;
        this.size = newSize;
    }

    /**
     * Constructor of Class GraphView which takes the Country linked list as parameter
     * and setup the XY Axis of the chart
     *
     * @param newList [a list contains selected CellularDataCountry]
     * @author Zerong Li, Qianli Li
     */
    public GraphView(LinkedList<CellularDataCountry> newList) {
        super(new NumberAxis(), new NumberAxis());
        super.setTitle("TEAM 03");
        this.xAxis = (NumberAxis) getXAxis();
        this.yAxis = (NumberAxis) getYAxis();
        this.xAxis.setLabel("Year");
        this.yAxis.setLabel("Subscription Rate");
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
        CellularDataCountry[] cellularData = this.model.getCellularData(); // get all country data
        CountrySelector countrySelector = new CountrySelector(cellularData, this.size); // select some countries
        LinkedList<CellularDataCountry> selectedCountryList = countrySelector.selectCountries();
        this.selectedCountries = selectedCountryList;
        Iterator<CellularDataCountry> itr = selectedCountryList.iterator();
        while (itr.hasNext()) {
            CellularDataCountry selectCountry = itr.next();
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
        Iterator<CellularDataCountry> itr = selectedCountries.iterator();
        while (itr.hasNext()) {
            CellularDataCountry selectedCountry = itr.next();
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
    public Series<Number, Number> seriesFromCountry(CellularDataCountry newCountry) {
        CellularDataCountry currentCountry = newCountry;
        String name = currentCountry.getName();
        XYChart.Series<Number, Number> countrySeries = new XYChart.Series<Number, Number>();
        countrySeries.setName(name);
        LinkedList<SubscriptionYear> SubscriptionsLinkedList = currentCountry.getSubscription();
        Iterator<SubscriptionYear> itr = SubscriptionsLinkedList.iterator(); // Traversing in the SubscriptionYear list
        while (itr.hasNext()) {
            SubscriptionYear currentSubscriptionObject = itr.next();
            // draw a line with data set
            XYChart.Data<Number, Number> currentData = new XYChart.Data<Number, Number>(currentSubscriptionObject.getYear(), currentSubscriptionObject.getSubscriptions());
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
    public LinkedList<CellularDataCountry> getSelectedCountries() {
        return this.selectedCountries;
    }
}
