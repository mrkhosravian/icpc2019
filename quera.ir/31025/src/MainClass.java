import java.util.Scanner;
import java.util.Timer;

public class MainClass {
    static int n,k;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] ss = s.nextLine().split(" ");
        long f = System.nanoTime();
        n = Integer.valueOf(ss[0]);
        k = Integer.valueOf(ss[1]);

        System.out.println(divide(n,k));
        System.err.println((System.nanoTime() - f)/Math.pow(10,6) + " ms");


    }

    public static int divide(int n,int k){
        double m = (n / Math.pow(2,k));
        return (int)Math.floor(m);
    }

}
