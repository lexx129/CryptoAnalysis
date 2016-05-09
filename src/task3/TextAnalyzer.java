package task3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import task2.Cypher;

/**
 * Created by Lexx on 05.05.2016.
 */
public class TextAnalyzer {
    public String alph = "";
    public String text;


    public TextAnalyzer(String ptFile) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(ptFile));
        this.text = readTextFromFile(br);
        for (int i = 0; i < text.length(); i++) {
            if (!this.alph.contains(text.charAt(i) + ""))
                if (Character.isLetter(text.charAt(i)))
                    this.alph += text.charAt(i);
        }
    }

    public static String readTextFromFile(BufferedReader br) throws IOException {
        StringBuilder text = new StringBuilder();
        String line = null;
        String newLine = System.getProperty("line.separator");
        while ((line = br.readLine()) != null) {
            text.append(line);
            text.append(newLine);
        }
        return text.toString().toLowerCase();
    }

    public ArrayList<String> getForbiddenBigrams() {
        String buffer;
        ArrayList<String> forbidden = new ArrayList<>();
        for (int i = 0; i < alph.length(); i++) {
            for (int j = 0; j < alph.length(); j++) {
                buffer = "" + alph.charAt(i) + alph.charAt(j);
                if (!text.contains(buffer))
                    forbidden.add(buffer);
            }
        }
        return forbidden;
    }
}