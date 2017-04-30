package State;
import java.util.ArrayList;
import Common.Same;
public class ThreeWithTwoState extends MyState {

	@Override
	public CardType JudgeType(CardState cardstate) {
		SingleCard card_index=new SingleCard();
		int len = cardstate.CardSize();
		for(int i=0;i<4;i++)
			card_index.a[i]=new ArrayList<Integer>();
		//����������ֳ���Ƶ��
		Same.getMax( card_index,cardstate.GetCardList()); //a[0,1,2,3]�ֱ��ʾ�ظ�1,2,3,4�ε���
		//3��2 -----�غ��ظ�3�ε���
		if(len>=5 && card_index.a[2].size()==1 &&card_index.a[1].size()==1 && len==5)
			return CardType.c32;
		else cardstate.SetCardState(new BoomWithOne());
		return cardstate.JudgeType();
	}

}
