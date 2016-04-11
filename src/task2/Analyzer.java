package task2;

/**
 * Created by Lexx on 10.04.2016.
 */
public class Analyzer {

    public float analyzeMatchIndex(String first, String second){
        boolean delta;
        int sum = 0;
        for (int i = 0; i < first.length() - 1; i++) {
            delta = first.charAt(i) == second.charAt(i);
            if (delta)
                sum++;
        }
        return sum / (float)first.length();
    }

    private float amount(char symbol, String target){
        float res = 0;
        for (int i = 0; i < target.length(); i++) {
            if (target.charAt(i) == symbol)
                res++;
        }
//        System.out.println("Amount of " + symbol + "'s is " + res / (float) target.length());
        return res / (float) target.length();
    }

    public float analyzeAverageMatchIndex(String first, String second){
        float sum = 0;
        for (int i = 0; i < first.length() - 1; i++) {
            sum += amount(first.charAt(i), first) *
                    amount(second.charAt(i), second);
        }
        return sum;
    }
}
