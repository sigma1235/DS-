import java.util.*;

public class RingElection {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes in the ring: ");
        int n = sc.nextInt();
        boolean[] active = new boolean[n];
        int[] processes = new int[n];

        // Initialize processes
        for (int i = 0; i < n; i++) {
            processes[i] = i;
            active[i] = true;
        }

        System.out.print("Enter process ID (0 to " + (n - 1) + ") that failed (will be excluded): ");
        int failed = sc.nextInt();
        active[failed] = false;

        System.out.print("Enter process ID (0 to " + (n - 1) + ") to initiate election: ");
        int initiator = sc.nextInt();

        List<Integer> electionList = new ArrayList<>();
        int current = (initiator + 1) % n;

        // Send election message around the ring
        electionList.add(initiator);
        while (current != initiator) {
            if (active[current]) {
                electionList.add(current);
                System.out.println("Process " + current + " added to election.");
            }
            current = (current + 1) % n;
        }

        int coordinator = Collections.max(electionList);
        System.out.println("\nElection complete. New coordinator is Process " + coordinator);
    }
}
