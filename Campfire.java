//import com.github.google.guava.;
import com.google.common.*;

public class Campfire {
    
    //replenish health at campfire
    public void health_replenish(){
        if (this.character.health != 0){
            this.character.health += (rest + this.character.alliance);
            }
        }
    
    //learn how dialgoue trees work

    ImmutableGraph<Integer> graph =
    GraphBuilder.directed()
        .<Integer>immutable()
        .addNode(1)
        .putEdge(2, 3) // also adds nodes 2 and 3 if not already present
        .putEdge(2, 3) // no effect; Graph does not support parallel edges
        .build();

    Set<Integer> successorsOfTwo = graph.successors(2); // returns {3}
}
