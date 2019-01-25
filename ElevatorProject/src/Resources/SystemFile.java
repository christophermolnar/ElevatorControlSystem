package Resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

/*
 * SystemFile - Responsible for validating each line in the systems input file.
 *  Creates Messages with the valid requests
 */
public class SystemFile {

	public final static int LOWESTFLOOR = 0; // Temporary variable for lowest
	// floor
	public final static int HIGHESTFLOOR = 10; // Temporary variable for highest
	// floor
	private String filename;

	/*
	 * SystemFile() - Creates a SystemFile object with the name of the file it wants
	 * to input
	 * 
	 * @param: String - filename
	 */
	public SystemFile(String filename) {
		this.filename = filename;
		this.readValidateAndCreateMessages();
	}

	/*
	 * validateFloor - Validates that the floor is within the upper and lower
	 * boundary
	 * 
	 * @param: floor - The floor to be checked return:
	 * 
	 * @return: Boolean - True if the floor falls within the allowable range,
	 * otherwise False
	 */
	private Boolean validateFloorRange(int floor) {
		return (floor >= LOWESTFLOOR && floor <= HIGHESTFLOOR);
	}

	/*
	 * readValidateAndCreateMessages - reads file using readFile() and the validates
	 * and creates messages using validateLine()
	 */
	private void readValidateAndCreateMessages() {
		ArrayList<String> requests = readFile();
		for (int position = 0; position < requests.size(); position++) {
			validateLine(requests.get(position));
		}
	}

	/*
	 * ValidateLine - Validate the string it gets passed. Valid if contains time,
	 * start floor, direction and destination floor
	 * 
	 * @param: String lineInfo - The line it will be validating return: Boolean
	 * 
	 * @return- True if message created, otherwise False
	 */
	private Boolean validateLine(String lineInfo) {
		String[] messageDetails = new String[4];
		try {
			messageDetails = lineInfo.split(" ");

			LocalTime time = LocalTime.parse(messageDetails[0]);

			int startingFloor = Integer.parseInt(messageDetails[1]);

			Directions direction;

			if (messageDetails[2].equalsIgnoreCase("up")) {
				direction = Directions.UP;

			} else if (messageDetails[2].equalsIgnoreCase("down")) {
				direction = Directions.DOWN;
			} else // If not "up" or "down" it's an invalid direction
			{
				System.out.println("Invalid Direction");
				System.out.println("at " + new Exception().getStackTrace()[0].toString());
				System.out.println("Line Format: hh:mm:ss:nnnn startFloor direction endFloor");
				System.out.println("Ex: 14:05:15.22 2 Up 4 \n");
				return false;
			}

			int destinationFloor = Integer.parseInt(messageDetails[3]);

			if (validateFloorRange(startingFloor) && validateFloorRange(destinationFloor)) {
				Message message = new Message(time, startingFloor, direction, destinationFloor);
				return true;
			}
		} catch (PatternSyntaxException pse) {
			System.out.println("Invalid Message Format");
			pse.printStackTrace();
		} catch (DateTimeParseException dtpe) {
			System.out.println("Invalid time enetered");
			dtpe.printStackTrace();
		} catch (NumberFormatException nfe) {
			System.out.println("The Floor wasn't an Integer");
			nfe.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException obe) {
			System.out.println("Invalid Message Entry");
			obe.printStackTrace();
		}

		System.out.println("Line Format: hh:mm:ss:nnnn startFloor direction endFloor");
		System.out.println("Ex: 14:05:15.22 2 Up 4 \n");
		return false;
	}

	/*
	 * readFile() - Reads the file stored in the filename attribute line by line
	 * 
	 * @return: ArrayList<String> - The parsed file line by line
	 */
	private ArrayList<String> readFile() {
		Scanner scanner;
		ArrayList<String> requestsFromInputFile = new ArrayList<String>();
		try {
			File n = new File("src/Resources/" + filename);
			scanner = new Scanner(n);
			while (scanner.hasNextLine()) {
				requestsFromInputFile.add(scanner.nextLine());

			}
			scanner.close();
			return requestsFromInputFile;
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
		return requestsFromInputFile;
	}

	public static void main(String[] args) {
		SystemFile s = new SystemFile("elevatorInputFile.txt");

		// Example of how to retrieve MessagesQueue
		Queue<Message> que = Message.getMessageQueue();
		while (que.peek() != null) {
			Message m2 = que.remove();
			System.out.println(m2.toString());
		}
	}

    /*
     * testValidateLine -public method for testing validateLine()
     * 
     * @param: String lineInfo - The line it will be validating return: Boolean
     * 
     * @return- True if message created, otherwise False
     */
    public Boolean testValidateLine(String lineInfo) {
	return this.validateLine(lineInfo);
    }
}
