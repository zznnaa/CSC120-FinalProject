import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;

public class Game {
    public int nCharacters; //number of characters
    public ArrayList<Character> characters; //list of characters
    public ArrayList<String> characterNames; //list of character names
    public ArrayList<Human> enemies; //list of enemies
    public static boolean gameOver; //game end condition
    public Scanner sc; 
    public ArrayList<HashtablePair<Hashtable<String, String>, Hashtable<String, String>>> scripts; //an ArrayList of the four possible hashtable pair scripts
    public ArrayList<String> recentActions; //list of recent actions
    public int successfulBattles;
    
    public Game(int nCharacters) {
        this.nCharacters = nCharacters;
        this.characters = new ArrayList<>(nCharacters);
        this.characterNames = new ArrayList<>(nCharacters);
        this.enemies = new ArrayList<>();
        Game.gameOver = false;
        this.recentActions = new ArrayList<>(3);
        this.successfulBattles = 0; 
        this.sc = new Scanner(System.in);
        //character #1 script - character responses
        Hashtable<String, String> one = new Hashtable<String, String>();
            one.put("Beginning", "You commanded that last battle terribly. I've seen infants do better.");
            one.put("Option 1", "Miraculously. You almost tripped and fell face first onto your sword.");
            one.put("Option 2", "Yes.");
            one.put("Option 3", "You would send us all up to look for Pan before I had the chance.");
            one.put("Option 4", "Is that not what I'm doing?");
            one.put("Option 5", "I've noticed most Imperialists don't take kindly to my advice.");
            one.put("Option 6", "Yet you never seem to have trouble making us fight your battles.");
            one.put("Option 7", "I don't want to see them die");
        //character #1 script - user responses
            Hashtable<String, String> two = new Hashtable<String, String>();
            two.put("A", "A - What do you mean? We won.");
            two.put("B", "B - You think you could do a better job?");
            two.put("1.A", "A - Farwoodsian to a fault, give me a break.");
            two.put("1.B", "B - Well the Imperial Crown isn't going to just let me quit, so you might try helping me improve instead.");
            two.put("2.A", "A - You're Farwoodsian to a fault - give me a break.");
            two.put("2.B", "B - Well the Imperial Crown isn't going to just let me quit, so you might try helping me improve instead.");
            two.put("4.A", "A - Uhhh, sure.");
            two.put("4.B", "B - Oh, you're from the Far Woods.");
            two.put("5.A", "A - Most don't take kindly to anyone from the Far Woods, unfortunately.");
            two.put("5.B", "B - Why keeo giving it then?");
        //character #2 script - character responses
            Hashtable<String, String> three = new Hashtable<String, String>();
            three.put("Beginning", "You skinned this squirrel nicely. All it needs to be perfect is a little tamarind juice and paprika.");
            three.put("Option 1", "Devina, the Academy. Undercooking your food as well as your brains.");
            three.put("Option 2", "Second favorite, but the Imperium's never made the first correctly.");
            three.put("Option 3", "You mean tilapia, or catfish. But everyone only catches them to sell as an Imperial export now.");
            three.put("Option 4", "Devina? Most Lakers don't bother trying out for the Academy.");
            three.put("Option 5", "And now it'll never leave him.");
            three.put("Option 6", "But when was the last time they let him visit home?");
            three.put("Option 7", "I tried going home once. They knew me from a photo in their recruitment ads and chased me out of the resturant I was eating at, calling me an Imperialist.");
        //character #2 script - user responses
            Hashtable<String, String> four = new Hashtable<String, String>();
            four.put("A", "A - Anything on sticks is pretty nice. I always liked the Academy's cubes with green sauce.");
            four.put("B", "B - That a favorite meal from home?");
            four.put("1.A", "A - We're not all bad! Just last week Anji was showing me the fresh fish out of the Back Lakes.");
            four.put("1.B", "B - My friend Anji kept sneaking me out to try to teach me how to cook. I didn't know a pot from a frying pan, and they kept saying, \"I know you're Imperium, but Devina I didn't think it was *this* bad.\"");
            four.put("2.A", "A - We're not all bad! Just last week Anji was showing me fresh fish out of the Back Lakes.");
            four.put("2.B", "B - My friend Anji kep sneaking me out to try to teach me how to cook. I can't tell a pot from a frying pan, and they kept saying, \"Devina, I know you're Imperium, but I didn't think it was *this* bad.\"");
            four.put("4.A", "A - It took him in after the Lakeside Wars.");
            four.put("4.B", "B - He thought he could change it.");
            four.put("5.A", "A - He's done good there, a Laker graduating at 18, getting assigned a regiment at 20.");
            four.put("5.B", "B - It's afraid if everyone goes home, they'll never want to come back!");
        this.scripts = new ArrayList<HashtablePair<Hashtable<String, String>, Hashtable<String, String>>>();
            this.scripts.add(new HashtablePair<>(one, two));
            this.scripts.add(new HashtablePair<>(three, four));
    }

