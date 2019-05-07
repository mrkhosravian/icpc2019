package icpc16;

public class D {

    public static void main(String[] args) {

        solution("1000100100001");
        solution("0001111001");
        solution("010011100000");
        solution("10101010101");

    }

    public static void solution(String input) {

        int max = 0;
        int current_max = 0;

        for (char c : input.toCharArray()) {

            if(c == '0') current_max++;
            else {

                if(current_max > max) max = current_max;

                current_max = 0;

            }

        }

        if(current_max > max) max = current_max;

        System.out.println(max);

    }

}
