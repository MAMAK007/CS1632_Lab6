package cs1632;

public class Main {
    public static void main(String[] args) {
        PlayingCardDeck deck = new PlayingCardDeck();
        while (!deck.isEmpty()) {
            PlayingCard c = deck.nextCard();
            System.out.println("Card is " + c.cardName());
        }
    }
}