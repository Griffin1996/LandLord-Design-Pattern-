package State;
public class TriadCardState extends MyState{
	public CardType JudgeType(CardState cardstate) {
	int len=cardstate.CardSize();
	if(len==3&&cardstate.GetCardList().get(0).getValue()==cardstate.GetCardList().get(len-1).getValue() )
		return CardType.c3;
	else cardstate.SetCardState(new BombState());
	return cardstate.JudgeType();
	}
}
