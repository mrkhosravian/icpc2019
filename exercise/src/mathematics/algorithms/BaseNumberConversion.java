package mathematics.algorithms;

import annotations.Persian;

@Persian("تبدیل مبنا")
public class BaseNumberConversion {

    public static void main(String[] args) {



    }

    public static String convert(String value, int sourceBase, int targetBase) {

        int nSourceBase = Integer.parseInt(value, sourceBase); //to base 10

        String strnTargetBase = Integer.toString(nSourceBase, targetBase); //to target base

        return strnTargetBase;

    }

}
