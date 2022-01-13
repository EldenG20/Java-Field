import java.util.*;
import java.lang.*;

/**
* This class is the command class of the program Java Field, and handles all user input.
* 
* @author Elden Garrett
* @version ver1.0.0
*/

public class Field
{
    //fields
    private Player player;
    private Player computer;
    private GameGrid field;
    
    //constructor
    /**
    * Default constructor for the Field class
    */
    public Field()
    {
    } 

    /**
    * Non-default constructor of the Field class.
    * @param player     The user's player as a Player object.
    * @param computer   The computer player as a Player object.
    * @param field      The game space as a GameGrid object.
    */
    public Field(Player player, Player computer, GameGrid field)
    {
        this.player = player;
        this.computer = computer;
        this.field = field;
    }

    /**
    * This method handles the player/computer choosing their next actions in 
    * the game until a victor has been decided.
    */
    public void beginGame()
    {
        boolean end = false;
        boolean playerTurn = true;
        do
        {           
                while (end == false)
                {
                    displayGame();
  
                    while (playerTurn)
                    {  
                        try
                        {
                            System.out.println("Press 1 to capture a grid square");
                            System.out.println("Press 2 to sabotage the enemy");
                            System.out.println("Press 3 to direct strike a heart");
                            System.out.println("Press 7 to exit game");
                            int choice = Input.acceptIntegerInput("Please select an action");
                            //validate and apply the choice of move 
                            if (Validation.intOutOfRange(choice, 1, 3) & choice != 7)
                            {
                                System.out.println("You must select an option that is within range");
                                Input.waitForEnter();
                            }
                            else
                            {
                                switch (choice)
                                {
                                    case 1:
                                    {
                                        captureGrid(player, computer);
                                        playerTurn = false;
                                        player.setTurnCounter(player.getTurnCounter() + 1);
                                        break;
                                    }
                                    case 2:
                                    {
                                        sabotageEnemy(player, computer);
                                        playerTurn = false;
                                        player.setTurnCounter(player.getTurnCounter() + 1);
                                        break;
                                    }
                                    case 3:
                                    {
                                        if (hasLineToOpponent(player, computer))
                                        {
                                            directStrike(player, computer, end);
                                            playerTurn = false;
                                            player.setTurnCounter(player.getTurnCounter() + 1);
                                        }
                                        else
                                        {
                                            System.out.println("You do not have a direct line to the opponent");
                                            Input.waitForEnter();
                                        }
                                        if (player.getHearts() == 0 | computer.getHearts() == 0)
                                            end = true; 
                                        //playerTurn = false;
                                        //player.setTurnCounter(player.getTurnCounter() + 1);
                                        break; 
                                    }
                                    case 7:
                                    end = true;
                                    System.out.println("You have chosen to exit. Goodbye.");
                                    playerTurn = false;
                                }
                            }
                        }
                        catch (Exception e)
                        {
                            System.out.println("Please choose a valid menu option.");
                        }
                        
                    }    
                    //computer's turn
                    if (end == false)
                    {    
                        while (playerTurn == false)
                        {
                            Random rand = new Random();
                            if (computer.getTurnCounter() == 0)
                            {
                                computer.setTurnCounter(computer.getTurnCounter() + 1);
                                captureGrid(computer, player);
                                playerTurn = true;
                            }
                            else if (hasLineToOpponent(computer, player))
                            {
                                computer.setTurnCounter(computer.getTurnCounter() + 1);
                                directStrike(computer, player, end);
                                if (player.getHearts() == 0 | computer.getHearts() == 0)
                                            end = true;
                                playerTurn = true;
                            }
                            else if (hasLineToOpponent(player, computer))
                            {
                                computer.setTurnCounter(computer.getTurnCounter() + 1);
                                sabotageEnemy(computer, player);
                                playerTurn = true;
                            }
                            else
                            {    
                                //we will make the computer attempt to capture a grid square 60% of the time
                                int compChoice = rand.nextInt(0, 99);
                                if (compChoice >= 39)
                                {
                                    computer.setTurnCounter(player.getTurnCounter() + 1);
                                    captureGrid(computer, player);
                                    playerTurn = true;
                                }
                                else
                                {
                                    computer.setTurnCounter(player.getTurnCounter() + 1);
                                    sabotageEnemy(computer, player);
                                    playerTurn = true;
                                }
                            }                        
                        }
                    }
                }
        } while (end == false);
    }

