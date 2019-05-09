package icpc16;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class F {

    public static void main(String[] args) {

        var s = new Scanner(System.in);

        int n = s.nextInt();
        int d = s.nextInt();

        ArrayList<Integer> array = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {

            array.add(s.nextInt());

        }

        System.out.println(patoes(d, array));

    }

    public static int patoes(int d, ArrayList<Integer> animals) {

        animals.sort(Integer::compareTo);

        int patoes = 0;

        for (int i = animals.get(0); i < animals.get(animals.size()-1); i++) {

            if(animals.contains(i)) patoes++;

            i+=d-1;

        }

        return patoes;

    }


}
