package Strategy;

import java.util.List;

import FlyweightFactory.Card;
import Game.Model;
import State.CardType;

public abstract class Strategy {
	public abstract void CardAlgorithmInterface(List<String> model,List<Card> player,List<String> list,int role,int dizhuFlag);
	public abstract int getValueInt(String n);
}
