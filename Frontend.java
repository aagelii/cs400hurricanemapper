// --== CS400 File Header Information ==--
// Name: Viraj Sule
// Email: vsule@wisc.edu
// Team: GD Blue
// Role: Frontend Developer
// TA: Surabhi
// Lecturer: Gary Dahl
// Notes to Grader: N/A
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Frontend {
  // allows input to be captured from the user
  private Scanner scan;

  /**
   * Constructor for Frontend Class
   */
  public Frontend() {
    scan = new Scanner(System.in);
  }


  /**
   * Begins the program with a set of instructions and calls Base mode
   * 
   * @param backend instance of Backend that is being run
   */
  public void run(Backend backend) {
    // move into main part with welcome message, then call base mode
    System.out
        .println("Welcome to Hurricane Mapper: A program designed to help you navigate through "
            + "a data set of hurricanes!");
    System.out.println("You can press \'i\' to enter insert mode,\'s\' to enter search mode, "
        + "or \'x\' to exit the current mode or exit the program.");
    System.out
        .println("The program starts in the base mode and displays the first three hurricanes "
            + "in the dataset in alphabetical order.");
    System.out.println("All key inputs must be followed by the \'enter\' key.");
    // System.out.format("%15s%10d%16s", "hello" , 4455 , "world");
    System.out.println();
    // System.out.format("%11s%10d%16s", "hello" , 1 , "world");


    baseMode(backend);

  }

  /**
   * Takes in a file path and initializes the backend. Then calls run() to run the program
   * 
   * @param args csv file
   */
  public static void main(String[] args) {
    Backend backend = null;
    try {
      backend = new Backend(args[0]);
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    }
    Frontend frontend = new Frontend();
    frontend.run(backend);

  }

  /**
   * This method brings the user back to the base mode, which is where they can see the first 3
   * hurricanes in the data set as well as scroll through the hurricanes
   * 
   * @param backend instance of Backend that is being run
   */
  public void baseMode(Backend backend) {
    // display the movies from the database, initially none should display
    System.out.format("%52s", "First 3 Hurricanes (Alphabetically)\n");
    System.out.format("%13s%15s%20s%8s%10s", "Name", "Date Formed", "Date Dissipated", "MPH",
        "Deaths");
    List<HurricaneInterface> threeHurricanes = backend.getThreeHurricanes(0);
    for (int i = 0; i < threeHurricanes.size(); i++) {
      System.out.println();
      System.out.format("%13s%15s%20s%8d%10d", threeHurricanes.get(i).getName(),
          threeHurricanes.get(i).getDateFormed(), threeHurricanes.get(i).getDateDissipated(),
          threeHurricanes.get(i).getMPH(), threeHurricanes.get(i).getDeaths());
    }
    System.out.println("\n");
    System.out.println("Welcome to the base mode.");
    System.out.println(
        "You can enter numbers in order to scroll through the dataset of hurricanes and view their properties.");
    System.out.println(
        "Press 'i' to enter insert mode, 's' to enter search mode, or 'x' to exit the program");


    String in = "";

    // // takes in input until x is entered
    while (in.compareTo("i") != 0 && in.compareTo("s") != 0 && in.compareTo("x") != 0) {
      try {
        // in = br.readLine();
        in = scan.next();

        // user adjusts start index for three movies here
        threeHurricanes = backend.getThreeHurricanes(Integer.parseInt(in));
        for (int i = 0; i < threeHurricanes.size(); i++) {
          System.out.println();
          System.out.format("%13s%15s%20s%8d%10d", threeHurricanes.get(i).getName(),
              threeHurricanes.get(i).getDateFormed(), threeHurricanes.get(i).getDateDissipated(),
              threeHurricanes.get(i).getMPH(), threeHurricanes.get(i).getDeaths());
        }
        System.out.println();

      } catch (Exception e) {
        // e.printStackTrace();
      }

    }
    // enters insert mode
    if (in.compareTo("i") == 0) {
      insertMode(backend);
    }

    // enters search mode
    if (in.compareTo("s") == 0) {
      searchMode(backend);
    }


  }

  /**
   * This method brings the user insert mode, where the program asks the user a set of questions to
   * create a new hurricane object and insert it into the dataset
   * 
   * @param backend instance of Backend that is being run
   */
  public void insertMode(Backend backend) {
    System.out.println("\nWelcome to Insert Mode.");
    System.out.println("Enter 'x' at anytime to exit Insert Mode");

    String in = "";
    ArrayList<String> hurricanePropertiesArr = new ArrayList<String>();
    for (int i = 0; i < 5; i++) {
      hurricanePropertiesArr.add(null);
    }
    // creates the set of questions for creating hurricane
    String[] instructions = new String[5];
    instructions[0] = "What is the hurricane's name?";
    instructions[1] = "What date was the hurricane formed? Enter as mm/dd/yyyy.";
    instructions[2] = "What date was the hurricane dissipated? Enter as mm/dd/yyyy.";
    instructions[3] = "What was the MPH of the hurricane?";
    instructions[4] = "How many deaths were caused by this hurricane?";

    boolean exitMode = false;
    // method resolves and returns to base mode when x is entered
    while (in.compareTo("x") != 0 && hurricanePropertiesArr.contains(null)) {
        try {
        for (int i = 0; i < 5; i++) {
          exitMode = false;
          boolean stepComplete = false;
          while (!stepComplete) {
            System.out.println(instructions[i]);
            // in = br.readLine();
            in = scan.next();
            if (in.compareTo("x") == 0) {
              exitMode = true;
              break;
            }
            // checking if inputs are correct format and then adding to hurricanPropertiesArr
            if (i == 0) {

              String[] test = in.split("");
              boolean allString = true;
              for (String val : test) {
                try {
                  Integer.parseInt(val);
                  allString = false;
                  break;
                } catch (Exception e) {
                  // expected behavior
                }
              }
              if (allString) {
                // String value, no error
                hurricanePropertiesArr.set(i, in);
                stepComplete = true;
              } else {
                // if int then redo because name cannot contain numbers
                System.out.println("Hurricane Names cannot contain numbers");

              }


            } else if (i <= 2) {
              try {
                Integer.parseInt(in);
                // if int then redo because incorrect format
                System.out.println("Please enter the date in the format mm/dd/yyyy");
                continue;
              } catch (Exception e) {
                // String value, no error
                if (in.split("/").length == 3) {
                  if (in.split("/")[0].length() == 2 && in.split("/")[1].length() == 2
                      && in.split("/")[2].length() == 4 && in.length() == 10) {
                    hurricanePropertiesArr.set(i, in);
                    stepComplete = true;
                  } else {
                    System.out.println("Please enter the date in the format mm/dd/yyyy");
                  }
                } else {
                  System.out.println("Please enter the date in the format mm/dd/yyyy");
                }
              }
            } else {
              try {
                Integer.parseInt(in);
                // if int no error
                hurricanePropertiesArr.set(i, in);
                stepComplete = true;
                continue;
              } catch (Exception e) {
                // mph and deaths cannot contain letters, only numbers
                System.out.println("You can only enter numbers for this category");
              }
            }

          }
          if (exitMode) {
            System.out.println("\nExiting Insert Mode...\n");
            break;
          }
        }
      } catch (Exception e) {
        // e.printStackTrace();
      }

    }

    if (!exitMode) {
      System.out.println("\nNew Hurricane was added to dataset...\n");
      // adding new hurricane to dataset
      backend.insert(new Hurricane(hurricanePropertiesArr.get(0), hurricanePropertiesArr.get(1),
          hurricanePropertiesArr.get(2), Integer.parseInt(hurricanePropertiesArr.get(3)),
          Integer.parseInt(hurricanePropertiesArr.get(4))));
    }
    baseMode(backend);
  }


  /**
   * This method brings the user to search mode, which is where they can search up a name of a
   * hurricane and retrieve that hurricane's properties
   * 
   * @param backend instance of Backend that is being run
   */
  public void searchMode(Backend backend) {
    System.out.println("\nWelcome to Search Mode.");
    System.out.println("Enter 'x' at anytime to exit the Search Mode.");
    System.out.println(
        "Search up a name of a Hurricane to look it up in the dataset and retrieve its properties.");
    String in = "";
    while (in.compareTo("x") != 0) {
      try {
        in = scan.nextLine();
        try {
          if (in.compareTo("x") == 0) {
            break;
          }
          // calls Backend.lookup() to retrieve hurricane object
          Hurricane hurricane = backend.lookup(in);
          System.out.format("%13s%15s%20s%8s%10s", "Name", "Date Formed", "Date Dissipated", "MPH",
              "Deaths");
          System.out.println();
          System.out.format("%13s%15s%20s%8d%10d", hurricane.getName(), hurricane.getDateFormed(),
              hurricane.getDateDissipated(), hurricane.getMPH(), hurricane.getDeaths());
          System.out.println();
        } catch (NoSuchElementException e) {
          // if NoSuchElementException was thrown, then the searched hurricane was not in the
          // dataset
          System.out.println("Hurricane is not in the dataset! Please try searching another name.");
        }
      } catch (Exception e) {
        // e.printStackTrace();
      }
    }
    System.out.println("\nExiting Search Mode...");
    baseMode(backend);

  }

}
