package icpc16;

import jdk.jshell.execution.Util;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

public class I_reworked {

    @Test
    public void testMatrixes() {

        Matrix m1 = new Matrix(new int[][]{{0, 1, 0}, {1, 3, 1}, {1, 2, 0}});
        Matrix m2 = new Matrix(new int[][]{{1, 0, 0}, {1, 1, 1}, {1, 0, 1}});
        Matrix m3 = new Matrix(new int[][]{{0, 1, 0}, {0, 1, 1}, {1, 1, 0}});

        assertEquals(m1, Matrix.multiplyMatrixes(m2, m3));

        assertEquals(m1, m1.clone());
        assertEquals(m2, m2.clone());
        assertEquals(m3, m3.clone());

        assertEquals(new Matrix(new int[][]{{1, 0, 0}, {3, 1, 2}, {2, 0, 1}}), m2.toPowerN(2));

    }

    public static void main(String[] args) {

        //inputs
        var s = new Scanner(System.in);

        int n = s.nextInt();
        s.nextLine();

        KahoGramMember[] members = new KahoGramMember[n];

        for (int i = 0; i < n; i++) {

            int followers_count = s.nextInt();

            members[i] = new KahoGramMember(i+1);

            for (int j = 0; j < followers_count; j++) { //get id of followers

                members[i].addFollower(s.nextInt());

            }

        }

        System.out.println(Utils.timeResult(() -> leastNeededBitKalam(members)));

    }

    public static int leastNeededBitKalam(KahoGramMember[] members) {

        final Matrix m = KahoGramMember.boolGraphMatrixOfMembers(members);

        Matrix pre_current, current = m;

        int power = 2;
        do {

            pre_current = current;
            current = m.clone().toPowerN(power++).translateIntoBoolMatrix();

            for (int i = 0; i < current.rows(); i++) {

                for (int j = 0; j < current.rows(); j++) {

                    if(current.matrix[i][j] == 1) members[i].addFollower(j + 1);

                }

            }

        } while(!current.equals(pre_current));

        //give ads to least members that have all members as absolute followers
        //find least chosen sets that contains all members

        Arrays.sort(members, Comparator.comparingInt(o -> ((KahoGramMember) o).getFollowers().size()).reversed());

        var is_in = new Boolean[members.length];
        for (int i = 0; i < is_in.length; i++) is_in[i] = false;

        for (int p = 0; p < Math.pow(2, members.length); p++) {

            //update is in
            new Consumer<Integer>() {

                @Override
                public void accept(Integer end_index) {

                    if(is_in[end_index]) {

                        //reset then update the root
                        is_in[end_index] = false;
                        accept(end_index-1);

                    } else is_in[end_index] = true;

                }

            }.accept(is_in.length - 1);

            var sum_of_followers = new HashSet<Integer>();

            for (int i = 0; i < is_in.length; i++) {

                if (is_in[i]) sum_of_followers.addAll(members[i].getFollowers());

            }

            if (sum_of_followers.size() == members.length)
                return Math.toIntExact(Arrays.stream(is_in).filter(b -> b).count());

        }

        return -1;

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


}

class Matrix {

    public int[][] matrix;

    public Matrix(int[][] matrix) {

        this.matrix = matrix;

        //checkLengths();

    }

    public Matrix(int rows, int columns) {

        matrix = new int[rows][columns];

    }

    public Matrix(Collection<int[]> matrix) {

        this.matrix = new int[matrix.size()][((int[]) matrix.toArray()[0]).length];

        AtomicInteger i = new AtomicInteger(0);
        matrix.forEach(ints -> this.matrix[i.getAndIncrement()] = ints);

        //checkLengths();

    }

    protected void checkLengths() throws Exception {

        if(!Arrays.stream(matrix).skip(1).allMatch(ints -> ints.length == matrix[0].length)) throw new Exception("Not a matrix");

    }

    public Matrix print(String separator) {

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[0].length; j++) {

                System.out.print(matrix[i][j] + separator);

            }

            System.out.println();

        }
        System.out.println();

        return this;

    }

    public Matrix print() {

        print("\t");

        return this;

    }

    public int rows() {

        return matrix.length;

    }

    public int columns() {

        return matrix[0].length;

    }

    public Matrix multiplyTo(Matrix m) {

        this.matrix = multiplyMatrixes(matrix, m.matrix);

        return this;

    }

    public static Matrix multiplyMatrixes(Matrix first, Matrix second) {

        return new Matrix(multiplyMatrixes(first.matrix, second.matrix));

    }

    private static int[][] multiplyMatrixes(int[][] first, int[][] second) {

        if(first[0].length != second.length) return null; //throw new Exception("Can't perform multiplying on this matrixes");

        int[][] product = new int[first.length][second[0].length];

        for (int i = 0; i < first.length; i++) { //iterates over rows of first matrix

            for (int j = 0; j < second[0].length; j++) { //iterates over columns of second matrix

                for (int k = 0; k < first.length; k++) { //iterates for multiplying matching indexes

                    product[i][j] += first[i][k] * second[k][j];

                }

            }

        }

        return product;

    }

    public Matrix translateIntoBoolMatrix() {

        for (int i = 0; i < rows(); i++) {

            for (int j = 0; j < columns(); j++) {

                matrix[i][j] = matrix[i][j] != 0 ? 1 : 0;

            }

        }

        return this;

    }

    public Matrix toPowerN(int n) {

        //if(n <= 0) throw new Exception("Can't power the matrix to n <= 0");

        if(n == 1) return this;

        final int[][] copy = copyMatrix(matrix);

        for (int i = 1; i < n; i++) {

            matrix = multiplyMatrixes(matrix, copy);

        }

        return this;

    }

    public Matrix clone() {

        return new Matrix(copyMatrix(matrix));

    }

    private static int[][] copyMatrix(int[][] original) {

        int[][] copy = new int[original.length][original[0].length];

        for (int i = 0; i < copy.length; i++) {

            copy[i] = Arrays.copyOf(original[i], original[i].length);

        }

        return copy;

    }

    private static boolean areEqualMatrixes(int[][] first, int[][] second) {

        if(first.length != second.length || first[0].length != second[0].length) return false;

        for (int i = 0; i < first.length; i++) {

            if(!Arrays.equals(first[i], second[i])) return false;

        }

        return true;

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof Matrix)) return false;

        return areEqualMatrixes(matrix, ((Matrix) o).matrix);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(matrix);
    }
}

class KahoGramMember {

    private final Set<Integer> followers;
    private final int id;

    public KahoGramMember(int id) {

        this.id = id;

        followers = new HashSet<>();
        followers.add(id);

    }

    public KahoGramMember(int id, int... followers) {

        this.id = id;

        this.followers = new HashSet<>();

        addFollowers(followers);
        this.followers.add(id);

    }

    public int getId() {

        return id;

    }

    public void addFollower(int id) {

        followers.add(id);

    }

    public void addFollowers(int... ids) {

        var ids_wrapper = new Integer[ids.length];

        for (int i = 0; i < ids.length; i++) {

            ids_wrapper[i] = ids[i];

        }

        followers.addAll(Arrays.asList(ids_wrapper));

    }

    public Set<Integer> getFollowers() {

        return followers;

    }

    public static Matrix boolGraphMatrixOfMembers(KahoGramMember[] members) {

        Matrix product = new Matrix(members.length, members.length);

        Arrays.stream(members).forEach(member -> member.getFollowers().forEach(follower -> product.matrix[member.id - 1][follower - 1] = 1));

        return product;

    }

    @Override
    public String toString() {

        return "#"+ id + " -> " + Arrays.toString(followers.toArray());

    }
}
