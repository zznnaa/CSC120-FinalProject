import java.util.Scanner;
import java.util.Random;

public class Game {
    public Human enemy;
    public Human character;
    public Scanner sc;
    public boolean gameEnd;
    
    public Game(){
        this.enemy = new Human("enemy", 10, 5, 0, true);
        this.character = new Human("human", 10, 5, 5, false);
        this.sc = new Scanner(System.in);
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
    game.gameMethod();
    
}
}

// A game consists of three battles
