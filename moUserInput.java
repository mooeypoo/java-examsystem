package StudentDB;

import java.util.Scanner;
/**
 * This class is responsible for variable validation
 * and prompt input. 
 * 
 * @author Moriel Schottlender
 *
 */
public class moUserInput {
	private static Scanner keyboard = new Scanner(System.in);

	/**
	 * A method to repeatedly ask the user for input until 
	 * the input is valid. If condition is used, 
	 * input is measured against it.
	 * 
	 * @param informationText	The information text to prompt
	 * 							to the user.
	 * @return					Returns the final value of the accepted
	 * 							input, as an integer.
	 */
	public int askInputInt(String informationText) {
		Boolean error = false;
		String userInp = "";
		do {
			System.out.print(informationText);
			userInp = keyboard.nextLine();
			if (!this.isType(userInp, "int")) {
				error = true;
				System.err.println("Error: must be a whole number.");
			} else {
				error = false;
			}
		} while (error == true);
		return Integer.parseInt(userInp);
	}
	
	/**
	 * A method to repeatedly ask the user for input until 
	 * the input is valid. If condition is used, 
	 * input is measured against it.
	 * 
	 * @param informationText	The information text to prompt
	 * 							to the user.
	 * @return					Returns the final value of the accepted
	 * 							input, as a double.
	 */
	public double askInputDouble(String informationText) {
		Boolean error = false;
		String userInp = "";
		do {
			System.out.print(informationText);
			userInp = keyboard.nextLine();
			if (!this.isType(userInp, "double")) {
				System.err.println("Error: must be a number.");
				error = true;
			} else {
				error = false;
			}
			
		} while (error == true);
		return Double.parseDouble(userInp);
	}
	
	/**
	 * A method to repeatedly ask the user for input until 
	 * the input is valid. If condition is used, 
	 * input is measured against it.
	 * 
	 * @param informationText	The information text to prompt
	 * 							to the user.
	 * @return					Returns the final value of the accepted
	 * 							input, as a float.
	 */
	public float askInputFloat(String informationText) {
		Boolean error = false;
		String userInp = "";
		do {
			System.out.print(informationText);
			userInp = keyboard.nextLine();
			// validate:
			if (!this.isType(userInp, "float")) {
				System.err.println("Error: must be a number.");
				error = true;
			} else {
				error = false;
			}
			
		} while (error == true);
		return Float.parseFloat(userInp);
	}

	public Boolean askInputBoolean(String informationText) {
		Boolean exit = false;
		String userInp = "";
		do {
			System.out.print(informationText);
			userInp = keyboard.nextLine();
			if (userInp.equalsIgnoreCase("y") || userInp.equalsIgnoreCase("yes")) {
				exit = true;
				return true;
			} else if (userInp.equalsIgnoreCase("n") || userInp.equalsIgnoreCase("no")) {
				exit = true;
				return true;
			}
		} while (exit == true);
	
		return false;
	}

	/**
	 * Tests if a specific input can be converted to a specific type.
	 * 
	 * @param input The input to test. Accepts String, int, double or float.
	 * @param type	Which type to test against. Accepts 'int','float' or 'double'.
	 * @return Boolean	True if can be transformed to requested type. False otherwise.
	 */
	public Boolean isType(String inp, String type) {
		return _testType(inp,type);
	}

	/**
	 * Tests if a specific input can be converted to a specific type.
	 * 
	 * @param input The input to test. Accepts String, int, double or float.
	 * @param type	Which type to test against. Accepts 'int','float' or 'double'.
	 * @return Boolean	True if can be transformed to requested type. False otherwise.
	 */
	public Boolean isType(int inp, String type) {
		String strNum = Integer.toString(inp);
		return _testType(strNum,type);
	}

	/**
	 * Tests if a specific input can be converted to a specific type.
	 * 
	 * @param input The input to test. Accepts String, int, double or float.
	 * @param type	Which type to test against. Accepts 'int','float' or 'double'.
	 * @return Boolean	True if can be transformed to requested type. False otherwise.
	 */
	public Boolean isType(float inp, String type) {
		String strNum = Float.toString(inp);
		return _testType(strNum,type);
	}

	/**
	 * Tests if a specific input can be converted to a specific type.
	 * 
	 * @param input The input to test. Accepts String, int, double or float.
	 * @param type	Which type to test against. Accepts 'int','float' or 'double'.
	 * @return Boolean	True if can be transformed to requested type. False otherwise.
	 */
	public Boolean isType(double inp, String type) {
		String strNum = Double.toString(inp);
		return _testType(strNum,type);
	}

	/**
	 * The engine behind 'isType' function. This tests the input based
	 * on the requested type.
	 * 
	 * @param testStr	The string to test
	 * @param type		The type to test against: int, float, double
	 * @return			true if the string was converted successfully, 
	 * 					false otherwise.
	 */
	private Boolean _testType(String testStr, String type) {
		try {
			if (type.equalsIgnoreCase("float")) {
				Float.parseFloat(testStr);
			} else if (type.equalsIgnoreCase("int")) {
				Integer.parseInt(testStr);
			} else if (type.equalsIgnoreCase("double")) {
				Double.parseDouble(testStr);
			}
			return true;
		} catch(Exception e) {
			return false;
		}
		
	}
	
}
