public class Enemy {
//attributes
public int health;

//constructor
public Enemy(int health){
    this.health = health;
}

//methods
public void kick(Character c){
    //-5 points to protagonist
    System.out.println("Your troop has suffered a kick");
    int damage = 5;
    c.health -= damage;
}

public void shoot(Character c){
    // -10 points to protagonist 
    System.out.println("A member of your troop is down with a grave injury");
    int damage = 10;
    c.health -= damage;
}   

public static void main(String[] args) {
    
}
}
