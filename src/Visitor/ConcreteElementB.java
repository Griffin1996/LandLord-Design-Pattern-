package Visitor;

import Common.Position;
import FlyweightFactory.Player;
import Game.GameUI;

//具体元素的实现对象
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
		//已有的功能实现 
		Position.rePosition(main,player);//重新定位
		}
}
	

