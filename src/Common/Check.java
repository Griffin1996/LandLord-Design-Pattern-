package Common;
import java.util.ArrayList;
import java.util.List;

import FlyweightFactory.Card;
import State.CardType;
public class Check {
	//����Ƶ��Ƿ��ܳ�
	public static int checkCards(ArrayList<Card> c,ArrayList<Card>[] current){
		//�ҳ���ǰ���������ĸ����Գ���,c�ǵ�ѡ����
		ArrayList<Card> currentlist=(current[0].size()>0)?current[0]:current[2];
		CardType cType=Judge.jugdeType(c);
		//���������ֱͬ�ӹ���
		if(cType!=CardType.c4&&c.size()!=currentlist.size())
			return 0;
		//�Ƚ��ҵĳ�������
		if(Judge.jugdeType(c)!=Judge.jugdeType(currentlist))
		{
			
			return 0;
		}
		//�Ƚϳ������Ƿ�Ҫ��
		//��ը��
		if(cType==CardType.c4)
		{
			if(c.size()==2)
				return 1;
			if(currentlist.size()==2)
				return 0;
		}
		//����,����,3��,4ը��
		if(cType==CardType.c1||cType==CardType.c2||cType==CardType.c3||cType==CardType.c4){
			if(c.get(0).getValue()<=currentlist.get(0).getValue())
			{
				 return 0;
			}else {
				return 1;
			}
		}
		//˳��,���ӣ��ɻ���
		if(cType==CardType.c123||cType==CardType.c1122||cType==CardType.c111222)
		{
			if(c.get(0).getValue()<=currentlist.get(0).getValue())
				return 0;
			else 
				return 1;
		}
		//���ظ���������
		//3��1,3��2 ,�ɻ�������˫,4��1,2,ֻ��Ƚϵ�һ�����У���һ�޶��� 
		if(cType==CardType.c31||cType==CardType.c32||cType==CardType.c411||cType==CardType.c422
				||cType==CardType.c11122234||cType==CardType.c1112223344){
			List<Card> a1=Order.getOrder(c); //�ҳ�����
			List<Card> a2=Order.getOrder(currentlist);//��ǰ�����
			if(a1.get(0).getValue()<a2.get(0).getValue())
				return 0;
		}
		return 1;
	}
}
