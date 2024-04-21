public class nonBattle {
    //thinking about how this should work - the success of campfire determines your team's replenishment of health
    //amount of health determines how much training you can do, training causes one fourth health sap of battle while increasing experience (multiplier of damage)
    //figure out how dialogue trees work
    
    int rest;
    Game game;

    nonBattle(Game g){
        this.rest = 5;
        this.game = g;
    }

    public void health_replenish(){
    if (game.character.health != 0){
        game.character.health += (rest + game.character.alliance);
        }
    }

    //just choose two teammates (can't train if no teammates) and fight each other with damage and experience reports until user says stop.

    //campfire dialogue tree

    public static void main(String[] args) {
        Game game = new Game();
        nonBattle trainTime = new nonBattle(game);
        trainTime.health_replenish();

    }

}
