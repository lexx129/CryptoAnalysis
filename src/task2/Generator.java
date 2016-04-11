package task2;

import java.util.Random;

/**
 * Created by Lexx on 10.04.2016.
 */
public class Generator {
    private static String CYR_ALPH = "абвгдежзийклмнопрстуфхцчшщъыьэюя";
    private static String LAT_ALPH = "abcdefghijklmnopqrstuvwxyz";
    private int length;

    public Generator(int length) {
        this.length = length;
    }

    // generating random string. 1 is for Cyrillic, 2 is for Latin,
    // 3 is for mixed.
    public String generate(int type) {
        Random rnd = new Random();
        char[] text = new char[length];
        switch (type) {
            case 1:
                for (int i = 0; i < length; i++) {
                    text[i] = CYR_ALPH.charAt(rnd.nextInt(CYR_ALPH.length()));
                }
                break;
            case 2:
                for (int i = 0; i < length; i++) {
                    text[i] = LAT_ALPH.charAt(rnd.nextInt(LAT_ALPH.length()));
                }
                break;
            case 3:
                for (int i = 0; i < length - 1; i++) {
                    boolean chosen = rnd.nextBoolean();
                    if (chosen)
                        text[i] = CYR_ALPH.charAt(rnd.nextInt(CYR_ALPH.length()));
                    else
                        text[i] = LAT_ALPH.charAt(rnd.nextInt(LAT_ALPH.length()));
                }
        }
        return new String(text);
    }
}
