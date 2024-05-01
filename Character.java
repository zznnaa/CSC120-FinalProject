import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;
import com.google.common.graph.*;
import com.google.common.graph.ImmutableNetwork;

public class Character extends Human{
    public Hashtable<String, String> dialogueScript;
    public Hashtable<String, String> edgeScript;
    public ImmutableNetwork<String, String> dialogue;
    public String currentLocation; //current location user in that character's dialogue network

    public Character(String name, int health, int experience, int alliance, boolean isEnemy, HashtablePair script) {
        super(name, health, experience, alliance, isEnemy);
        this.dialogueScript = script.getKey();
        this.edgeScript = script.getValue();
        //network builder using keys in hashtable
        ImmutableNetwork<String, String> dialogue =
            NetworkBuilder.directed()
            .allowsParallelEdges(true)
            .<String, String>immutable()
            .addEdge("beginning", "option 1", "A")
            .addEdge("beginning", "option 2", "B")
            .addEdge("option 2", "option 2.1", "B.A")
            .addEdge("option 1", "option 1.1", "A.A")
            .addEdge("option 2.1", "last option", "last edge")
            .addEdge("option 1.1", "last option", "last edge 2")
            .build();
        //assign to this instance of Character
        this.dialogue = dialogue;
        //assigns current location as first node in network
        this.currentLocation = this.dialogue.nodes().iterator().next();
    }

    public void talk(){
        //print hashtable graph
        System.out.println(dialogue);
    }

public static void main(String[] args) {
    
}
}
