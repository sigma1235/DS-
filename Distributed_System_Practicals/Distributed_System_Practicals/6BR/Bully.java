import java.util.Scanner;

public class Bully {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        int[] processes = new int[n];
        boolean[] alive = new boolean[n];

        // Initialize process IDs and alive status
        for (int i = 0; i < n; i++) {
            processes[i] = i + 1;
            alive[i] = true;
        }

        System.out.print("Enter crashed coordinator process ID: ");
        int crashed = sc.nextInt();
        alive[crashed - 1] = false;

        System.out.print("Enter process ID to initiate election: ");
        int initiator = sc.nextInt();

        System.out.println("\nElection Started by Process " + initiator);
        int coordinator = -1;
        for (int i = initiator; i < n; i++) {
            if (alive[i]) {
                System.out.println("Process " + (i + 1) + " is alive");
                coordinator = i + 1;
            }
        }

        if (coordinator != -1) {
            System.out.println("Process " + coordinator + " becomes the new coordinator.");
        } else {
            System.out.println("No alive process found to become coordinator.");
        }
    }
}