    /**
     * Randomly generate a number within a given range
     * @param min lower bound of desired range
     * @param max upper bound of desired range
     * @return the randomly generated number
     */
    public int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
    
    /**
     * Overloaded method to randomly generate method when the range is not specified
     * @return the randomly generated number
     */
    public int getRandomNumber() {
        Random random = new Random();
        return random.nextInt();
    }

    /**
     * Creates a new game character by randomly sampling stats and assigning a name based on the user's input
     * @param name name of the character to be added
     */
    public void addCharacter(String name) {
        // randomly sample character's stats
        int health = getRandomNumber(15, 50);
        int experience = 0;
        int alliance = 0;
        int random = getRandomNumber(0,10);
        int scriptNum = 1;
        if (random < 5) {
            scriptNum = 0;
        }
        HashtablePair<Hashtable<String, String>, Hashtable<String, String>> script = scripts.get(scriptNum);
        // add new character to list of characters
        this.characters.add(new Character(name, health, experience, alliance, false, script)); 
    }

    /**
     * Creates a new enemy by randomly sampling a name and enemy's stats
     * @return the enemy created
     */
    public Human addEnemy() {
        ArrayList<String> enemyNames = new ArrayList<String>(Arrays.asList("Frog", "Hulk", "Tigress", "Chameleon", "Boss Wolf", "Dmitri", "Mei Ling", "Bian Zo"));
        ArrayList<String> enemyDescriptors = new ArrayList<String>(Arrays.asList("The Terror", "The Shadow", "The Silent", "The Bloody", "The Savage", "The Nefarious", "The Mutilator", "The Cyclone"));
        // randomly sample stats
        int health = getRandomNumber(15, 50);
        int experience = getRandomNumber(1, 5);

        // create a new name
        int i = getRandomNumber(0, enemyNames.size());
        int j = getRandomNumber(0, enemyDescriptors.size());
        String name = enemyNames.get(i) + ", " +enemyDescriptors.get(j);

        Human enemy = new Human(name, health, experience, 0, true);
        this.enemies.add(enemy); 
        return enemy; 
    }

    /**
     * Saves the most recent mode user enter's- train, campfire or battle
     * @param mode
     */
    public void saveRecentAction(String mode) {
        if (recentActions.size() > 2) {
            recentActions.remove(0);
        }
        recentActions.add(mode);
    }

    /**
     * Lists out living characters in the user's troop
     */
    public void listCharacters() {
        int x = 0;
        for (Character option : characters) {
            x += 1;
            System.out.println(x + ". " + option.name);   
        }
    }
    
    /**
     * Checks if the user can battle or not
     * @return a boolean representing whether the user can engage in a battle
     */
    public boolean canBattle() {
        // no action yet? good to go!
        if (recentActions.size() < 1) {
            return true; 
        }
        // after 1st action, if last action was battle, return false
        else {
            return !(recentActions.get(recentActions.size() - 1).equals("battle")); 
        }
    }

