import java.lang.Math;
import java.util.*;

/**
* This class creates the Player characters in the game.
*
* @author Elden Garrett
* @version ver1.0.0
*/

public class Player
{
    //fields
    private String name;
    private int attack;
    private int defense;
    private int coins;
    private int hearts;
    private int turnCounter;
    private int captured;
    private int lost;
    private String ownerSymbol;
    

    //constructors
    /**
    * Default constructor for the Player class.
    */
    public Player()
    {
        this.name = "unknown";
        this.attack = 5;
        this.defense = 7;
        this.coins = 3000;
        this.hearts = 3;
        this.turnCounter = 0;
        this.captured = 0;
        this.lost = 0;
        this.ownerSymbol = "";
    }
    /**
    * The non-default constructor used for the user player.
    * @param playerName     The name input by the user as a String.
    * @param ownerSymbol    The owner symbol derived from playerName entered as a String.
    */
    public Player(String playerName, String ownerSymbol)
    {
        this.name = playerName;
        this.attack = 5;
        this.defense = 7;
        this.coins = 3000;
        this.hearts = 3;
        this.turnCounter = 0;
        this.captured = 0;
        this.lost = 0;
        this.ownerSymbol = ownerSymbol;
    }

    /**
    * The non-default constructor used for the computer opponent
    * @param compName   The name of the computer opponent as a String.
    * @param attack     The attack stat of the computer as an integer.
    * @param defense    The defense stat of the computer as an integer.
    * @param coins      The number of starting coins of the computer as an integer.
    * @param ownerSymbol The owner symbol used in the display of the game space in between turns.
    */
    public Player(String compName, int attack, int defense, int coins, String ownerSymbol)
    {
        this.name = compName;
        this.attack = attack;
        this.defense = defense;
        this.coins = coins;
        this.hearts = 3;
        this.turnCounter = 0;
        this.captured = 0;
        this.lost = 0;
        this.ownerSymbol = ownerSymbol;
    }

    //methods 
    
    /**
    * This method is a display method to return the stats of the player/computer as a string.
    * @return   The state of the Player object as a String.
    */
    public String display()
    {
        return "Name: " + this.name + "\n" +
                "Attack: " + this.attack + "\n" + "Defense: " + this.defense +
                "\nCoins: " + this.coins + "\nHearts: " + this.hearts + 
                "\nMoves played: " + this.turnCounter + "\nCaptured: " +
                this.captured + "\nLost: " + this.lost;
    }

    /**
    * A display method for the Player object at the end of the game, to be printed to "outcome.txt".
    * @return   The particular state fields of the Player object as a String, as required by the Assessment.
    */
    public String displayEndOfGame()
    {
        return "Name: " + this.name + "\nMoves played: " + this.turnCounter +
                "\nAttack: " + this.attack + "\nDefense: " + this.defense 
                 + "\nCaptured: " + this.captured + "\nLost: " + this.lost;
    }

    /**
    * Displays the number of hearts in a graphic form, rather than an integer.
    * @return           The heart display graphic as a String.
    */
    public String displayHearts()
    {
        String heart = "";
        if (this.hearts == 3)
            heart = "♥ ♥ ♥";
        else if (this.hearts == 2)
            heart = "♥ ♥";
        else if (this.hearts == 1)
            heart = "♥";
        else
            heart = "";
        return heart;
    }

    /**
    * Accessor method for the attack of the Player object.
    * @return   The attack of the Player object as an integer.
    */
    public int getAttack()
    {
        return this.attack;
    }

    /**
    * Accessor method for the number of Tiles captured by the Player object.
    * @return   The number of Tiles captured by the Player object as an integer. 
    */
    public int getCaptured()
    {
        return this.captured;
    }

    /**
    * Accessor method for the Player coins
    * @return   The number of coins currently held by the Player object as an integer.
    */
    public int getCoins()
    {
        return this.coins;
    }

    /**
    * Accessor method for the Player Defense
    * @return   The defense stat of the Player object as an integer.
    */
    public int getDefense()
    {
        return this.defense;
    }

    /**
    * Accessor method for the current number of hearts of the Player object.
    * @return   The current number of hearts of the Player object as an integer.
    */
    public int getHearts()
    {
        return this.hearts;
    }

    /**
    * Accessor method for the number of Tiles lost that werh previously held by the Player object.
    * @return   The number of Tiles lost by the Player object, that were previously held.
    */
    public int getLost()
    {
        return this.lost;
    }

    /**
    * Accessor method for the Player object's name
    * @return   The Player object name as a String.
    */
    public String getName()
    {
        return this.name;
    }

    /**
    * Accessor method for the owner symbole of the Player object.
    * @return   The owner symbol of the Player object as a String.
    */
    public String getOwnerSymbol()
    {
        return this.ownerSymbol;
    }

    /**
    * Accessor method for the turn counter of the Player object.
    * @return   The number of turns played by the Player object as an integer.
    */
    public int getTurnCounter()
    {
        return this.turnCounter;
    }

    /**
    * Increments the number of captured Tiles of the Player object.
    */
    public void incrementCaptured()
    {
        this.captured++;
    }

    /**
    * Increments the number of lost Tiles of the Player object.
    */
    public void incrementLost()
    {
        this.lost++;
    }

    /**
    * Mutator method for the attack stat of the Player object.
    * @param attack     The new attack stat as an integer.
    */
    public void setAttack(int attack)
    {
        this.attack = attack;
    }

    /**
    * Mutator method for the number of captured Tiles
    * @param captured   The new number of captured Tiles of the Player object as an integer.
    */
    public void setCapture(int captured)
    {
        this.captured = captured;
    }

    /**
    * Mutator method for the number of coins held by the Player object.
    * @param coins      The new number of coins held by the Player object as an integer.
    */
    public void setCoins(int coins)
    {
        this.coins = coins;
    }

    /**
    * Mutator method for the defense of the Player object.
    * @param defense    The new defense of the Player object as an integer.
    */
    public void setDefense(int defense)
    {
        this.defense = defense;
    }

    /**
    * Mutator method for the number of hearts held by the Player.
    * @param hearts     The new number of hearts of the Player object as an integer.
    */
    public void setHearts(int hearts)
    {
        this.hearts = hearts;
    }

    /**
    * Mutator method for the number of Tiles lost by the Player object.
    * @param lost       The new number of Tiles lost by the Player object as an integer.
    */
    public void setLost(int lost)
    {
        this.lost = lost;
    }

    /**
    * Mutator method for the name of the Player object.
    * @param name       The new name of the Player object as a String.
    */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
    * Mutator method for the owner symbol of the Player object.
    * @param ownerSymbol    The new owner symbol of the Player object as a String.
    */
    public void setOwnerSymbol(String ownerSymbol)
    {
        this.ownerSymbol = ownerSymbol;
    }

    /**
    *Mutator method for the turn counter of the Player object.
    * @param turnCounter    The new value of the turn counter of the Player object as an integer.
    */
    public void setTurnCounter(int turnCounter)
    {
        this.turnCounter = turnCounter;
    }

    
}
