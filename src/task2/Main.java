package task2;

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
        System.out.println("cyrillic string: " + generator.generate(1));
        System.out.println("latin string: " + generator.generate(2));
        System.out.println("mixed string: " + generator.generate(3));
    }
}