    /**
     * Checks if the user can train or not
     * @return a boolean representing whether the user can train
     */
    public boolean canTrain() {
        if (characters.size() < 2) {
            System.out.println("You have less that 2 soldiers left.");
            return false; 
        }
        else {
             // no action yet? good to go!
            if (recentActions.size() < 1) {
                return true; 
            }
            else {
                // if you have less than 3 actions and the last action was not battle, you're fine
                if (recentActions.size() < 3) {
                    return !(recentActions.get(recentActions.size()-1).equals("battle"));
                }
                // if your last 3 actions contains battle and your last action was not battle, you're fine
                else {
                    return recentActions.contains("battle") && !(recentActions.get(recentActions.size() - 1).equals("battle"));
                }
            }
        }
    }

    /**
     * Checks if the user is allowed to enter campfire mode or not
     * @return a boolean representing whether the user can choose to start a campfire
     */
    public boolean canCampFire() {
        // no action yet? good to go!
        if (recentActions.size() < 1) {
            return true; 
        }
        else {
            // if you have less than 3 actions and the last action was not campfire, you're fine
            if (recentActions.size() < 3) {
                return !(recentActions.get(recentActions.size() - 1).equals("campfire"));
            }
            // if your last 3 actions contains battle and your last action was not campfire, you're fine
            else {
                return recentActions.contains("battle") && !(recentActions.get(recentActions.size() - 1).equals("campfire"));
            }
        }
    }


    /**
     * Advances a battle by one more attack
     * @param fight checks if the user wishes to return an attack or check characers' stats
     * @param protagonist the "hero" in a particular battle or training session
     * @param villain the "enemy" in a particular battle or training session
     * @param isTraining determines if the ongoing batttle a real battle session or training sesison
     * @return whether or not the battle has ended
     */
    public boolean advanceBattle(boolean fight, Character protagonist, Human villain, boolean isTraining){
        if (isTraining == true) {
            // vilain or protagonist dies, training over
            if (!protagonist.isAlive() || !villain.isAlive()) {
                System.out.println("******TRAINING OVER******\n");

                Human winner; 
                Human loser; 

                if (protagonist.isAlive()) {
                    winner = protagonist;
                    loser = villain;
                }
                else {
                    winner = protagonist;
                    loser = villain;
                }
                System.out.println(winner.name + " defeated " + loser.name);
                return false; // the battle is over
            }

            // else, keep fighting
            else {
                protagonist.attack(villain);
                return true;
            } 
        }
        else {
            //if any character dies, you lose the battle. Remove the dead character from game too
            if (!protagonist.isAlive()) {
                System.out.println(protagonist.name + " is dead. The enemy triumphed. BATTLE OVER.");
                characters.remove(protagonist);
                return false; // the battle is over
            }

            // if the character is alive, check if enemy is dead
            else {
                // if the enemy is dead, the character has won the battle
                if (!villain.isAlive()) {
                    System.out.println("Congrats! You have won the battle");
                    successfulBattles += 1;
                    return false; // battle is over and advanceBattle is no longer true
                }

                // else either return an attack or check stats
                else {
                    // return an attack
                    if (fight) {
                        protagonist.attack(villain);
                    }

                    // check stats
                    else{
                        System.out.println(protagonist);
                    }
                    return true; // battle not over; can still advance battle
                }
            }
        }

    }

