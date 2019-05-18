package mathematics.problems;

import annotations.Algorithm;
import annotations.TimeLimit;
import annotations.Title;
import annotations.URL;
import mathematics.algorithms.BigIntegerExercise;
import mathematics.algorithms.Fibonacci;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Algorithm(BigIntegerExercise.class)
@Algorithm(Fibonacci.class)
@Title("495 - Fibonacci Freeze")
@URL("https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=6&page=show_problem&problem=436")
@TimeLimit(3.000)
public class FibonacciFreeze {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int index = scanner.nextInt();
        printAnswer(index, fibonacci(index));

        while(scanner.hasNext()) {

            index = scanner.nextInt();
            printAnswer(index, fibonacci(index));

        }

    }

    public static void printAnswer(int index, BigInteger ans) {

        System.out.println("The Fibonacci number for " + index + " is " + ans);

    }

    private static List<BigInteger> calculateds = new ArrayList<>(5000);
    static {

        calculateds.add(BigInteger.ZERO);
        calculateds.add(BigInteger.ONE);

    }

    public static BigInteger fibonacci(int index) {

        if(calculateds.size() - 1 >= index) {

            return calculateds.get(index);

        } else {

            calculateds.add(index, fibonacci(index - 1).add(fibonacci(index - 2)));

        }

        return fibonacci(index);

    }

}