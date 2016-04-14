package task2;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
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
                            for (int i = 0; i < 4; i++) {
                                System.out.println("Strings' length :");
                                int length = scan.nextInt();
                                Generator generator = new Generator(length);
                                String cyr1 = generator.generate(1);
                                String cyr2 = generator.generate(1);
                                bw.write(cyr1 + "\n" + cyr2);
                                bw.flush();
                                float index = analyzer.analyzeMatchIndex(cyr1, cyr2);
                                System.out.println("Match index for " + i + 1 + " pair = "
                                        + index);
                                bw.write("\nMatch index = " + index);
                                bw.flush();
                            }
                            bw.write("------------");
                            bw.flush();
                            break;
                        case "eng":
                            for (int i = 0; i < 4; i++) {
                                System.out.println("Strings' length :");
                                int length = scan.nextInt();
                                Generator generator = new Generator(length);
                                String lat1 = generator.generate(2);
                                String lat2 = generator.generate(2);
                                bw.write(lat1 + "\n" + lat2);
                                bw.flush();
                                float index = analyzer.analyzeMatchIndex(lat1, lat2);
                                System.out.println("Match index for " + i + 1 + " pair = "
                                        + index);
                                bw.write("\nMatch index = " + index);
                                bw.flush();
                            }
                            bw.write("------------");
                            bw.flush();
                            break;
                        case "mix":
                            for (int i = 0; i < 4; i++) {
                                System.out.println("Strings' length :");
                                int length = scan.nextInt();
                                Generator generator = new Generator(length);
                                String mix1 = generator.generate(3);
                                String mix2 = generator.generate(3);
                                bw.write(mix1 + "\n" + mix2);
                                bw.flush();
                                float index = analyzer.analyzeMatchIndex(mix1, mix2);
                                System.out.println("Match index for " + i + 1 + " pair = "
                                        + index);
                                bw.write("\nMatch index = " + index);
                                bw.flush();
                            }
                            bw.write("------------");
                            bw.flush();
                            break;
                    }
                } else if (type == 2) {
                    System.out.println("Alphabet: ");
                    String alph = scan.next();
                    switch (alph) {
                        case "cyr":
                            for (int i = 0; i < 4; i++) {
                                System.out.println("Strings' length :");
                                int length = scan.nextInt();
                                Generator generator = new Generator(length);
                                String cyr1 = generator.generate(1);
                                String cyr2 = generator.generate(1);
                                bw.write(cyr1 + "\n" + cyr2);
                                bw.flush();
                                float index = analyzer.analyzeAverageMatchIndex(cyr1, cyr2);
                                System.out.println("Average match index for " + i + 1 + " pair = "
                                        + index);
                                bw.write("\nAverage match index = " + index);
                                bw.flush();
                            }
                            bw.write("------------");
                            bw.flush();
                            break;
                        case "eng":
                            for (int i = 0; i < 4; i++) {
                                System.out.println("Strings' length :");
                                int length = scan.nextInt();
                                Generator generator = new Generator(length);
                                String lat1 = generator.generate(2);
                                String lat2 = generator.generate(2);
                                bw.write(lat1 + "\n" + lat2);
                                bw.flush();
                                float index = analyzer.analyzeAverageMatchIndex(lat1, lat2);
                                System.out.println("Average match index for " + i + 1 + " pair = "
                                        + index);
                                bw.write("\nAverage match index = " + index);
                                bw.flush();
                            }
                            bw.write("------------");
                            bw.flush();
                            break;
                        case "mix":
                            for (int i = 0; i < 4; i++) {
                                System.out.println("Strings' length :");
                                int length = scan.nextInt();
                                Generator generator = new Generator(length);
                                String mix1 = generator.generate(3);
                                String mix2 = generator.generate(3);
                                bw.write(mix1 + "\n" + mix2);
                                bw.flush();
                                float index = analyzer.analyzeAverageMatchIndex(mix1, mix2);
                                System.out.println("Average match index for " + i + 1 + " pair = "
                                        + index);
                                bw.write("\nAverage match index = " + index);
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
                BufferedReader br = new BufferedReader(new FileReader("./src/task2/vigenere.txt"));
                String alphabet = br.readLine();
                String key = br.readLine();
                String plain = br.readLine();
                Cypher cypher = new Cypher();
                analyzer = new Analyzer();
                bw = new BufferedWriter(new FileWriter("./src/task2/analyzed.txt"));
                String cyphered = cypher.cypher(plain, key, alphabet);
//                System.out.println("Enter shift length: ");
//                int length = scan.nextInt();
                System.out.println("Choose mode: \n 1 - shifting plain text; \n 2 - shifting cyphered with k = 5;");
                int chosen = scan.nextInt();
                switch (chosen){
                    case 1:
                        for (int i = 0; i < plain.length(); i++) {
                            String shifted = shift(plain, i);
                            bw.write(i + ": " + analyzer.analyzeMatchIndex(plain, shifted));
                            bw.flush();
                            System.out.println(i + ": " + analyzer.analyzeMatchIndex(plain, shifted));
                        }
                        break;
                    case 2:
                        System.out.println("cyphered is " + cyphered);
                        for (int i = 0; i < plain.length(); i++) {
                            String shifted = shift(cyphered, i);
                            bw.write(i + ": " + analyzer.analyzeMatchIndex(cyphered, shifted) + "\n");
                            bw.flush();
                            System.out.println(i + ": " + analyzer.analyzeMatchIndex(cyphered, shifted));
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




