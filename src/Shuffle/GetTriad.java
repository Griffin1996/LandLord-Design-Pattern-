package Shuffle;

import java.util.ArrayList;
import java.util.List;

import FlyweightFactory.Card;
import Game.Model;

public class GetTriad {

	//拆3带
	public static void getThree(List<Card> list,Model model){
		List<Card> del=new ArrayList<Card>();//要删除的Cards
		//连续3张相同
		for(int i=0,len=list.size();i<len;i++){
			if(i+2<len&&list.get(i).getValue()==list.get(i+2).getValue())
			{
				String s=list.get(i).name+",";
				s+=list.get(i+1).name+",";
				s+=list.get(i+2).name;
				model.a3.add(s);
				for(int j=i;j<=i+2;j++)
					del.add(list.get(j));
				i=i+2;
			}
		}
		list.removeAll(del);
	}

}
