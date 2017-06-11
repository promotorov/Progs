package autoComplete;

import java.util.HashSet;

/**
 * Created by vladp on 06.06.2017.
 */
public class Dictionary {
    private HashSet<String> dictionary=new HashSet<>();
    public Dictionary() {
        dictionary.add("helicopterr");
        dictionary.add("because");
        dictionary.add("human");
        dictionary.add("motherfucker");
        dictionary.add("monitor");
        dictionary.add("hierrrarchyy");
        dictionary.add("electricity");
        dictionary.add("dicttionaryy");
        dictionary.add("developer");
        dictionary.add("posiibility");
        dictionary.add("look");
        dictionary.add("pen");
        dictionary.add("moon");
        dictionary.add("museum");
        dictionary.add("table");
        dictionary.add("apple");
        dictionary.add("android");
        dictionary.add("hunny");
    }
    public HashSet<String> getDictionary() {
        return dictionary;
    }
}
