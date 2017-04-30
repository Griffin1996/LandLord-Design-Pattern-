package Game;

import java.util.ArrayList;
import java.util.List;

import FlyweightFactory.Card;
import Iterator.ConcreteAggregate;
import Shuffle.GetBoomb;
import Shuffle.GetPair;
import Shuffle.GetPlane;
import Shuffle.GetSingle;
import Shuffle.GetStraight;
import Shuffle.GetStraightPair;
import Shuffle.GetTriad;
import Strategy.Context;

public class Model {
	//һ����
	int value; //Ȩֵ
	int num;// ���� (�����ܹ����꣬û�е��������)
	public List<String> a1=new ArrayList<String>(); //����
	public List<String> a2=new ArrayList<String>(); //����
	public List<String> a3=new ArrayList<String>(); //3��
	public List<String> a123=new ArrayList<String>(); //����
	public List<String> a112233=new ArrayList<String>(); //����
	public List<String> a111222=new ArrayList<String>(); //�ɻ�
	public List<String> a4=new ArrayList<String>(); //ը��
	//����
	public static Model getModel(ConcreteAggregate<Card> concreteAggregate){
		//�ȸ���һ��list
		Context context ;
		ArrayList<Card> list2=new ArrayList<Card>(concreteAggregate.getItem());
		Model model=new Model();		
		//------�Ȳ�ը��
		GetBoomb.getBoomb(list2, model); //ok
		//------��3��
		GetTriad.getThree(list2, model);
		//��ɻ�
		GetPlane.getPlane(list2, model);
		//------�����
		GetPair.getTwo(list2, model);
		//������
		GetStraightPair.getTwoTwo(list2, model);
		//��˳��
		GetStraight.get123(list2, model);
		//��
		GetSingle.getSingle(list2, model);		
		return model;
	}

}
