package Common;
import java.awt.Point;

import FlyweightFactory.Card;
public class Move {
	//�ƶ�Ч���ĺ���,���ڷ���
	public static void move(Card card,Point from,Point to){
		if(to.x!=from.x){
			double k=(1.0)*(to.y-from.y)/(to.x-from.x);
			double b=to.y-to.x*k;
			int flag=0;//�ж������������ƶ�����
			if(from.x<to.x)
				flag=20;
			else {
				flag=-20;
			}
			for(int i=from.x;Math.abs(i-to.x)>20;i+=flag)
			{
				double y=k*i+b;//������Ҫ�õ���ѧ�е����Ժ���
			
				card.setLocation(i,(int)y);
				try {
					Thread.sleep(5); //�ӳ٣����Լ�����
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//λ��У׼
		card.setLocation(to);
	}
	
	
}