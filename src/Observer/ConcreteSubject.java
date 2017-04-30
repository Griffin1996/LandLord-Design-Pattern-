package Observer;

import FlyweightFactory.Card;
import Iterator.ConcreteAggregate;

public class ConcreteSubject extends Subject { 
	private String subjectState;//目标对象的状态 
	//private ConcreteAggregate<Card> PlayerCard;
	public String getSubjectState() { return subjectState; } 
	
	public void setSubjectState(String subjectState) { 
		this.subjectState = subjectState; 
		this.notifyObservers();//通知各个观察者 
		}
}
