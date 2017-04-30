package Iterator;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import FlyweightFactory.Card;
@SuppressWarnings("hiding")
public class ConcreteAggregate<Card> implements Aggregate<Card>,Cloneable {
	private ArrayList<Card> items = new ArrayList<Card>();
	public ConcreteAggregate(){
			this.CreateIterator();
		}
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public MyIterator<Card> CreateIterator(Card e) {
		// TODO Auto-generated method stub
		return new ConcreteIterator(this);
	}
		@Override
	public Object clone() throws CloneNotSupportedException {
		@SuppressWarnings("unchecked")
		ConcreteAggregate<Card> o = (ConcreteAggregate<Card>) super.clone();
		try {
			o.items=deepCopy(items);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
        return o;
	
		    }
		
		public ArrayList<Card> deepCopy(ArrayList<Card> src) throws IOException, ClassNotFoundException{   
	        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();   
	        ObjectOutputStream out = new ObjectOutputStream(byteOut);   
	        out.writeObject(src);   
	       
	        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());   
	        ObjectInputStream in =new ObjectInputStream(byteIn);   
	        @SuppressWarnings("unchecked")
			ArrayList<Card> dest = (ArrayList<Card>)in.readObject();   
	        return dest;   
	    } 
	@Override
	public int length() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Card get(int index) {
		// TODO Auto-generated method stub
		return (Card)items.get(index);
	}

	@Override
	public void add(Card obj) {
		// TODO Auto-generated method stub
		items.add(obj);
	}
	public ArrayList<Card> getItem(){
		return  this.items;
	}

	@Override
	public void removeAll(ArrayList<Card> currentList) {
		
		items.removeAll(currentList);
	}

	@Override
	public void remove(Card e) {
		
		items.remove(e);
		
	}
	@Override
	public MyIterator<Card> CreateIterator() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addAll(ArrayList<Card> currentList) {
		// TODO Auto-generated method stub
		items.addAll(currentList);
	}
	

}
