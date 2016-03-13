package task1;

import java.util.LinkedList;

/**
 * Created by Lexx on 06.03.2016.
 */
public class Cypher {
    private String ALPH = "абвгдежзийклмнопрстуфхцчшщъыьэюя";


    private String message;
    private String cyphered;
    private String decyphered;
    private Transposition transposition;

    public Cypher(String message, Transposition trans) {
        this.transposition = trans;
        this.message = message.replaceAll("\\s", "");
        int diff = this.message.length() % trans.getLength();
        if (diff != 0)
            for (int i = 1; i < diff; i++) {
                this.message += "~";
            }
        cyphered = "";
    }

    public Cypher(Transposition transposition, String cyphered) {
        this.transposition = transposition;
        this.cyphered = cyphered;
        decyphered = "";
    }

    public void setTrans(Transposition trans) {
        this.transposition = trans;
    }

    public String getMessage() {
        return message;
    }

    public String getCyphered() {
        return cyphered;
    }

    public String getDecyphered() {
        return decyphered;
    }

    public void cypher() {
        int pos = 0;
        int n = transposition.getLength();
        for (int i = 0; i < message.length() / n; i++) {
            char[] block = new char[n];
            char[] temp;
            temp = message.substring(pos, n + pos).toCharArray();
            pos += n;
            for (int j = 0; j < n; j++) {
                block[transposition.getElements()[j]] = temp[j];
            }
            cyphered = cyphered.concat(String.valueOf(block));
        }
    }

    public void decypher() {
        int pos = 0;
        int n = transposition.getLength();
        for (int i = 0; i < cyphered.length() / n; i++) {
            char[] block = new char[n];
            char[] temp = cyphered.substring(pos, n + pos).toCharArray();
            pos += n;
            for (int j = 0; j < n; j++) {
                block[j] = temp[transposition.getElements()[j]];
            }
            decyphered = decyphered.concat(String.valueOf(block));
        }
//        decyphered = decyphered.replaceAll("~+", "");
    }

    public LinkedList<String> brute(LinkedList<int[]> source){
        LinkedList<String> result = new LinkedList<String>();
        for (int[] aSource : source) {
            setTrans(new Transposition(aSource));
            decypher();
            result.add(decyphered);
        }
        return result;
    }
}
