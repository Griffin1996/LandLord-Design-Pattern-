package State;
public class CoupleKingState extends MyState {
	@Override
	public CardType JudgeType(CardState cardstate) {
		// TODO Auto-generated method stub
		int len=cardstate.CardSize();
		if(len==2&&cardstate.GetCardList().get(1).getColor()==5)
		return CardType.c2;
		else cardstate.SetCardState(new ThreeWithOneState());
		return cardstate.JudgeType();
	}
}
