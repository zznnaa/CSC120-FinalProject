import java.util.Scanner;
import com.google.common.graph.*;
import com.google.common.graph.Traverser;

public class Campfire {
    // //replenish health at campfire
    // public void health_replenish(){
    //     if (this.character.health != 0){
    //         this.character.health += (rest + this.character.alliance);
    //         }
    //     }
    
    //learn how dialgoue trees work

public static void main(String[] args) {
    
    ImmutableNetwork<String, String> myGraph =
        NetworkBuilder.directed()
        .allowsParallelEdges(true)
        .<String, String>immutable()
        .addEdge("beginning", "option 1", "A")
        .addEdge("beginning", "option 2", "B")
        .addEdge("beginning", "option 3", "C")
        .addEdge("beginning", "option 4", "D")
        .build();  

    System.out.println(myGraph);

    Traverser.forGraph(myGraph).breadthFirst("beginning")
                .forEach(x->System.out.println(x));

    Scanner sc = new Scanner(System.in);

    String userInput = "A"; //sc.nextLine()
    
    String currentLocation = "beginning";

    System.out.println("\n Current Location: " + currentLocation);

    // while (currentLocation != myGraph.incidentNodes(userInput)){
    //     System.out.println("That's not an avaliable option. Try again.");
    // }

    for (String successor : myGraph.successors(currentLocation)){
        if (myGraph.hasEdgeConnecting(currentLocation, successor) &&
            myGraph.edgeConnecting(currentLocation, successor).get().equals(userInput)){
                currentLocation = successor;
                System.out.println("Your new location is: " + currentLocation);
                break;
            }
    }

}
}
