package Common;
import java.util.ArrayList;

import FlyweightFactory.Card;
import State.CardState;
import State.CardType;
public class Judge {
	//�ж�����
	public static CardType jugdeType(ArrayList<Card> list){
		//��Ϊ֮ǰ��������ԱȽϺ�4�ж�
		CardState cardstate=new CardState();
		cardstate.SetCardList(list);
		return cardstate.JudgeType();
	}
	
}