    /**
    * Creates the owner symbol from the player name input by the user.
    * Also converts the symbol to upper case for aesthetic purposes in game.
    * The owner symbol will be the first character entered by the user, excluding spaces.
    * @param playerName     The player name input by the user as a String.
    * @return               The derived owner symbol as a String.
    */
    public String createOwnerSymbol(String playerName)
    {
        String ownerSymbol = String.valueOf(playerName.trim().toUpperCase().charAt(0));
        return ownerSymbol;
    }

    /** 
    * The purpose of this method is to display the state of the game board in between turns to the user.
    */
    public void displayGame()
    {
        System.out.println("---------------------------------------------------" + 
                           "---------------------------------------------------");
        System.out.println("\n" + player.getOwnerSymbol() + " = " + player.getName() + ",  " + 
                            computer.getOwnerSymbol() + " = Computer Player\n");
        System.out.println("Name: " + player.getName() + " | Captured: "+
                            + player.getCaptured() + " | Lost: " + player.getLost() +
                            " | Attack: " + player.getAttack() + " | Defense: " + 
                            player.getDefense() + " | Hearts: "
                            + player.displayHearts() + " | Coins: " + player.getCoins());
        System.out.println("");
        System.out.print("         ");
        for (int i = 0; i < field.getGridSize(); i++)
        {
            System.out.print(String.format("%-18d",(i+1)));
        }
        System.out.println("");
        for (int i = 0; i < field.getGridSize(); i++)
        {
            System.out.print(String.format("%-2s",(i+1)));
            for (int j = 0; j < field.getGridSize(); j++)
            {
                System.out.print(field.selectGameTile((j + 1), (i + 1)).displayBrackets() + "  ");
            }
            System.out.print("  " + (i+1));
            System.out.println("");
        }
        System.out.println("");
        System.out.print("         ");
        for (int i = 0; i < field.getGridSize(); i++)
        {
            System.out.print(String.format("%-18d",(i+1)));
        }
        System.out.println("\n");
        System.out.println("Name: " + computer.getName() + " | Captured: "+
                            + computer.getCaptured() + " | Lost: " + computer.getLost() +
                            " | Attack: " + computer.getAttack() + " | Defense: " + 
                            computer.getDefense() + " | Hearts: "
                            + computer.displayHearts() + " | Coins: " + computer.getCoins());
        System.out.println("");
        System.out.println("---------------------------------------------------" + 
                           "---------------------------------------------------");
    }

    /**
    * This method is called to handle the "capturing a grid spot" action.
    * @param attacker   The attacking Player object for the action.
    * @param defender   The defending Player object for the action.
    */
    public void captureGrid(Player attacker, Player defender)
    {         
        System.out.println(attacker.getName() + " has chosen to capture a grid spot!\n");
        Random rand = new Random();
        boolean proceed = false;
        boolean owned = false;
        int x = 1;
        int y = 1;
        //determines the x, y coordinates of the contended grid square.
        if (attacker.getName().equals("Computer"))
        {
            do
            {
                x = rand.nextInt(1, field.getGridSize() + 1);
                y = rand.nextInt(1, field.getGridSize() + 1);
                if (field.selectGameTile(x, y).getOwner().equals("C"))
                    owned = true;
                else
                    owned = false;

            } while (owned);
        }
        else
        {    
            do 
            {
                try
                {
                    x = Input.acceptIntegerInput("Please enter the x coordinate:");
                    y = Input.acceptIntegerInput("Please enter the y coordinate");
                    if (Validation.intOutOfRange(x, 1, (field.getGridSize())) | 
                        Validation.intOutOfRange(y, 1, (field.getGridSize())) | 
                        field.selectGameTile(x, y).getOwner().equals(attacker.getOwnerSymbol()))
                    {
                        System.out.println("Please enter a coordinate that is within " + 
                        "the game space, that you do not own.");
                    }
                    else
                        proceed = true;
                }
                catch (Exception e)
                {
                    System.out.println("Error. Please enter a valid coordinate.");
                }
                
            } while (proceed == false);
        }
        //the dice roll itself
        winnerOfDiceRoll(attacker, defender, x, y);
    }
    
