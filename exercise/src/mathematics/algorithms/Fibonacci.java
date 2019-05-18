package mathematics.algorithms;

import annotations.Persian;

@Persian("اعداد فیبوناتچی")
public class Fibonacci {

    public static final int MAX_INT_FIB = 1836311903;

    public static void main(String[] args) {

        fibonacci(Integer.MAX_VALUE);

    }

    public static void fibonacci(int limit) {

        int x = 0, y = 1, z = x + y;

        while (y < limit) {

            System.out.print(y + " ");

            x = y;
            y = z;
            z = x + y;

        }

    }

}
