package Strategy;

import java.util.List;

import FlyweightFactory.Card;
import Game.Model;
import State.CardType;

public class PlayStrategyOne extends Strategy {
	public PlayStrategyOne(List<String> model,List<Card> player,List<String> list,int role,int dizhuFlag) {
		// TODO Auto-generated constructor stub
		CardAlgorithmInterface(  model, player, list, role,dizhuFlag);
	}
	public  int getValueInt(String n){
		String name[]=n.split(",");
		String s=name[0];
		int i=Integer.parseInt(s.substring(2, s.length()));
		if(s.substring(0, 1).equals("5"))
			i+=3;
		if(s.substring(2, s.length()).equals("1")||s.substring(2, s.length()).equals("2"))
			i+=13;
		return i;
	}
	@Override
	public void CardAlgorithmInterface(List<String> model,List<Card> player,List<String> list,int role,int dizhuFlag) {
		// TODO Auto-generated method stub
		//¶¥¼Ò
				if((role+1)%3==dizhuFlag)
				{
					
					for(int i=0,len=model.size();i<len;i++)
					{
						if(getValueInt(model.get(i))>player.get(0). getValue())
						{
							list.add(model.get(i));
							break;
						}
					}
				}else {//Æ«¼Ò
					
					for(int len=model.size(),i=len-1;i>=0;i--)
					{
						if(getValueInt(model.get(i))>player.get(0).getValue())//
						{
							list.add(model.get(i));
							break;
						}
					}
				}
	}

}
