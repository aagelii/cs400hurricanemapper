// --== CS400 File Header Information ==--
// Name: <Justin Ochalla>
// Email: <jochalla@wisc.edu>
// Team: <GD blue>
// Role: <Data Wrangler>
// TA: <Surabhi>
// Lecturer: <Dahl>
// Notes to Grader: <optional extra notes>
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class HurricaneDataReader {

	public static List<HurricaneInterface> readDataSet(Reader inputFileReader) throws FileNotFoundException, IOException {
		List<HurricaneInterface> result = null; //creates a list to store the hurricane objects on
		List<String[]> info = new ArrayList<String[]>(); //creates a list of string arrays to store the data read from the file
		try {
			//initializes a few variables and lists to read the data and store it
			result = new ArrayList<HurricaneInterface>(); 
			BufferedReader bRead = new BufferedReader(inputFileReader);
			String[] curInfoList = new String[5];
			String curLine;
			bRead.readLine(); //reads the first line of labels
			int counter=0; //initializes a counter to track which data it is currently on
			while((curLine = bRead.readLine())!=null) { //uses a while loop to keep reading lines until the whole file has been read
				for(int i=0;i<4;i++) { //loops through the first 4 pieces of data
					//gets the index of the end of the first string of data, then stores it in the list and resizes the string to remove that data
					int index = curLine.indexOf(","); 
					curInfoList[i] = curLine.substring(0,index);
					index++;
					curLine = curLine.substring(index);
				}
				curInfoList[4]=curLine; //stores the last info in the current info list
				//adds the current info list to the list of all info, then creates a hurricane object and adds it to the data, then increments the counter
				info.add(curInfoList);
				HurricaneInterface newHur = new Hurricane((info.get(counter)[0]),(info.get(counter)[1]),(info.get(counter)[2]),Integer.parseInt(info.get(counter)[3]),Integer.parseInt(info.get(counter)[4]));
				result.add(newHur);
				counter++;
			}	 //catches a file not found and io exception and returns an error in both cases
		}catch(FileNotFoundException e) {
				throw new FileNotFoundException("ERROR: The file was not found.");
			} catch(IOException e){
			throw new IOException("ERROR: The file cannot be opened.");
		} 
		//returns the resulting list
		return result;
	}
}
