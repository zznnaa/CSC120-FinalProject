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
            // .addEdge("beginning", "option 1", "A")
            // .addEdge("beginning", "option 2", "B")
            // .addEdge("option 2", "option 2.1", "B.A")
            // .addEdge("option 1", "option 1.1", "A.A")
            // .addEdge("option 2.1", "last option", "last edge")
            // .addEdge("option 1.1", "last option", "last edge 2")
            .addEdge("Beginning", "Option 1", "A")
            .addEdge("Beginning", "Option 2", "B")
            .addEdge("Option 1", "Option 3", "1.A")
            .addEdge("Option 1", "Option 4", "1.B")
            .addEdge("Option 2", "Option 3", "2.A")
            .addEdge("Option 2", "Option 4", "2.B")
            .addEdge("Option 4", "Option 5", "4.A")
            .addEdge("Option 4", "Option 5", "4.B")
            .addEdge("Option 5", "Option 6", "5.A")
            .addEdge("Option 5", "Option 7", "5.B")
            .build();
        //assign to this instance of Character
        this.dialogue = dialogue;
        //assigns current location as first node in network
        this.currentLocation = this.dialogue.nodes().iterator().next();
    }

public static void main(String[] args) {
    
}
}
