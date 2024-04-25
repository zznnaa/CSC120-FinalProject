import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;
import com.google.common.graph.*;
import com.google.common.graph.Traverser;

//ARCHITECTURE NOTES: turn dialogue into a parent class,
//and make the talk method generalized so any two hashtables could be fed into the talk method?
//could put the talk method into human then potentially?
//each hashtable would need to be unique and hardcoded into each character

//starting by making this work with a scanner and format properly
//keep dialogue and hashtables as attributes of the human, but have a talk method in human or game class

public class Dialogue {
    Hashtable<String, String> hashtable;
    // ImmutableNetwork<String, String> myGraph;

    public Dialogue(){
        this.hashtable = new Hashtable<String, String>();
        // how do I construct a network within this class?
    }
    
    //talk method
    public void talk(){
        Scanner sc = new Scanner(System.in);
        
        //hashtable that holds all the character's responses
        Hashtable ht = new Hashtable<String, String>();
        ht.put("beginning", "Hello. My name is Farfelle. I'm a warrior from the Far Woods.");
        ht.put("option 1", "Ah you should one day.");
        ht.put("option 2", "You've visited? I miss it.");
        ht.put("option 2.1", "The way the fall leaves would scatter on the ground.");

        //hashtable that holds all the user's preprogrammed responses
        Hashtable ht2 = new Hashtable<String, String>();
        ht2.put("A", "A - I've never been.");
        ht2.put("B", "B - I went there once as a child.");
        ht2.put("B.A", "A - What do you miss most about it?");

        //network builder using the hashtables
        ImmutableNetwork<Object, Object> dialogue =
            NetworkBuilder.directed()
            .allowsParallelEdges(true)
            .<Object, Object>immutable()
            .addEdge(ht.get("beginning"), ht.get("option 1"), ht2.get("A"))
            .addEdge(ht.get("beginning"), ht.get("option 2"), ht2.get("B"))
            .addEdge(ht.get("option 2"), ht.get("option 2.1"), ht2.get("B.A"))
            .build();

        //print hashtable graph
        System.out.println("\n" + dialogue);

        //traverse options in hashtable graph
        // Traverser.forGraph(dialogue).breadthFirst(ht.get("beginning"))
        // .forEach(x->System.out.println(x));

        Object currentLocation = dialogue.nodes().iterator().next();
        System.out.println("Current Location: " + currentLocation);

        //while loop not running all the way through
        //make sure null within while loop actually stops it
        //while (dialogue.successors(currentLocation).size() != 0){
            //ask for user input
            System.out.println("\n Pick a response:");
            Iterator<Object> iterator = dialogue.incidentEdges(currentLocation).iterator();
            while (iterator.hasNext()){
                Object line = iterator.next();
                System.out.println(line);
            }
            String userInput2 = "A"; //sc.nextLine();

            //testing validity of input
            boolean validInput = false;
            //for each edge connected to beginning node
            for (Object o: dialogue.incidentEdges(currentLocation)){
                //if user input is equal to one of the edges' first characters
                if (userInput2.charAt(0) == o.toString().charAt(0)){
                    //update user input to match the full name of the edge
                    Object updatedUserInput = o;
                    //use updated user input to update current location of graph
                    Object currentlocation = dialogue.incidentNodes(updatedUserInput).target();
                    System.out.println("\n Your new location is: " + currentlocation);
                    validInput = true;
                    break;
                }
            }
            System.out.println(currentLocation);
            //checks if user input is valid (replace with try/catch once done coding the rest of it)
            if (validInput == false){
                System.out.println("That's not a valid user input. Enter A or B");
            }

        //}
    }

public static void main(String[] args) {

    Dialogue c = new Dialogue();
    c.talk();
    
    // //network builder using strings
    // ImmutableNetwork<String, String> myGraph =
    //     NetworkBuilder.directed()
    //     .allowsParallelEdges(true)
    //     .<String, String>immutable()
    //     .addEdge("beginning", "option 1", "A")
    //     .addEdge("beginning", "option 2", "B")
    //     .build();  

    //     //print string graph
    //     System.out.println(myGraph);

    //     //traverse options in string graph
    //     Traverser.forGraph(myGraph).breadthFirst("beginning")
    //     .forEach(x->System.out.println(x));

    //     String userInput = "A"; //sc.nextLine()

    //     String currentLocation = "beginning";

    //     System.out.println("\n Current Location: " + currentLocation);

    //     //navigating to different locations in string graph
    //     if (myGraph.incidentNodes(userInput) != null){
    //         currentLocation = myGraph.incidentNodes(userInput).target();
    //         System.out.println("Your new location is: " + currentLocation);
    //     } else {
    //         System.out.println("That's not a valid user input. Enter A or B");
    //         //userInput something something
    //     }

    // if (userInput2.charAt(0) == ht2.get("A").toString().charAt(0)){
    //     Object replaceUserInputwithNewVariable = ht2.get("A");
    // }

    //navigating to different locations in hashtable graph
    // if (myGraph2.incidentNodes(userInput2) != null){
    //     currentLocation2 = myGraph2.incidentNodes(userInput2).target();
    //     System.out.println("Your new location is: " + currentLocation2);
    // } else {
    //     System.out.println("That's not a valid user input. Enter A or B");
    //     //userInput something something
    // }
        




        
}
}
