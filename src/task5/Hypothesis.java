package task5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;

public class Hypothesis {
    public ArrayList<ArrayList<Double>> computeHds(String alph, String cyphered, int n1, int n2) {
        ArrayList<ArrayList<Double>> Hds = new ArrayList<>();
        for (int d = n1; d < n2 + 1; d++) {
            ArrayList<Integer> Z = new ArrayList<>();
            HashMap<Integer, Integer> Pz = new HashMap<>(alph.length());
//            for (int i = 0; i < alph.length(); i++) {
//                Pz.add((double)0);
//            }
//            double[] Pz = new double[alph.length()];

            int t = cyphered.length() / d;
            int r = cyphered.length() % d;
            int L = 0;
            for (int j = 0; j < ((t - 1) * d + r); j++) {
                int z = (alph.indexOf(cyphered.charAt(j)) - alph.indexOf(cyphered.charAt(j + d))
                        + alph.length()) % alph.length();
                if (Pz.containsKey(z))
                    Pz.replace(z, Pz.get(z)+ 1);
//                    Pz.put(z, Pz.++);
//                    Pz.set(Pz.indexOf((double) z), Pz.get(z) + 1);
                else {
                    Pz.put(z, 1);
                }
                L++;
                Z.add(z);
            }
            ArrayList<Double> P = new ArrayList<>();
            for (int i = 0; i < Pz.size(); i++) {
                  P.add((double)Math.round(((double)Pz.get(i) / L) * 1000d) / 1000d);
//                P.add((double) Math.round((Pz.get(i) / (double) L) * 1000d) / 1000d);
//                double temp = (double) Math.round((Pz.get(i) / L) * 10000000d) / 10000000d;
//                P.add(temp);
            }
            Hds.add(P);
        }
        return Hds;
    }

    public Double findD(ArrayList<ArrayList<Double>> Hds, double[] freques, int n1) {
        double min = 999999999;
        double count = n1;
        double d = -1;
        for (int i = 0; i < Hds.size(); i++) {
            double s = 0;
            for (int j = 0; j < freques.length; j++) {
                double temp = (Hds.get(i).get(j) - freques[j]) * (Hds.get(i).get(j) - freques[j]);
                s += temp;
            }
            if (s < min) {
                d = count;
                min = s;
            }
            count++;
        }
        return d;
    }


}
