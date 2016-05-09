package task5;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Matcher;

/**
 * Created by Lexx on 05.05.2016.
 */
public class Analyzer {
    private int length = 0;

    public double[] AnalyzeFrequency(String alphabet, String target) {
        double[] frequencies = new double[alphabet.length()];
        for (int i = 0; i < target.length(); i++) {
            try {
                if (Character.isLetter(target.charAt(i))) {
                    length++;
                    frequencies[alphabet.indexOf(target.charAt(i))]++;
                }
            } catch (ArrayIndexOutOfBoundsException e){
                System.err.println(target.charAt(i));
            }
        }
        for (int i = 0; i < frequencies.length; i++) {
            frequencies[i] = (double) Math.round((frequencies[i] / length) * 1000d) / 1000d;
        }

//        Arrays.sort(frequencies);
        return frequencies;
    }

    public ArrayList<Double> Hyp0(double[] freques, String alph) {
        ArrayList<Double> res = new ArrayList<>();
        for (int i = 0; i < alph.length(); i++) {
            double p = 0;
            for (int j = 0; j < alph.length(); j++) {
                p += (freques[j]) * ((freques[(i + j) % alph.length()]));
            }
            res.add(p);
        }
        return res;
    }

}
