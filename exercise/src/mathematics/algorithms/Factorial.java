package mathematics.algorithms;

import annotations.Persian;

import java.util.ArrayList;
import java.util.List;

@Persian("فاکتوریل")
public class Factorial {

    public static void main(String[] args) {

        assert fact(5) == 120;
        assert fact(0) == 1;
        assert fact(4) == 24;

        assert factRecursive(5) == 120;
        assert factRecursive(0) == 1;
        assert factRecursive(4) == 24;

        assert factDynamic(5) == 120;
        assert factDynamic(0) == 1;
        assert factDynamic(4) == 24;

    }

    private static List<Integer> facts = new ArrayList<>();
    static {

        facts.add(1);

    }

    public static int factDynamic(int n) {

        if(facts.size() - 1 >= n) return facts.get(n);

        facts.add(n, factDynamic(n-1) * n);

        return factDynamic(n);

    }

    public static int factRecursive(int n) {

        if(n == 0) return 1;

        return n * factRecursive(n-1);

    }

    public static int fact(int n) {

        int fact = 1;

        for (int i = 2; i <= n; i++) {

            fact *= i;

        }

        return fact;

    }

}
