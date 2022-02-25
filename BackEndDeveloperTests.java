// --== CS400 File Header Information ==-- 
// Name: <Zachary Paronto>
// Email: <paronto@wisc.edu>
// Team: <GD blue>
// Role: <Backend Developer>
// TA: <Surabhi>
// Lecturer: <Heimerl>
// Notes to Grader: <optional extra notes>
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.FileNotFoundException;
import java.io.StringReader;
import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;
import java.util.NoSuchElementException;

public class BackEndDeveloperTests {
  
  //This test checks to see if Backend objects are instantiated correctly by the class constructors
  @Test
  public void testConstructor() throws FileNotFoundException {
    //uncomment the following block when Backend class is implemented
    String[] args = new String[2];
    //since the path was grabbed directly from my computer, the constructor will fail to produce
    //  a backend object based on it. This is expected.
    try {
    args[0] = "C:\\Users\\codem\\eclipse-workspace\\400 Project Two\\src\\hurricanes.csv";
    args[1] = "This String is not a valid path";
    Backend backend = new Backend(args);
    Backend backendString = new Backend(args[0]);
    } catch(Exception e) {
      System.out.println("This portion of the test will fail when not on Zach P's computer."
          + "Functionality verified on there though.");
    }
    
    
    try {
      Backend backendNoPath = new Backend(args[1]);
      fail("Program executed successfully on what should be an invalid path");
    }catch(FileNotFoundException e) {
      
    }
    Backend backendWReader = new Backend(new StringReader(
    "name,formed,dissipated,mph,deaths\n"
    + "Katrina,8/23/2005,8/31/2005,175,1245\n"
    + "Carl,10/26/2010,10/28/2010,125,500\n"
    + "Helen,3/9/2021,3/15/2021,50,3\n"));

    //fail("Class not implemented yet");
    
  }
  
  @Test
  public void testInsert() {
    //uncomment the following block when Backend class is implemented
  Backend backend = new Backend(new StringReader(
  "name,formed,dissipated,mph,deaths\n"
  + "Katrina,8/23/2005,8/31/2005,175,1245\n"
  + "Carl,10/26/2010,10/28/2010,125,500\n"
  + "Helen,3/9/2021,3/15/2021,50,3\n"));
  if(backend.insert(new Hurricane("","","", 0, 0 )))
    fail("Insert method added an empty Hurricane object");
    
    //fail("Class not implemented yet");
  }
  @Test
  public void testContains() {
    //uncomment the following block when Backend class is implemented
  Backend backend = new Backend(new StringReader(
  "name,formed,dissipated,mph,deaths\n"
  + "Katrina,8/23/2005,8/31/2005,175,1245\n"
  + "Carl,10/26/2010,10/28/2010,125,500\n"
  + "Helen,3/9/2021,3/15/2021,50,3\n"));
    if(backend.contains("spofeuhe"))
      fail("Method returned true for a value that is not in the tree");   
    if(!backend.contains("Katrina"))
      fail("Method returned false for a value that is in the tree");
    if(!backend.contains("Carl"))
      fail("Method returned false for a value that is in the tree");
    if(!backend.contains("Helen"))
      fail("Method returned false for a value that is in the tree");
    
    //fail("Class not implemented yet");
  }
  
  //Method tests whether the get method in the Backend class functions as expected for varied inputs
  @Test
  public void testGet() {
    //uncomment the following block when Backend class is implemented
  Backend backend = new Backend(new StringReader(
  "name,formed,dissipated,mph,deaths\n"
  + "Katrina,8/23/2005,8/31/2005,175,1245\n"
  + "Carl,10/26/2010,10/28/2010,125,500\n"
  + "Helen,3/9/2021,3/15/2021,50,3\n"));
    List<HurricaneInterface> _list = backend.getThreeHurricanes("Katrina");
    if(_list.size() !=3&&_list.size()!=1)
      fail("List does not contain the proper amount of hurricanes");
    if(_list.toString().indexOf("Katrina")==-1)
      fail("List does not contain the expected values from the tree");
    try {
      backend.getThreeHurricanes("wofelajn");
      fail("Method should give an error for elementss that aren't in the tree");
    } catch(NoSuchElementException e) {
      
    }
    
    
    //fail("Class not implemented yet");
  }
  
  //Method tests whether lookup method in the Backend class functions as expected for varied inputs
  @Test
  public void testLookup() {
    //uncomment the following block when Backend class is implemented
  Backend backend = new Backend(new StringReader(
  "name,formed,dissipated,mph,deaths\n"
  + "Katrina,8/23/2005,8/31/2005,175,1245\n"
  + "Carl,10/26/2010,10/28/2010,125,500\n"
  + "Helen,3/9/2021,3/15/2021,50,3\n"));
    assertEquals(backend.lookup("Katrina").getName(), "Katrina");
    try{
      backend.lookup("osfnui");
      fail("Method should have thrown an exception when hurricane isn't in the tree");
    } catch(NoSuchElementException e) {
      
    }
    //fail("Class not implemented yet");
    
  }
  

}
