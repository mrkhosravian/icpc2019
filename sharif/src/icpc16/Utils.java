package icpc16;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.Callable;

public class Utils {

    public static <T> TimeResult<T> timeResult(Callable<T> c) {

        long start = System.nanoTime();

        T result = null;

        try {

            result = c.call();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return new TimeResult<T>(result, System.nanoTime() - start);

    }

    /**
     * Make sure c data values won't change during the function call
     * Avoid increasing calls parameter too much! causes unreal time result due to cache to CPU
     */
    public static <T> TimeResult<T> avgTimeResult(Callable<T> c, final int calls) {

        long sum = 0, start;
        T result = null;

        for (int i = 0; i < calls; i++) {

            start = System.nanoTime();

            try {

                result = c.call();

            } catch (Exception e) {

                e.printStackTrace();

            }

            sum += System.nanoTime() - start;

        }

        return new TimeResult<>(result, sum/calls);

    }

    public static class TimeResult<T> {

        public T result;
        public long time;

        public TimeResult(T result, long time) {

            this.result = result;
            this.time = time;

        }

        @Override
        public String toString() {

            return result + "\n<< in " + time + " nano seconds >>";

        }

    }

    public static class Math {

        @Test
        public void testMath() {

            assertEquals(1, fact(1));
            assertEquals(120, fact(5));
            assertEquals(6, nCr(6, 1));

        }

        public static int nCr(int n, int r) {

            int a = 1;

            for (int i = n; i > n - a; i--) {

                a *= i;

            }

            return a / fact(a);

        }

        public static int fact(int n) {

            if(n < 0) return -1;

            int result = 1;

            for (int i = 2; i <= n; i++) {

                result *= i;

            }

            return result;

        }

    }

}
