// --== CS400 File Header Information ==--
// Name: <Zachary Paronto>
// Email: <paronto@wisc.edu>
// Team: <GD blue>
// Role: <Backend Developer>
// TA: <Surabhi>
// Lecturer: <Heimerl>
// Notes to Grader: <Code in the constructors was adapted from project one Backend>
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.NotDirectoryException;
import java.util.*;
import java.util.zip.DataFormatException;

public class Backend implements FrontendInterface {
  private RedBlackTree<HurricaneInterface> rbt;
  private List<HurricaneInterface> hurricaneList;

  /**
   * Constructs a backend object based on an array of String values. Index 0 in that array is
   * assumed to be the file path
   * <p>
   * 
   * @param path an array of string values where the first index is a file path
   * @throws FileNotFoundException if the path is invalid or the file is in incorrect format
   */
  public Backend(String[] path) throws FileNotFoundException {
    rbt = new RedBlackTree<HurricaneInterface>();
    
    HurricaneDataReader hReader = new HurricaneDataReader();
    FileReader f;
    // attempts to make a FileReader at the provided path
    f = new FileReader(new File(path[0]));
    BufferedReader read = new BufferedReader(f);

    try {
      hurricaneList = hReader.readDataSet(read);
    } catch (Exception e) {
      throw new FileNotFoundException("File not in correct format: " + path[0]);
    }
    populateRBTree();
  }

  /**
   * Constructs a backend object based on the input String value
   * <p>
   * 
   * @param path a String value representing a file path
   * @throws FileNotFoundException if the path is invalid or the file is in incorrect format
   */
  public Backend(String path) throws FileNotFoundException {
    rbt = new RedBlackTree<HurricaneInterface>();
    HurricaneDataReader hReader = new HurricaneDataReader();
    // attempts to make a FileReader at the provided path

    //FileReader f = new FileReader(path); // ERROR HERE

    //BufferedReader read = new BufferedReader(f);

    try {
      hurricaneList = hReader.readDataSet(new BufferedReader(new FileReader(path)));
    } catch (Exception e) {
      e.printStackTrace();
      throw new FileNotFoundException("File not in correct format: " + path);
    }

    populateRBTree();
  }


  /**
   * Constructs a backend object based on a Reader object passed in
   * <p>
   * 
   * @param inputReader a reader object attached to an appropriate CSV File
   */
  public Backend(Reader inputReader) {
    rbt = new RedBlackTree<HurricaneInterface>();
    HurricaneDataReader hReader = new HurricaneDataReader();
    
    try {
      hurricaneList = hReader.readDataSet(inputReader);
    } catch (FileNotFoundException e) {
      System.out.println("No matching file was found.");
      e.printStackTrace();
    } /*catch (DataFormatException e) {
      System.out.println("File does not follow the correct format.");
      e.printStackTrace();
    }*/catch (IOException e) {
      e.printStackTrace();
    }

    populateRBTree();
  }


  /**
   * Populates the Red Black Tree instance field 
   */
  private void populateRBTree() {
    for (HurricaneInterface hurricaneInterface : hurricaneList) {
      rbt.insert(hurricaneInterface);
    }
  }


  /**
   * Adds the parameter to the tree if it is not null and not a duplicate value
   * <p>
   * 
   * @Override
   * @param hurricane the hurricane object to be inserted
   * @return boolean true if the value is inserted, false otherwise
   */
  public boolean insert(Hurricane hurricane) {
    // ensures hurricane object was created correctly
    if(hurricane.getName()==null||hurricane.getName()=="")
      return false;
    // attempts to add object to the tree and list of all objects
    try {
      rbt.insert(hurricane);
    } catch (NullPointerException e) {
      System.out.println("Object must not be null.");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("Hurricane with matching name is already in the tree.");
      return false;
    }
    // if no errors occur, object must have been added
    return true;
  }


  /**
   * Searches the tree for a hurricane with the same name as the parameter
   * <p>
   *
   * @param name the name of the hurricane to be searched for
   * @return True if a hurricane object with the same name is found, false otherwise
   */
  @Override
  public boolean contains(String name) {
    Iterator<HurricaneInterface> iterator = rbt.iterator();
    Hurricane hurricane = new Hurricane(name,"","",0,0);
    return rbt.contains(hurricane);
  }