    /**
     * Simulates a training session
     */
    public void train() {
        boolean battleOngoing = true; // status of battle
        ArrayList<Character> fightingPair = new ArrayList<>(2); // the fighting pair
        ArrayList<Integer> initialHealth = new ArrayList<>(2); // keeps track of proponent and opponent's health

        // Ask the user for the opponent and proponent 
        System.out.println("Which pair of your characters would you like to match against each other?");
        this.listCharacters();
        String chr1 = sc.nextLine();
        String chr2 = sc.nextLine();

        // Force user to enter names that are in the game
        while (!(characterNames.contains(chr1) || characterNames.contains(chr2))) {
            System.out.println("You must match soldiers that are currently in your troop.");
            chr1 = sc.nextLine();
            chr2 = sc.nextLine();
        }
        
        // find proponent and opponent in ArrayList characters and save their health
        for (Character character: characters) {
            if (character.name.equals(chr1)) {
                fightingPair.add(character);
                initialHealth.add(character.health);
            }
            else if (character.name.equals(chr2)) {
                fightingPair.add(character);
                initialHealth.add(character.health);
            }
        } 

        Character proponent = fightingPair.get(0);
        Character opponent = fightingPair.get(1);

        // while a particular battle is ongoing
        while(battleOngoing == true) {
            // opponent attacks proponent
            opponent.attack(proponent);

            // proponent returns the attack 
            battleOngoing = this.advanceBattle(true, proponent, opponent, true); 
        }

        // update characters' experience based on battle outcome
        // proponent and opponent die
        if (!proponent.isAlive() && !opponent.isAlive()) {
            System.out.println("Both characters lost their lives. More training is recommended");
            proponent.experience += 5;
            opponent.experience += 5;
        }

        // proponent dies, opponent doesn't
        else if (!proponent.isAlive() && opponent.isAlive()) {
            proponent.experience += 3;
            opponent.experience += 5;
        }

        // proponent doesn't die, opponent does
        else if (proponent.isAlive() && !opponent.isAlive()) {
            proponent.experience += 5;
            opponent.experience += 3;
        }

        // reinstate their health (subtract 2 health as a result of the training)
        proponent.health = initialHealth.get(0) - 2;
        opponent.health = initialHealth.get(1) - 2;

        // show character updated stats after a training session
        System.out.println(proponent);
        System.out.println(opponent);

        // save the action, train
        saveRecentAction("train");
    }


    /**
     * Simulates a real battle session
     * @param enemy the enemy to be fought
     */
    public void battle(Human enemy) {
        boolean battleOngoing = true; // status of battle

        // while a particular battle is ongoing
        while(battleOngoing == true) {
            // enemy randomly chooses a character to attack
            int i = this.getRandomNumber(0, this.characters.size());
            enemy.attack(this.characters.get(i));

            // Ask the user if they wish to attack or check stats. Call advanceBattle based on user response
            System.out.println("Do you wish to return the attack or check in with your troop? (attack/check stats)");
            String nextMove = sc.nextLine().toLowerCase();

            // force the user to enter a valid option
            while (!(nextMove.equals("attack") || nextMove.equals("check stats"))){
                System.out.println("You have not entered a valid option. Try again. You can either attack or check stats.");
                nextMove = this.sc.nextLine().toLowerCase();
            }

            if (nextMove.equals("attack")) {
                battleOngoing = this.advanceBattle(true, this.characters.get(i), enemy, false); // TO-DO: allow a different chr return attack
            }
            else if (nextMove.equals("check stats")) {
                battleOngoing = this.advanceBattle(false, this.characters.get(i), enemy, false);
            }
        }
        // save the action, battle
        saveRecentAction("battle");
    }    

