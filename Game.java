import java.util.Scanner;

import com.google.common.graph.Traverser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;

public class Game {
    public int nCharacters;
    public ArrayList<Character> characters; 
    public ArrayList<Human> enemies; 
    public static boolean gameOver;
    public Scanner sc; // create a scanner for the entire game class
    public ArrayList<HashtablePair<Hashtable<String, String>, Hashtable<String, String>>> scripts; //an ArrayList of the four possible hashtable pair scripts
    public ArrayList<String> recentActions; // saves the most recent actions
    public int successfulBattles; // saves number of battles won so far
    
    public Game(int nCharacters){
        this.nCharacters = nCharacters;
        this.characters = new ArrayList<>(nCharacters);
        this.enemies = new ArrayList<>();
        Game.gameOver = false;
        this.recentActions = new ArrayList<>(3);
        this.successfulBattles = 0; 
        this.sc = new Scanner(System.in);
        // Zoe testing out networks
        Hashtable<String, String> one = new Hashtable<String, String>();
            one.put("beginning", "Hello. My name is Farfelle. I'm a warrior from the Far Woods.");
            one.put("option 1", "Ah you should one day.");
            one.put("option 2", "You've visited? I miss it.");
            one.put("option 2.1", "The way the fall leaves would scatter on the ground.");
            one.put("option 1.1", "Only a day's ride");
            one.put("last option", "Testing the last string.");
        Hashtable<String, String> two = new Hashtable<String, String>();
            two.put("A", "A - I've never been.");
            two.put("B", "B - I went there once as a child.");
            two.put("B.A", "A - What do you miss most about it?");
            two.put("A.A", "A - How far away is it?");
            two.put("last edge", "A - last edge test 1");
            two.put("last edge 2", "A - last edge test 2");
        // Hashtable<String, String> three = ;
        // Hashtable<String, String> four = ;
        // Hashtable<String, String> five = ;
        // Hashtable<String, String> six = ;
        // Hashtable<String, String> seven = ;
        // Hashtable<String, String> eight = ;
        this.scripts = new ArrayList<HashtablePair<Hashtable<String, String>, Hashtable<String, String>>>();
            this.scripts.add(new HashtablePair<>(one, two));
            //this.scripts.add(new HashtablePair<>(three, four));
            //this.scripts.add(new HashtablePair<>(five, six));
            //this.scripts.add(new HashtablePair<>(seven, eight));

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
        HashtablePair<Hashtable<String, String>, Hashtable<String, String>> script = scripts.get(0);//getRandomNumber(0,4));
        // add new character to list of characters
        this.characters.add(new Character(name, health, experience, alliance, false, script)); 
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

    //lists out the characters in the user's party
    public void listCharacters(){
        int x = 0;
        for (Character option : characters){
            x += 1;
            System.out.println(x + ". " + option.name);
        }
    }
    
    // TO-DO: functions to check if user can battle, train or campfire
    // after 1st action, if last action was not battle, you can battle
    public boolean canBattle(){
        // no action yet? good to go!
        if (recentActions.size() < 1){
            return true; 
        }
        // after 1st action, if last action was battle, return false
        else{
            return !(recentActions.get(-1).equals("battle")); 
        }
    }

    // after 1st action, if last action was not battle and last 3 actions contain battle, return true
    public boolean canTrain(){
        // no action yet? good to go!
        if (recentActions.size() < 1){
            return true; 
        }
        else{
            // if you have less than 3 actions and the last action was not battle, you're fine
            if (recentActions.size() < 3){
                return !(recentActions.get(-1).equals("battle"));
            }
            // if you have up to 3 actions, 
            else{
                return recentActions.contains("battle");
            }
        }
    }

    public boolean canCampFire(){
        if (recentActions.size() == 0){
            return true; 
        }
        else if (recentActions.size() > 1){

        }
        return true;
    }


    // advance battle by one more attack
    public boolean advanceBattle(boolean fight, Character protagonist, Human villain){
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
                successfulBattles += 1;
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
        ArrayList<Character> fightingPair = new ArrayList<>(2); // the fighting pair
        ArrayList<Integer> initialHealth = new ArrayList<>(2); // keeps track of proponent and opponent's health

        // Ask the user for the opponent and proponent 
        System.out.println("Which pair of your characters would you like to match against each other?");
        String chr1 = sc.nextLine();
        String chr2 = sc.nextLine();

        // TO-DO: check if characters are in game first. Also make sure that the nCharacters > 0

        // find proponent and opponent in ArrayList characters and save their health
        for (Character character: characters){
            if (character.name.equals(chr1)){
                fightingPair.add(character);
                initialHealth.add(character.health);
            }
            else if (character.name.equals(chr2)){
                fightingPair.add(character);
                initialHealth.add(character.health);
            }
        } 

        Character proponent = fightingPair.get(0);
        Character opponent = fightingPair.get(1);

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

    public void campfire(){    
        //TO-DO: make sure this works in game loop
        System.out.println("You are camping with your troop in preparation for the next day's battle.");
        //asks user what character they want to talk to
        System.out.println("Which character would you like to talk to?");
        //lists out the characters
        this.listCharacters();
        String talkToName = this.sc.nextLine();
        
        //selects that character from the list of characters
        Character character = null;
        for (Character option : characters){
            if (talkToName.contains(option.name)){
                System.out.println("successfully chose which character");
                character = option;
                break;
            }
        }
        //prints network
        character.talk();

        //prints current location of user in that character's dialogue tree
        System.out.println("Current Location: " + character.currentLocation);
        System.out.println("Current location: " + character.dialogueScript.get(character.currentLocation));

        //TO-DO: replace while loop with three turn condition - if player has reached end of dialogue tree, print that statement
        //while loop to ask player for dialogue options
        int check = 0;
        //character.dialogue.successors(character.currentLocation).size() != 0
        while (check <= 3){
            check += 1;
            //ask for user input
            System.out.println("\n Pick a response:");
            //iterate through the edges in current location
            Iterator<String> iterator = character.dialogue.outEdges(character.currentLocation).iterator();
            while (iterator.hasNext()){
                String line = iterator.next();
                System.out.println(character.edgeScript.get(line));
            }
            String userInput2 = sc.nextLine().toUpperCase(); //"A";

            //testing validity of input
            boolean validInput = false;

            //for each edge connected to beginning node
            for (String option: character.dialogue.outEdges(character.currentLocation)){
                System.out.println("This is your edge object:");
                System.out.println(option);
                //if user input is equal to one of the edges' first characters
                if (userInput2.charAt(0) == character.edgeScript.get(option).toString().charAt(0)){
                    //update current location of user in character's dialogue network
                    character.currentLocation = character.dialogue.incidentNodes(option).target();
                    System.out.println("\n Your new location is: " + character.currentLocation);
                    System.out.println("Your new location is: " + character.dialogueScript.get(character.currentLocation));
                    System.out.println(character.dialogue.successors(character.currentLocation));
                    validInput = true;
                    break;
                }
            }
            //checks if user input is valid
            if (validInput == false){
                System.out.println("That's not a valid user input. Enter A or B");
            }
        }

    //if no more options, break loop
    if(character.dialogue.successors(character.currentLocation) == null){
        System.out.println("success");
    }
    
    System.out.println("Dawn has arrived, and with it, your next action. You will have to wait until the next campfire to talk to this person again.");

    //increase character's alliance
    character.alliance += 5;
    
    //TO-DO: increase character's alliance based on how far down they get in the character's dialogue graph
    //System.out.println(character.dialogue.successors(character.currentLocation));

    //traverse options in hashtable graph
    // Traverser.forGraph(character.dialogue).breadthFirst(character.currentLocation)
    // .forEach(x->System.out.println(x));

    // Traverser.forGraph(character.dialogue).breadthFirst(initialLocation)
    // .forEach(x->System.out.println(x));

    // save the action campfire
    saveRecentAction("campfire");
    }

    public static void main(String[] args) {
        Game game = new Game(3);
        int numOfBattles = 0;

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
        for (Character character : game.characters){
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
                numOfBattles += 1;
                break;

                case "train":
                // TO-DO: implement a method that check if troop is eligible for option. If troop is eliible, then train
                game.train();
                break;

                case "campfire":
                // TO-DO: implement a method that check if troop is eligible for option. If troop is eliible, then camp
                game.campfire();
                break;

                // default case
                default:
                System.out.println("You have not chosen a valid option. Try again");
            }

            if (numOfBattles >= 3){
                Game.gameOver = true;
                System.out.println("----------------------------GAME OVER------------------------------------------------");
                if (game.successfulBattles >= 2){
                    System.out.println("CONGRATULATIONS! YOU WON THE GAME.");
                }
                else{
                    System.out.println("SORRY, YOU LOST THE GAME. BETTER LUCK NEXT TIME");
                }
            }
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