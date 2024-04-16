public class Character{

//attributes
public int health;
public int experience;
public int maxSpeed;
public int maxStrength;

//constructor
public Character(int health, int experience, int maxSpeed, int maxStrength){
    this.health = health;
    this.experience = experience;
    this.maxSpeed = maxSpeed;
    this.maxStrength = maxStrength;
}

//methods
public void kick(Enemy e){
    //-5 points to enemy
    System.out.println("Enemy has suffered a kick");
    int damage = 5;
    e.health -= damage;
}

public void shoot(Enemy e){
    // -10 points to enemy 
    System.out.println("Enemy is down with a grave injury");
    int damage = 10;
    e.health -= damage;
}

public void printStatus(){
    System.out.println("Health: " + health);
}

public static void main(String[] args) {
    
}
}