package State;
import java.util.ArrayList;
import Common.Same;
public class PlaneWithnPairState extends MyState {

	@Override
	public CardType JudgeType(CardState cardstate) {
		// TODO Auto-generated method stub
		SingleCard card_index=new SingleCard();
		int len = cardstate.CardSize();
		for(int i=0;i<4;i++)
		card_index.a[i]=new ArrayList<Integer>();
		//求出各种数字出现频率
		Same.getMax( card_index,cardstate.GetCardList());
		//飞机
		//飞机带n双
		if(card_index.a[2].size()==len/5 && card_index.a[2].size()==len/5 &&
				((Integer)(card_index.a[2].get(len/5-1))-(Integer)(card_index.a[2].get(0))==len/5-1))
			return CardType.c1112223344;
		else cardstate.SetCardState(new NoneState());
		return cardstate.JudgeType();
	}

}
