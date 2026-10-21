package cs1632;

import java.util.Scanner;

public class Main {	
	public static void main(String[] args) {
		// Create initial deck of cards
		PlayingCardDeck deck = new PlayingCardDeck();
		// Create hand queues for P1 and P2
		PlayingCardHand<PlayingCard> p1Hand = new PlayingCardHand<PlayingCard>();
		PlayingCardHand<PlayingCard> p2Hand = new PlayingCardHand<PlayingCard>();
		// Create Scanner
		Scanner sc = new Scanner(System.in);

		// Deal the cards to P1 and P2
		System.out.println("----- DEAL PHASE -----");
		int cardDeal = 1;
		while (!deck.isEmpty()) {
			PlayingCard c = deck.nextCard();		
			// Every odd card goes to P1
			if ((cardDeal % 2) == 1) {
				p1Hand.push(c);
			} 
			// Every even card goes to P2
			else {
				p2Hand.push(c);
			}
			cardDeal++;
		}
		System.out.println("Cards dealt successfully!");
		System.out.println("P1: " + p1Hand.size() + " cards | P2: " + p2Hand.size() + " cards");
		System.out.println("> Enter to continue");
		sc.nextLine();
		
		// Start game between 2 bots
		System.out.println("----- GAME PHASE -----");	
		int p1Score = 0;
		int p2Score = 0;
		PlayingCard p1Card;
		PlayingCard p2Card;
		int winCon;
		int round = 0;
		// Play round
		while (!p1Hand.isEmpty() && !p2Hand.isEmpty()) {
			// Round number is incremented
			round++;
			System.out.println("ROUND " + round + ":");
			// Card is popped
			p1Card = p1Hand.pop();
			System.out.println("P1 plays: " + p1Card.cardName());
			p2Card = p2Hand.pop();
			System.out.println("P2 plays: " + p2Card.cardName());
			
			// Cards' values are compared
			winCon = p1Card.compareTo(p2Card);

			// P1 wins round
			if (winCon == 1) {
				p1Score++;
				System.out.println("P1 wins round!");
			} 
			// P2 wins round
			else if (winCon == -1) {
				p2Score++;
				System.out.println("P2 wins round!");
			} 
			// P1 and P2 tie the round
			else if (winCon == 0) {
				System.out.println("Tie round!");
				continue;
			}
			
			System.out.println("> Enter to continue");
			sc.nextLine();
		}
		
		// Final score is printed
		System.out.println("----- RESULTS PHASE -----");
		System.out.println("P1 Score: " + p1Score + " | P2 Score: " + p2Score);
		// P1 wins game
		if (p1Score > p2Score) {
			System.out.println("** P1 Wins! **");
		} 
		// P2 wins game
		else if (p1Score < p2Score) {
			System.out.println("** P2 Wins! **");
		} 
		// P1 and P2 tie the game
		else {
			System.out.println("** It's a tie! **");
		}
	}
}