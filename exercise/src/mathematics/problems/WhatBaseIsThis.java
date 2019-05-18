package mathematics.problems;

import annotations.Algorithm;
import annotations.TimeLimit;
import annotations.Title;
import annotations.URL;
import mathematics.algorithms.BaseNumberConversion;
import java.util.Scanner;

@Algorithm(BaseNumberConversion.class)
@Title("343 - What Base Is This?")
@URL("https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=5&page=show_problem&problem=279")
@TimeLimit(3.000)
public class WhatBaseIsThis {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String n1 = scanner.next();
        String n2 = scanner.next();

        System.out.println(solution(n1, n2));

    }

    public static int minBase(String n) {

        int max = -1;
        for (char c : n.toCharArray()) {

            int charBase = Integer.parseInt(String.valueOf(c), 36);

            if(charBase > max) max = charBase;

        }

        return max + 1;

    }

    public static String solution(String n1, String n2) {

        int n1MinBase = minBase(n1);
        int n2MinBase = minBase(n2);

        boolean isFound = false;

        while(n1MinBase < 36 || n2MinBase < 36) {

            int n1InMinBase = Integer.parseInt(n1, n1MinBase);
            int n2InMinBase = Integer.parseInt(n2, n2MinBase);

            if(n1InMinBase == n2InMinBase) {

                isFound = true;

                break;
            }

            if(n1InMinBase < n2InMinBase) {

                if(n1MinBase == 36) break;

                n1MinBase++;

            } else {

                if(n2MinBase == 36) break;

                n2MinBase++;

            }

        }

        if(!isFound) return n1 + " is not equal to " + n2 + " in any base 2..36";
        else return n1 +" (base " + n1MinBase + ") = " + n2 + " (base " + n2MinBase + ")";
    }

}
