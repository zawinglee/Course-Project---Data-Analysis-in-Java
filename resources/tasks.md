Tasks for Project 9 and Team 03
=====================================
Task Description:
Part A:
When we are not pair programming, first we discuss what two features the users might interested in and how to let the users aware of our features. After deciding the features, Zerong will write the paragraphs of the proposal and Qianli make the storyboard. Then, we dicuss the classes we need to create. Then, we decide to create 3 classes: MouseHoverOverShowCoordinate, MouseClickGraphLine, DataAnalysisWindow. Zerong will work on MouseHoverOverShowCoordinate and Qianli will work on MouseClickGraphLine. In synchronous pair programming side-by-side, we will work on DataAnalysisWindow together.
Part B:
Since we didn't have enought time, we designed to only create a data anlysis window and to hide/unhide the line of the country on the graph. When we are not pair programming, Zerong worked on class DataAnalysisWindow with constructor, getNumSubscriptionsForPeriod, getAverageNumExpectanciesForPeriod and inner class ResultWindow. Qianli will worked on class MouseClickGraphLine with cellularDataLine, lifeExpectancyLine and inner class CheckBoxHandler with CDCheckBoxAction,LECheckBoxAction. We didn't use the separate git Branches to commit our work. We all committed to the master git Branches.  We merged our work when we done the class.

Part A
---------

- task 1:
    - class ChartGraph
      - start [Zerong Li, Qianli Li]
      - subscriptionScene [Zerong Li, Qianli Li]
      - expectancyScene [Zerong Li, Qianli Li]
      - userInputWindow [Zerong Li, Qianli Li]
      - layout [Zerong Li, Qianli Li]
      - leftButton [Zerong Li, Qianli Li]


    - class GraphView
      - constructor(DataModelCD, int) [Zerong Li, Qianli Li]
      - constructor (LinkedList<CellularDataCountry>) [Zerong Li, Qianli Li]
      - update [Zerong Li, Qianli Li]
      - seriesFromCountry [Zerong Li, Qianli Li]

    - class DataAnalysisWindow
      - constructor [Zerong Li]
      - getNumSubscriptionsForPeriod [Zerong Li]
      - getAverageNumExpectanciesForPeriod [Zerong Li]
      - inner class resultWindow [Zerong Li]

    - class MouseClickGraphLine
      - cellularDataLine [Qianli Li]
      - lifeExpectancyLine [Qianli Li]
      - inner class CheckBoxHandler
        - CDCheckBoxAction [Zerong Li, Qianli Li]
        - LECheckBoxAction [Zerong Li, Qianli Li]


Part B
---------
- task 1:
    - class ChartGraph
      - start [Zerong Li, Qianli Li]
      - subscriptionScene [Zerong Li, Qianli Li]
      - expectancyScene [Zerong Li, Qianli Li]
      - userInputWindow [Zerong Li, Qianli Li]
      - layout [Zerong Li, Qianli Li]
      - leftButton [Zerong Li, Qianli Li]


    - class GraphView
      - constructor(DataModelCD, int) [Zerong Li, Qianli Li]
      - constructor (LinkedList<CellularDataCountry>) [Zerong Li, Qianli Li]
      - update [Zerong Li, Qianli Li]
      - seriesFromCountry [Zerong Li, Qianli Li]

    - class DataAnalysisWindow
      - constructor [Zerong Li]
      - getNumSubscriptionsForPeriod [Zerong Li]
      - getAverageNumExpectanciesForPeriod [Zerong Li]
      - inner class resultWindow [Zerong Li]

    - class MouseClickGraphLine
      - cellularDataLine [Qianli Li]
      - lifeExpectancyLine [Qianli Li]
      - inner class CheckBoxHandler
        - CDCheckBoxAction [Zerong Li, Qianli Li]
        - LECheckBoxAction [Zerong Li, Qianli Li]

    - PROJECTED COMPLETION DATE: June,25,2017
    - ACTUAL COMPLETION DATE TO github.com repo: June,25,2017


Extra Credit (if applicable)
-----------------------
In class demo.


Extra Credit Discussion (if applicable)
-----------------------
For the first synchronous programming, we worked on creating checkbox for hide and unhide the countries in the ChartGraph. We first created a VBox to hold the checkboxes. We used vBox.getChildren().add(new CheckBox(countryName)) in a while loop to create the checkboxes. However, we found that it's difficult to call the checkboxes. Then, we discussed what other ways we can try. We designed to make a LinkedList to hold the checkboxes. Then, we used pervious class LinkedList and Node to hold a list of checkboxes.
For another synchronous programming, we almost done everything, then we tried to improve our user interface. We tried to click on every buttons to make sure they worked, and we found that the users may not realized what the checkboxes were used for. Therefore, we added a description on the checkboxes to tell the user they were for hiding/ unhiding the line on the graph. And in the data analysis, we thought for the life expectancy we should calculate the average instead of the total, so we changed the life expectancy to calculate the average life expectancy during the selected period and subscription to calculate the total subscriptions during the selected period.
The synchronous programming is useful since we can learn many things from each other. And it also improved our teamwork skill and communication skill.
