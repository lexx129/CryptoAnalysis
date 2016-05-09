package task5;

import task2.*;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Lexx on 05.05.2016.
 */
public class Main {

    public static String LAT_ALPH = "abcdefghijklmnopqrstuvwxyz";

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

    public static void printFreques(double[] freques) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("./src/task5/frequences"));
        for (int i = 0; i < freques.length; i++) {
            bw.write(LAT_ALPH.charAt(i) + ": " + freques[i] + "\n");
            bw.flush();
        }
    }

    public static void printH0(ArrayList<Double> H0) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("./src/task5/H0.txt"));
        for (int i = 0; i < H0.size(); i++) {
            bw.write("P_" + i + " = " + H0.get(i) + "\n");
            bw.flush();
        }
    }

    public static void printHds(ArrayList<ArrayList<Double>> Hds){
        for (int i = 0; i < Hds.size(); i++) {
            ArrayList<Double> temp = Hds.get(i);
            for (int j = 0; j < temp.size(); j++) {
                System.out.print(temp.get(j) + ", ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader systemReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("./src/task5/longText.txt"));
        Analyzer analyzer = new Analyzer();
        String longText = readTextFromFile(br);
        br = new BufferedReader(new FileReader("./src/task5/openText_eng.txt"));
        br = new BufferedReader(new FileReader("./src/task5/cyphered.txt"));
        String cyphered = readTextFromFile(br);
        String openText = readTextFromFile(br);
        double[] frequences = analyzer.AnalyzeFrequency(LAT_ALPH, longText);
        printFreques(frequences);
        ArrayList<Double> H0 = analyzer.Hyp0(frequences, LAT_ALPH);
        printH0(H0);
        Cypher cypher = new Cypher();
        String key = "justkey";
//        String cyphered = cypher.cypher(openText, key, LAT_ALPH);
        Hypothesis hypo = new Hypothesis();
        System.out.println("Enter n1: ");
        int n1 = Integer.parseInt(systemReader.readLine());
        System.out.println("Enter n2: ");
        int n2 = Integer.parseInt(systemReader.readLine());
        ArrayList<ArrayList<Double>> Hds = hypo.computeHds(LAT_ALPH, cyphered, n1, n2);
        printHds(Hds);
        double d = hypo.findD(Hds, frequences, n1);
        System.out.println("d = " + d);
    }
}
