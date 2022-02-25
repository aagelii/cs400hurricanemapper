import java.io.*;
import java.util.List;
import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.Test;
import static org.junit.Assert.*;

// --== CS400 File Header Information ==--
// Name: <Justin Ochalla>
// Email: <jochalla@wisc.edu>
// Team: <GD Blue>
// Role: <Data Wrangler>
// TA: <Surabhi>
// Lecturer: <Dahl>
// Notes to Grader: <optional extra notes>
public class TestHurricaneDataReader {

	
	//tests that the class properly reads the data into a list and returns the correct string
	@Test
	public void testDataReader1() throws IOException {
		HurricaneDataReader test = new HurricaneDataReader();
		String filePath = "/Users/justinochalla/Documents/UW Madison/CS 400/Eclipse/HurricaneMapper/hurricanes.csv";
		FileReader f;
	    try {
	      f = new FileReader(new File(filePath));
	      
	    } catch (FileNotFoundException e) {
				//System.out.println("No File Found");
				Reader read = new BufferedReader(new StringReader(
					"name,formed,dissipated,mph,deaths\n"
						+ "Baker,08/18/1950,09/01/1950,105,38\n"
						+ "Carl,10/26/2010,10/28/2010,125,500\n"
						+ "Helen,3/9/2021,3/15/2021,50,3\n"));
				List<HurricaneInterface> testList = test.readDataSet(read);
				String expected = "Hurricane Baker formed on 08/18/1950 and dissipated on 09/01/1950. It was 105 mph and killed 38 people.";
				String result = testList.get(0).toString();
				if (!(expected.contentEquals(result)))
					fail("Expected output was incorrect");
			}
	}
	//ensures the class returns the proper number of hurricane objects
	@Test
	public void testDataReader2() throws IOException {
		HurricaneDataReader test = new HurricaneDataReader();
		String filePath = "/Users/justinochalla/Documents/UW Madison/CS 400/Eclipse/HurricaneMapper/hurricanes.csv";
		FileReader f;
	    try {
	      f = new FileReader(new File(filePath));
	      
	    } catch (FileNotFoundException e) {
				//System.out.println("No File Found");

				f = new FileReader(new File(filePath));
				Reader read = new BufferedReader(new StringReader(
					"name,formed,dissipated,mph,deaths\n"
						+ "Baker,08/18/1950,09/01/1950,105,38\n"
						+ "Carl,10/26/2010,10/28/2010,125,500\n"
						+ "Helen,3/9/2021,3/15/2021,50,3\n"
						+ "Frederic,3/9/1920,3/15/1922,126,6\n"
						+ "Camille,08/14/1969,08/22/1969,175,259\n"
						+ "Eloise,09/13/1975,09/24/1975,125,80\n"
						+ "Elena,08/28/1985,09/04/1985,125,9\n"
						+ "Opal,09/27/1995,10/06/1995,150,63\n"
						+ "Florence,09/23/1953,09/26/1953,115,76\n"));
				List<HurricaneInterface> testList = test.readDataSet(read);
				int expected = 8; // 118 normally if using hurricanes.csv
				int result = testList.size();
				if (!(expected == result))
					fail("Incorrect amount of data was read in.");
			}
	}
	//tests the getName method for hurricanes
	@Test
	public void testDataReader3() throws IOException {
		HurricaneDataReader test = new HurricaneDataReader();
		String filePath = "/Users/justinochalla/Documents/UW Madison/CS 400/Eclipse/HurricaneMapper/hurricanes.csv";
		FileReader f;
	    try {
	      f = new FileReader(new File(filePath));
	      
	    } catch (FileNotFoundException e) {
	      System.out.println("No File Found");
	    }
	    f = new FileReader(new File(filePath));
		Reader read = new BufferedReader(new StringReader(
			"name,formed,dissipated,mph,deaths\n"
				+ "Baker,08/18/1950,09/01/1950,105,38\n"
				+ "Carl,10/26/2010,10/28/2010,125,500\n"
				+ "Helen,3/9/2021,3/15/2021,50,3\n"
				+ "Frederic,3/9/1920,3/15/1922,126,6\n"));
	    List<HurricaneInterface> testList = test.readDataSet(read);
	    String expected = "Frederic";
	    String result = testList.get(3).getName();
	    if(!(expected.contentEquals(result)))
	    	fail("The method getName failed to get the correct name.");
	}
	//tests the getDeaths method for a hurricane object
	@Test
	public void testDataReader4() throws IOException {
		HurricaneDataReader test = new HurricaneDataReader();
		String filePath = "/Users/justinochalla/Documents/UW Madison/CS 400/Eclipse/HurricaneMapper/hurricanes.csv";
		FileReader f;
	    try {
	      f = new FileReader(new File(filePath));
	      
	    } catch (FileNotFoundException e) {
				//System.out.println("No File Found");

				f = new FileReader(new File(filePath));
				Reader read = new BufferedReader(new StringReader(
					"name,formed,dissipated,mph,deaths\n"
						+ "Baker,08/18/1950,09/01/1950,105,38\n"
						+ "Carl,10/26/2010,10/28/2010,125,500\n"
						+ "Helen,3/9/2021,3/15/2021,50,3\n"
						+ "Frederic,3/9/1920,3/15/1922,126,6\n"
						+ "Camille,08/14/1969,08/22/1969,175,259\n"
						+ "Eloise,09/13/1975,09/24/1975,125,80\n"
						+ "Elena,08/28/1985,09/04/1985,125,9\n"
						+ "Opal,09/27/1995,10/06/1995,150,63\n"
						+ "Florence,09/23/1953,09/26/1953,115,76\n"));
				List<HurricaneInterface> testList = test.readDataSet(read);
				int expected = 76;
				int result = testList.get(8).getDeaths();
				if (!(expected == result))
					fail("The method getDeaths returned the incorrect amount of deaths.");
			}
	}
	//tests every get method on different hurricane objects to ensure they were stored properly
	@Test
	public void testDataReader5() throws IOException {
		HurricaneDataReader test = new HurricaneDataReader();
		String filePath = "/Users/justinochalla/Documents/UW Madison/CS 400/Eclipse/HurricaneMapper/hurricanes.csv";
		FileReader f;
	    try {
	      f = new FileReader(new File(filePath));
	      
	    } catch (FileNotFoundException e) {
				//System.out.println("No File Found");
				f = new FileReader(new File(filePath));
				Reader read = new BufferedReader(new StringReader(
					"name,formed,dissipated,mph,deaths\n"
						+ "Baker,08/18/1950,09/01/1950,105,38\n" // 0
						+ "Carl,10/26/2010,10/28/2010,125,500\n"
						+ "Helen,3/9/2021,3/15/2021,50,3\n"			// 2
						+ "Frederic,3/9/1920,3/15/1922,126,6\n"
						+ "Camille,08/14/1969,08/22/1969,175,259\n" // 4
						+ "Eloise,09/13/1975,09/24/1975,125,80\n"
						+ "Elena,08/28/1985,09/04/1985,125,9\n" // 6
						+ "Opal,09/27/1995,10/06/1995,150,63\n"
						+ "Florence,09/23/1953,09/26/1953,115,76\n" // 8
						+ "Isbell,10/08/1964,10/19/1964,115,7\n"
						+ "Rita,09/18/2005,09/26/2005,180,97\n" // 10
						+ "Wilma,10/16/2005,10/27/2005,185,87\n"
						+ "Hermine,08/28/2016,09/08/2016,80,4\n" // 12
						+ "Matthew,09/28/2016,10/10/2016,165,603\n"
						+ "Irma,08/30/2017,09/14/2017,180,52\n"	// 14
						+ "Michael,10/07/2018,10/16/2018,160,31\n"
						+ "Ethel,09/12/1960,09/17/1960,115,1\n"	//16
						+ "Hilda,09/28/1964,10/05/1964,140,38\n"));
				List<HurricaneInterface> testList = test.readDataSet(read);
				String expected1 = "Baker";
				String result1 = testList.get(0).getName();
				String expected2 = "08/28/1985";
				String result2 = testList.get(6).getDateFormed();
				String expected3 = "10/19/1964";
				String result3 = testList.get(9).getDateDissipated();
				int expected4 = 115;
				int result4 = testList.get(16).getMPH();
				int expected5 = 38;
				int result5 = testList.get(17).getDeaths();
				boolean bool1 = expected1.contentEquals(result1);
				boolean bool2 = expected2.contentEquals(result2);
				boolean bool3 = expected3.contentEquals(result3);
				boolean bool4 = expected4 == result4;
				boolean bool5 = expected5 == result5;
				if (!(bool1 && bool2 && bool3 && bool4 && bool5))
					fail("One of the get methods failed");
			}
	}
}
