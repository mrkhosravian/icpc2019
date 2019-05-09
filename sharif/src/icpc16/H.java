package icpc16;

import java.util.*;

public class H {

    public static void main(String[] args) {

        var s = new Scanner(System.in);
        var map = new HashMap<String, Boolean>();

        int[] input = new int[13];

        for (int i = 0; i < 13; i++) {

            input[i] = s.nextInt();

        }

        map.put("1", input[0] == 1);
        map.put("2", input[1] == 1);
        map.put("3", input[2] == 1);
        map.put("up", input[3] == 1);
        map.put("4", input[4] == 1);
        map.put("5", input[5] == 1);
        map.put("6", input[6] == 1);
        map.put("down", input[7] == 1);
        map.put("7", input[8] == 1);
        map.put("8", input[9] == 1);
        map.put("9", input[10] == 1);
        map.put("_", input[11] == 1);
        map.put("0", input[12] == 1);

        int x = s.nextInt();
        int y = s.nextInt();

        System.out.println(solution(map, x, y));

    }

    public static int solution(Map<String, Boolean> control, int x, int y) {

        if(y == x) return 0; //check y = x

        int delta = y - x;
        int abs_delta = Math.abs(delta);
        var ways_length = new ArrayList<Integer>();

        //only with arrows
        if(delta > 0 && control.get("up") || delta < 0 && control.get("down")) ways_length.add(abs_delta);

        //with nearest + arrows
        int nearest = nearestHealthyDirectlyNavigableChannel(control, y);

        //no nearest, only possible way = using arrows
        if(nearest == -1 && ways_length.size() != 0) return Collections.min(ways_length);

        int delta_nearest = y - nearest;

        //continue with arrows
        if(delta_nearest > 0 && control.get("up")
                || delta_nearest < 0 && control.get("down")
                || delta_nearest == 0) {

            ways_length.add(Math.abs(delta_nearest) + (nearest < 10 ? 1 : 3));

        }

        return ways_length.size() == 0 ? -1 : Collections.min(ways_length);

    }

    public static int nearestHealthyDirectlyNavigableChannel(Map<String, Boolean> control, int y) {

        for (int i = 0; i <= 99; i++) {

            int left = y - i, right = y + i;

            if(left >= 0 && isNavigable(control, left)) return left;

            if(right <= 99 && isNavigable(control, right)) return right;

        }

        return -1;

    }

    public static boolean isNavigable(Map<String, Boolean> control, int channel) {

        return channel < 10 && control.get(String.valueOf(channel)) || control.get("_") && control.get(String.valueOf(channel % 10)) && control.get(String.valueOf(channel / 10));

    }

}
