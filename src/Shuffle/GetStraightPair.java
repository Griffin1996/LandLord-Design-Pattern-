package Shuffle;

import java.util.ArrayList;
import java.util.List;

import FlyweightFactory.Card;
import Game.Model;

public class GetStraightPair {

	//��˫˳
	public static void getTwoTwo(List<Card> list,Model model){
		List<String> del=new ArrayList<String>();//Ҫɾ����Cards
		//��model����Ķ�����
		List<String> l=model.a2;
		if(l.size()<3)
			return ;
		Integer s[]=new Integer[l.size()];
		for(int i=0,len=l.size();i<len;i++){
			String []name=l.get(i).split(",");
			s[i]=Integer.parseInt(name[0].substring(2,name[0].length()));
		}
		//s0,1,2,3,4  13,9,8,7,6
		for(int i=0,len=l.size();i<len;i++){
			int k=i;
			for(int j=i;j<len;j++)
			{
				if(s[i]-s[j]==j-i)
					k=j;
			}
			if(k-i>=2)//k=4 i=1
			{//˵����i��k������
				String ss="";
				for(int j=i;j<k;j++)
				{
					ss+=l.get(j)+",";
					del.add(l.get(j));
				}
				ss+=l.get(k);
				model.a112233.add(ss);
				del.add(l.get(k));
				i=k;
			}
		}
		l.removeAll(del);
	}
}
