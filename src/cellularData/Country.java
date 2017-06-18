package cellularData;

import java.util.Iterator;

/**
 * Class Country stores the name of the country and pass the country name and
 * subscriptions to class SubscriptionYear for storing and sorting data proposes
 * 
 * @author Zerong Li (Jerry), Sally Li
 */
public class Country {
	private String name;
	private int minYear;
	private int maxYear;
	LinkedList<SubscriptionYear> subscriptions;

	/**
	 * Constructs class Country and sets country name
	 * 
	 * @param newName	[variable stores the country name]
	 * @author Zerong Li
	 */
	public Country(String newName) {
		this.subscriptions = new LinkedList<SubscriptionYear>();
		this.name = newName;
		this.minYear = 9999;
		this.maxYear = 0;
	}

	/**
	 * Checks if two Country objects are equal
	 * 
	 * @author Zerong Li
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof Country) {
			Country obj = (Country) other;
			if (obj.getName().equalsIgnoreCase(this.name)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method addSubscriptionYear() use Class SubscriptionYear to add and store data
	 * 
	 * @param newYear 	[variable stores the year for one subscription data]
	 * @param singleSubscription	[variable stores subscriptions number of one year]
	 * @author Zerong Li
	 */
	public void addSubscriptionYear(int newYear, double singleSubscription) {
		SubscriptionYear temp = new SubscriptionYear(newYear, singleSubscription);
		subscriptions.add(temp);

		// adjusts minYear and maxYear while adding object
		if (newYear < this.minYear) {
			minYear = newYear;
		}
		if (newYear > this.maxYear) {
			maxYear = newYear;
		}
	}

	/**
	 * Method getNumSubscriptionsForPeriod() can calculate total number of
	 * subscriptions between the start and end year that the user requests
	 * 
	 * @param yearI	  [starting year which the user wants to start]
	 * @param yearII  [ending year which the user wants to end]
	 * @throws IllegalArgumentException	[a message that contains error message]
	 * @return subsCount [total number of subscriptions between start and end year]
	 * @author Zerong Li
	 */
	public double getNumSubscriptionsForPeriod(int yearI, int yearII) throws IllegalArgumentException {
		double subsCount = 0;

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

		Iterator<SubscriptionYear> itr = subscriptions.iterator(); // Traversing in the SubscriptionYear list
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

	/**
	 * returns the name of a country
	 * 
	 * @return name [variable stores the country name]
	 * @author Zerong Li
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * returns a String when object is called
	 * 
	 * @return result [a String that contains countries and data that have been added]
	 * @author Zerong Li
	 */
	public String toString() {
		String result = String.format("※%13s\t", this.name);
		for (SubscriptionYear x : subscriptions) { // for each object in SubscriptionYear
			result += String.format("%.2f\t", x.getSubscriptions());
		}
		result += "\n";
		return result;
	}
	
	/**
	 * returns the subscriptions linked list
	 *
	 * @return subscriptions	[a linked list that contains all subscriptions data]
	 * @author Zerong Li, Qianli Li
	 */
	public LinkedList<SubscriptionYear> getSubscription() {
		return this.subscriptions;
	}
}