package cellularData;

/**
 * Creates SubscriptionYear class that stores single subscription of a specific year
 * 
 * @author Zerong Li (Jerry), Sally Li
 */
public class SubscriptionYear {
	private int year; // variable that stores the year for one subscription data
	private double subscriptions; // variable that stores the number of subscriptions for a specific year

	/**
	 * Constructs class and private variables according what data are passing
	 * 
	 * @param newYear				[variable stores the year for one subscription data]
	 * @param totalSubscriptions	[variable that stores the number of subscriptions for a specific year]
	 * @author Zerong Li
	 */
	public SubscriptionYear(int newYear, double totalSubscriptions) {
		this.year = newYear;
		this.subscriptions = totalSubscriptions;
	}

	/**
	 * Deep Copy via Constructor
	 * 
	 * @param other	[SubscriptionYear object we want to make a deep copy of]
	 * @author Zerong Li
	 */
	public SubscriptionYear(SubscriptionYear other) {
		this(other.getYear(), other.getSubscriptions());
	}

	/**
	 * Sets subscriptions number
	 * 
	 * @param totalSubscriptions [variable that stores the number of subscriptions for a specific year]
	 * @author Zerong Li
	 */
	public void setSubscriptions(int totalSubscriptions) {
		this.subscriptions = totalSubscriptions;
	}

	/**
	 * returns subscriptions number for that year
	 * 
	 * @return subscriptions [variable that stores the number of subscriptions for a specific year]
	 * @author Zerong Li
	 */
	public double getSubscriptions() {
		return this.subscriptions;
	}

	/**
	 * Sets the year for a specific subscriptions data
	 * 
	 * @param newYear		[variable stores the year for one subscription data]
	 * @author Zerong Li
	 */
	public void setYear(int newYear) {
		this.year = newYear;
	}

	/**
	 * Mutator method to set a value of subscription
	 * 
	 * @param subscriptions	[the specific subscription]
	 * @author Zerong Li
	 */
	public void setSubscriptions(double subscriptions) {
		this.subscriptions = subscriptions;
	}

	/**
	 * returns subscriptions number for that year
	 * 
	 * @return year [variable stores the year for one subscription data]
	 * @author Zerong Li
	 */
	public int getYear() {
		return this.year;
	}

	/**
	 * Compare two SubscriptionYear objects in the linked list
	 * 
	 * @param other	[the SubscriptionYear object]
	 * @return true if the data in the SubscriptionYear element are same
	 * @author Zerong Li
	 */
	public boolean equals(SubscriptionYear other) {
		if (other instanceof SubscriptionYear) {
			SubscriptionYear obj = (SubscriptionYear) other;
			if (obj.getYear() == this.year && obj.subscriptions == this.subscriptions) {
				return true;
			}
		}
		return false;
	}

	/**
	 * returns a String when object is called
	 * 
	 * @return result [a String that contain subscriptions number]
	 * @author Zerong Li
	 */
	public String toString() {
		String result = "";
		return result + this.subscriptions;
	}

}