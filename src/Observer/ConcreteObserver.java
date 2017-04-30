package Observer;

import Common.Position;
import FlyweightFactory.Card;
import FlyweightFactory.Player;
import Game.GameUI;
import Iterator.ConcreteAggregate;
import Iterator.ConcreteIterator;

public class ConcreteObserver implements Observer {
	GameUI main;
	private String observerState;//观者者的状态 
	private Player player = new Player(0);
	public ConcreteObserver(GameUI main,Player player){
		this.player=player;
		this.main=main;
	}
	public void update(Subject subject) { //具体的更新实现 
		//PlayerCard = ((ConcreteSubject)subject).getSubjectState();
		observerState =((ConcreteSubject)subject).getSubjectState();
		switch(observerState)
		{
		case "悔牌":{ConcreteIterator <Card> it = new ConcreteIterator<Card> (player.GetPlayerCards());
		while(it.hasNext())
		{	
			main.container.remove(it.Next());
		
			//迭代遍历
		}}
		case "恢复手牌":
		{	
			ConcreteIterator <Card> it = new ConcreteIterator<Card> (player.GetPlayerCards());
			 while(it.hasNext())
				{	
					main.container.add(it.Next());
					//迭代遍历
				}
			 Position.rePosition(main, player);
		}
		
		
		}
		
	}	
}


