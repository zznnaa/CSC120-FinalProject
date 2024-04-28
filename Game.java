import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    public Human enemy;
    public int nCharacters;
    public ArrayList<Human> characters; // extending game to multiple characters
    public static boolean gameOver;
    public Scanner sc; // create a scanner for the entire game class
    
    public Game(int nCharacters){
        this.enemy = new Human("enemy", 10, 5, 0, true); 
        this.nCharacters = nCharacters;
        this.characters = new ArrayList<>(nCharacters);
        Game.gameOver = false;
        this.sc = new Scanner(System.in);
    }

    // adds a character
    public void addCharacter(String name){
            this.characters.add(new Human(name, 10, 5, 5, false)); // to-do: randomly generate characters stats
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

    // advance battle by one more attack
    public boolean advanceBattle(boolean fight, Human protagonist, Human villain){
        //if any character dies, you lose the battle
        if (!protagonist.isAlive()){
            System.out.println("Sorry you lost the battle.");
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

    // allows a battle to run either as a real battle or a training
    public void battle(boolean training){
        boolean battleOngoing = true; // status of battle

        // if it's a training session
        if (training){
            ArrayList<Human> fightingPair = new ArrayList<>(2); // the fighting pair
            ArrayList<Integer> initialHealth = new ArrayList<>(2); // keeps track of proponent and opponent's health

            // Ask the user for the opponent and proponent 
            System.out.println("Which pair of your characters would you like to match against each other?");
            String chr1 = sc.nextLine();
            String chr2 = sc.nextLine();

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

            // update characters' experience based on battle outcome
            // proponent and opponent die
            if (proponent.health == 0 && opponent.health == 0){
                System.out.println("Both characters lost their lives. More training is recommended");
                proponent.experience += 5;
                opponent.experience += 5;
            }

            // proponent dies, opponent doesn't
            else if (proponent.health == 0 && opponent.health != 0){
                System.out.println(opponent.name + " won the battle.");
                proponent.experience += 5;
                opponent.experience += 10;
            }

            // proponent doesn't die, opponent does
            else if (proponent.health != 0 && opponent.health == 0){
                System.out.println(opponent.name + " lost the battle.");
                proponent.experience += 10;
                opponent.experience += 5;
            }

            // reinstate their health
            proponent.health = initialHealth.get(0);
            opponent.health = initialHealth.get(1);
        }

        // if it's a real battle
        if (!training){
            // while a particular battle is ongoing
            while(battleOngoing == true){
                // enemy randomly chooses a character to attack
                int i = this.getRandomNumber(0, this.characters.size());
                this.enemy.attack(this.characters.get(i));

                // Ask the user if they wish to attack or retreat. Call advanceBattle based on user response
                System.out.println("Do you wish to return the attack or check in with your troop? (attack/retreat)");
                String nextMove = sc.nextLine().toLowerCase();
                if (nextMove.equals("attack")){
                    battleOngoing = this.advanceBattle(true, this.characters.get(i), this.enemy); // TO-DO: allow a different chr return attack
                }
                else if (nextMove.equals("retreat")){
                    battleOngoing = this.advanceBattle(false, this.characters.get(i), this.enemy);
                }  // TO-DO: throw an exception that doesn't break out of the loop when the user types in a response that's not part of the options given
            }
        }
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

        // while the game isn't over
        while(!gameOver){
            // testing training
            System.out.println("Your troop will now train");
            game.battle(true);
            // testing battle
            System.out.println("This is a real battle");
            game.battle(false);
            Game.gameOver = true;
        }   
    }
}

// NOTES on game mechanics:
// In a real battle, variuos characters attack. In training, there is always only two characters

// PROBLEMS IN CODE:
// Enemy can attack without the player choosing to either attack or retreat

// TO-DO:

// IMMEDIATE
// implement a switch case statement for all the possible game varieties
// map out your game to pull all the pieces together
// write up all the text needed: 
// - at the beginning of game
// - enemies descriptions
// - chracters description
// implement various enemies: set up a way to randomly sample their stats

// LATER
// change the prinout when the captain need to check in with players
// allow player choose which character should return an enemy's attack in "real" battles
// throw an exception that keeps asking for an input if the user types in a response that's not part of the options given without returning to the top of the loop or exiting the loop
// allow the user to end a training session when both characters are still alive

// ON DESIGN
// Figure out how to generate random numbers in a more efficient way