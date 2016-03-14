package task1;

import java.util.LinkedList;
import java.util.Random;

public class Transposition {

    private int[] elements;
    private int[] auxilary;
    private int[] random_elems;
    private LinkedList<int[]> result;

    public Transposition(int length) {
        this.elements = new int[length];
    }
    public Transposition(int[] elements) {
        this.elements = elements;
    }

    public int getLength() {
        return elements.length;
    }

    public int[] getElements() {
        return elements;
    }

    public int[] getAuxilary() {
        return auxilary;
    }

    public void setElements(int[] elements) {
        this.elements = elements;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public Transposition makeRandomTransposition(int n) {
        Transposition trans = new Transposition(n);
        random_elems = new int[n];
        auxilary = new int[n];
        for (int i = 0; i < n - 1; i++) {
            auxilary[i] = i + 1;
        }
        Random random = new Random();
        for (int i = 0; i < n - 1; i++) {
            int r = random.nextInt(n - 1);
            random_elems[i] = r;
            swap(auxilary, i, r);
        }
        System.out.println();
        auxilary[n - 1] = 0;
        trans.elements[0] = auxilary[0];
        for (int i = 1; i < n; i++) {
            trans.elements[auxilary[i - 1]] = auxilary[i];
        }
        return trans;
    }

    private boolean isCorrect(int[] arr) {
        int a = arr[0];
        int temp = a;
        int i = 0;
        while (i < arr.length - 1 && a != arr[temp]) {
            i++;
            temp = arr[temp];
        }
        return i == arr.length - 1 && a == arr[temp];
    }

    public LinkedList<int[]> makeAllTranspositions(int n) {
        result = new LinkedList<int[]>();
        int[] temp = elements;
        for (int i = 0; i < n; i++) {
            temp[i] = i;
        }
        generateNew(temp, 0, n);
        return result;
    }

    private void generateNew(int[] transp, int k, int n) {
        if (k == n) {
            if (isCorrect(transp))
                result.add(transp.clone());
        }
        else
        {
            for (int i = k; i < transp.length; i++) {
                swap(transp, k, i);
                generateNew(transp, k + 1, n);
                swap(transp, k ,i);
            }
        }
    }
}