    /**
    * This method is used by the sabotage enemy method.
    * It decrements the defending Player object's attack by 2.
    * @param attacker   The attacking Player object for the action.
    * @param defender   The defending Player object for the action.
    */
    public void decrementAttack(Player attacker, Player defender)
    {
        Random rand = new Random();
        int cost = rand.nextInt(500, 1501);
        System.out.println(attacker.getName() + " has chose to decrement " + 
                           defender.getName() + "'s attack!");
        System.out.println("The cost of this move will be: " + cost + ".");
        if (attacker.getCoins() >= cost)
        {
            defender.setAttack(defender.getAttack() - 2);
            if (defender.getAttack() < 0)
                defender.setAttack(0);
            System.out.println(defender.getName() + "'s attack has decreased by 2!");
            attacker.setCoins(attacker.getCoins() - cost);
            Input.waitForEnter();
        }
        else
        {
            System.out.println(attacker.getName() + " has insufficient funds"
                             + ". They must attempt to capture a grid square.");
            Input.waitForEnter();
            captureGrid(attacker, defender);
        }
    }
    
    /**
    * This method is used by the sabotageEnemy method.
    * It decrements the defending Player object's defense by 2.
    * @param attacker   The attacking Player object for the action.
    * @param defender   The defending Player object for the action.
    */
    public void decrementDefense(Player attacker, Player defender)
    {
        Random rand = new Random();
        int cost = rand.nextInt(500, 1501);
        System.out.println(attacker.getName() + " has chose to decrement " + 
                           defender.getName() + "'s defense!");
        System.out.println("The cost of this move will be: " + cost + ".");
        if (attacker.getCoins() >= cost)
        {
            defender.setDefense(defender.getDefense() - 2);
            attacker.setCoins(attacker.getCoins() - cost);
            if (defender.getDefense() < 0)
                defender.setDefense(0);
            System.out.println(defender.getName() + "'s defense has decreased by 2!");
            Input.waitForEnter();
        }
        else
        {
            System.out.println(attacker.getName() + " has insufficient funds"
                             + ". They must attempt to capture a grid square.");
            Input.waitForEnter();
            captureGrid(attacker, defender);
        }
    }

    /**
    * This method performs the direct strike to the defending Player, reducing their hearts by 1.
    * @param attacker   The attacking Player object for the action.
    * @param defender   The defending Player object for the action.
    * @param end        A boolean which will be modified if the defending Player hearts reduces to 0.
    */
    public void directStrike(Player attacker, Player defender, boolean end)
    {       
        if (hasLineToOpponent(attacker, defender))
        {
            System.out.println(attacker.getName() + " has dealt a direct strike to " +
                               defender.getName() + "'s heart!");
            defender.setHearts(defender.getHearts() - 1);
            if (defender.getHearts() == 0)
            {
                System.out.println(attacker.getName() + " has won the game!");
                end = true;
            }
            Input.waitForEnter();
        }
        else
        {
            System.out.println(attacker.getName() + " does not have a direct path to the opposing player..");
            Input.waitForEnter();
        }
    }

    /**
    * This method will display the rules of the game Java Field to the user. 
    * Used once only at the start of the game.
    */
    public void displayIntro()
    {
        System.out.println("!+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=" + 
                           "+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+!");
        System.out.println("\n\t\t\t\t\t\tWELCOME TO JAVA FIELD! \n" );
        System.out.println("\t Your objective is simple!");
        System.out.println("\t Destroy the three hearts of your opponent before they destroy yours.");
        System.out.println("\t To strike a heart, you must first set a vertical "
                            + "path from one end of the field to the other\n" + 
                            "\t Only then can the hearts be damaged");
        System.out.println("\t Be warned, capturing a grid space is not easy, as your opponent will attempt"
                            + " to sabotage your efforts.\n\t But you may do the same to them!");
        System.out.println("\t Good luck!\n");
        System.out.println("!+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=" + 
                           "+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+!\n");
        System.out.println("Please press enter/return to continue.");

        Input.waitForEnter();
    }

    /**
    * Accessor method for the computer attribute.
    * @return   The computer attribute for the Field object as a Player object.    
    */
    public Player getComputer()
    {
        return this.computer;
    }

