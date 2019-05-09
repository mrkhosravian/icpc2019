package icpc16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class G {

    public static void main(String[] args) {

        var s = new Scanner(System.in);

        int n = s.nextInt();

        var array = new int[n];

        for (int i = 0; i < n; i++) {

            array[i] = s.nextInt();

        }

        System.out.println(solution(array));

    }

    public static int solution(int[] numbers) {

        int max_qoteli = 0;

        outer: for (int i = numbers.length; i >= 3; i--) { // i  representing size of sub array

            for (int j = 0; j < numbers.length - i + 1; j++) { // iterates over count of sub arrays

                var sub = Arrays.copyOfRange(numbers, j, j + i - 1);

                if(isQoteli(sub)){

                    max_qoteli = sub.length;
                    break outer;

                }

            }

        }

        return max_qoteli;

    }

    public static boolean isQoteli(int[] array) {

        return Arrays.stream(array).skip(1).limit(1).allMatch(a -> a < array[0] && a < array[array.length-1]);

    }

}
