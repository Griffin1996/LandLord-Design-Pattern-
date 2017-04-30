package Common;
import java.awt.Point;

import FlyweightFactory.Card;
import FlyweightFactory.Player;
import Game.GameUI;
import Iterator.ConcreteAggregate;
public class Position {
	//���¶�λ flag�������1 ,2 ��������
	public static void rePosition(GameUI m,Player players ){
		Point p=new Point();
	
		if(players.GetNumber()==0)
		{
			p.x=50;
			p.y=(450/2)-(players.GetCardSize()+1)*15/2;
		}
		if(players.GetNumber()==1)
		{//�ҵ����� _y=450 width=830
			p.x=(800/2)-(players.GetCardSize()+1)*21/2;
			p.y=450;
		}
		if(players.GetNumber()==2)
		{
			p.x=700;
			p.y=(450/2)-(players.GetCardSize()+1)*15/2;
		}
		int len=players.GetCardSize();
		ConcreteAggregate<Card> list = players.GetPlayerCards();
		for(int i=0;i<len;i++){
			Card card=list.get(i);
			Move.move(card, card.getLocation(), p);
			m.container.setComponentZOrder(card, 0);//
			if(players.GetNumber()==1)p.x+=21;
			else p.y+=15;
			
		}
	}
}
