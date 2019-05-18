package mathematics.algorithms;

import annotations.Learn;
import annotations.Persian;

import java.math.BigInteger;
import java.util.Arrays;

@Persian("اعداد بزرگ")
@Learn(java.math.BigInteger.class)
public class BigIntegerExercise {

    public static void main(String[] args) {

        BigInteger n1 = new BigInteger("25453322155998656251211");
        BigInteger n2 = new BigInteger("zzzzzzzzzzzz", 36);

        System.out.println(n1.add(n2));
        System.out.println(n1.subtract(n2));
        System.out.println(n1.multiply(n2));
        System.out.println(n1.divide(n2));
        System.out.println(n1.mod(n2));
        System.out.println(n1.bitCount());
        System.out.println(n1.bitLength());
        System.out.println(n1.gcd(n2));
        System.out.println(n1.nextProbablePrime());
        System.out.println(Arrays.toString(n1.sqrtAndRemainder()));
        System.out.println(n1.isProbablePrime(Integer.MAX_VALUE));
        System.out.println(n2.toString(36));

    }

}
