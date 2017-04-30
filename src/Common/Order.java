package Common;

import java.util.ArrayList;
import java.util.List;

import FlyweightFactory.Card;

public class Order {

	//按照重复次数排序
	public static List getOrder(List<Card> list){
		List<Card> list2=new ArrayList<Card>(list);
		List<Card> list3=new ArrayList<Card>();
		List<Integer> list4=new ArrayList<Integer>();
		int len=list2.size();
		int a[]=new int[20];
		for(int i=0;i<20;i++)
			a[i]=0;
		for(int i=0;i<len;i++)
		{
			a[list2.get(i).getValue()]++;
		}
		int max=0;
		for(int i=0;i<20;i++){
			max=0;
			for(int j=19;j>=0;j--){
				if(a[j]>a[max])
					max=j;
			}
			for(int k=0;k<len;k++){
				if(list2.get(k).getValue()==max){
					list3.add(list2.get(k));
				}
			}
			list2.remove(list3);
			a[max]=0;
		}
		return list3;
	}
}
