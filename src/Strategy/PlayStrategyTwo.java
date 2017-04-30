package Strategy;

import java.util.List;

import Common.Order;
import FlyweightFactory.Card;

public class PlayStrategyTwo extends Strategy {
	public PlayStrategyTwo(List<String> model1,List<String> model2,List<Card> player,List<String> list,int role){
		CardAlgorithmInterface(  model1, model2,player, list, role);
	}
	
	public void CardAlgorithmInterface(List<String> model1,List<String> model2,List<Card> player,List<String> list,int role) {
		// TODO Auto-generated method stub
		//model1是主牌,model2是带牌,player是玩家出的牌,,list是准备回的牌
		//排序按重复数
		player=Order.getOrder(player);
		int len1=model1.size();
		int len2=model2.size();
		//如果有王直接炸了
		if(len1>0&&model1.get(0).length()<10)
		{
			list.add(model1.get(0));
			System.out.println("王炸");
			return;
		}
		if(len1<1 || len2<1)
			return;
		for(int len=len1,i=len-1;i>=0;i--)
		{	
			if(getValueInt(model1.get(i))>player.get(0).getValue())
			{
				list.add(model1.get(i));
				break;
			}
		} 
		list.add(model2.get(len2-1));
		if(list.size()<2)
			list.clear();

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
