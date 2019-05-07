import java.util.Scanner;

public class B {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int fingers = s.nextInt();
        int hands = s.nextInt();
        int n1 = s.nextInt();
        int n2 = s.nextInt();

        solution(fingers, hands, n1, n2);

   }

   public static void solution(int fingers_per_hand, int hands, int n1, int n2) {

        int n = n1 + n2;
        int fingers = fingers_per_hand * hands;

        while(n > fingers) {

            n -= fingers;

        }

       System.out.println(n);

   }

   public static int recursiveSolution(int all_fingers, int sum) {

        if(sum <= all_fingers) return sum;

        return recursiveSolution(all_fingers, sum - all_fingers);

   }

}
