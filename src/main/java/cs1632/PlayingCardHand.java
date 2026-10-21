package cs1632;

import java.util.LinkedList;

public class PlayingCardHand<T> {
	protected LinkedList<T> queueData;
	
	public PlayingCardHand() { 
		queueData = new LinkedList<>(); 
	}
	
	public void push(T d) { 
		queueData.addLast(d);
	}
	
	public T pop() {
		return queueData.removeFirst();
	}
	
	public T front() { 
		return queueData.getFirst();	
	}
	
	public int size() {
		return queueData.size();
	}
	
	public boolean isEmpty() { 
		return queueData.isEmpty(); 
	}

}
