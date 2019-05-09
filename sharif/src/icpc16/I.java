package icpc16;

import java.util.*;

public class I {

    public static void main(String[] args) {

        var s = new Scanner(System.in);

        int n = s.nextInt();
        s.nextLine();

        Member[] members = new Member[n];

        for (int i = 0; i < n; i++) {

            int followers_count = s.nextInt();

            members[i] = new Member(i+1);

            for (int j = 0; j < followers_count; j++) { //get id of followers

                members[i].followers.add(s.nextInt());

            }

        }

        solution(members);

    }

    public static int solution(Member[] members) {

        int[][] matrix = boliMatrixGraphOfMembers(members);

        int[][] current_matrix = powNBoliMatrix(matrix, 2);

        for (int k = 3; k == 3; k++) { //in sharte ro nemidonam chi bashe :)

            for (int i = 0; i < current_matrix.length; i++) {

                for (int j = 0; j < current_matrix.length; j++) {

                    if(current_matrix[i][j] == 1) members[i].followers.add(j+1);

                }

            }

            current_matrix = powNBoliMatrix(matrix, k);

        }

        //give ads to least members that have all members as absolute followers

        //find shakha

        var shaka = new ArrayList<Member>(1);



    }

    public static int[][] boliMatrixGraphOfMembers(Member[] members) {

        int[][] matrix = new int[members.length][members.length];

        for (Member member : members) {

            member.followers.forEach(id -> matrix[member.id-1][id-1] = 1);

        }

        return matrix;

    }

    public static int[][] powNBoliMatrix(int[][] matrix, int n) {

        int[][] result = powNMatrix(matrix, n);

        makeMatrixBoli(result);

        return result;

    }

    public static boolean contains1ExceptMainDiameter(int[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix.length; j++) {

                if(i == j) continue;

                if(matrix[i][j] == 1) return true;

            }

        }

        return false;

    }

    public static int[][] copy2DSquareMatrix(int[][] original) {

        int[][] new_matrix = new int[original.length][original.length];

        for (int i = 0; i < original.length; i++) {

            System.arraycopy(original[i], 0, new_matrix[i], 0, original.length);

        }

        return new_matrix;

    }

    public static int[][] powNMatrix(int[][] matrix, int n) {

        int[][] result = multiplyMatrixes(matrix, matrix);

        for (int i = 1; i < n - 1; i++) {

            result = multiplyMatrixes(result, matrix);

        }

        return result;
    }

    public static void makeMatrixBoli(int[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[0].length; j++) {

                if(matrix[i][j] != 0) matrix[i][j] = 1;
                else matrix[i][j] = 0;

            }

        }

    }

    public static int[][] multiplyMatrixes(int[][] first, int[][] second) {

        int[][] product = new int[first.length][second[0].length];

        for(int i = 0; i < first.length; i++) {

            for (int j = 0; j < second[0].length; j++) {

                for (int k = 0; k < second.length; k++) {

                    product[i][j] += first[i][k] * second[k][j];

                }

            }

        }

        return product;
    }

    public static void printMatrix(int[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[0].length; j++) {

                System.out.print(matrix[i][j]);

            }

            System.out.println();

        }

    }

}

class Member {

    Set<Integer> followers;
    int id;

    public Member(int id, Integer... followers) {

        this.followers = new HashSet<>(Arrays.asList(followers));
        this.followers.add(id);
        this.id = id;

    }

    @Override
    public String toString() {

        return id + " : " + Arrays.toString(followers.toArray());

    }
}