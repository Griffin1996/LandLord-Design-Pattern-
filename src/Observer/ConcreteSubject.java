package Observer;

import FlyweightFactory.Card;
import Iterator.ConcreteAggregate;

public class ConcreteSubject extends Subject { 
	private String subjectState;//Ŀ������״̬ 
	//private ConcreteAggregate<Card> PlayerCard;
	public String getSubjectState() { return subjectState; } 
	
	public void setSubjectState(String subjectState) { 
		this.subjectState = subjectState; 
		this.notifyObservers();//֪ͨ�����۲��� 
		}
}
