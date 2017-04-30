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
	//一组牌
	int value; //权值
	int num;// 手数 (几次能够走完，没有挡的情况下)
	public List<String> a1=new ArrayList<String>(); //单张
	public List<String> a2=new ArrayList<String>(); //对子
	public List<String> a3=new ArrayList<String>(); //3带
	public List<String> a123=new ArrayList<String>(); //连子
	public List<String> a112233=new ArrayList<String>(); //连牌
	public List<String> a111222=new ArrayList<String>(); //飞机
	public List<String> a4=new ArrayList<String>(); //炸弹
	//拆牌
	public static Model getModel(ConcreteAggregate<Card> concreteAggregate){
		//先复制一个list
		Context context ;
		ArrayList<Card> list2=new ArrayList<Card>(concreteAggregate.getItem());
		Model model=new Model();		
		//------先拆炸弹
		GetBoomb.getBoomb(list2, model); //ok
		//------拆3带
		GetTriad.getThree(list2, model);
		//拆飞机
		GetPlane.getPlane(list2, model);
		//------拆对子
		GetPair.getTwo(list2, model);
		//拆连队
		GetStraightPair.getTwoTwo(list2, model);
		//拆顺子
		GetStraight.get123(list2, model);
		//拆单
		GetSingle.getSingle(list2, model);		
		return model;
	}

}
