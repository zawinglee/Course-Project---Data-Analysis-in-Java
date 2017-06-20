package lifeExpectancyAtBirth;

/**
 * Provides access to CSV data.
 * @author Foothill College, Bita M, Zerong Li (Jerry), Qianli Li
 */
public class DataModelLE 
{
	private LifeExpectancyCountry[] lifeExpectancy;

	public DataModelLE()
	{
		lifeExpectancy = parseCSVFile("resources/lifeExpectancyAtBirth.csv");
	}
	
	private LifeExpectancyCountry[] parseCSVFile(final String filename)
	{
		// Parses the CSV data file
		// NOTE: Handle all exceptions in the constructor.
		//       For full credit, do *not* throw exceptions to main. 
		CSVReader parser = new CSVReader(filename);

		// In class CSVReader the accessor methods only return values of instance variables.
		String [] countryNames = parser.getCountryNames();
		int [] yearLabels = parser.getYearLabels();
		double [][] parsedTable = parser.getParsedTable();		


		// Holds the data for all Country object read from the input data file.
		LifeExpectancyCountry[] countries;
 
		// Initializes the to the number of entries read by CSVReader.
		countries = new LifeExpectancyCountry[countryNames.length];

		// Reference to a Country object
		LifeExpectancyCountry current;

		// Go through each country name parsed from the CSV file.
		for (int countryIndex = 0; countryIndex < countries.length; countryIndex++)
		{
			int numberOfYears = yearLabels.length;   

			// TODO: Initially convert your CountryList to a generic LinkedList and make sure that list builds 
			// 		 correctly using the original Country constructor which takes the numberOfYears to setup
			// 		 the array of expectancies.
			// NOTE: Once you've verified that your generic LinkedList builds correctly,
			//       make sure to comment the line below before submitting.
			//current = new Country(countryNames[countryIndex], numberOfYears);		// version 1

			// TODO: Once you are successful in creating a generic LinkedList of countries, create a
			// 		 LinkedList of SubscriptionYear in the Country class.
			// 	     So, your Country class should no longer have an array of SubscriptionYear objects.
			current = new LifeExpectancyCountry(countryNames[countryIndex]);	// version 2 and final version of Country constructor

			// Go through each year of cellular data read from the CSV file.
			for (int yearIndex = 0; yearIndex < numberOfYears; yearIndex++)
			{
				double [] allExpectancies = parsedTable[countryIndex];
				double countryData = allExpectancies[yearIndex];
				current.addExpectancyYear(yearLabels[yearIndex], countryData);
			}

			// Add the newly created country to the 1D array.
			countries[countryIndex] = current;
		}
		
		return countries;
	}
	
	public LifeExpectancyCountry[] getLifeExpectancy()
	{
		return this.lifeExpectancy;
	}
}