    /**
    * Accessor method for the field attribute.
    * @return   The field attribute for the Field object as a GameGrid object.
    */
    public GameGrid getField()
    {
        return this.field;
    }

    /**
    * Accessor method for the player attribute.
    * @return   The player attribute for the Field object as a Player object.
    */
    public Player getPlayer()
    {
        return this.player;
    }

    /**
    * This method evaluates whether the attacker has a direct line to the defender, 
    * in order to perform a direct strike on the defender's heart.
    * @param attacker   The attacking Player object for the action.
    * @param defender   The defending Player object for the action.
    * @return           Returns a boolean "true" if the attacker has a direct line, "false" if not.
    */
    public boolean hasLineToOpponent(Player attacker, Player defender)
    {
        boolean hasLineToOpponent = false;
            for (int i = 0; i < field.getGridSize(); i++)
            {
                int tempCount = 0;
                
                    for (int j = i; j < Math.pow(field.getGridSize(), 2); j += field.getGridSize())
                    {
                        if (field.getGameSpace()[j].getOwner().equals(attacker.getOwnerSymbol()))
                        {       
                            tempCount += 1;
                            if (tempCount == field.getGridSize())
                                hasLineToOpponent = true;
                        }
                        else 
                            tempCount = 0;  

                    }
            } 
        return hasLineToOpponent;
    }

    /**
     * Method to begin the program.
     * @param args  An array of Strings representing command line arguments.
     */
    public static void main(String[] args)
    {
        Field newGame = new Field();
        newGame.startProgram();
    }

    /**
    * This method passes reads the "boosts.txt" file and creates Tile objects
    * to apply to the GameGrid object's gameSpace.
    * @param choice     A String which will be either "Y", "y", "N", or "n", 
                        indicating whether or not the user wishes to play in Hardcore Mode  
    */
    private void readBoosts(String choice)
    {
        FileIO io = new FileIO("boosts.txt");
        Random rand = new Random();
        String[] input = io.readFile().split("\\n");
        //take the read string of line boosts.txt and split it into a String array
        for (int i = 0; i < input.length; i++)
        {
            String[] boostStatString = input[i].split(",");
            int[] boostStats = new int[3];
            for (int j = 0; j < 3; j++)
            {
                boostStats[j] = Integer.parseInt(boostStatString[j]);
            }

            Tile boostTile = new Tile(boostStats[0], boostStats[1], boostStats[2]);
            if (choice.equals("y") || choice.equals("Y"))
                field.boostGridTileHardcore(boostTile, field);
            else
                field.boostGridTile(boostTile, field);     
        }
    }

    /**
    * This method requests the dimensions of the Java Field play space.
    * It must be an integer between 3 and 10, inclusive.
    * @return               The dimensions integer successfully input by the user.
    *                       It will be a value between 3 and 10, inclusive.
    */
    public int requestDimensions()
    {
        int dimensions = 0;
        do 
        {
            try
            {
                dimensions = Input.acceptIntegerInput("Please enter the " + 
                                                  "size of the game grid."  +
                                                 " This must be an integer " + 
                                                 "between 3 and 10, inclusive.");
            }
            catch (Exception e)
            {
                System.out.println("Error.");
            }
        } while (dimensions < 3 || dimensions > 10);
        return dimensions;
    }

    /**
    * Requests from the user whether they would like to play in Hardcore Mode or not.
    * @return   A string which will contain either "Y", "y", "N", or "n".
    */
    public String requestHardcoreMode()
    {
        String choice = "n";
        boolean proceed = false;
        do 
        {
            try
            {
                System.out.println("Would you like to enable Hardcore Mode? It will stack boosts on the game grid" +
                                ", rather than overwriting boosts.\nThe recommended experience.");
                choice = Input.acceptStringInput("Please enter y or n: ").trim();
                if (Validation.isBlank(choice))
                    System.out.println("Choice cannot be blank.");
                else if (choice.equals("Y") | choice.equals("y") | choice.equals("N") | choice.equals("n"))
                    proceed = true;
                    //System.out.println("Please enter N or n if you don't wish to enable Hardcore Mode.");
                else
                    System.out.println("Please enter N or n if you don't wish to enable Hardcore Mode.");
            }
            catch (Exception e)
            {
                System.out.println("Error.");
            }
        } while (proceed == false);
        return choice;
    }

