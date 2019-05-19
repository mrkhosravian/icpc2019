package mathematics.algorithms;

import annotations.Formula;
import annotations.Persian;

import java.math.BigInteger;

@Persian("باقی مانده اعداد بزرگ")
@Formula("(A*B*C) mod N == ((A mod N) * (B mod N) * (C mod N)) mod N")
public class BigMod {

    public static void main(String[] args) {



    }

    public static BigInteger bigMod(int b, int p, int m) {

        if(p == 0) return BigInteger.ONE;

        if(p % 2 == 0) {

            return bigMod(b, p/2, m).pow(2).mod(BigInteger.valueOf(m));

        } else {

            return bigMod(b, p-1, m).multiply(BigInteger.valueOf(b % m)).mod(BigInteger.valueOf(m));

        }

    }

}
