import java.util.Random;

public class Human{

    //attributes
    public String name;
    public int health;
    public int experience;
    public int alliance;
    public boolean isEnemy;

    //constructor
    public Human(String name, int health, int experience, int alliance, boolean isEnemy){
        this.name = name;
        this.health = health;
        this.experience = experience;
        this.alliance = alliance;
        this.isEnemy = isEnemy;
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

    // attack methods. 
    public void kick(Human h){
        //-5 points to enemy
        System.out.println(h.name + " has suffered a kick");
        int damage = 5;
        h.health -= damage;
    }

    public void shoot(Human h){
        // -10 points to enemy 
        System.out.println(h.name + "is down with a grave injury");
        int damage = 10;
        h.health -= damage;
    }

    // randomly samples possible attack methods
    public void attack(Human h){
        int x = this.getRandomNumber(1, 2);
        if (x == 1){
            this.kick(h);
        } else if (x == 2) {
            this.shoot(h);
        }
    }

    // checks if the human is alive or not
    public boolean isAlive(){
        return this.health > 0;
    }

    public String toString(){
        if (!isEnemy){
            return name + " has health " + health + ", experience " + experience + ", alliance " + alliance + ".";
        }
        else{
            return name + " has health " + health + ", experience " + experience + ".";
        }

    }

    public static void main(String[] args) {
        Human h = new Human("enemy", 10, 5, 0, true);
        System.out.println(h);
    }
}

// TO-DO:
// make alliance private and implement a getter for it
// edit the attack methods to cause damage based on experience and alliance (remember that enemy's allaince won't affect their attacks)

// public String toString(){
//     return "Name: " + name +
//             "\nHealth: " + health +
//             "\nExperience: " + experience +
//             "\nAlliance: " + alliance +
//             "\nisEnemy: " + isEnemy;
// }