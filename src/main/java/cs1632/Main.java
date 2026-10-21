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
		PlayingCard p1Card;
		PlayingCard p2Card;
		int winCon;
		int warWin = 0;
		int round = 0;
		// Play round
		while (!p1Hand.isEmpty() && !p2Hand.isEmpty() && warWin == 0) {
			// Round number is incremented
			round++;
			System.out.println("ROUND " + round + ":");
			// Card is popped
			p1Card = p1Hand.pop();
			System.out.println(p1Hand.size() + " | P1 plays: " + p1Card.cardName());
			p2Card = p2Hand.pop();
			System.out.println(p2Hand.size() + " | P2 plays: " + p2Card.cardName());
			
			// Cards' values are compared
			winCon = p1Card.compareTo(p2Card);

			// P1 wins round
			if (winCon == 1) {
				p1Hand.push(p1Card);
				p1Hand.push(p2Card);
				System.out.println("* P1 wins round!\n");
			} 
			// P2 wins round
			else if (winCon == -1) {
				p2Hand.push(p1Card);
				p2Hand.push(p2Card);
				System.out.println("* P2 wins round!\n");
			} 
			// P1 and P2 tie for war
			else if (winCon == 0) {
				System.out.println("* Tie - War!\n");
				
				// Create the initial war pot
				PlayingCardHand<PlayingCard> warPot = new PlayingCardHand<PlayingCard>();
				warPot.push(p1Card);
				warPot.push(p2Card);

				// War loop
				PlayingCard p1WarCard;
				PlayingCard p2WarCard;
				while (true) {
					// Each player drops 3 cards into war pot
					if (p1Hand.size() > 3 && p2Hand.size() > 3) {
						System.out.println("Each player adds 3 cards to the war pot...");
						for (int i = 0; i < 3; i++) {
							warPot.push(p1Hand.pop());
							warPot.push(p2Hand.pop());
						}
					}
					// If a player doesn't have enough cards to place into pot
					else {
						if (p1Hand.size() < p2Hand.size()) {
							System.out.println("P1 doesn't have enough cards to enter the pot!");
						} 
						else if (p1Hand.size() > p2Hand.size()) {
							System.out.println("P2 doesn't have enough cards to enter the pot!");
						}
						else {
							System.out.println("Both players don't have enough cards to enter the pot!");
						}
						warWin = 1;
						break;
					}
					
					
					// Draw cards
					p1WarCard = p1Hand.pop();
					System.out.println(p1Hand.size() + " | P1 plays: " + p1WarCard.cardName());
					p2WarCard = p2Hand.pop();
					System.out.println(p2Hand.size() + " | P2 plays: " + p2WarCard.cardName());
					
					// P1 wins pot
					if (p1WarCard.compareTo(p2WarCard) == 1) {
						p1Hand.push(p1WarCard);
						p1Hand.push(p2WarCard);
						for (PlayingCard c : warPot) {
							p1Hand.push(c);
						}
						System.out.println("* P1 wins the war!\n");
						break;
					} 
					// P2 wins pot
					else if (p1WarCard.compareTo(p2WarCard) == -1) {
						p2Hand.push(p1WarCard);
						p2Hand.push(p2WarCard);
						for (PlayingCard c : warPot) {
							p2Hand.push(c);
						}
						System.out.println("* P2 wins the war!\n");
						break;
					} 
					// Another tie; add to pot and continue war
					else {
						warPot.push(p1WarCard);
						warPot.push(p2WarCard);
						System.out.println("* A tie! The war rages on!\n");
						continue;
					}
				}
				continue;
			}
		}
		
		// Final score is printed
		System.out.println("----- RESULTS PHASE -----");
		// P1 wins game
		if (p1Hand.size() > p2Hand.size()) {
			System.out.println("* P1 Wins!");
		} 
		// P2 wins game
		else if (p1Hand.size() < p2Hand.size()) {
			System.out.println("* P2 Wins!");
		} 
		// P1 and P2 tie the game
		else {
			System.out.println("* It's a tie!");
		}
	}
}