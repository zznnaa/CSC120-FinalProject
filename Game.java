import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    public Human enemy;
    public Human character;
    public ArrayList<Human> characters = new ArrayList<>(); // extending game to multiple characters
    public boolean gameEnd;
    
    public Game(){
        this.enemy = new Human("enemy", 10, 5, 0, true);
        this.character = new Human("character", 10, 5, 5, false);
        this.gameEnd = false;
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
    public boolean advanceBattle(boolean fight){
        //if any character dies, you lose the battle
        if (!this.character.isAlive()){
            System.out.println("Sorry you lost the battle.");
            return false; // the battle is over
        }

        // if the character is alive, check if enemy is dead
        else{
            // if the enemy is dead, the character has won the battle
            if (!this.enemy.isAlive()){
                System.out.println("Congrats! You have won the battle");
                return false; // battle is over and advanceBattle is no longer true
            }

            // else either return an attack or retreat
            else{
                // return an attack
                if (fight){
                    this.character.attack(this.enemy);
                }

                // retreat
                else{
                    System.out.println("The captain has ordered the troop to retreat");
                    System.out.println(this.character);
                }
                return true; // battle not over; can still advance battle
            }
        }
    }

    //game method
    public void gameMethod(){
    System.out.println("Your game has started.");
    if (character.name == "insert whatever ending condition we want for the game here")
        gameEnd = true;
        System.out.println("Your game has ended.");
        //return
    }

    public static void main(String[] args) {
        Game game = new Game();
        Scanner sc = new Scanner(System.in);
        // while the game isn't over
        while(game.gameEnd == false){
            // while a particular battle is ongoing
            boolean battleOngoing = true;
            while(battleOngoing == true){
                game.enemy.attack(game.character);
                // Ask the user if they wish to attack or retreat. Call advanceBattle based on user response
                System.out.println("Do you wish to return the attack or check in with your troop? (attack/retreat)");
                String nextMove = sc.nextLine().toLowerCase();
                if (nextMove.equals("attack")){
                    battleOngoing = game.advanceBattle(true);
                }
                else{battleOngoing = game.advanceBattle(false);}  // TO-DO: throw an exception that doesn't break out of the loop when the user types in a response that s not part of the options given
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
// Implement Training as an instance of battle 

// IMMEDIATE
// how to implement a number of battles within game
// implement various characters
// implement various enemies

// LATER
// how does player choose which character should attack enemy?
// Figure out how to generate random numbers in a more efficient way

// ON DESIGN
// throw an exception that doesn't break out of the loop when the user types in a response that s not part of the options given