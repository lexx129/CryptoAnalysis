package task2;

import java.security.SecureRandom;
import java.util.Scanner;

/**
 * Created by Lexx on 10.04.2016.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Strings' length :");
        int length = scan.nextInt();
        Generator generator = new Generator(length);
//        System.out.println("cyrillic string: " + generator.generate(1));
//        System.out.println("latin string: " + generator.generate(2));
//        System.out.println("mixed string: " + generator.generate(3));
        String lat1 = generator.generate(2);
        String lat2 = generator.generate(2);
        Analyzer analyzer = new Analyzer(lat1, lat2);
        System.out.println(analyzer.analyze() * 100);
        String mix1 = generator.generate(3);
        String mix2 = generator.generate(3);
        analyzer = new Analyzer(mix1, mix2);
        System.out.println(analyzer.analyze() * 100);
    }
}
