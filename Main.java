import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Welcome to the Game Menu!");
            System.out.println("1. Play Blackjack");
            System.out.println("2. Play Slot Machine");
            System.out.println("3. Exit");

            System.out.print("Please choose an option (1, 2, 3): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Start Blackjack
                    Blackjack.main(args);
                    break;
                case 2:
                    // Start Slot Machine
                    SlotMachine.main(args);
                    break;
                case 3:
                    System.out.println("Exiting the game. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        }

        scanner.close();
    }
}
