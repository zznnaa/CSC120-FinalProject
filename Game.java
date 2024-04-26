import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    public Human enemy;
    // public Human character;
    public int nCharacters;
    public ArrayList<Human> characters; // extending game to multiple characters
    public boolean gameEnd;
    
    public Game(int nCharacters){
        this.enemy = new Human("enemy", 10, 5, 0, true);
        // this.character = new Human("character", 10, 5, 5, false);
        this.nCharacters = nCharacters;
        this.characters = new ArrayList<>(nCharacters);
        this.gameEnd = false;
    }

    // adds a character
    public void addCharacter(String name){
            this.characters.add(new Human(name, 10, 5, 5, false)); 
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


    public static void main(String[] args) {
        Game game = new Game(2);
        Scanner sc = new Scanner(System.in);

        System.out.println("Your game has started.");

        // TO-DO: add in short description of game objective and what is expected of the player

        // name your characters
        System.out.println("You will now choose your troop. Enter any " + game.nCharacters + " names.");
        // String characterNames
        for (int i = 0; i < game.nCharacters; i++){
            game.addCharacter(sc.nextLine());
        }

        // while the game isn't over
        while(game.gameEnd == false){
            // while a particular battle is ongoing
            boolean battleOngoing = true;
            while(battleOngoing == true){
                // enemy chooses a random character to attack
                int i = game.getRandomNumber(0, game.characters.size());
                game.enemy.attack(game.characters.get(i));

                // Ask the user if they wish to attack or retreat. Call advanceBattle based on user response
                System.out.println("Do you wish to return the attack or check in with your troop? (attack/retreat)");
                String nextMove = sc.nextLine().toLowerCase();
                if (nextMove.equals("attack")){
                    battleOngoing = game.advanceBattle(true, game.characters.get(i), game.enemy); // TO-DO: allow a different chr return attack
                }
                else{battleOngoing = game.advanceBattle(false, game.characters.get(i), game.enemy);}  // TO-DO: throw an exception that doesn't break out of the loop when the user types in a response that s not part of the options given
            }
            game.gameEnd = true;
        }
        sc.close();   
    }
}

// Maybe create a battle method that takes in the "enemy", "protagonist" and whether or not it's training or battle. 
// If it's training, the enemy would be another character
// Then the next step would be to implement various characters. Then we can add one training to game. Then multiple battles to game.

// TO-DO:
// Implement Training. To do this, wrap "training" battles in an if statement that allows you tweak health and experience accordingly 
// wrap real battles in a similar manner

// IMMEDIATE
// how to implement a number of battles within game
// implement various enemies <- would come with implementing the three required battles

// LATER
// allow player choose which character should return an enemy's attack in "real" battles
// how does player choose which character should attack enemy?
// Figure out how to generate random numbers in a more efficient way

// ON DESIGN
// throw an exception that doesn't break out of the loop when the user types in a response that's not part of the options given