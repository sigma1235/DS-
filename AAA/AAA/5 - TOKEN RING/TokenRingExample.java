import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

class TokenRing {
    private boolean hasToken = false;

    // Request the token and wait if it's not available
    public synchronized void requestToken() {
        while (!hasToken) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Give the token to the first process
    public synchronized void giveToken() {
        hasToken = true;
        notify();
    }

    // Pass the token to the next process
    public synchronized void passToken() {
        hasToken = false;
        notify();
    }
}

public class TokenRingExample {
    private static int totalProcesses;
    private static int[] processIDs;
    private static TokenRing tokenRing = new TokenRing();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the total number of processes: ");
        totalProcesses = scanner.nextInt();

        processIDs = new int[totalProcesses];

        System.out.println("Enter process IDs separated by space. '$' means the token is with that number:");
        for (int i = 0; i < totalProcesses; i++) {
            processIDs[i] = scanner.nextInt();
        }

        Arrays.sort(processIDs);  // Sorting process IDs for simplicity

        TokenPassing(scanner);
    }

    private static void TokenPassing(Scanner scanner) {
        // Initially give the token to the first process
        tokenRing.giveToken();

        int i = 0;
        StringBuilder logContent = new StringBuilder();  // Use StringBuilder to accumulate log content
        int tokenIndex = 0;  // Start with the first process holding the token

        while (i < totalProcesses) {
            int currentProcessID = processIDs[i];
            String theRing = "";

            // Ask the user whether to pass the token or not
            System.out.print("Do you want to pass the token to the next process? (y/n): ");
            String passOrComplete = scanner.next();

            // Consume any leftover newline characters
            scanner.nextLine();  // This line consumes the newline character that `next()` leaves behind

            if (passOrComplete.equalsIgnoreCase("n")) {
                // Keep the token with the current process
                tokenIndex = i;  // Token stays with the current process
            } else if (passOrComplete.equalsIgnoreCase("y")) {
                // Move the token to the next process
                tokenIndex = (i + 1) % totalProcesses;  // Pass to the next process
            } else {
                System.out.println("Invalid input, please enter 'y' or 'n'.");
                continue;
            }

          
            tokenRing.requestToken();  // Request token before entering critical section
            System.out.println("Process " + currentProcessID + " is in the critical section.");

            try {
                Thread.sleep(1000);  // Simulate critical section
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            if (passOrComplete.equalsIgnoreCase("n")) {
                System.out.println("Process " + currentProcessID + " completed and did not pass the token.");
            } else if (passOrComplete.equalsIgnoreCase("y")) {
                // Ask the user to enter a string to write to the shared log file
                System.out.print("Enter a string to append to the log file for Process " + currentProcessID + ": ");
                
                // Use nextLine() to capture the entire line of input, including spaces
                String userInput = scanner.nextLine();

                // Append the string to the log content that will be written to the file
                logContent.append("Process " + currentProcessID + ": " + userInput + "\n");

                // Pass the token to the next process (only if passed)
                int nextIndex = (i + 1) % totalProcesses;
                int nextProcessID = processIDs[nextIndex];
                System.out.println("Process " + currentProcessID + " passed the token to Process " + nextProcessID);
            }

            System.out.println(theRing);

            tokenRing.passToken();  // Pass the token
            tokenRing.giveToken();  // Give the token to the next process

            try {
                Thread.sleep(1000);  // Simulate token passing delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            i++;
        }

        // Once all processes are completed, write the accumulated log content to the file
        try (FileWriter writer = new FileWriter("process_log.txt", true)) {
            writer.write(logContent.toString());
            System.out.println("All process logs have been written to process_log.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
    }
}