    /**
    * This method is used to request the name the user wishes to be known as. 
    * It must be between 3 and 12 characters long.
    * @return               The user's input name as a String, after validation checks on its length.
    */
    public String requestName()
    {
        String playerName = "";
        boolean proceed = false;
        do 
        {
            try
            {
                playerName = Input.acceptStringInput("Please enter your name: ").trim();
                if (Validation.isBlank(playerName) || Validation.lengthWithinRange(playerName, 3, 12))
                {
                    System.out.println("You must enter a name within 3 and 12 characters long.");
                }
                else 
                {
                    System.out.println("Thank you.");
                    proceed = true;
                }    
            }
            catch (Exception e)
            {
                System.out.println("Please enter a name as a String between 3 and 12 characters long.");
            }
        } while (proceed == false);
        return playerName;
    }

    /**
    * The sabotage enemy method. Either prompts the user for a sabotage method, 
    * or automates the process if the Player is a computer.
    * @param attacker   The attacking Player object for the action.
    * @param defender   The defending Player object for the action.
    */
    public void sabotageEnemy(Player attacker, Player defender)
    {
        System.out.println(attacker.getName() + " has chosen to sabotage " 
                            + defender.getName() + "!");
        if (attacker.getName() == "Computer")
        {
            Random rand = new Random();
            int compChoice = rand.nextInt(1, 101);
            if (hasLineToOpponent(defender, attacker))
            {
                sabotageGridSquare(attacker, defender);
            }
            else if (compChoice <= 40)
            {
                decrementAttack(attacker, defender);
            }
            else if (compChoice > 80)
            {
                sabotageGridSquare(attacker, defender);
            }
            else 
            {
                decrementDefense(attacker, defender);
            }
        }
        else
        {    
            boolean proceed = false;
            do
            {
                try
                {
                    System.out.println("Press 1 to decrement the opponents attack by 2");
                    System.out.println("Press 2 to decrement the opponents defense by 2");
                    System.out.println("Press 3 to sabotage an opponents grid square");
                    int choice = Input.acceptIntegerInput("Please select an action");
                    switch (choice)
                    {
                        case 1:
                        decrementAttack(attacker, defender);
                        proceed = true;
                        break;

                        case 2:
                        decrementDefense(attacker, defender);
                        proceed = true;
                        break;

                        case 3:
                        sabotageGridSquare(attacker, defender);
                        proceed = true;
                        break;

                        default:
                        System.out.println("Please select a valid sabotage option.");
                    }
                }
                catch (Exception e)
                {
                    System.out.println("Please select a valid sabotage option.");
                }
                
            } while (proceed == false);
            
            
        }
    }

