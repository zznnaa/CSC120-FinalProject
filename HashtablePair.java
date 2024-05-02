import java.util.Hashtable;

public class HashtablePair<K,V> {
    
    Hashtable<String, String> dialogueHashtable;
    Hashtable<String, String> edgeHashtable;

    public HashtablePair(Hashtable<String, String> dialogueHashtable, Hashtable<String, String> edgeHashtable){
        this.dialogueHashtable = dialogueHashtable;
        this.edgeHashtable = edgeHashtable;
    }

    public Hashtable<String, String> getKey(){
        return this.dialogueHashtable;
    }

    public Hashtable<String, String> getValue(){
        return this.edgeHashtable;
    }
}
