package task1;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("./src/task1/input.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("./src/task1/output.txt"));
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter n: ");
        int n = scan.nextInt();
        Transposition tr = new Transposition(n);
        Transposition random = tr.makeRandomTransposition(n);
        bw.write("Random transposition: " + Arrays.toString(random.getElements())
                + "\n" + "Auxilary transposition: " + Arrays.toString(tr.getAuxilary()));
        bw.flush();
//        System.out.println("Random transposition: " + Arrays.toString(random.getElements())
//        + "\n" + "Auxilary transposition: " + Arrays.toString(tr.getAuxilary()));

//        System.out.println("Resulted GCDs: ");
//        String message = br.readLine().toLowerCase();
        String message = readTextFromFile(br);
        br.close();
        Cypher cypher = new Cypher(message, random);
        cypher.cypher();
        String cyphered = cypher.getCyphered();
        bw.write("\nИсходное сообщение: " + cypher.getMessage() +
                "\nЗашифрованное сообщение: " + cypher.getCyphered());
        bw.flush();
//        System.out.println("Исходное сообщение: " + cypher.getMessage() +
//                "\nЗашифрованное сообщение: " + cypher.getCyphered());

        cypher = new Cypher(random, cyphered);
        cypher.decypher();
        bw.write("\nРасшированное сообщение: " + cypher.getDecyphered());
        bw.flush();
//        System.out.println("Расшированное сообщение: " + cypher.getDecyphered());

        KasiskyTest test = new KasiskyTest(5, cyphered);
        int[] res = test.findKeyLength();
        System.out.println("Maximal gcd is " + res[1] + ", that means, " +
                "probable key length is " + res[0]);
        LinkedList<int[]> transposititons = tr.makeAllTranspositions(res[0]);
        Transposition transposition = new Transposition(res[0]);
        cypher = new Cypher(cyphered, transposition);
        LinkedList<String> bruted = cypher.brute(transposititons);
        for (String aBruted : bruted) {
            bw.write("\n" + aBruted + "\n******");
            bw.flush();
        }

    }
}
