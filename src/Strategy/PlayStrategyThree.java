package Strategy;

import java.util.ArrayList;
import java.util.List;

import FlyweightFactory.Card;

public class PlayStrategyThree extends Strategy {
	public PlayStrategyThree(List<String> model,List<Card> player,List<String> list,int role){
		CardAlgorithmInterface(  model, player, list, role);
	}
	
	public void CardAlgorithmInterface(List<String> model,List<Card> player,List<String> list,int role) {
		// TODO Auto-generated method stub
		
		for(int i=0,len=model.size();i<len;i++)
		{
			String []s=model.get(i).split(",");
			if(s.length==player.size()&&getValueInt(model.get(i))>player.get(0).getValue())
			{
				list.add(model.get(i));
				return;
			}
		}
	}

	@Override
	public int getValueInt(String n) {
		// TODO Auto-generated method stub
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
	public void CardAlgorithmInterface(List<String> model, List<Card> player,
			List<String> list, int role, int dizhuFlag) {
		// TODO Auto-generated method stub
		
	}

}
