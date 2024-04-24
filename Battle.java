import java.util.Random;

public class Battle {
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
    
    // player has option to check in with team during a battle (similar to a time out)
    public void checkIn(String order){
        System.out.println("The captain has ordered the troop to retreat");
        System.out.println(this.character);
    }

    // allows turn based attacks between characters and an enemy
    public void doBattle(){
        System.out.println("Your battle with the enemy has started.");
        while (end != true){
            //enemy attacks character. 
            // TO-DO: let enemy pick a random character from list of characters to attack. 
            this.enemy.attack(this.character);
    
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

            // if character & enemy alive, keep fighting
            else if(this.character.isAlive() && this.enemy.isAlive()){
                this.character.attack(this.enemy);
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
// how to implement how to ask the player whether to check in or not after 5 attacks. Let the player decide when to check in by teelling them that they can call the chekIn function 
