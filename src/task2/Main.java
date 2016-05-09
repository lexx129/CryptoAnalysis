package task2;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br;
        Scanner scan = new Scanner(System.in);
        System.out.println("Working mode: \n 1 - Count match indexes;" +
                "\n 2 - Vigenere");
        int mode = scan.nextInt();
        switch (mode) {
            case 1:
                BufferedWriter bw = new BufferedWriter(new FileWriter("./src/task2/analyzed.txt"));
                Analyzer analyzer = new Analyzer();
                System.out.println("What kind of analysing? " +
                        "\n 1: MatchIndex; \n 2: AverageMatchIndex.");
                int type = scan.nextInt();
                if (type == 1) {
                    System.out.println("Alphabet: ");
                    String alph = scan.next();
                    switch (alph) {
                        case "cyr":
                            br = new BufferedReader(new FileReader("./src/task2/cyr.txt"));
                            for (int i = 0; i < 4; i++) {
                                String cyr1 = br.readLine().toLowerCase();
                                String cyr2 = br.readLine().toLowerCase();
                                if (cyr1.length() > cyr2.length())
                                    cyr1 = cyr1.substring(0, cyr2.length());
                                else
                                    cyr2 = cyr2.substring(0, cyr1.length());
                                float index = analyzer.analyzeMatchIndex(cyr1, cyr2) * 100;
                                System.out.println("Match index for " + (i + 1) + " pair = "
                                        + index);
                                bw.write("\nMatch index = " + index + "\n");
                                bw.flush();
                            }
                            bw.write("------------");
                            bw.flush();
                            break;
                        case "eng":
                            br = new BufferedReader(new FileReader("./src/task2/eng.txt"));
                            for (int i = 0; i < 4; i++) {
                                String lat1 = br.readLine().toLowerCase();
                                String lat2 = br.readLine().toLowerCase();
                                if (lat1.length() > lat2.length())
                                    lat1 = lat1.substring(0, lat2.length());
                                else
                                    lat2 = lat2.substring(0, lat1.length());
                                float index = analyzer.analyzeMatchIndex(lat1, lat2) * 100;
                                System.out.println("Match index for " + (i + 1) + " pair = "
                                        + index);
                                bw.write("\nMatch index = " + index + "\n");
                                bw.flush();
                            }
                            bw.write("------------");
                            bw.flush();
                            break;
                        case "rand":
                            for (int i = 0; i < 4; i++) {
                                System.out.println("Strings' length :");
                                int length = scan.nextInt();
                                Generator generator = new Generator(length);
                                String rand1 = generator.generate(2);
                                String rand2 = generator.generate(2);
                                bw.write(rand1 + "\n" + rand2);
                                bw.flush();
                                float index = analyzer.analyzeMatchIndex(rand1, rand2) * 100;
                                System.out.println("Match index for " + (i + 1) + " pair = "
                                        + index);
                                bw.write("\nMatch index = " + index + "\n");
                                bw.flush();
                            }
                            bw.write("------------");
                            bw.flush();
                            break;
                        default:
                            System.err.println("No such alphabet.");
                    }
                } else if (type == 2) {
                    System.out.println("Alphabet: ");
                    String alph = scan.next();
                    switch (alph) {
                        case "cyr":
                            br = new BufferedReader(new FileReader("./src/task2/cyr.txt"));
                            for (int i = 0; i < 4; i++) {
                                String cyr1 = br.readLine().toLowerCase();
                                String cyr2 = br.readLine().toLowerCase();
                                if (cyr1.length() > cyr2.length())
                                    cyr1 = cyr1.substring(0, cyr2.length());
                                else
                                    cyr2 = cyr2.substring(0, cyr1.length());
                                float index = analyzer.analyzeAverageMatchIndex(cyr1, cyr2, "cyr");
                                System.out.println("Average match index for " + (i + 1) + " pair = "
                                        + index);
                                bw.write("\nAverage match index = " + index + "\n");
                                bw.flush();
                            }
                            bw.write("------------");
                            bw.flush();
                            break;
                        case "eng":
                            br = new BufferedReader(new FileReader("./src/task2/eng.txt"));
                            for (int i = 0; i < 4; i++) {
                                String lat1 = br.readLine().toLowerCase();
                                String lat2 = br.readLine().toLowerCase();
                                if (lat1.length() > lat2.length())
                                    lat1 = lat1.substring(0, lat2.length());
                                else
                                    lat2 = lat2.substring(0, lat1.length());
                                float index = analyzer.analyzeAverageMatchIndex(lat1, lat2, "eng");
                                System.out.println("Average match index for " + (i + 1) + " pair = "
                                        + index);
                                bw.write("\nAverage match index = " + index + "\n");
                                bw.flush();
                            }
                            bw.write("------------");
                            bw.flush();
                            break;
                        case "rand":
                            for (int i = 0; i < 4; i++) {
                                System.out.println("Strings' length :");
                                int length = scan.nextInt();
                                Generator generator = new Generator(length);
                                String rand1 = generator.generate(2);
                                String rand2 = generator.generate(2);
                                bw.write(rand1 + "\n" + rand2);
                                bw.flush();
                                float index = analyzer.analyzeAverageMatchIndex(rand1, rand2, "eng");
                                System.out.println("Average match index for " + (i + 1) + " pair = "
                                        + index);
                                bw.write("\nAverage match index = " + index + "\n");
                                bw.flush();
                            }
                            bw.write("------------");
                            bw.flush();
                            break;
                        default:
                            System.err.println("No such language, pls try again.");
                            break;
                    }
                }
                break;
            case 2:
                br = new BufferedReader(new FileReader("./src/task2/vigenere1.txt"));
                String alphabet = br.readLine();
                String key = br.readLine();
                String plain = br.readLine().toLowerCase();
                Cypher cypher = new Cypher();
                analyzer = new Analyzer();
                bw = new BufferedWriter(new FileWriter("./src/task2/analyzed1.txt"));
                String cyphered = cypher.cypher(plain, key, alphabet);
//                System.out.println("Enter shift length: ");
//                int length = scan.nextInt();
                System.out.println("Choose mode: \n 1 - shifting plain text; \n 2 - shifting cyphered;");
                int chosen = scan.nextInt();
                switch (chosen) {
                    case 1:
                        System.out.println("plain is " + plain);
                        for (int i = 0; i < 15; i++) {
                            String shifted = shift(plain, i);
                            bw.write(i + ": " + analyzer.analyzeMatchIndex(plain, shifted) + "\n");
                            bw.flush();
                            System.out.println(i + ": " + analyzer.analyzeMatchIndex(plain, shifted) * 100);
                        }
                        break;
                    case 2:
                        System.out.println("cyphered is " + cyphered);
                        System.out.println("key is " + key);
                        for (int i = 0; i < 15; i++) {
                            String shifted = shift(cyphered, i);
//                            System.out.println("shift is " + shifted);
                            bw.write(i + ": " + analyzer.analyzeMatchIndex(cyphered, shifted) + "\n");
                            bw.flush();
                            System.out.println(i + ": " + analyzer.analyzeMatchIndex(cyphered, shifted) * 100);
                        }
                        break;
                }

//                analyzer.
                break;
//            case 3:
//                String in = scan.next();
//                System.out.println(shift(in, 2));
        }
    }

    public static String shift(String in, int length) {
//        char[] temp = new char[in.length()];
//        temp[0] = in.charAt(in.length());
//        for (int i = 0; i < in.length() - 1; i++) {
//            temp[i + 1] = in.charAt(i);
//        }
//        return new String(temp);
        String shifted = in;
        for (int i = 0; i < length; i++) {
            shifted = shifted.charAt(shifted.length() - 1) + shifted.substring(0, shifted.length() - 1);
        }
        return shifted;
    }
}

//        System.out.println("cyrillic string: " + generator.generate(1));
//        System.out.println("latin string: " + generator.generate(2));
//        System.out.println("mixed string: " + generator.generate(3));




