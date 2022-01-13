/**
* This class is a utility class, used to validate inputs of the user for 
* compliance with the games requirements
*
* @author Elden Garrett
* @version ver1.0.0
*/

public class Validation
{
    //fields

    //constructors
    /**
    * Default Constructor for the Validation class.
    */
    public Validation()
    {
    }

    //methods
    /**
    * This method returns true if the input integer is out of the specified range
    * @param inputInt   The integer to be evaluated.
    * @param min        The minimum of the specified range as an integer.
    * @param max        The maximum of the specified range as an integer.
    * @return           Returns whether the inputInt is out of range (true) or not (false), as a boolean.
    */
    public static boolean intOutOfRange(int inputInt, int min, int max)
    {
        if (inputInt < min || inputInt > max)
            return true;
        else
            return false;
    }

    /**
    * This method returns the boolean "true" if the input String is blank
    * @param inputString    The String to be evaluated.
    * @return               Boolean value. "True" if the string is blank, "false" if it is not.
    */
    public static boolean isBlank(String inputString)
    {
        if (inputString.trim().equals(""))
            {
                return true;
            }
        else 
        {
            return false;
        }
    }

    /**
    * This method returns the boolean "true" if the input String is out of the specified length range.
    * @param inputString    The String to be evaluated.
    * @param min            The minimum length of the string as an integer.
    * @param max            The maximum length of the string as an integer.
    * @return               Boolean value. "True" if the input String is out of range, "false" if it is not.
    */
    public static boolean lengthWithinRange(String inputString, int min, int max)
    {
        if (inputString.trim().length() < min || inputString.trim().length() > max)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
