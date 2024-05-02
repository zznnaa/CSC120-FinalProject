import java.util.Random;

public class Human{
    public String name;
    public int health;
    public int maxHealth;
    public int experience;
    public int alliance;
    public boolean isEnemy;

    public Human(String name, int health, int experience, int alliance, boolean isEnemy){
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.experience = experience;
        this.alliance = alliance;
        this.isEnemy = isEnemy;
    }

    /**
     * Randomly generate a number within a given range
     * @param min lower bound of desired range
     * @param max upper bound of desired range
     * @return the randomly generated number
     */
    public int getRandomNumber(int min, int max){
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
    
    /**
     * Overloaded method to randomly generate method when the range is not specified
     * @return the randomly generated number
     */
    public int getRandomNumber(){
        Random random = new Random();
        return random.nextInt();
    }

    /**
     * Simulates a kick by decreasing a character's health
     * @param h the character to be kicked
     */
    public void kick(Human h){
        System.out.println(h.name + " has suffered a kick");
        int damage = 5 + experience;
        h.health -= damage;
    }

    /**
     * Simulates a shooting attack by decreasing a characters health by a certain amount
     * @param h the character to be shot
     */
    public void shoot(Human h){
        System.out.println(h.name + "is down with a grave injury");
        int damage = 10 + experience;
        h.health -= damage;
    }

    /**
     * Randomly samples from the two possible attack methods
     * @param h the character to be attacked
     */
    public void attack(Human h){
        int x = this.getRandomNumber(1, 2);
        if (x == 1){
            this.kick(h);
        } else if (x == 2) {
            this.shoot(h);
        }
    }

    /**
     * Checks if a charcter is alive or not
     * @return a boolean based on character's current life status
     */
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
}