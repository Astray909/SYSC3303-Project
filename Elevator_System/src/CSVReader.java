/**
 * Read and parse CSV files
 * @author Jia Chen Huang 101073186
 * @version Feb 11
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSVReader {




	public CSVReader() throws FileNotFoundException {
		try {
			//Get scanner instance
			Scanner scanner = new Scanner(new File("SampleCSVFile.csv"));

			//Set the delimiter used in file
			scanner.useDelimiter(",");

			//Get all tokens and store them in some data structure
			//I am just printing them
			while (scanner.hasNext()) 
			{
				System.out.print(scanner.next() + "|");
			}

			//Do not forget to close the scanner  
			scanner.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
