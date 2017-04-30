package Memeto;
import FlyweightFactory.Card;
import Iterator.ConcreteAggregate;
public class Originator {
	private ConcreteAggregate<Card> MySelf=null ;
	public Memento CreateMemento() throws CloneNotSupportedException {
		return new Memento(this);
	}
	@SuppressWarnings("unchecked")
	public void SetState(ConcreteAggregate<Card> m) throws CloneNotSupportedException{
		MySelf=(ConcreteAggregate<Card>) m.clone();
		
	}
	public ConcreteAggregate<Card> GetState(){
		return MySelf;
	}
	
	public void RestoreMemento(Memento memento){
		this.MySelf=memento.GetState();
	}
}
