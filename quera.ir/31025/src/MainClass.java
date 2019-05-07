import java.util.Scanner;
import java.util.Timer;

public class MainClass {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt(), k = s.nextInt();

        System.out.println((int) Math.floor(n / Math.pow(2, k)));


    }

}