    /**
     * Simulates a conversation between the player and a character from their party.
     */
    public void campfire() {    
        Character character = null;
        System.out.println("You are camping with your troop in preparation for the tomorrow's battle.");
        
        //replenish every character's health (except if they are dead)
        for (Character option: characters){
            option.health = option.maxHealth;
        }
        
        //asks user what character they want to talk to
        System.out.println("Which character would you like to talk to?");
        //lists out the characters
        this.listCharacters();
        
        //user selects character from the list of characters
        boolean chooseCharacter = false;
        while (chooseCharacter == false) {
            //asks for user input
            String talkToName = this.sc.nextLine();
            //iterates through character list
            for (Character option : characters) {
                //checks if user input contains the character's name
                if (talkToName.contains(option.name)) {
                    //if so, allows user to choose that character if dialogue options are remaining 
                    if (!option.dialogue.successors(option.currentLocation).isEmpty()) {
                        character = option;
                        chooseCharacter = true;
                        break;
                    //if no more dialogue options, break loop
                    } else if (option.dialogue.successors(option.currentLocation).isEmpty()) {
                        System.out.println("You have exhausted all of this character's dialogue options. Please choose another one.");
                    }
                }
            } 
        }

        //prints line for current location of user in that character's dialogue tree
        System.out.println("\n" + character.name + ": " + character.dialogueScript.get(character.currentLocation));

        int check = 0;
        //while loop navigates down dialogue tree
        while (check <= 1) {
            //if there are no more options for a character, break loop
            if(character.dialogue.successors(character.currentLocation).isEmpty()) {
                System.out.println("\n You have exhausted all your dialogue options for this character.");
                System.out.println("\n Dawn has arrived, and with it, your next action. Your characters have rested and regained their full health, but you will have to wait until the next campfire to talk to another person.");
                return;
            }
            
            check += 1;
            //ask for user input
            System.out.println("\n Pick a response:");
            //iterates through the edges *from* a current location
            Iterator<String> iterator = character.dialogue.outEdges(character.currentLocation).iterator();
            //prints each edge
            while (iterator.hasNext()) {
                String line = iterator.next();
                System.out.println(character.edgeScript.get(line));
            }
            String userInput2 = sc.nextLine().toUpperCase(); //"A";

            //checks validity of user input
            boolean validInput = false;

            //for each edge connected to beginning node
            for (String option: character.dialogue.outEdges(character.currentLocation)) {
                //if user input is equal to one of the edges' first characters
                if (userInput2.charAt(0) == character.edgeScript.get(option).toString().charAt(0)) {
                    //update current location of user in character's dialogue network
                    character.currentLocation = character.dialogue.incidentNodes(option).target();
                    System.out.println("\n" + character.name + ": " + character.dialogueScript.get(character.currentLocation));
                    //increase character's alliance
                    character.alliance += 1;
                    validInput = true;
                    break;
                }
            }
            //checks if user input is valid
            if (validInput == false) {
                System.out.println("That's not a valid user input. Enter A or B");
            }
        }
    
        System.out.println("\n Dawn has arrived, and with it, your next action. Your characters have rested and regained their full health, but you will have to wait until the next campfire to talk to this person again.");

    // save the action, campfire
    saveRecentAction("campfire");
    }

    /**
     * Prints out all the actions a player is allowed to take and how that affects the outcome of the game
     */
    public void help() {
        System.out.println("train - Allows player to train two characters on their team. After each training session, the chosen characters experience goes up, and each character loses two health points.\r\n" + //
                        "battle - Allows player to battle bosses. The battle ends when the enemy or a character on the team dies. Health points decrease for the characters on the team who don't die.\r\n" + //
                        "campfire - Allows player to talk with a character on their team. Replenishes the team's health points and increases the chosen character's alliance with the user.\r\n" + //
                        "attack - allows the characters to deliver a blow to a boss. This can only be called when in battle mode\r\n" + //
                        "check stats - allows the player to the stats of all character in ther troop. This can only be called when in battle mode. \r\n" + //
                        "\r\n" + //
                        "The game ends when you've completed three battles. You win the game if you have successfully won two out of the three battles. \r\n" + //
                        "\r\n" + //
                        "Caveats: \r\n" + //
                        "- When the game starts, you can train, battle or campfire\r\n" + //
                        "- After a battle, you must campfire\r\n" + //
                        "- After a campfire, you can train or battle\r\n" + //
                        "- After three non-battles, you must battle.\r\n" + //
                        "- During battle, if you choose to check stats, you give the enemy an opportunity to attack you without you returning the blow");
    }