  /**
   * Returns a list of up to three HurricaneInterface objects starting from the first hurricane 
   * found with the same name as the parameter. First element in the list will be the hurricane
   * with the given name. The following will be its inorder successors, if there are any.
   * <p>
   *
   * @param name the name of the hurricane to begin the list
   * @return List<HurricaneInterface> a list of the hurricane objects selected
   * @throws NoSuchElementException if no hurricane with that name is found or name is null
   */
  @Override
  public List<HurricaneInterface> getThreeHurricane(String name) throws NoSuchElementException {
    // ensures hurricane is in the list, method throws exception otherwise
    if(name ==null)
      throw new NoSuchElementException("Name must not be null");
    Hurricane hurricane = lookup(name);
    List<HurricaneInterface> list = new ArrayList<HurricaneInterface>();
    // adds the named hurricane to the list, then finds its next two inorder successors
    list.add(hurricane);
    Iterator<HurricaneInterface> iterator = rbt.iterator();
    if (iterator.hasNext()) {
      HurricaneInterface next = iterator.next();
      while (iterator.hasNext() && next.getName().compareTo(name) != 0) {
        next = iterator.next();
      }
        
    }


    // ticks the iterator one more time to correct output
    //iterator.next();

    // adds successors to the returned list.
    if(iterator.hasNext()) {
      list.add(iterator.next());
      if(iterator.hasNext())
        list.add(iterator.next());
    }
    
    

    return list;

  }

  /**
   * Returns a list of up to three HurricaneInterface objects starting from the given index.
   * First element in the list will be the hurricane at the given index. 
   * The following will be its inorder successors, if there are any.
   * <p>
   * 
   * @Override
   * @param int startIndex the index to start the search
   * @return List<HurricaneInterface> a list of the hurricane objects selected
   * @throws IndexOutOfBoundsException if startIndex >= tree size
   */
  public List<HurricaneInterface> getThreeHurricanes(int startIndex)
      throws IndexOutOfBoundsException {

    List<HurricaneInterface> list = new ArrayList<HurricaneInterface>();
    hurricaneList = new ArrayList<HurricaneInterface>();

    // creates a list of Hurricane objects in order stored in the instance field of the class
    Iterator<HurricaneInterface> iterator = rbt.iterator();
    List<HurricaneInterface> hurricaneList = new ArrayList<HurricaneInterface>();
    if (iterator.hasNext()) {
      HurricaneInterface t=iterator.next();
      hurricaneList.add(t);
    }
    while (iterator.hasNext()) {
      HurricaneInterface data = iterator.next();
      if(hurricaneList.contains(data)){
        iterator.next();
      }
      hurricaneList.add(data);
    }

    // adds first object to the list to be returned if index is within bounds
    if (startIndex >= hurricaneList.size())
      throw new IndexOutOfBoundsException(
          "Index is not valid for an array of size " + hurricaneList.size());
    // adds the named hurricane to the list, then finds its next two inorder successors
    list.add(hurricaneList.get(startIndex));
    
    //adds first successor to the returned list
    if(startIndex<hurricaneList.size()-1)
    list.add(hurricaneList.get(startIndex+1));
    
    //adds second successor to the returned list
    if(startIndex<hurricaneList.size()-2)
    list.add(hurricaneList.get(startIndex+2));

    return list;

  }


  /**
   * Returns the hurricane object that has a name field equal to the parameter
   * <p>
   * 
   * @Override
   * @param  name the name of the hurricane to be searched for
   * @return Hurricane the hurricane with the matching name as the parameter
   * @throws NoSuchElementException if no hurricane with that name is found or name is null
   */
  public Hurricane lookup(String name) throws NoSuchElementException {
    //prevents a null name from being passed
    if(name == null)
      throw new NoSuchElementException("Name must not be null");
    
    // searches through the tree for the first inorder node with a matching name value for its data
    Iterator<HurricaneInterface> iterator = rbt.iterator();
    
    while (iterator.hasNext()) {
      HurricaneInterface current = iterator.next();
      if (current.getName().compareTo(name) == 0)
        return (Hurricane) current;
    }
    
    // operates when loop executes without returning(i.e. no matching value found)
    throw new NoSuchElementException("No hurricane with a matching name was found.");

  }

}
