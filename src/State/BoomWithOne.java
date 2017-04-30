package State;

import java.util.ArrayList;
import Common.Same;
public class BoomWithOne extends MyState {

	@Override
	public CardType JudgeType(CardState cardstate) {
		// TODO Auto-generated method stub
		SingleCard card_index=new SingleCard();
		int len = cardstate.CardSize();
		for(int i=0;i<4;i++)
			card_index.a[i]=new ArrayList<Integer>();
		//求出各种数字出现频率
		Same.getMax( card_index,cardstate.GetCardList());
		if(card_index.a[3].size()==1 && len==6)
			return CardType.c411;
		else cardstate.SetCardState(new BoomWithTwo());
		return cardstate.JudgeType();
	}

}
