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

//methods
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

public String toString(){
    return "Name: " + name +
            "\nHealth: " + health +
            "\nExperience: " + experience +
            "\nAlliance: " + alliance +
            "\nisEnemy: " + isEnemy;
}

public static void main(String[] args) {
    Human h = new Human("enemy", 10, 5, 0, true);
    System.out.println(h);
}
}