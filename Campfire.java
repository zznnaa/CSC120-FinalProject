// import java.util.Hashtable;
// import java.util.Scanner;
// import com.google.common.graph.*;
// import com.google.common.graph.Traverser;

// public class Campfire {
//     Hashtable<String, String> hashtable;
//     // ImmutableNetwork<String, String> myGraph;

//     public Campfire(){
//         this.hashtable = new Hashtable<String, String>();
//         // this.myGraph = new ImmutableNetwork<String, String> 
//         // NetworkBuilder.directed()
//         // .allowsParallelEdges(true)
//         // .<String, String>immutable()
//         // .addEdge("beginning", "option 1", "A")
//         // .addEdge("beginning", "option 2", "B")
//         // .build();
//     }
    
//     // //replenish health at campfire
//     // public void health_replenish(){
//     //     if (this.character.health != 0){
//     //         this.character.health += (rest + this.character.alliance);
//     //         }
//     //     }
    
//     //learn how dialgoue trees work

// public static void main(String[] args) {
    
//     //hashtable that holds all the character's responses
//     Hashtable ht = new Hashtable<String, String>();
//     ht.put("beginning", "Hello. My name is Farfelle. I'm a warrior from the Far Woods.");
//     ht.put("option 1", "Ah you should one day.");
//     ht.put("option 2", "You've visited? I miss it.");

//     //hashtable that holds all the user's preprogrammed responses
//     Hashtable ht2 = new Hashtable<String, String>();
//     ht2.put("A", "A - I've never been.");
//     ht2.put("B", "B - I went there once as a child.");

//     //network builder using the hashtables
//     ImmutableNetwork<Object, Object> myGraph2 =
//         NetworkBuilder.directed()
//         .allowsParallelEdges(true)
//         .<Object, Object>immutable()
//         .addEdge(ht.get("beginning"), ht.get("option 1"), ht2.get("A"))
//         .addEdge(ht.get("beginning"), ht.get("option 2"), ht2.get("B"))
//         .build();
    
//     //network builder using strings
//     ImmutableNetwork<String, String> myGraph =
//         NetworkBuilder.directed()
//         .allowsParallelEdges(true)
//         .<String, String>immutable()
//         .addEdge("beginning", "option 1", "A")
//         .addEdge("beginning", "option 2", "B")
//         .build();  

//     //print hashtable graph
//     System.out.println(myGraph2);

//     //traverse options in hashtable graph
//     Traverser.forGraph(myGraph2).breadthFirst(ht.get("beginning"))
//                 .forEach(x->System.out.println(x));

//     Scanner sc = new Scanner(System.in);

//     String userInput = "A"; //sc.nextLine()
    
//     Object currentLocation = ht.get("beginning");

//     System.out.println("\n Current Location: " + currentLocation);

//     //navigating to different locations in string graph
//     if (myGraph.incidentNodes(userInput) != null){
//         currentLocation = myGraph.incidentNodes(userInput).target();
//         System.out.println("Your new location is: " + currentLocation);
//     } else {
//         System.out.println("That's not a valid user input. Enter A or B");
//         //userInput something something
//     }

//     //navigating to different locations in hashtable graph
//     if (myGraph2.incidentNodes(userInput) != null){
//         currentLocation = myGraph2.incidentNodes(userInput).target();
//         System.out.println("Your new location is: " + currentLocation);
//     } else {
//         System.out.println("That's not a valid user input. Enter A or B");
//         //userInput something something
//     }


        
// }
// }
