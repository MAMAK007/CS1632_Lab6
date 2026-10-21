package cs1632;

public class PlayingCard implements Comparable<PlayingCard> {
    public final static String [] CardValues = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    public final static String [] SuitValues = { "♦️", "♣️", "♥️", "♠️" };
    private int cardValue;
    private int suitValue;
    private PlayingCard() {}
    public PlayingCard(int cv, int sv) { cardValue = cv; suitValue = sv; }
    public String cardName() { return CardValues[cardValue] + " " + SuitValues[suitValue]; }
    public int playingCardValue() { return cardValue; }
    
    @Override
	public int compareTo(PlayingCard card) {
		if (this.playingCardValue() > card.playingCardValue()) {
			return 1;
		} 
		else if (this.playingCardValue() < card.playingCardValue()) {
			return -1;
		} 
		else {
			return 0;
		} 
	}
}
