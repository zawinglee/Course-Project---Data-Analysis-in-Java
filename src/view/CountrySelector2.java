package view;

import java.util.Random;

import lifeExpectancyAtBirth.*;

public class CountrySelector2 {
    private LinkedList<LifeExpectancyCountry> selectedCountries;

    /**
     * Builds a linked list of random countries
     *
     * @param allCountries 	[An array of Country objects]
     * @param requestedSize [The requested number of elements]
     * @author Zerong Li (Jerry), Qianli Li
     */
    public CountrySelector2(LifeExpectancyCountry[] allCountries, int requestedSize) {
        // Build the list out of a random selection of countries.
        Random random = new Random();

        // A singly linked list of country data.
        int[] traverse = new int[requestedSize];
        selectedCountries = new LinkedList<LifeExpectancyCountry>();
        for (int i = 0; i < requestedSize; i++) {
            int selectedIndex = random.nextInt(allCountries.length);

            // Guarantee that no two same Country object store in the linked list
            traverse[i] = selectedIndex;
            if (i == 0) {
                selectedCountries.add(allCountries[selectedIndex]);
            } else {
                for (int j = 0; j < i; j++) {
                    if (traverse[j] != selectedIndex) {
                        if (j == i - 1)
                            selectedCountries.add(allCountries[selectedIndex]);
                    } else {
                        break;
                    }
                }
            }
        }
    }

    /**
     * Accessor method for randomly selected list of countries.
     *
     * @return LinkedList of Country objects.
     */
    public LinkedList<LifeExpectancyCountry> selectCountries() {
        return this.selectedCountries;
    }
}
