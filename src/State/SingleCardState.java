package State;
public class SingleCardState extends MyState {
	
	public CardType JudgeType(CardState cardstate) {
		// TODO Auto-generated method stub
		
		if(cardstate.CardSize()==1)
		return CardType.c1;
		else cardstate.SetCardState(new CoupleCardState());
		return cardstate.JudgeType();
	}

}
