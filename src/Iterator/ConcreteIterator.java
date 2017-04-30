package Iterator;

import java.util.Iterator;

public class ConcreteIterator<E> implements MyIterator<E> {
	private Aggregate<E> aggregate;  
	private int currentIndex = 0; //±éÀú×´Ì¬ public ConcreteIterator(Aggregate<E> aggregate) {    this.aggregate = aggregate;   }  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ConcreteIterator(Aggregate aggregate){
		this.aggregate=aggregate;
	}
	@Override
	public E First() {  
		currentIndex = 0;  
		if(hasNext()){  
			return aggregate.get(currentIndex);  
			}
		else { 
			return null;      
			} 
		}  
	public boolean hasNext() {  
		return (currentIndex <= (aggregate.length()-1) );  
		}  
	public E Next() {  
		  
		if(hasNext()){  
			return aggregate.get(currentIndex++);  
			}
		else{ 
			return null;  
			} 
	
		}  
	public E current(){  
		return aggregate.get(currentIndex);  
		}
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean IsDone() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public E CurrentItem() {
		// TODO Auto-generated method stub
		return null;
	}
	
} 

