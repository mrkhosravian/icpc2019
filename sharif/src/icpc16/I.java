package icpc16;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.util.*;

public class I {

    /**
     * run this test to check all test cases
     */
    @Test
    public void testCases() {

        File testsDir = new File("src/icpc16/TestCases I");

        File[] inputs = testsDir.listFiles((dir, name) -> name.contains(".in"));
        File[] answers = testsDir.listFiles((dir, name) -> name.contains(".ans"));

        Arrays.sort(inputs, Comparator.comparingInt(file -> Integer.parseInt(file.getName().replaceFirst(".in", ""))));
        Arrays.sort(answers, Comparator.comparingInt(file -> Integer.parseInt(file.getName().replaceFirst(".ans", ""))));

        for (int i = 0; i < inputs.length; i++) {

            try (Scanner in = new Scanner(inputs[i]);
                 Scanner ans = new Scanner(answers[i])) {

                handleNewInput(in);

                assertEquals(ans.nextInt(), leastBitKalamNeeded(), "Test #" + inputs[i].getName().replaceFirst(".in", ""));

            } catch (Exception ex) {

                ex.printStackTrace();

            }


        }

    }

    public static void main(String[] args) {

        new I().run();

    }

    private KahoGramMember[] members;

    public void run() {

        handleNewInput(Utils.keyboard);

        System.out.println(Utils.timeResult(this::leastBitKalamNeeded));

    }

    public int leastBitKalamNeeded() {

        KahoGramMember firstNotMarkedMember = firstNotMarkedMember();

        while (firstNotMarkedMember != null) {

            markEm(firstNotMarkedMember);

            firstNotMarkedMember = firstNotMarkedMember();

        }

        return (int) Arrays.stream(members).filter(m -> m.mark == Mark.Master).count();

    }

    private Stack<KahoGramMember> toCheck = new Stack<>();
    private Set<Integer> calledIds = new HashSet<>();
    private void markEm(KahoGramMember startMember) {

        calledIds.clear();
        startMember.mark = Mark.Master;
        calledIds.add(startMember.id);

        for (int fId : startMember.followers) {

            KahoGramMember follower = getMember(fId);
            follower.mark = Mark.ConnecetedToMaster;
            toCheck.push(follower);

        }

        while(!toCheck.isEmpty()) {

            KahoGramMember checkMember = toCheck.pop();

            for (int fId : checkMember.followers) {

                if(calledIds.contains(fId)) continue;

                KahoGramMember follower = getMember(fId);
                follower.mark = Mark.ConnecetedToMaster;
                calledIds.add(fId);
                toCheck.push(follower);

            }

        }

    }

    private KahoGramMember firstNotMarkedMember() {

        for (KahoGramMember m : members) {

            if(m.mark == Mark.NotMarked) return m;

        }

        return null;

    }

    private KahoGramMember getMember(int id) {

        return members[id - 1];

    }

    public void handleNewInput(Scanner input) {

        members = new KahoGramMember[input.nextInt()];

        for (int i = 0; i < members.length; i++) {

            int[] followers = new int[input.nextInt()];

            for (int j = 0; j < followers.length; j++) {

                followers[j] = input.nextInt();

            }

            members[i] = new KahoGramMember(i + 1, followers);

        }

    }

}

class KahoGramMember {

    public final int id;
    public final int[] followers;
    public Mark mark = Mark.NotMarked;

    public KahoGramMember(int id, int[] followers) {

        this.id = id;
        this.followers = followers;

    }

}

enum Mark {

    ConnecetedToMaster, Master, NotMarked

}