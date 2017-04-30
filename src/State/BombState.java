package State;
public class BombState extends MyState {
	public CardType JudgeType(CardState cardstate) {
		int len=cardstate.CardSize();
		if(len==4 &&cardstate.GetCardList().get(0).getValue()==cardstate.GetCardList().get(len-1).getValue())
		return CardType.c4;
		else cardstate.SetCardState(new CoupleKingState());
		return cardstate.JudgeType();
	}
}
