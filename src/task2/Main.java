package task2;

import java.util.Scanner;

/**
 * Created by Lexx on 10.04.2016.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Working mode: \n 1 - generate strings;" +
                "\n 2 - Count match index;" + "\n 3 - Count average match index;" +
                "\n 4 - Vigenere");
        int mode = scan.nextInt();
        System.out.println("Strings' length :");
        int length = scan.nextInt();
        Generator generator = new Generator(length);
//        System.out.println("cyrillic string: " + generator.generate(1));
//        System.out.println("latin string: " + generator.generate(2));
//        System.out.println("mixed string: " + generator.generate(3));
        String lat1 = generator.generate(2);
        String lat2 = generator.generate(2);
        String cyr1 = generator.generate(1);
        String cyr2 = generator.generate(1);
        Analyzer analyzer = new Analyzer();
        System.out.println(analyzer.analyzeMatchIndex(cyr1, cyr2) * 100);
        System.out.println(analyzer.analyzeAverageMatchIndex(cyr1, cyr2) * 100 + "\n-----");
        System.out.println(analyzer.analyzeMatchIndex(lat1, lat2) * 100);
        System.out.println(analyzer.analyzeAverageMatchIndex(lat1, lat2) * 100 + "\n-----");
        String mix1 = generator.generate(3);
        String mix2 = generator.generate(3);
        System.out.println(analyzer.analyzeMatchIndex(mix1, mix2) * 100);
        System.out.println(analyzer.analyzeAverageMatchIndex(mix1, mix2) * 100);
        Cypher cypher = new Cypher();
        String cyphered = cypher.cypher(cyr1, "примерключа", Generator.CYR_ALPH);
        System.out.println("plain:" + cyr1 + "\ncyphered: " + cyphered);
    }
}
