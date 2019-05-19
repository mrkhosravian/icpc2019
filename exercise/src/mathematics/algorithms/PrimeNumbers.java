package mathematics.algorithms;

import annotations.Persian;
import java.util.*;

@Persian("اعداد اول")
public class PrimeNumbers {

    //other algorithms: Miller-Rabin (probably prime), Solovay-Strassen (probably prime), Lucas (probably prime)

    public static void main(String[] args) {



    }

    public static void printPrimesNaive(int limit) {

        for (int i = 2; i < limit; i++) {

            if(isPrimeNaive(i)) System.out.println(i);

        }

    }
    public static boolean isPrimeNaive(int number) {

        for (int i = 2; i <= Math.sqrt(number); i++) {

            if(number % i == 0) return false;

        }

        return true;

    }

    //not suitable for large limits
    public static void printPrimesSieveOfEratosthenes(int limit) {

        boolean[] primes = new boolean[limit + 1]; //index 0 and 1 are not used

        Arrays.fill(primes, true);

        for (int i = 2; i <= Math.sqrt(limit); i++) {

            if(primes[i]) {

                for (int j = i*i; j <= limit; j += i) {

                    primes[j] = false;

                }

            }

        }

        for (int i = 2; i < primes.length; i++) {

            if(primes[i]) System.out.print(i + " ");

        }

    }

    /**
     * Let q be an integer of the form 2x + 1.
     *
     * q is excluded if and only if x is of the
     * form i + j + 2ij. That means,
     *
     * q = 2(i + j + 2ij) + 1 = (2i + 1)(2j + 1)
     *
     * So, an odd integer is excluded from the final list if
     * and only if it has a factorization of the form (2i + 1)(2j + 1)
     * which is to say, if it has a non-trivial odd factor.
     */
    public static void printPrimesSieveOfSundaram(int limit) {

        int newLimit = (limit - 2) / 2; //Sieve of Sundaram produces primes smaller than 2x+2 for a given number x.

        boolean[] isInForm = new boolean[newLimit + 1]; //is index in form of i+j+2ij where  1 <= i <= j
        Arrays.fill(isInForm, false);

        for (int i = 1; i < isInForm.length ; i++) {

            for (int j = i; i+j+2*i*j <= newLimit ; j++) {

                isInForm[i+j+2*i*j] = true;

            }

        }

        if(limit > 2) System.out.print(2 + " ");

        //Remaining primes are of the form 2*i+1 such that marked[i] is false.

        for (int i = 1; i < isInForm.length; i++) {

            if(!isInForm[i]) System.out.print(2*i+1 + " ");

        }

    }

    private static int[] primes;
    public static void printPrimesBitwiseSieve(int limit) {

        primes = new int[limit/64 + 1];

        //skip even numbers
        for (int i = 3; i <= Math.sqrt(limit); i+=2) {

            for (int j = i * i, k = i << 1; j < limit; j += k) {

                makeCompositeBitWiseSieve(j);

            }

        }

        System.out.print(2 + " ");

        for (int i = 3; i <= limit; i+=2) {

            if(readBit(i) == 0) System.out.print(i + " ");

        }

    }
    private static int readBit(int number) {

        return (primes[number/64] & (1 << ((number >> 1) & 31)));

    }
    private static void makeCompositeBitWiseSieve(int number) {

        primes[number/64] |= (1 << ((number >> 1) & 31));

    }

    /**
     * Fermat's Little Theorem:
     * If n is a prime number, then for every a, 1 < a < n-1,
     *
     * a^(n-1) ≡ 1 (mod n)
     *  OR
     * a^(n-1) % n = 1
     *
     *
     * Example: Since 5 is prime, 24 ≡ 1 (mod 5) [or 24%5 = 1],
     *          34 ≡ 1 (mod 5) and 44 ≡ 1 (mod 5)
     *
     *          Since 7 is prime, 26 ≡ 1 (mod 7),
     *          36 ≡ 1 (mod 7), 46 ≡ 1 (mod 7)
     *          56 ≡ 1 (mod 7) and 66 ≡ 1 (mod 7)
     *
     * can also make it probabilistic
     */
    public static boolean isPrimeFermat(int number) {

        //Corner cases
        if (number <= 1 || number == 4) return false; //1 and 4
        if (number <= 3) return true; //2 and 3

        for (int i = 2; i < number - 1; i++) {

            if(FLT(i, number - 1, number) != 1) return false;

        }

        return true;
    }

    /**
     * FLT = Fermat's Little Theorem
     * @return a^(b) % c
     */
    private static int FLT(int a, int b, int c) {

        int result = 1;

        //update a if a >= c
        a = a % c;

        while(b > 0) {

            //If b is odd, multiply a with result
            if ((b & 1) == 1) result = (result * a) % c;

            //n must be even now
            b = b >> 1; // b = b/2
            a = (a * a) % c;

        }

        return result;

    }

}
