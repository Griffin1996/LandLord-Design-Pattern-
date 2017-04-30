package FlyweightFactory;

import java.util.Hashtable;
public class CardFlyweightFactory {
	//享元模式 CardFlyWeights维持一张映射表,记录所需纸牌是否在hashtable中,在则直接返回,否则新创建一个纸牌
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Hashtable<String,Card> CardFlyWeights = new Hashtable();
	public static Card CreateCard(String name){
		if(!CardFlyWeights.containsKey(name))
			CardFlyWeights.put(name,new Card(name));
		return (Card)CardFlyWeights.get(name);
	}
}
