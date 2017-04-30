package Common;
import java.util.ArrayList;
import java.util.List;

import FlyweightFactory.Card;
import State.CardType;
public class Check {
	//检查牌的是否能出
	public static int checkCards(ArrayList<Card> c,ArrayList<Card>[] current){
		//找出当前最大的牌是哪个电脑出的,c是点选的牌
		ArrayList<Card> currentlist=(current[0].size()>0)?current[0]:current[2];
		CardType cType=Judge.jugdeType(c);
		//如果张数不同直接过滤
		if(cType!=CardType.c4&&c.size()!=currentlist.size())
			return 0;
		//比较我的出牌类型
		if(Judge.jugdeType(c)!=Judge.jugdeType(currentlist))
		{
			
			return 0;
		}
		//比较出的牌是否要大
		//王炸弹
		if(cType==CardType.c4)
		{
			if(c.size()==2)
				return 1;
			if(currentlist.size()==2)
				return 0;
		}
		//单牌,对子,3带,4炸弹
		if(cType==CardType.c1||cType==CardType.c2||cType==CardType.c3||cType==CardType.c4){
			if(c.get(0).getValue()<=currentlist.get(0).getValue())
			{
				 return 0;
			}else {
				return 1;
			}
		}
		//顺子,连队，飞机裸
		if(cType==CardType.c123||cType==CardType.c1122||cType==CardType.c111222)
		{
			if(c.get(0).getValue()<=currentlist.get(0).getValue())
				return 0;
			else 
				return 1;
		}
		//按重复多少排序
		//3带1,3带2 ,飞机带单，双,4带1,2,只需比较第一个就行，独一无二的 
		if(cType==CardType.c31||cType==CardType.c32||cType==CardType.c411||cType==CardType.c422
				||cType==CardType.c11122234||cType==CardType.c1112223344){
			List<Card> a1=Order.getOrder(c); //我出的牌
			List<Card> a2=Order.getOrder(currentlist);//当前最大牌
			if(a1.get(0).getValue()<a2.get(0).getValue())
				return 0;
		}
		return 1;
	}
}
