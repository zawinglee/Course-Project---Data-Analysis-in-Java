project folder:
team03-project09/

Brief description of submitted files:

 lib/javafx-dialogs-0.0.4.jar
 	- the external Dialogs library helps us creates a window that collect user input
 	
 lib/jfxrt.jar
 	- the external library that is given by instructor
 	
 src/cellularData/CellularDataCountry.java
	- Class CellularDataCountry stores the name of the country and pass the country name and
	- subscriptions to class SubscriptionYear for storing and sorting data proposes

 src/cellularData/CSVReader.java
	- Constructs class CSVReader scans data form CSV files
 	- uses data to form different kinds of arrays
  	- returns these arrays to main class

 src/cellularData/DataModelCD
	- Provides access to cellular CSV data

 src/cellularData/LinkedList.java
	- Creates a generic LinkedList class to implement the generic Iterable interface

 src/cellularData/Node.java
	- Create a java generic class Node and generate a linked list of that type of object

 src/cellularData/SubscriptionYear.java
	- Creates SubscriptionYear class that stores single subscription of a specific year

 src/lifeExpectancyAtBirth/LifeExpectancyCountry.java
	- Class LifeExpectancyCountry stores the name of the country and pass the country name and
 	- expectancies to class ExpectancyYear for storing and sorting data proposes

 src/lifeExpectancyAtBirth/CSVReader.java
	- Constructs class CSVReader scans data form CSV files
	- uses data to form different kinds of arrays
	- returns these arrays to main class

 src/lifeExpectancyAtBirth/DataModelLE
	- Provides access to lifeExpectancyAtBirth CSV data

 src/lifeExpectancyAtBirth/LinkedList.java
	- Creates a generic LinkedList class to implement the generic Iterable interface

 src/lifeExpectancyAtBirth/Node.java
	- Create a java generic class Node and generate a linked list of that type of object

 src/lifeExpectancyAtBirth/SubscriptionYear.java
	- Creates ExpectancyYear class that stores single expectancy of a specific year

 src/view/ChartGraph.java -> *class to start our Application*
	- Creates a model of the data
	- Uses the model to instantiate an object of type javafx.scene.chart.LineChart
	- via the GraphView class
	- Sets up the scene with basic modification to the stage

 src/view/CountrySelector.java
	- Randomly Selects countries for the cellular data graph
   
 src/view/CountrySelector2.java
	- Randomly Selects countries for the Life expectancy graph

 src/view/GraphView.java
	- Creates a data series for each CellularDataCountry object in the cellular data graph

 src/view/GraphView2.java
	- Creates a data series for each LifeExpectancyCountry object in the Life expectancy graph
   
 src/view/DataAnalysisWindow.java
	- Class DataAnalysisWindow calculates enable user to
	- calculate the average life expectancy within a period and
 	- the total number of subscriptions within a period
 	
 src/view/MouseClickGraphLine.java
	- Class MouseClickGraphLine enable user to hide and show
	- a specific data line on the graph
	
 resources/cellular.csv
	- A CSV (Comma Separated Value) file.
    - Manage the subscription data over many years for different countries.

 resources/lifeExpectancyAtBirth.csv
    - A CSV (Comma Separated Value) file.
    - Manage the life expectancy at birth data over many years for different countries.

 resources/project_proposal.md
	- task proposal
	
 resources/tasks.md
	- task description
	 
 README.txt
    - description of submitted files

 storyboard.png
    - task proposal