    public static void main(String[] args) {
        Game game = new Game(3);
        int numOfBattles = 0;

        System.out.println("----------------------------------------- THE IMPERIAL CROWN ------------------------------------------");
        System.out.println("""
            Congratualations! As a student at the Imperial Academy of War, The Imperial Crown has selected YOU to serve in Her Majesty's Army, First Cohort. 
            You must unite soldiers from the Far Woods, Back Lakes and Imperium City under your command in order to lead them to victory against enemy forces.
            The General of War, Second to Her Imperial Majesty has charged you with a simple command: "FAILURE IS NOT AN OPTION"
            (NOTE: If at any point you're unsure of what to do, seek HELP. Even the best warlords need help from time to time.)
            """);

        // name your characters
        System.out.println("You have been given the opportunity to choose " + game.nCharacters + " soldiers to fight with you. Enter the name(s) of your chosen soldier(s):");
        // save characters
        for (int i = 0; i < game.nCharacters; i++) {
            game.addCharacter(game.sc.nextLine());
        }
        // save character name
        for (Human character : game.characters) {
            game.characterNames.add(character.name);
        }

        // allows player see a well-formatted output of characters' stats 
        System.out.println("\nCommander, meet your Regiment: ");
        for (Character character : game.characters) {
            System.out.println("- " + character);
        }

        // while the game isn't over
        while(!gameOver) {

            // Allow player choose next move based on characters' stats
            System.out.println("\nCommander, would you like to train with your team, go into battle or set up camp? \nRemember that you must maximize the strengths and resources of your Regiment. Choose wisely\n");
            String cmd = game.sc.nextLine().toLowerCase(); 

            while (!(cmd.equals("battle") || cmd.equals("train") || cmd.equals("campfire") || cmd.equals("help"))){
                System.out.println("Strange request detected. (HINT: you can train, battle or have a campfire. You may also seek for HELP)");
                cmd = game.sc.nextLine().toLowerCase();
            }

            // allows player to switch between different modes in a game
            switch (cmd) {
                case "battle":
                if (game.canBattle()) {
                    Human enemy = game.addEnemy();
                    // print out enemy, and ask user one last time to battle or train
                    System.out.println("You're coming up against " + enemy.name + ".\n" + enemy + "\nCommander, are you confident that your troop is prepared for this battle?");
                    String confirmation = game.sc.nextLine().toLowerCase();

                    // validate input
                    while(!(confirmation.equals("yes") || confirmation.equals("no"))) {
                        System.out.println("Unrecognizable command. Would you still like to go into battle? (yes/no)");
                        confirmation = game.sc.nextLine();
                    }
                    if (confirmation.equals("yes") ){
                        System.out.println("\nToday, you will battle " + enemy.name + ".\n");
                        game.battle(enemy);
                        numOfBattles += 1;
                    }
                    else {
                        break;
                    }
                }
                else {
                    System.out.println("The General of War has forbidden you to engage in another battle in order to preserve your Regiment. \nYou must either train with your troop or set up camp to replenish your soldiers' strengths.");
                }
                break;

                case "train":
                if (game.canTrain()) {
                    game.train();
                }
                else {
                    System.out.println("Commander, an enemy is around the corner. You can set up camp to prepare your soldier(s) for tomorrow or march on to the battle clearing.");
                }
                break;

                case "campfire":
                if (game.canCampFire()) {
                    game.campfire();
                }
                else {
                    System.out.println("War is not for the weak nor lazy. You go into battle now or train with your soldiers.");
                }
                break;

                case "help":
                game.help();
            }

            if (numOfBattles >= 3) {
                Game.gameOver = true;
                System.out.println("---------------------------- HER MAJESTY'S ARMY, FIRST COHORT: DELTA REGIMENT ----------------------------");
                if (game.successfulBattles >= 2) {
                    System.out.println("****** CONGRATULATIONS! YOUR IMPRESSIVE VICTORY'S HAVE LANDED YOU A PLACE IN HER MAJESTY'S COURT. ******");
                }
                else {
                    System.out.println("****** YOUR PERFORMANCE HAS DISAPPOINTED IMPERIAL STANDARDS. REPORT TO YOUR NEAREST POST OFFICE FOR REASSIGNMENT. ******");
                }
            }
        }   
    }
}



// GAME EXTENSIONS
// Allow chrs train against old enemies battled
// allow the user to end a training session when both characters are still alive
// Add in audio while player is in battle mode (battle music) and campfire (crackling of a fire)
// allow player choose which character should return an enemy's attack in "real" battles
// outcome of game should be based on both alliance and number of battles won
// add graphics to make text print out more interesting
// Figure out how to generate random numbers in a more efficient way
// Allow user to quit game voluntarily
// Allow user "reun-away" from a battle
// think about what methods and attributes should be public or private and make changes accordingly
// increase character's alliance based on how far down they get in the character's dialogue graph

