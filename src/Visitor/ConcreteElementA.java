package Visitor;

import FlyweightFactory.Player;

//����Ԫ�ص�ʵ�ֶ��� 
public class ConcreteElementA extends Element { 
	private Player player;
	public ConcreteElementA(Player player){
		this.player=player;
	}
		
	public void accept(Visitor visitor) { 
		//�ص������߶������Ӧ���� 
		visitor.visitConcreteElementA(this);
		} 
	//ʾ����������ʾԪ�����еĹ���ʵ�� 
	public void opertionA(){ //���еĹ���ʵ�� 
		player.Order();
	} 
}


