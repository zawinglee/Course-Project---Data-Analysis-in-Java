package lifeExpectancyAtBirth;

import java.util.Iterator;

/**
 * Class Country stores the name of the country and pass the country name and
 * expectancies to class ExpectancyYear for storing and sorting data proposes
 * 
 * @author Zerong Li (Jerry), Qianli Li
 */
public class LifeExpectancyCountry {
	private String name;
	private int minYear;
	private int maxYear;
	LinkedList<ExpectancyYear> expectancies;

	/**
	 * Constructs class Country and sets country name
	 *
	 * @param newName	[variable stores the country name]
	 * @author Zerong Li, Qianli Li
	 */
	public LifeExpectancyCountry(String newName) {
		this.expectancies = new LinkedList<ExpectancyYear>();
		this.name = newName;
		this.minYear = 9999;
		this.maxYear = 0;
	}

	/**
	 * Checks if two Country objects are equal
	 *
	 * @author Zerong Li, Qianli Li
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof LifeExpectancyCountry) {
			LifeExpectancyCountry obj = (LifeExpectancyCountry) other;
			if (obj.getName().equalsIgnoreCase(this.name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Class addExpectancyYear() use Class ExpectancyYear to add and store data
	 *
	 * @param newYear 	[variable stores the year for one subscription data]
	 * @param singleExpectancy	[variable stores expectancies number of one year]
	 * @author Zerong Li, Qianli Li
	 */
	public void addExpectancyYear(int newYear, double singleExpectancy) {
		ExpectancyYear temp = new ExpectancyYear(newYear, singleExpectancy);
		expectancies.add(temp);

		// adjusts minYear and maxYear while adding object
		if (newYear < this.minYear) {
			minYear = newYear;
		}
		if (newYear > this.maxYear) {
			maxYear = newYear;
		}
	}

	/**
	 * Method getAverageNumExpectanciesForPeriod() can calculate total number of
	 * expectancies between the start and end year that the user requests
	 *
	 * @param yearI	  [starting year which the user wants to start]
	 * @param yearII  [ending year which the user wants to end]
	 * @throws IllegalArgumentException	[a message that contains error message]
	 * @return averageLifeExpectancy [the average life expectancy between start and end year]
	 * @author Zerong Li, Qianli Li
	 */
	public double getAverageNumExpectanciesForPeriod(int yearI, int yearII) throws IllegalArgumentException {
		double totalLifeExpectancy = 0;
		double averageLifeExpectancy = 0;
		int yearCount = 0;

		// two sides are out of bound (xxx----xxx)
		if ((yearI < this.minYear) && (this.maxYear < yearII)) {
			System.out.println(">>>Illegal Argument Request of start and end year: (" + yearI + " to " + yearII + ")."
					+ "\n>>>Valid period for " + this.name + " is " + this.minYear + " to " + this.maxYear + ".");
			yearI = this.minYear;
			yearII = this.maxYear;
		}
		// invalid on the left (starting year xxx---)
		else if ((yearI < this.minYear) && (this.minYear <= yearII) && (this.maxYear >= yearII)) {
			this.maxYear = yearII;
			System.out.println(">>>Illegal Argument Request of start year: " + yearI + "." + "\n>>>Valid period for "
					+ this.name + " is " + this.minYear + " to " + this.maxYear + ".");
			yearI = this.minYear;
		}
		// invalid on the right (ending year ---xxx)
		else if ((yearI >= this.minYear) && (this.maxYear < yearII) && (yearI <= this.maxYear)) {
			this.minYear = yearI;
			System.out.println(">>>Illegal Argument Request of end year: " + yearII + "." + "\n>>>Valid period for "
					+ this.name + " is " + this.minYear + " to " + this.maxYear + ".");
			yearII = this.maxYear;

		}
		// totally out of bound (two sides both are not within valid period (xxxxxx)
		else if ((yearI < this.minYear) && (yearII < this.minYear)) {
			System.out.println(">>>Illegal Argument Request of start and end year: (" + yearI + " to " + yearII + ")."
					+ "\n>>>Valid period for " + this.name + " is " + this.minYear + " to " + this.maxYear + ".");
			yearI = this.minYear;
			yearII = this.maxYear;

		} else if ((yearI > this.maxYear) && (yearII > this.maxYear)) {
			System.out.println(">>>Illegal Argument Request of start and end year: (" + yearI + " to " + yearII + ")."
					+ "\n>>>Valid period for " + this.name + " is " + this.minYear + " to " + this.maxYear + ".");
			yearI = this.minYear;
			yearII = this.maxYear;

		} else if (yearI > yearII) { // requested end year > requested start year (2015-2000)
			String err = ">>>Illegal Argument Request of start and end year: (" + yearI + " to " + yearII + ")."
					+ "\n>>>Valid period for " + this.name + " is " + this.minYear + " to " + this.maxYear + ".";
			System.out.println(err);
			yearI = this.minYear;
			yearII = this.maxYear;
		}

		Iterator<ExpectancyYear> itr = expectancies.iterator(); // Traversing in the SubscriptionYear list
		yearCount = yearII - yearI;
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

	/**
	 * returns the name of a country
	 *
	 * @return name [variable stores the country name]
	 * @author Zerong Li, Qianli Li
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * returns a String when object is called
	 *
	 * @return result [a String that contains countries and data that have been added]
	 * @author Zerong Li, Qianli Li
	 */
	public String toString() {
		String result = String.format("※%13s\t", this.name);
		for (ExpectancyYear x : expectancies) { // for each object in SubscriptionYear
			result += String.format("%.2f\t", x.getExpectancies());
		}
		result += "\n";
		return result;
	}
	
	/**
	 * returns the expectancies linked list
	 *
	 * @return expectancies	[a linked list that contains all expectancies data]
	 * @author Zerong Li, Qianli Li
	 */
	public LinkedList<ExpectancyYear> getSubscription() {
		return this.expectancies;
	}

	public int getMinYear() {
		return this.minYear;
	}
	public int getMaxYear() {
		return this.maxYear;
	}
}
