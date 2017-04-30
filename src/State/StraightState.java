package State;
import java.util.ArrayList;
import Common.Same;
public class StraightState extends MyState {

	@Override
	public CardType JudgeType(CardState cardstate) {
		// TODO Auto-generated method stub
		//单连,保证不存在王
		SingleCard card_index=new SingleCard();
		int len = cardstate.CardSize();
		for(int i=0;i<4;i++)
		card_index.a[i]=new ArrayList<Integer>();
		//求出各种数字出现频率
		Same.getMax( card_index,cardstate.GetCardList());
		if(len>=5 && (cardstate.GetCardList().get(0).getColor()!=5)&&(card_index.a[0].size()==len) &&
				(cardstate.GetCardList().get(0).getValue()-cardstate.GetCardList().get(len-1).getValue()==len-1))
			return CardType.c123;
		else cardstate.SetCardState(new StraightPairState());
		return cardstate.JudgeType();
	}

}
