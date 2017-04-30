package Iterator;

import java.util.ArrayList;

public interface Aggregate<Card> {
	public  MyIterator<Card> CreateIterator();
	public  MyIterator<Card> CreateIterator(Card e);
	public int length();
	public Card get(int index);
	public void add(Card obj);
	public void addAll(ArrayList<Card> currentList);
	public void remove(Card e);
	public void removeAll(ArrayList<Card> currentList);
	
}
