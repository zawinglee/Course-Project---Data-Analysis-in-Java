package lifeExpectancyAtBirth;

/**
 * Creates SubscriptionYear class that stores single subscription of a specific year
 *
 * @author Zerong Li (Jerry), Qianli Li
 */
public class ExpectancyYear {
    private int year; // variable that stores the year for one subscription data
    private double expectancies; // variable that stores the number of expectancies for a specific year

    /**
     * Constructs class and private variables according what data are passing
     *
     * @param newYear				[variable stores the year for one subscription data]
     * @param totalExpectancies	[variable that stores the number of expectancies for a specific year]
     * @author Zerong Li, Qianli Li
     */
    public ExpectancyYear(int newYear, double totalExpectancies) {
        this.year = newYear;
        this.expectancies = totalExpectancies;
    }

    /**
     * Deep Copy via Constructor
     *
     * @param other	[ExpectancyYear object we want to make a deep copy of]
     * @author Zerong Li, Qianli Li
     */
    public ExpectancyYear(ExpectancyYear other) {
        this(other.getYear(), other.getExpectancies());
    }

    /**
     * sets expectancies number
     *
     * @param totalExpectancies [variable that stores the number of expectancies for a specific year]
     * @author Zerong Li, Qianli Li
     */
    public void setExpectancies(int totalExpectancies) {
        this.expectancies = totalExpectancies;
    }

    /**
     * returns expectancies number for that year
     *
     * @return expectancies [variable that stores the number of expectancies for a specific year]
     * @author Zerong Li, Qianli Li
     */
    public double getExpectancies() {
        return this.expectancies;
    }

    /**
     * sets the year for a specific expectancies data
     *
     * @param newYear		[variable stores the year for one subscription data]
     * @author Zerong Li, Qianli Li
     */
    public void setYear(int newYear) {
        this.year = newYear;
    }

    /**
     * Mutator method to set a value of subscription
     *
     * @param expectancies	[the specific subscription]
     * @author Zerong Li, Qianli Li
     */
    public void setExpectancies(double expectancies) {
        this.expectancies = expectancies;
    }

    /**
     * returns expectancies number for that year
     *
     * @return year [variable stores the year for one subscription data]
     * @author Zerong Li, Qianli Li
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Compare two SubscriptionYear objects in the linked list
     *
     * @param other	[the SubscriptionYear object]
     * @return true if the data in the SubscriptionYear element are same
     * @author Zerong Li, Qianli Li
     */
    public boolean equals(ExpectancyYear other) {
        if (other instanceof ExpectancyYear) {
            ExpectancyYear obj = (ExpectancyYear) other;
            if (obj.getYear() == this.year && obj.expectancies == this.expectancies) {
                return true;
            }
        }
        return false;
    }

    /**
     * returns a String when object is called
     *
     * @return result [a String that contain expectancies number]
     * @author Zerong Li, Qianli Li
     */
    public String toString() {
        String result = "";
        return result + this.expectancies;
    }

}
