package Strategy;

import java.util.List;

import Common.Order;
import FlyweightFactory.Card;

public class PlayStrategyFive extends Strategy {
	public PlayStrategyFive(List<String> model1,List<String> model2,List<Card> player,List<String> list,int role){
		CardAlgorithmInterface(  model1, model2,player, list, role);
	}

	public void CardAlgorithmInterface(List<String> model1,List<String> model2,List<Card> player,List<String> list,int role) {
		// TODO Auto-generated method stub
		//ÅÅÐò°´ÖØ¸´Êý
		player=Order.getOrder(player);
		int len1=model1.size();
		int len2=model2.size();
		
		if(len1<1 || len2<2)
			return;
		for(int i=0;i<len1;i++){
			if(getValueInt(model1.get(i))>player.get(0). getValue())
			{
				list.add(model1.get(i));
				for(int j=1;j<=2;j++)
					list.add(model2.get(len2-j));
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
