package Shuffle;

import java.util.ArrayList;
import java.util.List;

import FlyweightFactory.Card;
import Game.Model;

public class GetStraight {
	//拆连子
	public static void get123(List<Card> list,Model model){
		List<Card> del=new ArrayList<Card>();//要删除的Cards
		if(list.size()>0&&(list.get(0).getValue()<7 ||list.get(list.size()-1).getValue()>10))
			return;
		if(list.size()<5)
			return;
		for(int i=0,len=list.size();i<len;i++)
		{
			int k=i;
			for(int j=i;j<len;j++){
				if(list.get(i).getValue()-list.get(j).getValue()==j-i)
				{
					k=j;
				}
			}
			if(k-i>=4)
			{
				String s="";
				for(int j=i;j<k;j++)
				{
					s+=list.get(j).name+",";
					del.add(list.get(j));
				}
				s+=list.get(k).name;
				del.add(list.get(k));
				model.a123.add(s);
				i=k;
			}
		}
		list.removeAll(del);
	}

}
