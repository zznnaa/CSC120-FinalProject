import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;
import com.google.common.graph.*;

//ARCHITECTURE NOTES: turn dialogue into a parent class,
//and make the talk method generalized so any two hashtables could be fed into the talk method?
//could put the talk method into human then potentially?
//each hashtable would need to be unique and hardcoded into each character

//starting by making this work with a scanner and format properly
//keep dialogue and hashtables as attributes of the human, but have a talk method in human or game class
//have four different hashtables and randomize which character gets the hashtable

public class Dialogue {
    Hashtable<String, String> ht;
    Hashtable<String, String> ht2;
    // ImmutableNetwork<String, String> myGraph;

    public Dialogue(){
        this.ht = new Hashtable<String, String>();
        this.ht2 = new Hashtable<String, String>();
        // how do I construct a network within this class?
    }
    
    //talk method
    public void talk(){
        Scanner sc = new Scanner(System.in);
        
        //array list that holds all the key value pairs
        //iterate through the array list, adding key value pairs to the hashtable(s) and then putting edges between them in the graph
        //Pair<String, String> p = Pair.of("test", "test");
       
        //rename hashtables to be better
        //hashtable that holds all the character's responses
        Hashtable ht = new Hashtable<String, String>();
        ht.put("beginning", "Hello. My name is Farfelle. I'm a warrior from the Far Woods.");
        ht.put("option 1", "Ah you should one day.");
        ht.put("option 2", "You've visited? I miss it.");
        ht.put("option 2.1", "The way the fall leaves would scatter on the ground.");
        ht.put("option 1.1", "Only a day's ride");
        ht.put("last option", "Testing the last string.");

        //hashtable that holds all the user's preprogrammed responses
        Hashtable ht2 = new Hashtable<String, String>();
        ht2.put("A", "A - I've never been.");
        ht2.put("B", "B - I went there once as a child.");
        ht2.put("B.A", "A - What do you miss most about it?");
        ht2.put("A.A", "A - How far away is it?");
        ht2.put("last edge", "A - last edge test 1");
        ht2.put("last edge 2", "A - last edge test 2");

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

        //print hashtable graph
        System.out.println("\n" + dialogue);

        //traverse options in hashtable graph
        // Traverser.forGraph(dialogue).breadthFirst(ht.get("beginning"))
        // .forEach(x->System.out.println(x));

        String currentLocation = dialogue.nodes().iterator().next();
        System.out.println("Current Location: " + currentLocation);
        System.out.println("Current location: " + ht.get(currentLocation));

        //while loop to ask player for dialogue options
        while (dialogue.successors(currentLocation).size() != 0){
            //ask for user input
            System.out.println("\n Pick a response:");
            //iterate through the edges in current location
            Iterator<String> iterator = dialogue.outEdges(currentLocation).iterator();
            while (iterator.hasNext()){
                String line = iterator.next();
                System.out.println(ht2.get(line));
            }
            String userInput2 = sc.nextLine(); //"A";

            //testing validity of input
            boolean validInput = false;
            //for each edge connected to beginning node
            for (String option: dialogue.outEdges(currentLocation)){
                System.out.println("This is your edge object:");
                System.out.println(option);
                //if user input is equal to one of the edges' first characters
                if (userInput2.charAt(0) == ht2.get(option).toString().charAt(0)){
                    //update current location of graph
                    currentLocation = dialogue.incidentNodes(option).target();
                    System.out.println("\n Your new location is: " + currentLocation);
                    System.out.println("Your new location is: " + ht.get(currentLocation));
                    validInput = true;
                    break;
                }
            }
            //checks if user input is valid (replace with try/catch once done coding the rest of it)
            if (validInput == false){
                System.out.println("That's not a valid user input. Enter A or B");
            }

        }
    }

public static void main(String[] args) {

    Dialogue c = new Dialogue();
    c.talk();
        
}
}
