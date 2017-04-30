package Shuffle;

import java.util.ArrayList;
import java.util.List;

import FlyweightFactory.Card;
import Game.Model;

public class GetPair {

	//拆对子
	public static void getTwo(List<Card> list,Model model){
		List<Card> del=new ArrayList<Card>();//要删除的Cards
		//连续2张相同
		for(int i=0,len=list.size();i<len;i++){
			if(i+1<len&&list.get(i).getValue()==list.get(i+1).getValue())
			{
				String s=list.get(i).name+",";
				s+=list.get(i+1).name;
				model.a2.add(s);
				for(int j=i;j<=i+1;j++)
					del.add(list.get(j));
				i=i+1;
			}
		}
		list.removeAll(del);
	}
}
