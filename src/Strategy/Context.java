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
		//����ǵ���
		if(cardtype==CardType.c1)
		{
			PlayStrategyOne strategy1=new PlayStrategyOne(model.a1, player, list, role,main.dizhuFlag);
			strategy=strategy1;
		}
		
		//����Ƕ���
		else if(cardtype==CardType.c2)
		{
			PlayStrategyOne strategy1=new PlayStrategyOne(model.a2, player, list, role,main.dizhuFlag);
			strategy=strategy1;
		}
		//3��
		
		else if(cardtype==CardType.c3)
		{
			PlayStrategyOne strategy1=new PlayStrategyOne(model.a2, player, list, role,main.dizhuFlag);
			strategy=strategy1;
		}//ը��
		else if(cardtype==CardType.c4)
		{
			PlayStrategyOne strategy1=new PlayStrategyOne(model.a2, player, list, role,main.dizhuFlag);
			strategy=strategy1;
		}//�����3��1
		
		else if(cardtype==CardType.c31){
			 //ƫ�� �漰������
			//if((role+1)%3==main.dizhuFlag)
			PlayStrategyTwo strategy2=new PlayStrategyTwo(model.a3, model.a1, player, list, role);
			strategy=strategy2;
		}//�����3��2
		
		else if(cardtype==CardType.c32){
			 //ƫ��
			//if((role+1)%3==main.dizhuFlag)
			PlayStrategyTwo strategy2=new PlayStrategyTwo(model.a3, model.a1, player, list, role);
			strategy=strategy2;
		}//�����4��11
		
		else if(cardtype==CardType.c411){
			PlayStrategyFive strategy5 =new PlayStrategyFive(model.a4, model.a1, player, list, role);
			strategy=strategy5;
		}
		//�����4��22
		else if(cardtype==CardType.c422){
			PlayStrategyFive strategy5 =new PlayStrategyFive(model.a4, model.a2, player, list, role);
			strategy=strategy5;
		}
		
		//˳��
		else if(cardtype==CardType.c123){
			PlayStrategyThree strategy3 =new PlayStrategyThree(model.a123, player, list, role);
			strategy=strategy3;
		}
		//˫˳
		else if(cardtype==CardType.c1122){
			PlayStrategyThree strategy3 =new PlayStrategyThree(model.a112233, player, list, role);
			strategy=strategy3;
		}
		
		//�ɻ�����
		else if(cardtype==CardType.c11122234){
			PlayStrategyFour strategy4 =new PlayStrategyFour(model.a111222,model.a1, player, list, role);
			strategy=strategy4;
		}
		//�ɻ�����
		else if(cardtype==CardType.c1112223344){
			PlayStrategyFour strategy4 =new PlayStrategyFour(model.a111222,model.a2, player, list, role);
			strategy=strategy4;
		}
		
	}

}
