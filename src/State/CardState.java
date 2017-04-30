package State;
import FlyweightFactory.Card;

import java.util.ArrayList;
public class CardState {
	private MyState cardstate;
	private ArrayList<Card> list;
	public  CardType JudgeType(){
		return cardstate.JudgeType(this);
	}
	public CardState(){
		this.cardstate=new SingleCardState();
	}
	public CardState(MyState state){
		this.cardstate=state;
	}
	public  MyState GetState(){
		return cardstate;
	}
	public int CardSize(){
		return list.size();
	}
	public void SetCardState(MyState cardstate){
		this.cardstate=cardstate;
	}
	public void SetCardList(ArrayList<Card> card){
		this.list=card;
	}
	public ArrayList<Card> GetCardList(){
		return list;
	}	
}

