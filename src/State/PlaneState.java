package State;

import java.util.ArrayList;

import Common.Same;
public class PlaneState extends MyState {

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
		if(len>=5 && card_index.a[2].size()==len/3 && (len%3==0) &&
				(cardstate.GetCardList().get(0).getValue()-cardstate.GetCardList().get(len-1).getValue()==(len/3-1)))
			return CardType.c111222;
		else cardstate.SetCardState(new PlaneWithNsingleState());
		return cardstate.JudgeType();
	}

}
