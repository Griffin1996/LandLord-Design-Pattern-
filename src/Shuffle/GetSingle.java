package Shuffle;

import java.util.ArrayList;
import java.util.List;

import FlyweightFactory.Card;
import Game.Model;

public class GetSingle {

	//����
	public static void getSingle(List<Card> list,Model model){
		ArrayList<Card> del=new ArrayList<Card>();//Ҫɾ����Cards
		//1
		for(int i=0,len=list.size();i<len;i++){
				model.a1.add(list.get(i).name);
				del.add(list.get(i));
			}
		list.removeAll(del);
	}
}
