package State;
import java.util.ArrayList;
import Common.Same;
public class PlaneWithNsingleState extends MyState {

	@Override
	public CardType JudgeType(CardState cardstate) {
		// TODO Auto-generated method stub
		SingleCard card_index=new SingleCard();
		int len = cardstate.CardSize();
		for(int i=0;i<4;i++)
		card_index.a[i]=new ArrayList<Integer>();
		//����������ֳ���Ƶ��
		Same.getMax( card_index,cardstate.GetCardList());
		//�ɻ�
		if(len>=5 && card_index.a[2].size()==len/4 &&
				((Integer)(card_index.a[2].get(len/4-1))-(Integer)(card_index.a[2].get(0))==len/4-1))
			return CardType.c11122234;
		else cardstate.SetCardState(new PlaneWithnPairState());
		return cardstate.JudgeType();
	}

}
