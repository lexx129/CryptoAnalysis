package task1_KasiskyTest;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;
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

    public void brute(LinkedList<int[]> source, BufferedWriter bw) throws IOException {
        bw.write("\n\n***Possible variants of source text (there're " + source.size() + " of them)***\n");
        bw.flush();
        for (int i = 0; i < source.size(); i++) {
            setTrans(new Transposition(source.get(i)));
            decyphered = "";
            decypher();
//            result.add(decyphered);
            bw.write("\n" + Arrays.toString(source.get(i)) + "   " + decyphered + "\n*************");
            bw.flush();
            if (i % 100 == 0) {
                System.out.println("now on " + i + "'s transposition");
            }

        }
    }
}
