package icpc16;

import java.util.Scanner;

public class C {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        System.out.println((isPow2(sumOfDivisions(n)) ? 1 : 0));

    }

    // TODO: 5/7/2019 Finding divisions fastest way
    public static int sumOfDivisions(int n) {

        int sum = 0;

        for (int i = 1; i <= n/2; i++) {

            if(n % i == 0) sum += i;

        }

        return sum;

    }

    public static boolean isPow2(int n) {

        int i;

        for (i = 1; i < n; i *= 2);

        return i == n;

    }

}
