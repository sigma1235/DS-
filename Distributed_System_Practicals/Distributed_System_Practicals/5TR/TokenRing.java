import java.util.Scanner;

public class TokenRing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes in the ring: ");
        int n = sc.nextInt();
        System.out.print("Enter the process number that will start with the token (0 to " + (n - 1) + "): ");
        int tokenHolder = sc.nextInt();

        System.out.print("Enter number of rounds to simulate: ");
        int rounds = sc.nextInt();

        for (int i = 0; i < rounds; i++) {
            System.out.println("\n--- Round " + (i + 1) + " ---");
            for (int j = 0; j < n; j++) {
                int current = (tokenHolder + j) % n;
                System.out.println("Process " + current + " has the token.");
                System.out.print("Does Process " + current + " want to enter critical section? (yes/no): ");
                String input = sc.next();
                if (input.equalsIgnoreCase("yes")) {
                    System.out.println("Process " + current + " enters critical section...");
                    System.out.println("Process " + current + " exits critical section.");
                }
            }
        }

        System.out.println("\nToken Ring simulation ended.");
    }
}
