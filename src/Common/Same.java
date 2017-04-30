package Common;

import java.util.ArrayList;

import FlyweightFactory.Card;
import State.SingleCard;

public class Same {
	//得到最大相同数
	public static void getMax(SingleCard card_index,ArrayList<Card> list){
		int count[]=new int[14];//1-13各算一种,王算第14种
		for(int i=0;i<14;i++)
			count[i]=0;
		for(int i=0,len=list.size();i<len;i++){
			if(list.get(i).getColor()==5)
				count[13]++;
			else
				count[list.get(i).getValue()-1]++;
		}
		for(int i=0;i<14;i++)
		{
			switch (count[i]) {
			case 1:
				card_index.a[0].add(i+1);
				break;
			case 2:
				card_index.a[1].add(i+1);
				break;
			case 3:
				card_index.a[2].add(i+1);
				break;
			case 4:
				card_index.a[3].add(i+1);
				break;
			}
		}
	}	

}
