/**
 * Created by TriplePi on 23.03.2017.
 */

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * Created by TriplePi on 31.01.2017.
 */
public class test {
    public static void main(String[] args) {
        int[] a = {3, 0};
        int[] b = {3, 4};
        compareTo(a, b);
    }

    public static int compareTo(int[] first, int[] second) {
        System.out.println(Arrays.toString(first) + " " + Arrays.toString(second));
        if (first[1] == second[1] && first[0] == second[0]) {
            System.out.println("equal");
            return 0;
        }
        System.out.println("Zahodish?");
        if ((first[0] > second[0]) && (first[1] < second[1])) {
            System.out.println("lf");
            return 1;
            //влево вперёд
        }
        if ((first[0] > second[0]) && (first[1] > second[1])) {
            System.out.println("rf");
            return 2;
            //вправо вперёд
        }
        if ((first[0] < second[0]) && (first[1] < second[1])) {
            System.out.println("lb");
            return 3;
            //влево назад
        }
        if ((first[0] < second[0]) && (first[1] > second[1])) {
            System.out.println("rb");
            return 4;
            //вправо назад
        }

        System.out.println("wtf");
        return 5;
    }
}
