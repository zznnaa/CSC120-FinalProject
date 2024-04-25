import java.util.Hashtable;
import java.util.Scanner;
import com.google.common.graph.*;
import com.google.common.graph.Traverser;

public class Campfire {
    Hashtable<String, String> hashtable;
    // ImmutableNetwork<String, String> myGraph;

    public Campfire(){
        this.hashtable = new Hashtable<String, String>();
        // this.myGraph = new ImmutableNetwork<String, String> 
        // NetworkBuilder.directed()
        // .allowsParallelEdges(true)
        // .<String, String>immutable()
        // .addEdge("beginning", "option 1", "A")
        // .addEdge("beginning", "option 2", "B")
        // .build();
    }
    
    // //replenish health at campfire
    // public void health_replenish(){
    //     if (this.character.health != 0){
    //         this.character.health += (rest + this.character.alliance);
    //         }
    //     }
    
    //learn how dialgoue trees work

public static void main(String[] args) {
    
    //network builder using strings
    ImmutableNetwork<String, String> myGraph =
        NetworkBuilder.directed()
        .allowsParallelEdges(true)
        .<String, String>immutable()
        .addEdge("beginning", "option 1", "A")
        .addEdge("beginning", "option 2", "B")
        .build();  

        //print string graph
        System.out.println(myGraph);

        //traverse options in string graph
        Traverser.forGraph(myGraph).breadthFirst("beginning")
        .forEach(x->System.out.println(x));

        String userInput = "A"; //sc.nextLine()

        String currentLocation = "beginning";

        System.out.println("\n Current Location: " + currentLocation);

        //navigating to different locations in string graph
        if (myGraph.incidentNodes(userInput) != null){
            currentLocation = myGraph.incidentNodes(userInput).target();
            System.out.println("Your new location is: " + currentLocation);
        } else {
            System.out.println("That's not a valid user input. Enter A or B");
            //userInput something something
        }

    //hashtable that holds all the character's responses
    Hashtable ht = new Hashtable<String, String>();
    ht.put("beginning", "Hello. My name is Farfelle. I'm a warrior from the Far Woods.");
    ht.put("option 1", "Ah you should one day.");
    ht.put("option 2", "You've visited? I miss it.");

    //hashtable that holds all the user's preprogrammed responses
    Hashtable ht2 = new Hashtable<String, String>();
    ht2.put("A", "A - I've never been.");
    ht2.put("B", "B - I went there once as a child.");

    //network builder using the hashtables
    ImmutableNetwork<Object, Object> myGraph2 =
        NetworkBuilder.directed()
        .allowsParallelEdges(true)
        .<Object, Object>immutable()
        .addEdge(ht.get("beginning"), ht.get("option 1"), ht2.get("A"))
        .addEdge(ht.get("beginning"), ht.get("option 2"), ht2.get("B"))
        .build();

        //print hashtable graph
        System.out.println("\n" + myGraph2);

        //traverse options in hashtable graph
        Traverser.forGraph(myGraph2).breadthFirst(ht.get("beginning"))
        .forEach(x->System.out.println(x));

        String userInput2 = "A"; //sc.nextLine()

        Object currentLocation2 = ht.get("beginning");

        System.out.println("\n Current Location: " + currentLocation2);
        
        //for loop, for each node connected to beginning
            //if user input equals one of the nodes then make new variable
            //else return error
        //if there are incident nodes connected to new input, move within graph

        if (userInput2.charAt(0) == ht2.get("A").toString().charAt(0)){
            Object replaceUserInputwithNewVariable = ht2.get("A");
        }

        //navigating to different locations in hashtable graph
        if (myGraph2.incidentNodes(userInput2) != null){
            currentLocation2 = myGraph2.incidentNodes(userInput2).target();
            System.out.println("Your new location is: " + currentLocation2);
        } else {
            System.out.println("That's not a valid user input. Enter A or B");
            //userInput something something
        }

        //is there a way to indicate a specific key, value pair from a hashtable as the node in a graph?
        




        
}
}
