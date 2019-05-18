package mathematics.problems;

import annotations.Algorithm;
import annotations.TimeLimit;
import annotations.Title;
import annotations.URL;
import mathematics.algorithms.BigIntegerExercise;

import java.math.BigInteger;

@Algorithm(BigIntegerExercise.class)
@Title("485 - Pascal's Triangle of Death")
@URL("https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=6&page=show_problem&problem=426")
@TimeLimit(3.000)
public class PascalsTriangleOfDeath {

    public static void main(String[] args) {

        printPascal(BigInteger.TEN.pow(60));

    }

    public static void printPascal(BigInteger limit) {

        outer:
        for (int line = 1; true; line++) {

            BigInteger element = BigInteger.ONE;

            for (int i = 1; i<= line; i++) {

                if(element.compareTo(limit) > 0) break outer;

                System.out.print(element + " ");

                element = element.multiply(BigInteger.valueOf(line - i)).divide(BigInteger.valueOf(i));

            }

            System.out.println();

        }

    }

}
