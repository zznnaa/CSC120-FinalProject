import java.util.Random;

public class Battle {
    
    //public Game game; battle cannot have game b/c game brings everything together. Analogous to train
    public Human enemy;
    public Human character;
    boolean end;

    // a battle is between an enemy and a character. To extend the second attribute character to an arrayList of characters
    Battle(Human e, Human c){
        this.enemy = e;
        this.character = c;
        this.end = false;
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
    
    public void checkIn(String order){
        System.out.println("The captain has ordered the troop to retreat");
        System.out.println(this.character);
    }

    public void doBattle(){
        System.out.println("Your battle with the enemy has started.");
        while (end != true){
            //enemy attacks character. 
            // TO-DO: let enemy pick a random character from list of characters to attack. Enemy should also use random attacks (to be implemented in Human)
            //choose random number between range
            int x = this.getRandomNumber(1, 2);
            // based on random number, enemy chooses an attack
            if (x == 1){
                this.enemy.kick(this.character);
            } else {
                this.enemy.shoot(this.character);
            }
    
            // Check if character is alive
            //character dead, enemy alive
            if (!this.character.isAlive() && this.enemy.isAlive()){
                System.out.println("Sorry you lost the battle.");
                this.end = true;
            }
            // character dead, enemy dead
            else if (!this.character.isAlive() && !this.enemy.isAlive()){
                System.out.println(this.character.name + " was a noble warrior. They gave their life to put an end to " + this.enemy.name);
                this.end = true;
            }
            // character alive, enemy dead
            else if(this.character.isAlive() && !this.enemy.isAlive()){
                System.out.println("Congrats! You have won the battle");
                this.end = true;
            }

            // if character & enemy alive, keep going
            //based on random number, character chooses an attack
            // put this into an if statement to sto pplayer from playing after they've died.
            if (x == 1){
                this.character.kick(this.enemy);
            } else {
                this.character.shoot(this.enemy);
            }
        }
    }


    public static void main(String[] args) {
        Human character = new Human("Zee", 10, 2, 10, false);
        Human enemy = new Human("Thug", 10, 10, 0, true);
        Battle battle = new Battle(enemy, character);
        battle.doBattle();
    }
}

//NOTES TO US
// Need to fix how battle ends. Character can't keep playing after health = 0. Still can't figure out the error


// MORE NOTES
// how to implement how to ask the player whether to check in or not after 5 attacks. Maybe let the player decided when to check in by teelling them that they can call the chekIn function 
