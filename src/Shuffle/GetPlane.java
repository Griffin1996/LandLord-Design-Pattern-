package Shuffle;

import java.util.ArrayList;
import java.util.List;

import FlyweightFactory.Card;
import Game.Model;

public class GetPlane {
	//拆飞机
	public static void getPlane(List<Card> list,Model model){
		List<String> del=new ArrayList<String>();//要删除的Cards
		//从model里面的3带找
		List<String> l=model.a3;
		if(l.size()<2)
			return ;
		Integer s[]=new Integer[l.size()];
		for(int i=0,len=l.size();i<len;i++){
			String []name=l.get(i).split(",");
			s[i]=Integer.parseInt(name[0].substring(2,name[0].length()));
		}
		for(int i=0,len=l.size();i<len;i++){
			int k=i;
			for(int j=i;j<len;j++)
			{
				if(s[i]-s[j]==j-i)
					k=j;
			}
			if(k!=i)
			{//说明从i到k是飞机
				String ss="";
				for(int j=i;j<k;j++)
				{
					ss+=l.get(j)+",";
					del.add(l.get(j));
				}
				ss+=l.get(k);
				model.a111222.add(ss);
				del.add(l.get(k));
				i=k;
			}
		}
		l.removeAll(del);
	}

}
