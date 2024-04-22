import java.util.Scanner;

public class Training extends Battle{
    //thinking about how this should work - the success of campfire determines your team's replenishment of health
    //gains experience through training
    
    public Human character1;
    public Human character2;
    boolean end;
    public Scanner sc;

    Training(Human o, Human t){
        super(o, t);
        this.character2 = this.enemy;
        this.sc = new Scanner(System.in);
    }

    //instead of using empty returns, use breaks

    public void trainingLoop(){
        int characterExp = 0;
        int character2Exp = 0;
        while (end != true){
            //first character attacks and lists how much damage done
            int x = this.character.attack(this.character2);
            System.out.println(this.character2.name + " has " + this.character2.health + " health left.");
            //character gains experience based on attack level divided by 2
            characterExp += (this.character.attackLevel(x)/2);
            
            //second character attacks and lists how much damage done
            int y = this.character2.attack(this.character);
            System.out.println(this.character.name + " has " + this.character.health + " health left.");
            //character gains experience based on attack level divided by 2
            character2Exp += (this.character2.attackLevel(y)/2);
            
            //if characters have less than half their health left, training automatically ends
            if (this.character.health <= (this.character.health/2) || this.character2.health <= (this.character2.health/2)){
                System.out.println("Your training has ended. Your characters have less or equal to 1/2 of their health left.");
                return;
            //otherwise asks user if they want to continue the training
            } else {
                System.out.println("Do you want to continue training?");
                String z = this.sc.nextLine().toLowerCase();
                if (z.equals("yes")){
                    continue;
                }
                if (z.equals("no")){
                    System.out.println("Your training has ended.");
                    return;
                }
            }
        }
    }
    
    public void doTraining(){
        System.out.println(this.character.name + " and " + this.character2.name + " have started training.");
        this.trainingLoop();
        System.out.println("You have returned to the game.");
    }

    //just choose two teammates (can't train if no teammates) and fight each other with damage and experience reports until user says stop.

    public static void main(String[] args) {
        Human training_human1 = new Human("Day", 10, 1, 0, false);
        Human training_human2 = new Human("Night", 10, 1, 0, false);
        Training t = new Training(training_human2, training_human1);
        t.doTraining();

    }

}
