package task3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class ForbiddenChecker {
    String ct = "";
    ArrayList<String> fbigrams;

    public ForbiddenChecker(ArrayList<String> fbigrams) {
        this.fbigrams = fbigrams;
    }

    public boolean[][] getReferenceTable(int bufLength, String ctFile, String outFile) throws IOException{
        String ct = Main.readTextFromFile(ctFile);
        boolean[][] res = new boolean[bufLength][bufLength];
        for (int i = 0; i < bufLength; i++)
            res[i][i] = true;
        String[] splct = new String[ct.length() / bufLength];
        for (int i = 0; i < ct.length(); i += bufLength)
            splct[i / bufLength] = ct.substring(i, i + bufLength);
        for (int i = 0; i < bufLength; i++)
            for (int j = 0; j < bufLength; j++) {
                if (i == j)
                    continue;
                for (int k = 0; k < splct.length; k++)
                    if (fbigrams.contains("" + splct[k].charAt(i) + splct[k].charAt(j))) {
                        res[i][j] = true;
                        break;
                    }
            }
        for (int i = 0; i < res.length; i++)
            System.out.println(Arrays.toString(res[i]));

        FileWriter fw = new FileWriter(outFile);
        String mask = "\\";
        for (int i = 0; i < bufLength; i++)
            mask+="\t"+i;
        fw.write(mask+"\r\n");
        for (int i = 0; i < bufLength; i++) {
            mask = ""+i;
            for (int j = 0; j < bufLength; j++) {
                mask+="\t";
                if(res[i][j])
                    mask+="x";
            }
            fw.write(mask+"\r\n");
        }
        fw.flush();
        fw.close();
        return res;
    }
}
