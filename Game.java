import java.util.Scanner;
import java.util.Random;

public class Game {
    public Human enemy;
    public Human character;
    public Scanner sc;
    
    public Game(){
        this.enemy = new Human("enemy", 10, 5, 0, true);
        this.character = new Human("human", 10, 5, 5, false);
        this.sc = new Scanner(System.in);
    }

//everything below this, go through to seperate battle and game classes
    public void checkIn(Character c){
        c.printStatus();
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

public static void main(String[] args) {
    boolean end = false;
    Scanner sc = new Scanner(System.in);
    Game game = new Game();

    System.out.println("Your battle with the enemy has started.");
    do{
        //enemy attacks character
        game.enemy.kick(game.character);

        //checks to see if character has lost battle
        if (game.character.health == 0) {
            end = true;
            System.out.println("Sorry you lost the battle.");
        }
        else if (game.enemy.health == 0 && game.character.health != 0){
            end = true;
            System.out.println("Congrats you won the battle!");
        }

        System.out.println("Do you wish to attack the enemy or to see the status of your troops?");
        String order = sc.nextLine().toLowerCase();
        if (order.equals("attack")){
            //choose random number between range
            int x = game.getRandomNumber(1, 2);
            //based on random number, character chooses an attack
            if (x == 1){
                game.character.kick(game.enemy);
            } else {
                game.character.shoot(game.enemy);
            }
        } else {
            System.out.println("The captain has ordered the troop to retreat");
            game.checkIn(game.character);
        }

    } while (end != true);

    sc.close();

}
}

//NOTES TO US
// Need to fix how battle ends. Character can't keep playing after health = 0
// Create human class and let enemy and character inherit from there.
