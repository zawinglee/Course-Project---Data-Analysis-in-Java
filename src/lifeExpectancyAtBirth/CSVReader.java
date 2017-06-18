package lifeExpectancyAtBirth;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class CSVReader scans data form CSV files uses data to form different kinds
 * of arrays returns these arrays to main class
 * 
 * @author Zerong Li (Jerry), Qianli Li
 */
public class CSVReader {
	private String[] countryNames;
	private int numberOfCountries;
	private int[] yearLabels;
	private double[][] cellularDataTable;

	/**
	 * Constructs class and private variables Scans data from CVS
	 * 
	 * @param file
	 * @author Zerong Li, Qianli Li
	 */
	public CSVReader(String file) {

		try {
			Scanner input = new Scanner(new File(file));
			int count = 0;
			while (input.hasNext()) {
				count++;
				String str = input.nextLine(); // read the whole line

				// splitting a comma-separated string but ignoring dots in quotes
				String[] dataInLine = str.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);

				if (count == 2) { // Number of countries , 264
					this.numberOfCountries = Integer.parseInt(dataInLine[1]); // converts String to Integer

					// this.numberOfCountries = this.numberOfCountries - 1;
				} else if (count == 3) { // reads year
					this.yearLabels = new int[dataInLine.length - 1];
					for (int i = 0; i < yearLabels.length; i++) {
						this.yearLabels[i] = Integer.parseInt(dataInLine[i + 1]);
					}
					this.countryNames = new String[this.numberOfCountries]; // constructs countryNames array
					this.cellularDataTable = new double[this.numberOfCountries][this.yearLabels.length]; // constructs cellularDataTable array
				}

				else if (count >= 4) {
					int currentRow = count - 4;
					this.countryNames[currentRow] = dataInLine[0]; // fills countryNames
					for (int i = 0; i < this.yearLabels.length; i++) {
						this.cellularDataTable[currentRow][i] = Double.parseDouble(dataInLine[i + 1]);
					}

				}

			}

			input.close();
		} catch (FileNotFoundException ex) {
			System.out.println("File cannot be found  \n" + ex.getMessage());
		} catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("Illegal array index is reached \n" + ex.getMessage());
		}

	}

	/**
	 * 
	 * @author Zerong Li, Qianli Li
	 * @return countryNames [holds the names of all countries read from the CSV file]
	 */
	public String[] getCountryNames() {
		return this.countryNames;
	}

	/**
	 * 
	 * @author Zerong Li, Qianli Li
	 * @return yearLabels [holds the year number read from the CSV file]
	 */
	public int[] getYearLabels() {
		return this.yearLabels;
	}

	/**
	 * 
	 * @author Zerong Li, Qianli Li
	 * @return cellularDataTable [holds the data of specific country and year]
	 */
	public double[][] getParsedTable() {
		return this.cellularDataTable;
	}

	/**
	 * 
	 * @author Zerong Li, Qianli Li
	 * @return total number of years
	 */
	public int getNumberOfYears() {
		return this.yearLabels.length;
	}

}
