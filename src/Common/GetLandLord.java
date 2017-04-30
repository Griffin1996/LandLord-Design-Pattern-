package Common;
import java.util.List;

import FlyweightFactory.Card;
import Iterator.ConcreteAggregate;
public class GetLandLord {
	//地主牌权值，看是否抢地主
	public static int getScore(ConcreteAggregate<Card> list){
		int count=0;
		int len=list.length();
		for(int i=0;i<len;i++){
			Card card=list.get(i);
			if(card.name.substring(0, 1).equals("5"))
			{
				count+=5;
			}
			if(card.name.substring(2, card.name.length()).equals("2"))
			{	
				count+=2;
			}
		}
		return count;
		
	}
	
}
