package task2;

/**
 * Created by Lexx on 10.04.2016.
 */
public class Analyzer {
    private String first;
    private String second;

    public Analyzer(String first, String second) {
        this.first = first;
        this.second = second;
    }
    public float analyze(){
        boolean delta;
        int sum = 0;
        for (int i = 0; i < first.length() - 1; i++) {
            delta = first.charAt(i) == second.charAt(i);
            if (delta)
                sum++;
        }
        return sum / (float)first.length();
    }
}
