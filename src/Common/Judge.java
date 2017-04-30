package Common;
import java.util.ArrayList;

import FlyweightFactory.Card;
import State.CardState;
import State.CardType;
public class Judge {
	//判断牌型
	public static CardType jugdeType(ArrayList<Card> list){
		//因为之前排序过所以比较好4判断
		CardState cardstate=new CardState();
		cardstate.SetCardList(list);
		return cardstate.JudgeType();
	}
	
}
