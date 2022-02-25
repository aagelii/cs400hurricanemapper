// --== CS400 File Header Information ==--
// Name: Viraj Sule
// Email: vsule@wisc.edu
// Team: GD Blue
// Role: Frontend Developer
// TA: Surabhi
// Lecturer: Gary Dahl
// Notes to Grader: N/A

import org.junit.jupiter.api.Test; 
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringReader;

/**
 * This class contains a set of JUnit tests to test the implementation of the Frontend class
 * 
 * @author viraj
 *
 */
public class FrontEndDeveloperTests {

  /**
   * This method runs the frontend and redirects its output to a string. It passes in 'x' as an
   * input. If the program exits, the test succeeds
   */
  @Test
  public void TestXtoExit() {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;
    try {
      String input = "x";
      InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
      System.setIn(inputStreamSimulator);
      ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
      // set the output to the stream captor to read the output of the front end
      System.setOut(new PrintStream(outputStreamCaptor));
      Object frontend = new Frontend();
      ((Frontend) frontend).run(new Backend(new StringReader("name,formed,dissipated,mph,deaths\n"
          + "/Katrina,12/21/2020,12/25/2020,30,100\n" + "/Maria,11/21/2020,11/25/2020,20,90\n"
          + "/Matthew,10/21/2020,10/25/2020,10,80\n")));
      // set the output back to standard out for running the test
      System.setOut(standardOut);
      // same for standard in
      System.setIn(standardIn);
      assertNotEquals(null, frontend);

    } catch (Exception e) {
      // make sure stdin and stdout are set correctly after we get exception in test
      System.setOut(standardOut);
      System.setIn(standardIn);
      e.printStackTrace();
      fail("Exeption occured");
      // test failed
    }
  }

  /**
   * This method runs the frontend and redirects its output to a string. It passes in 'i' as an
   * input and then exits the program. If the program entered insert mode at any point, the test
   * succeeds.
   */
  @Test
  public void TestIForInsert() {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;
    try {
      String input = "i" + System.lineSeparator() + "x" + System.lineSeparator() + "x";
      InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
      System.setIn(inputStreamSimulator);
      ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
      // set the output to the stream captor to read the output of the front end
      System.setOut(new PrintStream(outputStreamCaptor));
      Object frontend = new Frontend();
      ((Frontend) frontend).run(new Backend(new StringReader("name,formed,dissipated,mph,deaths\n"
          + "/Katrina,12/21/2020,12/25/2020,30,100\n" + "/Maria,11/21/2020,11/25/2020,20,90\n"
          + "/Matthew,10/21/2020,10/25/2020,10,80\n")));

      // set the output back to standard out for running the test
      System.setOut(standardOut);
      // same for standard in
      System.setIn(standardIn);
      String appOutput = outputStreamCaptor.toString();
      assertEquals(true, appOutput.contains("Welcome to Insert Mode."));
    } catch (Exception e) {
      // make sure stdin and stdout are set correctly after we get exception in test
      System.setOut(standardOut);
      System.setIn(standardIn);
      e.printStackTrace();
      // test failed
      fail("Exception occured");
    }
  }

  /**
   * This method runs the frontend and redirects its output to a string. It passes in 's' as an
   * input and then exits the program. If the program entered search mode at any point, the test
   * succeeds.
   */
  @Test
  public void TestSForSearch() {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;
    try {
      String input = "s" + System.lineSeparator() + "x" + System.lineSeparator() + "x";
      InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
      System.setIn(inputStreamSimulator);
      ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
      // set the output to the stream captor to read the output of the front end
      System.setOut(new PrintStream(outputStreamCaptor));
      Object frontend = new Frontend();
      ((Frontend) frontend).run(new Backend(new StringReader("name,formed,dissipated,mph,deaths\n"
          + "/Katrina,12/21/2020,12/25/2020,30,100\n" + "/Maria,11/21/2020,11/25/2020,20,90\n"
          + "/Matthew,10/21/2020,10/25/2020,10,80\n")));

      // set the output back to standard out for running the test
      System.setOut(standardOut);
      // same for standard in
      System.setIn(standardIn);
      String appOutput = outputStreamCaptor.toString();
      assertEquals(true, appOutput.contains("Welcome to Search Mode."));
    } catch (Exception e) {
      // make sure stdin and stdout are set correctly after we get exception in test
      System.setOut(standardOut);
      System.setIn(standardIn);
      e.printStackTrace();
      // test failed
      fail("Exception occured");
    }
  }

  /**
   * This method runs the frontend and redirects its output to a string. If the program's output
   * contains the names of the first three hurricanes in the dataset, the test succeeds.
   */
  @Test
  public void TestInitialTop3Hurricanes() {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;
    try {
      // set the input stream to our input (with an r to test of the program lists genres)
      String input = "x";
      InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
      System.setIn(inputStreamSimulator);
      ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
      // set the output to the stream captor to read the output of the front end
      System.setOut(new PrintStream(outputStreamCaptor));
      Object frontend = new Frontend();
      ((Frontend) frontend).run(new Backend(new StringReader("name,formed,dissipated,mph,deaths\n"
          + "/Katrina,12/21/2020,12/25/2020,30,100\n" + "/Maria,11/21/2020,11/25/2020,20,90\n"
          + "/Matthew,10/21/2020,10/25/2020,10,80\n")));

      // set the output back to standard out for running the test
      System.setOut(standardOut);
      // same for standard in
      System.setIn(standardIn);
      String appOutput = outputStreamCaptor.toString();
      assertEquals(true, appOutput.contains("Katrina") && appOutput.contains("Maria")
          && appOutput.contains("Matthew"));
    } catch (Exception e) {
      // make sure stdin and stdout are set correctly after we get exception in test
      System.setOut(standardOut);
      System.setIn(standardIn);
      e.printStackTrace();
      // test failed
      fail("Exception occured");
    }
  }

  /**
   * This method runs the frontend and redirects its output to a string. It enters search mode and
   * searches one of the hurricanes in the data set. If the correct properties of the hurricane are
   * shown, the test succeeds.
   */
  @Test
  public void TestSearchForHurricane() {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;
    try {
      String input = "s" + System.lineSeparator() + "Katrina" + System.lineSeparator() + "x"
          + System.lineSeparator() + "x";
      InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
      System.setIn(inputStreamSimulator);
      ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
      // set the output to the stream captor to read the output of the front end
      System.setOut(new PrintStream(outputStreamCaptor));
      Object frontend = new Frontend();
      ((Frontend) frontend).run(new Backend(new StringReader("name,formed,dissipated,mph,deaths\n"
          + "/Katrina,12/21/2020,12/25/2020,30,100\n" + "/Maria,11/21/2020,11/25/2020,20,90\n"
          + "/Matthew,10/21/2020,10/25/2020,10,80\n")));

      // set the output back to standard out for running the test
      System.setOut(standardOut);
      // same for standard in
      System.setIn(standardIn);
      String appOutput = outputStreamCaptor.toString();
      assertEquals(true,
          appOutput.contains("Katrina") && appOutput.contains("30") && appOutput.contains("100"));
    } catch (Exception e) {
      // make sure stdin and stdout are set correctly after we get exception in test
      System.setOut(standardOut);
      System.setIn(standardIn);
      e.printStackTrace();
      // test failed
      fail("Exception occured");
    }
  }

}

