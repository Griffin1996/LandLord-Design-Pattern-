package Strategy;

import java.util.List;

import Common.Judge;
import FlyweightFactory.Card;
import Game.GameUI;
import Game.Model;
import Iterator.ConcreteAggregate;
import State.CardType;

public class Context {
	GameUI main;
	private Strategy strategy;

	public Context(GameUI main,CardType cardtype,Model model,List<Card> player,List<String> list,int role){
		this.main=main; 
		//如果是单牌
		if(cardtype==CardType.c1)
		{
			PlayStrategyOne strategy1=new PlayStrategyOne(model.a1, player, list, role,main.dizhuFlag);
			strategy=strategy1;
		}
		
		//如果是对子
		else if(cardtype==CardType.c2)
		{
			PlayStrategyOne strategy1=new PlayStrategyOne(model.a2, player, list, role,main.dizhuFlag);
			strategy=strategy1;
		}
		//3带
		
		else if(cardtype==CardType.c3)
		{
			PlayStrategyOne strategy1=new PlayStrategyOne(model.a2, player, list, role,main.dizhuFlag);
			strategy=strategy1;
		}//炸弹
		else if(cardtype==CardType.c4)
		{
			PlayStrategyOne strategy1=new PlayStrategyOne(model.a2, player, list, role,main.dizhuFlag);
			strategy=strategy1;
		}//如果是3带1
		
		else if(cardtype==CardType.c31){
			 //偏家 涉及到拆牌
			//if((role+1)%3==main.dizhuFlag)
			PlayStrategyTwo strategy2=new PlayStrategyTwo(model.a3, model.a1, player, list, role);
			strategy=strategy2;
		}//如果是3带2
		
		else if(cardtype==CardType.c32){
			 //偏家
			//if((role+1)%3==main.dizhuFlag)
			PlayStrategyTwo strategy2=new PlayStrategyTwo(model.a3, model.a1, player, list, role);
			strategy=strategy2;
		}//如果是4带11
		
		else if(cardtype==CardType.c411){
			PlayStrategyFive strategy5 =new PlayStrategyFive(model.a4, model.a1, player, list, role);
			strategy=strategy5;
		}
		//如果是4带22
		else if(cardtype==CardType.c422){
			PlayStrategyFive strategy5 =new PlayStrategyFive(model.a4, model.a2, player, list, role);
			strategy=strategy5;
		}
		
		//顺子
		else if(cardtype==CardType.c123){
			PlayStrategyThree strategy3 =new PlayStrategyThree(model.a123, player, list, role);
			strategy=strategy3;
		}
		//双顺
		else if(cardtype==CardType.c1122){
			PlayStrategyThree strategy3 =new PlayStrategyThree(model.a112233, player, list, role);
			strategy=strategy3;
		}
		
		//飞机带单
		else if(cardtype==CardType.c11122234){
			PlayStrategyFour strategy4 =new PlayStrategyFour(model.a111222,model.a1, player, list, role);
			strategy=strategy4;
		}
		//飞机带对
		else if(cardtype==CardType.c1112223344){
			PlayStrategyFour strategy4 =new PlayStrategyFour(model.a111222,model.a2, player, list, role);
			strategy=strategy4;
		}
		
	}

}
