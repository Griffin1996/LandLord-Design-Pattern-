package Common;
import java.util.List;

import FlyweightFactory.Card;
public class HideCards {

	//����֮ǰ��������
	public static void hideCards(List<Card> list){
		for(int i=0,len=list.size();i<len;i++){
			list.get(i).setVisible(false);
		}
	}
}
