package cs1632;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayingCardHandTest {

	private PlayingCardHand<String> hand;

	@BeforeEach
	void setUp() {
		// Fresh hand before every test keeps each test independent —
		// one failing test shouldn't cascade into misleading failures
		hand = new PlayingCardHand<>();
	}

	@Test
	void newHandIsEmpty() {
		assertTrue(hand.isEmpty());
		assertEquals(0, hand.size());
	}

	@Test
	void popOrderIsFIFO() {
		hand.push("first-in");
		hand.push("second-in");

		// The queue contract: first-in, first-out. This is the test
		// that would have FAILED on your old stack version — which is
		// exactly the point of locking behavior down in tests
		assertEquals("first-in", hand.pop());
		assertEquals("second-in", hand.pop());
	}

	@Test
	void frontDoesNotRemove() {
		hand.push("only card");

		assertEquals("only card", hand.front());
		assertEquals(1, hand.size(), "front() observes without consuming");
	}

	@Test
	void frontFollowsQueueHeadAsItMoves() {
		hand.push("a");
		hand.push("b");
		hand.pop();

		// After removing the head, front() should report the NEW head
		assertEquals("b", hand.front());
	}

	@Test
	void sizeTracksPushAndPop() {
		hand.push("x");
		hand.push("y");
		hand.push("z");
		assertEquals(3, hand.size());

		hand.pop();
		assertEquals(2, hand.size());
	}

	@Test
	void popOnEmptyThrows() {
		// assertThrows FAILS if no exception occurs — inverted from
		// try/catch logic, which trips people up the first time
		assertThrows(Exception.class, () -> hand.pop(),
				"removeFirst() on empty should raise NoSuchElementException");
	}

	@Test
	void iterationWalksOldestFirst() {
		hand.push("oldest");
		hand.push("newest");

		List<String> seen = new ArrayList<>();
		for (String s : hand) {
			seen.add(s);
		}

		// Documents iterator direction: head (next out) comes first.
		// Your Main relies on this when draining the war pot — though
		// as a stack-era leftover, that drain now returns the ORIGINAL
		// tie cards first. Order rarely matters for a pot, but worth knowing
		assertEquals(List.of("oldest", "newest"), seen);
	}

	@Test
	void emptyHandIterationHasNoNext() {
		Iterator<String> it = hand.iterator();
		assertFalse(it.hasNext());
	}
}