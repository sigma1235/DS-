import java.util.Scanner;

public class ClockSynchronization {

    // Client class to simulate client behavior
    static class Client {
        private int hours;
        private int minutes;
        private int adjustment; // To store the adjustment applied

        public Client(int hours, int minutes) {
            this.hours = hours;
            this.minutes = minutes;
            this.adjustment = 0; // Initially no adjustment
        }

        // Method to calculate time difference between server and client in minutes
        public int getTimeDifference(int serverHours, int serverMinutes) {
            int clientTotalMinutes = hours * 60 + minutes;
            int serverTotalMinutes = serverHours * 60 + serverMinutes;
            return clientTotalMinutes - serverTotalMinutes;
        }

        // Method to adjust client time based on the server's adjustment
        public void adjustTime(int adjustment) {
            int totalMinutes = hours * 60 + minutes + adjustment;
            this.adjustment = adjustment; // Store the adjustment
            hours = (totalMinutes / 60) % 24;  // Keep within a 24-hour format
            minutes = totalMinutes % 60;
        }

        // Method to print time in HH:mm format and show adjustment
        public void printTime() {
            System.out.print("Client time: " + String.format("%02d:%02d", hours, minutes));
            if (adjustment != 0) {
                System.out.print(" (+" + adjustment + ")");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Server accepts the server time from the user (in HH:mm format)
        System.out.print("Enter server time (HH:mm): ");
        String serverTimeInput = scanner.nextLine();
        String[] serverTimeParts = serverTimeInput.split(":");
        int serverHours = Integer.parseInt(serverTimeParts[0]);
        int serverMinutes = Integer.parseInt(serverTimeParts[1]);

        // Step 2: Create two clients and accept their times from user (in HH:mm format)
        System.out.print("Enter Client 1 time (HH:mm): ");
        String client1TimeInput = scanner.nextLine();
        String[] client1TimeParts = client1TimeInput.split(":");
        int client1Hours = Integer.parseInt(client1TimeParts[0]);
        int client1Minutes = Integer.parseInt(client1TimeParts[1]);

        System.out.print("Enter Client 2 time (HH:mm): ");
        String client2TimeInput = scanner.nextLine();
        String[] client2TimeParts = client2TimeInput.split(":");
        int client2Hours = Integer.parseInt(client2TimeParts[0]);
        int client2Minutes = Integer.parseInt(client2TimeParts[1]);

        // Create clients
        Client client1 = new Client(client1Hours, client1Minutes);
        Client client2 = new Client(client2Hours, client2Minutes);

        // Print initial client times
        System.out.println("\nInitial Client Times:");
        client1.printTime();
        client2.printTime();

        // Step 3: Clients send their time differences to the server
        int diffClient1 = client1.getTimeDifference(serverHours, serverMinutes);
        int diffClient2 = client2.getTimeDifference(serverHours, serverMinutes);
        
        System.out.println("\nClient 1 time difference with server: " + diffClient1 + " minutes");
        System.out.println("Client 2 time difference with server: " + diffClient2 + " minutes");

        // Step 4: Server calculates the average time difference
        int averageDifference = (diffClient1 + diffClient2) / 3;
        System.out.println("\nServer calculated the average time difference: " + averageDifference + " minutes");

        // Step 5: Server sends the adjustment to clients
        System.out.println("\nServer sends adjustment to clients:");
        
        // Adjust client times based on the average difference
        client1.adjustTime(averageDifference - diffClient1);
        client2.adjustTime(averageDifference - diffClient2);

        // Step 6: Clients update their time and print the result
        System.out.println("\nUpdated Client Times:");
        client1.printTime();
        client2.printTime();

        scanner.close();
    }
}

