package Observer;

import Common.Position;
import FlyweightFactory.Card;
import FlyweightFactory.Player;
import Game.GameUI;
import Iterator.ConcreteAggregate;
import Iterator.ConcreteIterator;

public class ConcreteObserver implements Observer {
	GameUI main;
	private String observerState;//�����ߵ�״̬ 
	private Player player = new Player(0);
	public ConcreteObserver(GameUI main,Player player){
		this.player=player;
		this.main=main;
	}
	public void update(Subject subject) { //����ĸ���ʵ�� 
		//PlayerCard = ((ConcreteSubject)subject).getSubjectState();
		observerState =((ConcreteSubject)subject).getSubjectState();
		switch(observerState)
		{
		case "����":{ConcreteIterator <Card> it = new ConcreteIterator<Card> (player.GetPlayerCards());
		while(it.hasNext())
		{	
			main.container.remove(it.Next());
		
			//��������
		}}
		case "�ָ�����":
		{	
			ConcreteIterator <Card> it = new ConcreteIterator<Card> (player.GetPlayerCards());
			 while(it.hasNext())
				{	
					main.container.add(it.Next());
					//��������
				}
			 Position.rePosition(main, player);
		}
		
		
		}
		
	}	
}


