import java.util.*;

public class ClockSync {

    // Function to convert hh:mm to total minutes
    public static int timeToMinutes(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    // Function to convert minutes to hh:mm format
    public static String minutesToTime(int minutes) {
        minutes = (minutes + 1440) % 1440; // handle negatives and wrap around 24 hours
        int hh = minutes / 60;
        int mm = minutes % 60;
        return String.format("%02d:%02d", hh, mm);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Input server time
        System.out.print("Enter Server Time (HH:MM): ");
        String serverTimeStr = sc.nextLine();
        int serverTimeMin = timeToMinutes(serverTimeStr);

        // Step 2: Input client times
        System.out.print("Enter number of clients: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        int[] clientTimesMin = new int[n];
        for (int i = 0; i < n; i++) {
           
           System.out.print("Enter Client " + (i + 1) + " Time (HH:MM): ");
            String clientTimeStr = sc.nextLine();
            clientTimesMin[i] = timeToMinutes(clientTimeStr);
        }

        // Step 3: Calculate differences and average
        int totalDifference = 0;
        for (int clientTime : clientTimesMin) {
            totalDifference += (clientTime - serverTimeMin);
        }

        // Divide by (number of clients + 1)
        int avgDifference = totalDifference / (n + 1);

        // Step 4: Calculate synchronized time
        int synchronizedTime = serverTimeMin + avgDifference;
        String synchronizedTimeStr = minutesToTime(synchronizedTime);

        // Output
        System.out.println("Synchronized Time: " + synchronizedTimeStr);
    }
}

