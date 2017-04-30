package FlyweightFactory;

import java.util.Hashtable;
public class CardFlyweightFactory {
	//��Ԫģʽ CardFlyWeightsά��һ��ӳ���,��¼����ֽ���Ƿ���hashtable��,����ֱ�ӷ���,�����´���һ��ֽ��
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Hashtable<String,Card> CardFlyWeights = new Hashtable();
	public static Card CreateCard(String name){
		if(!CardFlyWeights.containsKey(name))
			CardFlyWeights.put(name,new Card(name));
		return (Card)CardFlyWeights.get(name);
	}
}
