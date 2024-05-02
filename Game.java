import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Game {
    public int nCharacters;
    public ArrayList<Human> characters; 
    public ArrayList<Human> enemies; 
    public static boolean gameOver;
    public Scanner sc; // create a scanner for the entire game class
    ArrayList<String> recentActions; // saves the most recent actions
    
    public Game(int nCharacters){
        this.nCharacters = nCharacters;
        this.characters = new ArrayList<>(nCharacters);
        this.enemies = new ArrayList<>();
        Game.gameOver = false;
        this.sc = new Scanner(System.in);
        this.recentActions = new ArrayList<>(3);
    }

    //getRandomNumber within range
    public int getRandomNumber(int min, int max){
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
    
    //overloaded getRandomNumber without range
    public int getRandomNumber(){
        Random random = new Random();
        return random.nextInt();
    }

    // adds a character
    public void addCharacter(String name){
        // randomly sample character's stats
        int health = getRandomNumber(0, 5);
        int experience = getRandomNumber(0, 5);
        int alliance = getRandomNumber(0, 1);
        // add new character to list of characters
        this.characters.add(new Human(name, health, experience, alliance, false)); 
    }

    // method to create a new enemy and add to list of enemies
    public Human addEnemy(){
        ArrayList<String> enemyNames = new ArrayList<String>(Arrays.asList("Frog", "Hulk", "Tigress", "Chameleon", "Boss Wolf", "Dmitri", "Mei Ling", "Bian Zo"));
        ArrayList<String> enemyDescriptors = new ArrayList<String>(Arrays.asList("The Terror", "The Shadow", "The Silent", "The Bloody", "The Savage", "The Nefarious", "The Mutilator", "The Cyclone"));
        // randomly sample stats
        int health = getRandomNumber(0, 5);
        int experience = getRandomNumber(0, 5);

        // create a new name
        int i = getRandomNumber(0, enemyNames.size());
        int j = getRandomNumber(0, enemyDescriptors.size());
        String name = enemyNames.get(i) + ", " +enemyDescriptors.get(j);

        Human enemy = new Human(name, health, experience, 0, true);
        this.enemies.add(enemy); 
        return enemy; 
    }

    // method to save the most recent action called  (battle/train/campfire)
    public void saveRecentAction(String mode){
        if (recentActions.size() > 2){
            recentActions.remove(0);
        }
        recentActions.add(mode);
    }

    // TO-DO: functions to check if user can battle, train or campfire



    // advance battle by one more attack
    public boolean advanceBattle(boolean fight, Human protagonist, Human villain){
        //if any character dies, you lose the battle
        if (!protagonist.isAlive()){
            System.out.println(protagonist.name + " is dead. The enemy triumphed. BATTLE OVER.");
            return false; // the battle is over
        }

        // if the character is alive, check if enemy is dead
        else{
            // if the enemy is dead, the character has won the battle
            if (!villain.isAlive()){
                System.out.println("Congrats! You have won the battle");
                return false; // battle is over and advanceBattle is no longer true
            }

            // else either return an attack or retreat
            else{
                // return an attack
                if (fight){
                    protagonist.attack(villain);
                }

                // retreat
                else{
                    System.out.println("The captain has ordered the troop to retreat");
                    System.out.println(protagonist);
                }
                return true; // battle not over; can still advance battle
            }
        }
    }

    // implements a training session
    public void train(){
        boolean battleOngoing = true; // status of battle
        ArrayList<Human> fightingPair = new ArrayList<>(2); // the fighting pair
        ArrayList<Integer> initialHealth = new ArrayList<>(2); // keeps track of proponent and opponent's health

        // Ask the user for the opponent and proponent 
        System.out.println("Which pair of your characters would you like to match against each other?");
        String chr1 = sc.nextLine();
        String chr2 = sc.nextLine();

        // TO-DO: check if characters are in game first. Also make sure that the nCharacters > 0

        // find proponent and opponent in ArrayList characters and save their health
        for (Human character: characters){
            if (character.name.equals(chr1)){
                fightingPair.add(character);
                initialHealth.add(character.health);
            }
            else if (character.name.equals(chr2)){
                fightingPair.add(character);
                initialHealth.add(character.health);
            }
        } 

        Human proponent = fightingPair.get(0);
        Human opponent = fightingPair.get(1);

        // while a particular battle is ongoing
        while(battleOngoing == true){
        // opponent attacks proponent
        opponent.attack(proponent);

        // proponent returns the attack 
        battleOngoing = this.advanceBattle(true, proponent, opponent); 
        }

        // TO-DO: allow user to end training while both characters are still alive. Copy from the Training file

        // update characters' experience based on battle outcome
        // proponent and opponent die
        if (!proponent.isAlive() && !opponent.isAlive()){
            System.out.println("Both characters lost their lives. More training is recommended");
            proponent.experience += 5;
            opponent.experience += 5;
        }

        // proponent dies, opponent doesn't
        else if (!proponent.isAlive() && opponent.isAlive()){
            System.out.println(opponent.name + " won the battle.");
            proponent.experience += 5;
            opponent.experience += 10;
        }

        // proponent doesn't die, opponent does
        else if (proponent.isAlive() && !opponent.isAlive()){
            System.out.println(opponent.name + " lost the battle.");
            proponent.experience += 10;
            opponent.experience += 5;
        }

        // reinstate their health
        proponent.health = initialHealth.get(0);
        opponent.health = initialHealth.get(1);

        // TO-DO: show character updated stats after a training session

        // save the action train
        saveRecentAction("train");
    }


    // implements a real battle session. Takes in a particular enemy as a parameter
    public void battle(Human enemy){
        boolean battleOngoing = true; // status of battle

        // while a particular battle is ongoing
        while(battleOngoing == true){
            // enemy randomly chooses a character to attack
            int i = this.getRandomNumber(0, this.characters.size());
            enemy.attack(this.characters.get(i));

            // Ask the user if they wish to attack or retreat. Call advanceBattle based on user response
            System.out.println("Do you wish to return the attack or check in with your troop? (attack/retreat)");
            String nextMove = sc.nextLine().toLowerCase();

            // force the user to enter a valid option
            while (!(nextMove.equals("attack") || nextMove.equals("retreat"))){
                System.out.println("You have not entered a valid option. Try again. You can either attack or retreat.");
                nextMove = this.sc.nextLine();
            }

            if (nextMove.equals("attack")){
                battleOngoing = this.advanceBattle(true, this.characters.get(i), enemy); // TO-DO: allow a different chr return attack
            }
            else if (nextMove.equals("retreat")){
                battleOngoing = this.advanceBattle(false, this.characters.get(i), enemy);
            }
        }

        // save the action battle
        saveRecentAction("battle");
    }    

    public static void main(String[] args) {
        Game game = new Game(3);

        System.out.println("Your game has started.");

        // TO-DO: add in short description of game objective and what is expected of the player

        // name your characters
        System.out.println("You will now choose your troop. Enter any " + game.nCharacters + " names.");
        // String characterNames
        for (int i = 0; i < game.nCharacters; i++){
            game.addCharacter(game.sc.nextLine());
        }

        // allows player see a well-formatted output of characters' stats 
        System.out.println("\nHere's a decription of your troop commander: ");
        for (Human character : game.characters){
            System.out.println(character);
        }

        // while the game isn't over
        while(!gameOver){
            // Allow player choose next move based on characters' stats
            System.out.println("\nBased on your troop's stats, what would you like to do: train, engage in battle or have a campfire with your troop?");
            String cmd = game.sc.nextLine(); // check in with Jordan about this in OH. It's fine

            while (!(cmd.equals("battle") || cmd.equals("train") || cmd.equals("campfire"))){
                System.out.println("You have not entered a valid option. Try again. You can train, battle or have a campfire");
                cmd = game.sc.nextLine();
            }

            // allows player to switch between different modes in a game
            switch (cmd){
                case "battle":
                // TO-DO: implement a method that check if troop is eligible for option. If troop is eligible, then battle. Else, say can't battle. Must train or camp
                Human enemy = game.addEnemy();
                // TO-DO: print out enemy, and ask user one last time to battle or train
                System.out.println("\nToday, you will battle " + enemy.name + ".\n" + enemy);
                game.battle(enemy);
                break;

                case "train":
                // TO-DO: implement a method that check if troop is eligible for option. If troop is eliible, then train
                game.train();
                break;

                case "campfire":
                // TO-DO: implement a method that check if troop is eligible for option. If troop is eliible, then camp
                //game.campfire(false);
                break;

                // default case
                default:
                System.out.println("You have not chosen a valid option. Try again");
            }

            Game.gameOver = true;
        }   
    }
}

// NOTES on game mechanics:
// In a real battle, variuos characters attack. In training, there is always only two characters

// TO-DO:

// OH: 
// In the battle case, should I display enemy's stats and give the user another chance to battle or train?

// IMMEDIATE
// implement cantrain, canBattle and canCampfire
// implement a condition for gameOver: for now, after three battles, game ends. If you win two of those, you win the game
// write up all the text needed: 
// - at the beginning of game
// - enemies descriptions
// - chracters description

// FINE POINTS
// description of game when game starts
// edit attack methods to work according to original design
// change the range of characters & enemy stats
// should the user know characters' alliance or not?
//  a help menu of what a player can do e.g. q to quit

// LATER
// change the prinout when the captain need to check in with players
// allow player choose which character should return an enemy's attack in "real" battles
// throw an exception that keeps asking for an input if the user types in a response that's not part of the options given without returning to the top of the loop or exiting the loop
// allow the user to end a training session when both characters are still alive

// ON DESIGN
// Figure out how to generate random numbers in a more efficient way

// EXTENSIONS
// Allow chrs train against old enemies battled
// Display enemy's stats to user and double check if they wish to battle or not (in the switch case method)
// Add in audio while player is in battle mode (battle music) and campfire (crackling of a fire)