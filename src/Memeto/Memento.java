package Memeto;

import FlyweightFactory.Card;
import Iterator.ConcreteAggregate;

/**
 * Description: ±¸ÍüÂ¼½ÇÉ«
 */
public class Memento {
    private ConcreteAggregate<Card> MySelf=null;
    @SuppressWarnings("unchecked")
	public Memento( Originator o ) throws CloneNotSupportedException  { this.MySelf = (ConcreteAggregate<Card>) o.GetState().clone();}
    public ConcreteAggregate<Card> GetState(){
    	return MySelf;
    }
    public void SetState(ConcreteAggregate<Card> MySelf) throws CloneNotSupportedException{
    	this.MySelf=MySelf;
    }
}