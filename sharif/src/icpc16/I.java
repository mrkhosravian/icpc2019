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

        System.out.println(solution(members));

    }

    public static int solution(Member[] members) {

        int[][] matrix = boliMatrixGraphOfMembers(members);

        int[][] check_matrix = matrix;
        int[][] current_matrix = powNBoliMatrix(matrix, 2);


        for (int k = 3; !areEqualMatrixes(check_matrix, current_matrix) ; k++) { //until longest way length between 2 non-equal points = until current_matrix is same as its past

            for (int i = 0; i < current_matrix.length; i++) {

                for (int j = 0; j < current_matrix.length; j++) {

                    if(current_matrix[i][j] == 1) members[i].followers.add(j+1);

                }

            }

            check_matrix = current_matrix;
            current_matrix = powNBoliMatrix(matrix, k);

        }

        // TODO: 5/9/2019 figure out a better algorithm for this part
        //give ads to least members that have all members as absolute followers

        //algorithm 1 : check all possible ways dirtily
        for (int i = 1; i <= members.length; i++) { //iterate over size of choices

            int[] choices = new int[i];

            while(!Arrays.stream(choices).allMatch(choice -> choice == members.length - 1)) { //until all choices are choose

                //check all choices; sum of their followers should contain all members

                for (int j = 0; j < choices.length; j++) {

                    var followers_sum = new HashSet<Integer>();

                    for (int choice : choices) {

                        followers_sum.addAll(members[choice].followers);

                    }

                    if (followers_sum.size() == members.length) return i;

                }

                //update choices
                updateChoices(choices, members.length - 1);

            }

        }

        return -1;

    }

    public static boolean areEqualMatrixes(int[][] first, int[][] second) {

        if(first.length != second.length || first[0].length != second[0].length) return false;

        for (int i = 0; i < first.length; i++) {

            if(!Arrays.equals(first[i], second[i])) return false;

        }

        return true;

    }

    public static void updateChoices(int[] choices, int max) {

        _updateChoices(choices, max, choices.length - 1);

    }

    public static void _updateChoices(int[] choices, int max, int end_index) {

        if(choices[end_index]++ == max) {

            //reset this then update root
            choices[end_index] = 0;
            _updateChoices(choices, max, end_index - 1);

        }

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

                System.out.print(matrix[i][j] + " ");

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