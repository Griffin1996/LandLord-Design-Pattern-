package Visitor;

import Common.Position;
import FlyweightFactory.Player;
import Game.GameUI;

//����Ԫ�ص�ʵ�ֶ���
public class ConcreteElementB extends Element {
	private Player player;
	GameUI main;
	public ConcreteElementB(GameUI main,Player player){
	this.main=main;
	this.player=player;
	}
	public void accept(Visitor visitor) { 
		visitor.visitConcreteElementB(this); 
		} 
	public void opertionB(){ 
		//���еĹ���ʵ�� 
		Position.rePosition(main,player);//���¶�λ
		}
}
	

