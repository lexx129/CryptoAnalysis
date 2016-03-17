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
        System.out.println("Enter operation code: \n 0 - generate key; \n 1 - cyphering; \n 2 - decyphering; \n 3 - find key length; " +
                "\n 4 - bruting." );
        int mode = scan.nextInt();
        switch (mode){
            case 0:{ // key generation
                BufferedWriter bw_key = new BufferedWriter(new FileWriter("./src/task1/output_key.txt"));
                System.out.println("Enter n: ");
                int n = scan.nextInt();
                Transposition tr = new Transposition(n);
                Transposition random = tr.makeRandomTransposition(n);
                System.out.println("Random transposition: " + Arrays.toString(random.getElements())
                        + "\n" + "Auxilary transposition: " + Arrays.toString(tr.getAuxilary()));
                bw_key.write(Arrays.toString(random.getElements()));
                bw_key.flush();
                bw_key.close();
                break;
            }
            case 1:{  // cyphering
                BufferedReader br_key = new BufferedReader(new FileReader("./src/task1/output_key.txt"));
                BufferedWriter bw_cypher = new BufferedWriter(new FileWriter("./src/task1/output_cypher.txt"));
                String elements = readTextFromFile(br_key);
                elements = elements.substring(elements.indexOf('[') + 1, elements.lastIndexOf(']'));
                String[] temp_transp = elements.split(",");
                int[] transp = new int[temp_transp.length];
                for (int i = 0; i < transp.length; i++) {
                    transp[i] = Integer.parseInt(temp_transp[i].trim());
                }
                String message = readTextFromFile(br);
                br_key.close();
                br.close();
                Transposition random = new Transposition(transp);
                Cypher cypher = new Cypher(message, random);
                cypher.cypher();
                String cyphered = cypher.getCyphered();
                System.out.println("\nИсходное сообщение: " + cypher.getMessage() +
                        "\nЗашифрованное сообщение: " + cypher.getCyphered());
                bw_cypher.write(cypher.getCyphered());
                bw_cypher.flush();
                bw_cypher.close();
                break;
            }
            case 2: { // decyphering
                BufferedReader br_cypher = new BufferedReader(new FileReader("./src/task1/output_cypher.txt"));
                BufferedReader br_key = new BufferedReader(new FileReader("./src/task1/output_key.txt"));
                BufferedWriter bw_decypher = new BufferedWriter(new FileWriter("./src/task1/output_decypher.txt"));
                String elements = readTextFromFile(br_key);
                elements = elements.substring(elements.indexOf('[') + 1, elements.lastIndexOf(']'));
                String[] temp_transp = elements.split(",");
                int[] transp = new int[temp_transp.length];
                for (int i = 0; i < transp.length; i++) {
                    transp[i] = Integer.parseInt(temp_transp[i].trim());
                }
                String cyphered = readTextFromFile(br_cypher);
                br_key.close();
                br.close();
                Transposition random = new Transposition(transp);
                Cypher cypher = new Cypher(random, cyphered);
                cypher.decypher();
                String decyphered = cypher.getDecyphered();
                System.out.println("\nРасшированное сообщение: " + cypher.getDecyphered());
                bw_decypher.write(cypher.getDecyphered());
                bw_decypher.flush();
                bw_decypher.close();
                break;
            }
            case 3: { //find transposition's length
                BufferedReader br_cypher = new BufferedReader(new FileReader("./src/task1/output_cypher.txt"));
                BufferedWriter bw_keyLength = new BufferedWriter(new FileWriter("./src/task1/output_keyLength.txt"));
                String cyphered = readTextFromFile(br_cypher);
                System.out.println("Enter pieces' length: ");
                int n = scan.nextInt();
                KasiskyTest test = new KasiskyTest(n, cyphered);
                int[] res = test.findKeyLength();
                System.out.println("Maximal gcd is " + res[1] + ", that means, " +
                        "probable key length is " + res[0]);
                bw_keyLength.write(String.valueOf(res[0]));
                bw_keyLength.flush();
                bw_keyLength.close();
                break;
            }
            case 4: { // bruting!
                BufferedReader br_cypher = new BufferedReader(new FileReader("./src/task1/output_cypher.txt"));
                BufferedReader br_keyLength = new BufferedReader(new FileReader("./src/task1/output_keyLength.txt"));
                BufferedWriter bw_bruted = new BufferedWriter(new FileWriter("./src/task1/output_bruted.txt"));
                String cyphered = readTextFromFile(br_cypher);
                int length = Integer.parseInt(readTextFromFile(br_keyLength).trim());
                Transposition tr = new Transposition(length);
                LinkedList<int[]> transposititons = tr.makeAllTranspositions(length);
                Transposition transposition = new Transposition(length);
                Cypher cypher = new Cypher(transposition, cyphered);
                cypher.brute(transposititons, bw_bruted);
                break;
            }


        }



//        System.out.println("Random transposition: " + Arrays.toString(random.getElements())
//        + "\n" + "Auxilary transposition: " + Arrays.toString(tr.getAuxilary()));

//        System.out.println("Resulted GCDs: ");
//        String message = br.readLine().toLowerCase();

//        System.out.println("Исходное сообщение: " + cypher.getMessage() +
//                "\nЗашифрованное сообщение: " + cypher.getCyphered());


//        System.out.println("Расшированное сообщение: " + cypher.getDecyphered());


//        System.out.println("Maximal gcd is " + res[1] + ", that means, " +
//                "probable key length is " + res[0]);
//        LinkedList<int[]> transposititons = tr.makeAllTranspositions(res[0]);
//        Transposition transposition = new Transposition(res[0]);
//        cypher = new Cypher(transposition, cyphered);
//        cypher.brute(transposititons, bw);
    }
}
