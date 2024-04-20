public class Battle {
    
    public Game game;
    boolean end;

    Battle(Game g){
        this.game = g;
        this.end = false;
    }

    public void battle(){
        System.out.println("Your battle with the enemy has started.");
        do{
            //enemy attacks character
            game.enemy.kick(game.character);
    
            //checks to see if character has lost battle
            if (game.character.health == 0) {
                System.out.println("Sorry you lost the battle.");
                this.end = true;
            }
            else if (game.enemy.health == 0 && game.character.health != 0){
                System.out.println("Congrats you won the battle!");
                this.end = true;
            }
    
            System.out.println("Do you wish to attack the enemy or to see the status of your troops?");
            String order = game.sc.nextLine().toLowerCase();
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
                System.out.println(game.character);
            }
    
        } while (end != true);
    }


    public static void main(String[] args) {
        Game game = new Game();
        Battle battle = new Battle(game);
        battle.battle();
    }
}

//NOTES TO US
// Need to fix how battle ends. Character can't keep playing after health = 0
// Make sure check status works
