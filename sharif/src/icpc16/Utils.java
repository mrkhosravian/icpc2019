package icpc16;

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

}
