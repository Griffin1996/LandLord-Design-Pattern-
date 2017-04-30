package FlyweightFactory;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Iterator.ConcreteAggregate;

public class Player implements PlayerProduct ,Cloneable{	
	public ConcreteAggregate<Card> PlayerCard = new ConcreteAggregate<Card>();
	@SuppressWarnings("unused")
	private boolean islandlord=false;
	private int number;
	public String str="Player";
	public Player(int i){
		islandlord=false;
		number=i;
	}
	
	public int GetCardSize(){
		return PlayerCard.length();
	}
	
	public void AddPlayerCard(Card c){
		PlayerCard.add(c);
	}
	
	public void SetLandLord(){
		islandlord=true;
	}
	
	public int GetNumber(){
		return number;
	}
	
	public void SetCard(ConcreteAggregate<Card> PlayerCard){
		this.PlayerCard=PlayerCard;
	}
	
	 @SuppressWarnings("unchecked")
	public Object clone() throws CloneNotSupportedException {
		 	Player o = (Player) super.clone();
		 	o.PlayerCard=(ConcreteAggregate<Card>) this.PlayerCard.clone();

		 	return o;
	 }
	 
	 @SuppressWarnings("static-access")
	public void Order (){
		 this.order(this.PlayerCard);
	 }
	//对list排序
		public static void order( ConcreteAggregate<Card> c){	
			List<Card> playerList =  c.getItem();
			Collections.sort(playerList,new Comparator<Card>() {
				@Override
				public int compare(Card o1, Card o2) {
					// TODO Auto-generated method stub
					int a1=Integer.parseInt(o1.name.substring(0, 1));//花色
					int a2=Integer.parseInt(o2.name.substring(0,1));
					int b1=Integer.parseInt(o1.name.substring(2,o1.name.length()));//数值
					int b2=Integer.parseInt(o2.name.substring(2,o2.name.length()));
					int flag=0;
					//如果是王的话
					if(a1==5) b1+=100;
					if(a1==5&&b1==1) b1+=50;
					if(a2==5) b2+=100;
					if(a2==5&&b2==1) b2+=50;
					//如果是A或者2
					if(b1==1) b1+=20;
					if(b2==1) b2+=20;
					if(b1==2) b1+=30;
					if(b2==2) b2+=30;
					flag=b2-b1;
					if(flag==0)
						return a2-a1;
					else {
						return flag;
					}
				}
			});
		}
	public ConcreteAggregate<Card> GetPlayerCards(){
		return PlayerCard;
	}
}
