package mathematics.problems;

import annotations.Algorithm;
import annotations.TimeLimit;
import annotations.Title;
import annotations.URL;

import java.math.BigInteger;

@Algorithm(mathematics.algorithms.BigMod.class)
@Title("374 - Big Mod")
@URL("https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=5&page=show_problem&problem=310")
@TimeLimit(3.000)
public class BigMod {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        System.out.println(solution(3, 18132, 17));
        System.out.println(solution(17, 1765, 3));
        System.out.println(solution(2374859, 3029382, 36123));

        System.out.println(System.currentTimeMillis() - start);


    }

    public static BigInteger solution(int b, int p, int m) {

        if(p == 0) return BigInteger.ONE;

        if(p % 2 == 0) {

            return solution(b, p/2, m).pow(2).mod(BigInteger.valueOf(m));

        } else {

            return solution(b, p-1, m).multiply(BigInteger.valueOf(b % m)).mod(BigInteger.valueOf(m));

        }

    }

}
