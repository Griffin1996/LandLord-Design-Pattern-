package Visitor;

import FlyweightFactory.Player;

//具体元素的实现对象 
public class ConcreteElementA extends Element { 
	private Player player;
	public ConcreteElementA(Player player){
		this.player=player;
	}
		
	public void accept(Visitor visitor) { 
		//回调访问者对象的相应方法 
		visitor.visitConcreteElementA(this);
		} 
	//示例方法，表示元素已有的功能实现 
	public void opertionA(){ //已有的功能实现 
		player.Order();
	} 
}


