package cs1632;

import java.util.LinkedList;
import java.util.Random;

public class PlayingCardDeck {
    private LinkedList<PlayingCard> cardDeck;
    public void newShuffle() {
    	cardDeck.clear();
        PlayingCard [] cards = new PlayingCard[PlayingCard.CardValues.length * PlayingCard.SuitValues.length];
        int cn = 0;
        for (int cv = 0; cv < PlayingCard.CardValues.length; cv++) {
            for (int sv = 0; sv < PlayingCard.SuitValues.length; sv++) {
                cards[cn] = new PlayingCard(cv,sv);
                cn++;
            }
        }
        Random rand = new Random();
        for (int i = PlayingCard.CardValues.length * PlayingCard.SuitValues.length; i >= 1; i--) {
            int rn = rand.nextInt(i);
            cardDeck.add(cards[rn]);
            cards[rn] = cards[i-1];
        }
    }
    public PlayingCardDeck() {
        cardDeck = new LinkedList<>();
        newShuffle();
    }
    public PlayingCard nextCard() {
        PlayingCard c = cardDeck.peek();
        cardDeck.pop();
        return c;
    }
    public Boolean isEmpty() {
        return cardDeck.isEmpty();
    }
}
