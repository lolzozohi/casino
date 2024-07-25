import java.util.Random;
import java.util.Scanner;

public class SlotMachine {

    private static final String[] REELS = {"Cherry", "Lemon", "Orange", "Plum", "Bell", "Bar"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to the Slot Machine Game!");

        while (true) {
            System.out.print("Press Enter to pull the lever or type 'exit' to quit: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Thanks for playing!");
                break;
            }

            String reel1 = spinReel(random);
            String reel2 = spinReel(random);
            String reel3 = spinReel(random);

            System.out.println("Spinning...");
            System.out.println("Reel 1: " + reel1);
            System.out.println("Reel 2: " + reel2);
            System.out.println("Reel 3: " + reel3);

            if (reel1.equals(reel2) && reel2.equals(reel3)) {
                System.out.println("Congratulations! You won!");
            } else {
                System.out.println("Sorry, you lost. Try again!");
            }
        }

        scanner.close();
    }

    private static String spinReel(Random random) {
        int index = random.nextInt(REELS.length);
        return REELS[index];
    }
}
