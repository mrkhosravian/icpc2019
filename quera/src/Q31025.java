
import java.util.Scanner;

/*
    TITLE : صبا و سوال ساده
    URL : https://quera.ir/problemset/contest/31025/%D8%B3%D8%A4%D8%A7%D9%84-%D8%B5%D8%A8%D8%A7-%D9%88-%D8%B3%D9%88%D8%A7%D9%84-%D8%B3%D8%A7%D8%AF%D9%87
 */

public class Q31025 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), k = s.nextInt();
        System.out.println(powDiv(n,k));
    }

    static int powDiv(int n,int k){
        return (int) Math.floor(n / Math.pow(2, k));
    }
}