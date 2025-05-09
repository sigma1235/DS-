import java.util.*;

public class BerkeleyClock {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of slave nodes: ");
        int n = sc.nextInt();
        sc.nextLine();  // consume newline

        String[] times = new String[n + 1];
        int[] minutes = new int[n + 1];

        System.out.println("Enter time for each node in HH:MM (master first):");
        for (int i = 0; i <= n; i++) {
            System.out.print("Node " + i + ": ");
            times[i] = sc.nextLine();
            String[] parts = times[i].split(":");
            minutes[i] = Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
        }

        // Calculate average time
        int sum = 0;
        for (int m : minutes) sum += m;
        int avg = sum / (n + 1);

        System.out.println("\nAdjusted times:");
        for (int i = 0; i <= n; i++) {
            int adjusted = avg;
            int h = adjusted / 60;
            int m = adjusted % 60;
            System.out.printf("Node %d -> %02d:%02d (adjust by %+d mins)\n", i, h, m, avg - minutes[i]);
        }
    }
}
