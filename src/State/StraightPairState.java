package State;
import java.util.ArrayList;
import Common.Same;
public class StraightPairState extends MyState {
	@Override
	public CardType JudgeType(CardState cardstate) {
		// TODO Auto-generated method stub
		//����,��֤��������
		SingleCard card_index=new SingleCard();
		int len = cardstate.CardSize();
		for(int i=0;i<4;i++)
		card_index.a[i]=new ArrayList<Integer>();
		//����������ֳ���Ƶ��
		Same.getMax( card_index,cardstate.GetCardList());
		//����
		if(len>=5 && card_index.a[1].size()==len/2 && len%2==0 && len/2>=3
				&&(cardstate.GetCardList().get(0).getValue()-cardstate.GetCardList().get(len-1).getValue()==(len/2-1)))
			return CardType.c1122;
		else cardstate.SetCardState(new PlaneState());
		return cardstate.JudgeType();
	}

}
