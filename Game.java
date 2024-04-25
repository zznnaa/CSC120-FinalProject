import java.util.Scanner;
import java.util.Random;

public class Game {
    public Human enemy;
    public Human character;
    public boolean gameEnd;
    
    public Game(){
        this.enemy = new Human("enemy", 10, 5, 0, true);
        this.character = new Human("human", 10, 5, 5, false);
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
    public void advanceBattle(boolean fight){
        //if any character dies, you lose the battle
        if (!this.character.isAlive()){
            boolean battleEnd = true;
            System.out.println("Sorry you lost the battle.");
        }

        // if the character is alive, check if enemy is dead
        else{
            // if the enemy is dead, the character has won the battle
            if (!this.enemy.isAlive()){
                boolean battleEnd = true;
                System.out.println("Congrats! You have won the battle");
            }

            // else either return an attack or retreat
            else{
                boolean battleEnd = true;
                // return an attack
                if (fight){
                    this.character.attack(this.enemy);
                }

                // retreat
                else{
                    System.out.println("The captain has ordered the troop to retreat");
                    System.out.println(this.character);
                }
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
    while(game.gameEnd == false){
        while(currentBattle.battleEnd == false){
            game.enemy.attack(game.character);
            game.advanceBattle(true);
        }
    }   
}
}

// A game consists of three battles

// TO-DO:
// Fix the battle as a class vs a method problem
// Once this is done, make sure that Training as an instance of battle works
// Figure out how to generate random numbers in a more efficient way
