import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class YesNoFileWriter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = "output.txt";

        System.out.print("Enter number of iterations: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // consume newline

        try (FileWriter writer = new FileWriter(fileName, true)) { // append mode
            for (int i = 0; i < n; i++) {
                System.out.print("Do you want to write to the process?"+i+"(yes/no): ");
                String choice = scanner.nextLine().trim().toLowerCase();

                if (choice.equals("yes")) {
                    System.out.print("Enter text to write: ");
                    String text = scanner.nextLine();
                    writer.write(text + "\n");
                    System.out.println("Text written to file.");
                } else {
                    System.out.println("Skipped writing.");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            
        }

        scanner.close();
    }
}

