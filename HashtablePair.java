import java.util.Hashtable;

public class HashtablePair<K,V> {
    
    Hashtable dialogueHashtable;
    Hashtable edgeHashtable;

    public HashtablePair(Hashtable dialogueHashtable, Hashtable edgeHashtable){
        this.dialogueHashtable = dialogueHashtable;
        this.edgeHashtable = edgeHashtable;
    }

    public Hashtable getKey(){
        return this.dialogueHashtable;
    }

    public Hashtable getValue(){
        return this.edgeHashtable;
    }
}
