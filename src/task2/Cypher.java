package task2;

/**
 * Created by algavrilov on 11.04.2016.
 */
public class Cypher {
    // it's a Vigenere cyphering
    public String cypher (String plainText, String key, String alph){
        char[] result = new char[plainText.length()];
        for (int i = 0; i < plainText.length(); i++) {
            result[i] = alph.charAt((alph.indexOf(plainText.charAt(i)) +
                    alph.indexOf(key.charAt(i % key.length()))) % alph.length());
        }
        return new String(result);
    }
}