    /**
    * The sabotage grid square action.
    * If the defending Player owns any tiles within the game, this method 
    * requests input coordinates from the user, and removes ownership of the 
    * Tile from the defending Player if it is a valid coordinate. 
    * Else, will ask the user for correct coordinates.
    * @param attacker   The attacking Player object for the action.
    * @param defender   The defending Player object for the action.
    */
    public void sabotageGridSquare(Player attacker, Player defender)
    {
        Random rand = new Random();
        System.out.println(attacker.getName() + " has chosen to sabotage " 
                           + defender.getName() + "'s grid position!");
        //establish whether there are any tiles owned by the opponent or not
        boolean noOwner = true;
        noOwner = tilesOwnedByOpponent(attacker, defender);
        if (noOwner)
        {
            sabotageEnemy(attacker, defender);
        }
        else 
        {    
            int x;
            int y;
            boolean playerOwned = false;

            if (attacker.getName().equals("Computer"))
            {
                do
                {
                    x = rand.nextInt(1, field.getGridSize() + 1);
                    y = rand.nextInt(1, field.getGridSize() + 1);
                    if (field.selectGameTile(x, y).getOwner().equals(defender.getOwnerSymbol()))
                        playerOwned = true;
                    else
                        playerOwned = false;

                } while (playerOwned == false);
                int cost = rand.nextInt(1000, 2501);
                System.out.println("The cost is: " + cost + " coins.\n");
                if (attacker.getCoins() >= cost)
                {
                    System.out.println(attacker.getName() + " has sabotaged " +
                                    defender.getName() + " at position: ("
                                    + x + ", " + y + ")!");
                    Input.waitForEnter();
                    attacker.setCoins(attacker.getCoins() - cost);
                    defender.setAttack(defender.getAttack() - field.selectGameTile(x, y).getAttackBoost());
                        defender.setDefense(defender.getDefense() - field.selectGameTile(x, y).getDefenseBoost());

                    field.selectGameTile(x, y).setOwner("");
                    defender.incrementLost();
                }
                else
                {    
                    System.out.println(attacker.getName() + " has insufficient funds"
                                    + ". They must attempt to capture a grid square.");
                    Input.waitForEnter();
                    captureGrid(attacker, defender);
                }
            }
            else
            {    
                System.out.println("Please enter the grid coordinates you would like to sabotage:");        
                boolean proceed = false;
                do 
                {
                    try
                    {
                        x = Input.acceptIntegerInput("Please enter the x coordinate:");
                        y = Input.acceptIntegerInput("Please enter the y coordinate:");
                        if (field.selectGameTile(x,y).getOwner().equals("C"))
                        {
                            int cost = rand.nextInt(1000, 2501);
                            System.out.println("The cost is: " + cost + " coins.");
                            if (attacker.getCoins() < cost)
                            {
                                System.out.println(attacker.getName() + " has insufficient funds.");
                                Input.waitForEnter();
                            }
                            else
                            {
                                System.out.println(attacker.getName() + " has sabotaged " +
                                                defender.getName() + " at position: ("
                                                + x + ", " + y + ")!");
                                Input.waitForEnter();
                                attacker.setCoins(attacker.getCoins() - cost);
                                field.selectGameTile(x, y).setOwner("");
                                defender.setAttack(defender.getAttack() - field.selectGameTile(x, y).getAttackBoost());
                                defender.setDefense(defender.getDefense() - field.selectGameTile(x, y).getDefenseBoost());
                                defender.incrementLost();
                            }
                            proceed = true;
                        }
                        else 
                        {
                            System.out.println("You must enter a grid position owned by the opponent.");
                            Input.waitForEnter();
                        }  
                    }
                    catch (Exception e)
                    {
                        System.out.println("Please enter valid coordinates.");
                    }
                    
                } while (proceed == false);  
            }       
        }                                  
    }
    /**
    * Mutator method for the computer attribute.
    * @param computer   The new Player object to be set for the computer opponent.
    */
    public void setComputer(Player computer)
    {
        this.computer = computer;
    }

    /**
    * Mutator method for the field attribute.
    * @param field      The new GameGrid object to be set for the field attribute.
    */
    public void setField(GameGrid field)
    {
        this.field = field;
    }

    /**
    * Mutator method for the player attribute.
    * @param player     The new Player object to be set for the user/player.
    */
    public void setPlayer(Player player)
    {
        this.player = player;
    }

    /**
    * This method is called by main() at the start of the program to begin the game.
    * Sets up the game conditions and calls methods to start the game and write 
    * the outcome to "outcome.txt" at the end of the game.
    */
    public void startProgram()
    {
        //display the intro to the game
        displayIntro();

        // Request and validate the player name 
        //ask user to enter a name between 3 and 12 characters, then create and initialise the player and computer objects
        String playerName = requestName();
        String ownerSymbol = createOwnerSymbol(playerName);

        //create player and computer objects
        player = new Player(playerName, ownerSymbol);
        computer = new Player("Computer", 5, 7, 10000, "C");
        if (player.getOwnerSymbol().equals("C"))
            computer.setOwnerSymbol("♣");
        
        //ask the user for the grid size between 3 and 10, and initialise the game grid.
        int dimensions = requestDimensions();
        field = new GameGrid(dimensions);

        //populate the game grid with default Tile objects.
        for (int i = 0; i < field.getGameSpace().length; i++)
        {
            field.getGameSpace()[i] = new Tile();
        }

        //assign boosts randomly to Tiles in the grid.
        String choice = requestHardcoreMode();
        readBoosts(choice);

        // begins the game proper until a winner is decided:
        beginGame();
        
        //prints the result of the game to outcome.txt
        writeFile();
    }

