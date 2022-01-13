import java.util.*;

/**
* This class creates Tile objects which are used in the gameGrid.
* Tile objects store attack, defense, and coin boost values, and have an owner.
*
* @author Elden Garrett
* @version ver1.0.0
*/

public class Input
{
    /**
    * Default constructor for the Input class.
    */
    public Input()
    {
    }

    /**
    * This method accepts single character inputs from the user via the keyboard.
    * @param inputString    A message displaying the kind of information they need to input with the keyboard.
    * @param index          The character at which we wish to accept user input. Usually set to 0.
    * @return               The user's input character.
    */
    public static char acceptCharInput(String inputString, int index)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(inputString);
        String charString = console.nextLine().trim();
        return charString.charAt(index);
    }

    /**
    * This method accepts a double from the user.
    * @param inputString    A message displaying the kind of information they need to input with the keyboard.
    * @return               The user's input double.
    */
    public static double acceptDoubleInput(String inputString)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(inputString);
        return Double.parseDouble(console.nextLine().trim());
    }

    /**
    * This method accepts an integer from the user.
    * @param inputString    A message displaying the kind of information they need to input with the keyboard.
    * @return               The user's input integer.
    */
    public static int acceptIntegerInput(String inputString)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(inputString);
        return Integer.parseInt(console.nextLine().trim());
    }

    /**
    * This method accepts a String from the user.
    * @param inputString    A message displaying the kind of information they need to input with the keyboard.
    * @return               The user's input String.
    */
    public static String acceptStringInput(String inputString)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(inputString);
        return console.nextLine();
    }

    /**
    * This method is used to allow the user to read what has been printed on to the screen.
    * The program will pause until they press the return/enter key.
    * @return               The user's return/enter input.
    */
    public static String waitForEnter()
    {
        Scanner console = new Scanner(System.in);
        return console.nextLine();
    }

}
