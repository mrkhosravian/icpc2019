package icpc16;

public class I {

    public static void main(String[] args) {



    }

    public static int solution(int[][] matrix) {



    }

    public static int[][] multiplyMatricesBooli(int[][] first_matrix, int[][] second_matrix) {

        int[][] product = new int[first_matrix.length][second_matrix[0].length];

        for(int i = 0; i < first_matrix.length; i++) {

            for (int j = 0; j < second_matrix[0].length; j++) {

                for (int k = 0; k < second_matrix.length; k++) {

                    product[i][j] += first_matrix[i][k] * second_matrix[k][j];

                }

                if(product[i][j] != 0) product[i][j] = 1;

            }

        }

        return product;
    }

}