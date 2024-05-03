import java.util.Hashtable;

public class HashtablePair<K,V> {
    
    Hashtable<String, String> dialogueHashtable;
    Hashtable<String, String> edgeHashtable;

    public HashtablePair(Hashtable<String, String> dialogueHashtable, Hashtable<String, String> edgeHashtable){
        this.dialogueHashtable = dialogueHashtable;
        this.edgeHashtable = edgeHashtable;
    }

    /**
     * Return Hashtable key for Hashtable pair
     * @return Hashtable<String, String>
     */
    public Hashtable<String, String> getKey(){
        return this.dialogueHashtable;
    }

    /**
     * Return Hashtable value for Hashtable pair
     * @return Hashtable<String, String>
     */
    public Hashtable<String, String> getValue(){
        return this.edgeHashtable;
    }
}
