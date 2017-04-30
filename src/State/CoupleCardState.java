package State;
public class CoupleCardState extends MyState {	
	public CardType JudgeType(CardState cardstate) {
		int len=cardstate.CardSize();
		if(len==2 &&cardstate.GetCardList().get(0).getValue()==cardstate.GetCardList().get(len-1).getValue()
			|| (len==2&&cardstate.GetCardList().get(1).getColor()==5)
				)
			return CardType.c2;
		else cardstate.SetCardState(new TriadCardState());
		return cardstate.JudgeType();
	}
}
