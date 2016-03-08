package task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter n: ");
        int n = Integer.parseInt(br.readLine());
        Transposition tr = new Transposition(n);
        Transposition random = tr.makeRandomTransposition(n);
        System.out.println("Random transposition: " + Arrays.toString(random.getElements())
        + "\n" + "Auxilary transposition: " + Arrays.toString(tr.getAuxilary()));

        System.out.println("Enter message: ");
        String message = br.readLine();
        Cypher cypher = new Cypher(message, random);
        cypher.cypher();
        String cyphered = cypher.getCyphered();
        System.out.println("Исходное сообщение: " + cypher.getMessage() +
                "\nЗашифрованное сообщение: " + cypher.getCyphered());

        cypher = new Cypher(random, cyphered);
        cypher.decypher();
        System.out.println("Расшированное сообщение: " + cypher.getDecyphered());
    }
}
