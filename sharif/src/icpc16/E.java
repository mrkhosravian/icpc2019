package icpc16;

import java.util.ArrayList;
import java.util.Scanner;

public class E {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        final int n = scanner.nextInt();

        int[][] matrix = new int[n][n];

        scanner.nextLine(); //prevent empty string for line variable

        for (int i = 0; i < n; i++) {

            String line = scanner.nextLine();

            String[] elements = line.split(" ");

            for (int j = 0; j < n; j++) {

                matrix[i][j] = Integer.parseInt(elements[j]);

            }

        }

        System.out.println(losersCount(matrix));

    }

    public static int losersCount(int[][] matrix) {

        int counter = 0;

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix.length; j++) {

                if(isLoser(matrix, i, j)) counter++;

            }

        }

        return counter;

    }

    public static boolean isLoser(int[][] matrix, int i, int j) {

        ArrayList<Integer> neighbors = new ArrayList<>(4);

        if(i != 0) neighbors.add(matrix[i-1][j]);
        if(j != 0) neighbors.add(matrix[i][j-1]);
        if(i != matrix.length-1) neighbors.add(matrix[i+1][j]);
        if(j != matrix.length-1) neighbors.add(matrix[i][j+1]);

        return neighbors.stream().allMatch(a -> a <= matrix[i][j]);

    }

}