    /**
    * Assesses whether the opponent owns any Tiles in the gameSpace, to determine whether or not they
    * may attempt a "sabotage enemy grid square" move.
    * @param attacker   The attacking Player object.
    * @param defender   The opponent Player object. This is the object which
    *                   is being assessed for ownership of Tiles.
    * @return           Returns "true", if the opponent Player object does not hold 
    *                   any tiles in the GameGrid gameSpace yet.
    */
    public boolean tilesOwnedByOpponent(Player attacker, Player defender)
    {
        boolean noOwner = true;
        for (int i = 0; i < field.getGameSpace().length; i++)
            {
                if (field.getGameSpace()[i].getOwner().equals(defender.getOwnerSymbol()))
                {
                    noOwner = false;
                }
            }
        if (noOwner)
        {
            System.out.println(attacker.getName() + " cannot sabotage a grid square, because "
                                + defender.getName() + " doesn't own any.");
            Input.waitForEnter();
        }
        return noOwner;
    }

    /**
    * Handles the roll of the dice for contest of a grid tile and determines the winner.
    * @param attacker   The attacking Player object.
    * @param defender   The defending Player object.
    * @param x          The contested Tile objects x coordinate as an integer.
    * @param y          The contested Tile objects y coordinate as an integer.
    */
    public void winnerOfDiceRoll(Player attacker, Player defender, int x, int y)
    {
        Random rand = new Random();
        int attackDice1 = rand.nextInt(1, 7);
        int attackDice2 = rand.nextInt(1, 7);
        int attackDice3 = rand.nextInt(1, 7);
        int defenderDice1 = rand.nextInt(1, 7);
        int defenderDice2 = rand.nextInt(1, 7);
        
        //sum the result
        int totalAttack = attackDice1 + attackDice2 + attackDice3 + attacker.getAttack();
        int totalDefense = defenderDice1 + defenderDice2 + defender.getDefense();
        if (totalAttack > totalDefense)
            {    
                System.out.println(attacker.getName() + " has rolled a " + attackDice1 + 
                                    ", " + attackDice2 + " and a " + attackDice3
                                     + " for a total attack of " + totalAttack + ".");
                System.out.println(defender.getName() + " has rolled a " + defenderDice1 +
                                    " and a " + defenderDice2 + " for a total defense of "
                                    + totalDefense + ".");
                if (field.selectGameTile(x, y).getOwner().equals(defender.getOwnerSymbol()))
                {
                    defender.incrementLost();
                    defender.setAttack(defender.getAttack() - field.selectGameTile(x, y).getAttackBoost());
                    defender.setDefense(defender.getDefense() - field.selectGameTile(x, y).getDefenseBoost());
                }
                field.selectGameTile(x, y).setOwner(attacker.getOwnerSymbol());
                attacker.incrementCaptured();
                System.out.println(attacker.getName() + " has successfully captured position: (" + x + ", " + y + ")!!");
                attacker.setAttack(attacker.getAttack() + field.selectGameTile(x, y).getAttackBoost());
                attacker.setDefense(attacker.getDefense() + field.selectGameTile(x, y).getDefenseBoost());
                attacker.setCoins(attacker.getCoins() + field.selectGameTile(x, y).getCoinBoost());
                field.selectGameTile(x, y).setCoinBoost(0);
                Input.waitForEnter();
            }
            else
            {
                System.out.println(attacker.getName() + " has rolled a " + attackDice1 + 
                                    ", " + attackDice2 + " and a " + attackDice3
                                     + " for a total attack of " + totalAttack + ".");
                System.out.println(defender.getName() + " has rolled a " + defenderDice1 +
                                    " and a " + defenderDice2 + " for a total defense of "
                                    + totalDefense + ".");
                System.out.println(defender.getName() + " has successfully defended the grid position!");
                Input.waitForEnter();
            }
    }

    /**
    * This method writes the passed player information and victor to outcome.txt
    */
    private void writeFile()
    {
        String temp = "";
        StringBuffer buffer = new StringBuffer(temp);

        buffer.append("The final player stats: \n");
        buffer.append(player.displayEndOfGame() + "\n\n");
        if (computer.getHearts() == 0)
            buffer.append(player.getName() + " has won the game!");
        else if (player.getHearts() == 0)
            buffer.append("The Computer has won the game.");
        else 
            buffer.append("The player quit the game before a victor was decided.");
        FileIO io = new FileIO("outcome.txt");
        io.writeFile(buffer.toString());
    }    
}
