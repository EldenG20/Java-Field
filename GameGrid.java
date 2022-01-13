import java.util.*;
import java.lang.Math;

/**
* This class creates the game space of the game as a fixed-size array of Tile objects.
* A fixed size collection was chosen becuase once the game grid is initialised, it's size does not change.
*
* @author Elden Garrett
* @version ver1.0.0
*/

public class GameGrid
{
    //fields

    //gridSize is static as it will be set once at the start of the game 
    //and we wish to easily refer to the variable throughout other methods
    private Tile[] gameSpace;
    private static int gridSize; 

    /**
    * Default constructor of GameGrid
    */
    public GameGrid()
    {
        gameSpace = new Tile[9];
        gridSize = 3;
    }

    /**
    * Non-default constructor of GameGrid.
    * @param gridSize   The dimensions of the square Java Field game space as an integer.
    */
    public GameGrid(int gridSize)
    {
        this.gameSpace = new Tile[(int) Math.pow(gridSize, 2)];
        this.gridSize = gridSize;
    }

    //methods
    /**
    * This method randomly assigns a boost Tile object to the game space.
    * @param boostTile  An unnassigned Tile object which has had its fields modified by "boosts.txt".
    * @param field      The game space as a GameGrid object.
    */
    public void boostGridTile(Tile boostTile, GameGrid field)
    {
        Random rand = new Random();
        int boostIndex = rand.nextInt(0, (int) field.getGameSpaceSize());
        field.getGameSpace()[boostIndex] = boostTile;
    }

    /**
    * Adds the passed Tile stats to a random Tile in the passed GameGrid object.
    * @param boostTile  A Tile created with stats read from "boosts.txt".
    * @param field      The gameSpace as a GameGrid object. 
    */
    public void boostGridTileHardcore(Tile boostTile, GameGrid field)
    {
        Random rand = new Random();
        int boostIndex = rand.nextInt(0, (int) field.getGameSpaceSize());
        field.getGameSpace()[boostIndex].setAttackBoost(field.getGameSpace()[boostIndex].getAttackBoost() + boostTile.getAttackBoost());
        field.getGameSpace()[boostIndex].setDefenseBoost(field.getGameSpace()[boostIndex].getDefenseBoost() + boostTile.getDefenseBoost());
        field.getGameSpace()[boostIndex].setCoinBoost(field.getGameSpace()[boostIndex].getCoinBoost() + boostTile.getCoinBoost());
    }
    
    /**
    * Accessor method to get the gamespace field.
    * @return   The gameSpace field as a fixed size Tile array.
    */
    public Tile[] getGameSpace()
    {
        return gameSpace;
    }

    /**
    * Accessor method to get the length of the gameSpace
    * @return   The length of the gamespace Tile array as an integer. Equal to the input gridsize squared.
    */
    public int getGameSpaceSize()
    {
        return gameSpace.length;
    }

    /**
    * Accessor method to get the gridSize
    * @return   The dimensions of the game space as an integer.
    */
    public  int getGridSize()
    {
        return gridSize;
    }

    /**
    * The purpose of this method is for the user to input the specific x,y coordinates they want to interact with, 
    * and the method returns the Tile object from the gameSpace Tile array using a calculation.
    * @param x  The x input coordinate as an integer.
    * @param y  The y input coordinate as an integer.
    * @return   The specific Tile object within the gameSpace the user wished to access.
    */
    public Tile selectGameTile(int x, int y)
    {
        int index = (x + ((y - 1) * gridSize)) - 1;
        return this.gameSpace[index];
    }

}
