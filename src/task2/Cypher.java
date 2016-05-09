package task2;

/**
 * Created by algavrilov on 11.04.2016.
 */
public class Cypher {
    // it's a Vigenere cyphering
    public String cypher (String plainText, String key, String alph){
        char[] result = new char[plainText.length()];
        for (int i = 0; i < plainText.length(); i++) {
            try {
                result[i] = alph.charAt((alph.indexOf(plainText.charAt(i)) +
                        alph.indexOf(key.charAt(i % key.length()))) % alph.length());
            }
            catch (StringIndexOutOfBoundsException e){
                System.out.println(i);
                e.printStackTrace();
            }

        }
        return new String(result);
    }
}
