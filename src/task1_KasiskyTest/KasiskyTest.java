package task1_KasiskyTest;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lexx on 09.03.2016.
 */
public class KasiskyTest {

    private class Pair {
        public int index;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int value;

    }

    private int searchSpace;
    private String cypher;

    public KasiskyTest(int searchSpace, String cypher) {
        this.searchSpace = searchSpace;
        this.cypher = cypher;
    }

    private int gcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    public int[] findKeyLength() {
        List<Integer> repeated = new LinkedList<Integer>();
        for (int i = 0; i < cypher.length() - searchSpace + 1; i++) {
            String temp = cypher.substring(i, i + searchSpace);
            for (int j = i + 1; j < cypher.length() - searchSpace + 1; j++) {
                String temp2 = cypher.substring(j, j + searchSpace);
                if (temp.equals(temp2))
                    repeated.add(j - i);
            }
        }
        int[] gcds = new int[3000];
        for (int i = 0; i < repeated.size(); i++) {
            for (int j = i + 1; j < repeated.size(); j++) {
                gcds[gcd(repeated.get(i), repeated.get(j))]++;
            }
        }
        gcds[0] = 0;
//        System.out.println(Arrays.toString(gcds));
        List<Integer> res = new LinkedList<Integer>();
        int max = 0;
        int maxpos = 0;
        for (int i = 0; i < 30; i++) {
            res.add(i, gcds[i]);
            if (gcds[i] > max) {
                max = gcds[i];
                maxpos = i;
            }
        }
//        Collections.sort(res);
        System.out.println("**Resulted gcd's are: " + res.toString());
        return new int[]{maxpos, max};
    }

}
