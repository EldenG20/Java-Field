import java.util.*;
import java.io.*;

/**
* This class is a utility class, used for reading and writing text files.
*
* @author Elden Garrett
* @version ver1.0.0
*/

public class FileIO
{
    private String fileName;

    /**
    * Default constructor which creates an object of the class FileIO.
    */
    public FileIO()
    {
        fileName = "";
    }

    /**
    * Non-default constructor which passes a new file name as a String.
    * @param newFileName    The new file name as a String.
    */
    public FileIO(String newFileName)
    {
        fileName = newFileName;
    }

    /**
    * Returns the file name.
    * @return   The filename of the FileIO object.
    */    
    public String getFileName()
    {
        return fileName;
    }
    
    /**
    * Sets the file name.
    * @param newFileName    The file name we wish to set for this FileIO object.
    */
    public void setFileName(String newFileName)
    {
        fileName = newFileName;
    }

    /**
    * Returns a String, which is the contents of the fileName object
    * @return   The contents of the filename of the FileIO object calling it as a String.
    */
    public String readFile()
    {
        String contents = "";
        if(fileName.trim().length() > 0)
        {
            try
            {
                FileReader inputFile = new FileReader(fileName);
                Scanner parser = new Scanner(inputFile);
                while(parser.hasNextLine())
                {
                    contents += parser.nextLine() + "\n";    
                }
                inputFile.close();
            }
            catch(FileNotFoundException exception)
            {
                System.out.println(fileName + " not found");
            }
            catch(IOException exception)
            {
                System.out.println("An unexpected I/O error was encountered!");
            }
        }
        else
            System.out.println("Please Enter a FileName");
        return contents;
    }

    /**
    * Writes the passed content String the fileName of the calling FileIO object.
    * @param contents   The information to be written to fileName as a String.
    */
    public void writeFile(String contents)
    {
        try
        {
            if(fileName.trim().length() > 0)
            {
                PrintWriter outputFile = new PrintWriter(fileName);
                outputFile.println(contents);
                outputFile.close();
            }
            else
                System.out.println("Please Enter a FileName");
        }
        catch(IOException exception)
        {
            System.out.println("An error was encountered while trying to write the data to the " + fileName + " file.");
        }
    }
}

