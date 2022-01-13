/**
* This class creates Tile objects which are used in the gameGrid.
* Tile objects store attack, defense, and coin boost values, and have an owner.
*
* @author Elden Garrett
* @version ver1.0.0
*/
public class Tile
{
    //fields
    private int attackBoost;
    private int defenseBoost;
    private int coinBoost;
    private String owner;

    //constructors
    /**
    * Default constructor which creates an object of the class Tile.
    */
    public Tile()
    {
        attackBoost = 0;
        defenseBoost = 0;
        coinBoost = 0;
        owner = "";
    }

    /**
    * Non-default constructor which creates an object of the class Tile.
    * @param attackBoost    accepts the Tile object's attack boost stat as an integer
    * @param defenseBoost   accepts the Tile object's defense boost stat as an integer
    * @param coinBoost      accepts the Tile object's coin boost stat as an integer
    */
    public Tile(int attackBoost, int defenseBoost, int coinBoost)
    {
        this.attackBoost = attackBoost;
        this.defenseBoost = defenseBoost;
        this.coinBoost = coinBoost;
        owner = "";
    }

    //methods
    /**
    * display method to display the stat of the Tile
    * @return   the state of the Tile object as a String
    */
    public String display()
    {
        return "The state of this tile is:\n" + "Attack Boost: " 
                + this.attackBoost + "\nDefense Boost: " + this.defenseBoost 
                + "\nCoin Boost: " + this.coinBoost + "\nOwner: " + this.owner;
    }
    
    /**
    * the purpose of this method is an alternative display to print all of
    * the relevant game stats of the Tile object within brackets in one line, 
    * for use in the displayGame method in Field
    * @return   An alternative display of the state of the Tile object for use in the displayGame method
    */
    public String displayBrackets()
    {
        return "(" + String.format("%2d", this.attackBoost) + "," + 
                String.format("%2d", this.defenseBoost) + 
                "," + String.format("%5d", this.coinBoost) + ", " + 
                String.format("%1s", this.owner) + ")";
    }

    /**
    * accessor method to get the attackboost of a tile
    * @return   Attack boost stat of the Tile object as an integer.
    */
    public int getAttackBoost()
    {
        return attackBoost;
    }

    /**
    * accessor method to get the coinBoost of a tile
    * @return   Coin boost stat of the Tile object as an integer.
    */
    public int getCoinBoost()
    {
        return coinBoost;
    }

    /**
    * accessor method to get the defenseBoost of a tile
    * @return   Defense boost stat of the Tile object as an integer.
    */
    public int getDefenseBoost()
    {
        return defenseBoost;
    }

    /**
    * accessor method to get the Owner of a tile
    * @return   Owner of the Tile object as a String.
    */
    public String getOwner()
    {
        return owner;
    }

    /**
    * mutator method to set the attackBoost of a Tile object
    * @param attackBoost    The new attackBoost stat of the object as an integer.
    */
    public void setAttackBoost(int attackBoost)
    {
        this.attackBoost = attackBoost;
    }

    /**
    * mutator method to set the coinBoost of a Tile object
    * @param coinBoost    The new coinBoost stat of the object as an integer.
    */
    public void setCoinBoost(int coinBoost)
    {
        this.coinBoost = coinBoost;
    }

    /**
    * mutator method to set the defenseBoost of a Tile object
    * @param defenseBoost    The new defenseBoost stat of the object as an integer.
    */
    public void setDefenseBoost(int defenseBoost)
    {
        this.defenseBoost = defenseBoost;
    }

    /**
    * mutator method to set the owner of a Tile object
    * @param owner    The new owner symbol of the object as a String.
    */
    public void setOwner(String owner)
    {
        this.owner = owner;
    }
}
