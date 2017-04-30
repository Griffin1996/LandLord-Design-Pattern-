package State;
public class ThreeWithOneState extends MyState {
	@Override
	public CardType JudgeType(CardState cardstate) {
		// TODO Auto-generated method stub
		int len=cardstate.CardSize();
		if(len==4 &&((cardstate.GetCardList().get(0).getValue()==cardstate.GetCardList().get(len-2).getValue())||
				cardstate.GetCardList().get(1).getValue()==cardstate.GetCardList().get(len-1).getValue()))
		return CardType.c31;
		else cardstate.SetCardState(new ThreeWithTwoState());
		return cardstate.JudgeType();
	}
}
