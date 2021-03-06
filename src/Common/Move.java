package Common;
import java.awt.Point;

import FlyweightFactory.Card;
public class Move {
	//移动效果的函数,用于发牌
	public static void move(Card card,Point from,Point to){
		if(to.x!=from.x){
			double k=(1.0)*(to.y-from.y)/(to.x-from.x);
			double b=to.y-to.x*k;
			int flag=0;//判断向左还是向右移动步幅
			if(from.x<to.x)
				flag=20;
			else {
				flag=-20;
			}
			for(int i=from.x;Math.abs(i-to.x)>20;i+=flag)
			{
				double y=k*i+b;//这里主要用的数学中的线性函数
			
				card.setLocation(i,(int)y);
				try {
					Thread.sleep(5); //延迟，可自己设置
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//位置校准
		card.setLocation(to);
	}
	
	
}
