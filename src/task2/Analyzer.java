package task2;

/**
 * Created by Lexx on 10.04.2016.
 */
public class Analyzer {

    public static String CYR_ALPH = "абвгдежзийклмнопрстуфхцчшщъыьэюя";
    public static String LAT_ALPH = "abcdefghijklmnopqrstuvwxyz";

    float analyzeMatchIndex(String first, String second) {
        boolean delta;
        int sum = 0;
        for (int i = 0; i < first.length(); i++) {
            delta = first.charAt(i) == second.charAt(i);
            if (delta)
                sum++;
        }
        return sum / (float) first.length();
    }

    private float amount(char symbol, String target) {
        float res = 0;
        for (int i = 0; i < target.length(); i++) {
            if (target.charAt(i) == symbol)
                res++;
        }
//        System.out.println("Amount of " + symbol + "'s is " + res / (float) target.length());
        return res / (float) target.length();
    }

    public float analyzeAverageMatchIndex(String first, String second, String lang) {
        float sum = 0;
        float[] f;
        float[] s;
        if (lang.equals("eng")) {
            f = new float[26];
            s = new float[26];
            for (int i = 0; i < first.length(); i++) {
                if (f[LAT_ALPH.indexOf(first.charAt(i))] == 0)
                    f[LAT_ALPH.indexOf(first.charAt(i))] = amount(first.charAt(i), first);
                if (s[LAT_ALPH.indexOf(second.charAt(i))] == 0)
                    s[LAT_ALPH.indexOf(second.charAt(i))] = amount(second.charAt(i), second);
            }
            for (int i = 0; i < f.length; i++) {
                sum += f[i] * s[i];
            }
        } else if (lang.equals("cyr")) {
            f = new float[32];
            s = new float[32];
            for (int i = 0; i < first.length(); i++) {
                if (f[CYR_ALPH.indexOf(first.charAt(i))] == 0)
                    f[CYR_ALPH.indexOf(first.charAt(i))] = amount(first.charAt(i), first);
                if (s[CYR_ALPH.indexOf(second.charAt(i))] == 0)
                    s[CYR_ALPH.indexOf(second.charAt(i))] = amount(second.charAt(i), second);
            }
            for (int i = 0; i < f.length; i++) {
                sum += f[i] * s[i];
            }
        }
        return sum * 100;
    }
}
