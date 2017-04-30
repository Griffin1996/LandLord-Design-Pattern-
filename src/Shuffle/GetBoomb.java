package Shuffle;

import java.util.ArrayList;
import java.util.List;

import FlyweightFactory.Card;
import Game.Model;

public class GetBoomb {
	//拆炸弹
	public static void getBoomb(List<Card> list,Model model){
		List<Card> del=new ArrayList<Card>();//要删除的Cards
		//王炸
		if(list.size()>=2 &&list.get(0).getColor()==5 && list.get(1).getColor()==5)
		{
			model.a4.add(list.get(0).name+","+list.get(1).name); //按名字加入
			del.add(list.get(0));
			del.add(list.get(1));
		}
		//如果王不构成炸弹咋先拆单
		if(list.get(0).getColor()==5&&list.get(1).getColor()!=5)
		{
			del.add(list.get(0));
			model.a1.add(list.get(0).name);
		}
		list.removeAll(del);
		//一般的炸弹
		for(int i=0,len=list.size();i<len;i++){
			if(i+3<len && list.get(i).getValue()==list.get(i+3).getValue())
			{
				String s=list.get(i).name+",";
				s+=list.get(i+1).name+",";
				s+=list.get(i+2).name+",";
				s+=list.get(i+3).name;
				model.a4.add(s);
				for(int j=i;j<=i+3;j++)
					del.add(list.get(j));
				i=i+3;
			}
		}
		list.removeAll(del);
	}
}
