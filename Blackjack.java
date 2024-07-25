import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deck deck = new Deck();
        deck.shuffle();

        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();

        // Initial deal
        playerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());
        playerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());

        System.out.println("Welcome to Blackjack!");

        // Player's turn
        boolean playerDone = false;
        while (!playerDone) {
            System.out.println("Your hand: " + playerHand);
            System.out.println("Dealer's hand: " + dealerHand.getCards().get(0) + " and [hidden]");

            if (playerHand.getValue() > 21) {
                System.out.println("You busted! Dealer wins.");
                return;
            }

            System.out.print("Do you want to (1) Hit or (2) Stand? ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                playerHand.addCard(deck.drawCard());
            } else if (choice == 2) {
                playerDone = true;
            }
        }

        // Dealer's turn
        while (dealerHand.getValue() < 17) {
            dealerHand.addCard(deck.drawCard());
        }

        System.out.println("Dealer's hand: " + dealerHand);

        if (dealerHand.getValue() > 21 || playerHand.getValue() > dealerHand.getValue()) {
            System.out.println("You win!");
        } else if (playerHand.getValue() == dealerHand.getValue()) {
            System.out.println("It's a tie!");
        } else {
            System.out.println("Dealer wins!");
        }
    }
}

class Card {
    private final String suit;
    private final String rank;
    private final int value;

    public Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

class Deck {
    private final ArrayList<Card> cards = new ArrayList<>();

    public Deck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};

        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                cards.add(new Card(suit, ranks[i], values[i]));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return cards.remove(cards.size() - 1);
    }
}

class Hand {
    private final ArrayList<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public int getValue() {
        int value = 0;
        int aceCount = 0;

        for (Card card : cards) {
            value += card.getValue();
            if (card.toString().startsWith("Ace")) {
                aceCount++;
            }
        }

        while (value > 21 && aceCount > 0) {
            value -= 10;
            aceCount--;
        }

        return value;
    }

    @Override
    public String toString() {
        return cards.toString() + " (value: " + getValue() + ")";
    }
}
