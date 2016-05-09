package task3;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Lexx on 05.05.2016.
 */
public class Main {

    public static String readTextFromFile(String target) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(target));
        StringBuilder text = new StringBuilder();
        String line = null;
        String newLine = System.getProperty("line.separator");
        while ((line = br.readLine()) != null) {
            text.append(line);
            text.append(newLine);
        }
        return text.toString().toLowerCase();
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw_out = new BufferedWriter(new FileWriter
                ("./src/task3/forbidden_bigrams.txt"));
        BufferedWriter bw_alph = new BufferedWriter(new FileWriter
                ("./src/task3/alphabet.txt"));
        TextAnalyzer analyzer = new TextAnalyzer("./src/task3/longText.txt");
        ArrayList<String> forbidden = analyzer.getForbiddenBigrams();
        String out = forbidden.get(0);
        String alphabet = analyzer.alph;
        for (int i = 1; i < forbidden.size(); i++)
            out+=" "+forbidden.get(i);
        bw_out.write(out);
        bw_out.flush();
        bw_alph.write(alphabet);
        bw_alph.flush();
        ForbiddenChecker checker = new ForbiddenChecker(forbidden);
        checker.getReferenceTable(5,"iofiles/task_3/ct.txt","iofiles/task_3/table.txt");
        System.out.println("Done!");
    }
}
