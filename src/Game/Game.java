package Game;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import Adapter.Adapter;
import Adapter.JudgeIsWin;
import Adapter.JudgeWin;
import Adapter.Target;
import Common.GetLandLord;
import Common.HideCards;
import Common.Judge;
import Common.Move;
import Common.Order;
import Common.Position;
import FlyweightFactory.*;
import Iterator.ConcreteAggregate;
import Iterator.ConcreteIterator;
import Strategy.Context;
public class Game extends Thread {
	GameUI main;
	
	JudgeIsWin iswin = new JudgeIsWin();
	

	Adapter judge =new  Adapter(iswin);
	
	private boolean isRun = true;
	int i = 10;
	public void SetRun(boolean state){
		isRun=state;
	}
	public Game(GameUI m, int i) {
		this.main = m;
		this.i = i;
	}
	@Override
	public void run() {
		while (i > -1 && isRun) {
			main.time[1].setText("倒计时:" + i--);
			second(1);// 等一秒
		}
		if (i == -1)// 正常终结，说明超时
		main.time[1].setText("不抢");
		main.landlord[0].setVisible(false);
		main.landlord[1].setVisible(false);		
		//迭代器模式
		ConcreteIterator <Card> it = new ConcreteIterator<Card> (main.players[1].GetPlayerCards());
		while(it.hasNext())
		{	
			it.Next().SetCanClick();
			//迭代遍历
		}	
		// 如果自己抢到地主
		//观察者模式
		if (main.time[1].getText().equals("抢地主")) {
			// 得到地主牌
			main.players[1].GetPlayerCards().addAll(main.lordList); 
			openlord(true);
			second(2);// 等待五秒
			main.players[1].Order();//
			Position.rePosition(main, main.players[1]);
			setlord(1);
			} 
		else {
			// 电脑选地主		
			if (GetLandLord.getScore(main.players[0].GetPlayerCards()) < GetLandLord
					.getScore(main.players[2].GetPlayerCards())) {
				main.time[2].setText("抢地主");
				main.time[2].setVisible(true);
				setlord(2);// 设定地主
				openlord(true);
				second(3);
				main.lordList.get(0).turnRear();
				main.lordList.get(1).turnRear();
				main.lordList.get(2).turnRear();
				main.players[2].GetPlayerCards().addAll(main.lordList);			
				main.players[2].Order();
				Position.rePosition(main, main.players[2]);
				openlord(false);

			} else {
				main.time[0].setText("抢地主");
				main.time[0].setVisible(true);
				setlord(0);// 设定地主
				openlord(true);
				second(3);
				main.lordList.get(0).turnRear();
				main.lordList.get(1).turnRear();
				main.lordList.get(2).turnRear();
				main.players[0].GetPlayerCards().addAll(main.lordList);				
				main.players[0].Order();
				main.players[0].Order();
				Position.rePosition(main, main.players[0]);
			}			
		}
		// 选完地主后 关闭地主按钮
		main.landlord[0].setVisible(false);
		main.landlord[1].setVisible(false);
		turnOn(false);
		for (int i = 0; i < 3; i++)
		{
			main.time[i].setText("不要");
			main.time[i].setVisible(false);
		}
		// 开始游戏 根据地主不同顺序不同
		main.turn=main.dizhuFlag;
		while (true) {			
			if(main.turn==1) //我
			{				
				turnOn(true);// 出牌按钮 --我出牌
				timeWait(30, 1);// 我自己的定时器
				turnOn(false);//选完关闭
				main.turn=(main.turn+1)%3;
				if(judge.IsWin(main))//判断输赢
					break;
			}
			if (main.turn==0) 
			{
				computer0();
				main.turn=(main.turn+1)%3;
				if(judge.IsWin(main))//判断输赢
					break;
			}
			if(main.turn==2)
			{
				computer2();
				main.turn=(main.turn+1)%3;
				if(judge.IsWin(main))//判断输赢
					break;
			}
		}
	}
	// 等待i秒
	public void second(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 地主牌翻看
	public void openlord(boolean is) {
		for (int i = 0; i < 3; i++) {
			if (is)
				main.lordList.get(i).turnFront(); // 地主牌翻看
			else {
				main.lordList.get(i).turnRear(); // 地主牌闭合
			}
			main.lordList.get(i).SetCanClick() ;// 可被点击
		}
	}

	// 设定地主
	public void setlord(int i) {
		Point point = new Point();
		if (i == 1)// 我是地主
		{
			point.x = 80;
			point.y = 430;
			main.dizhuFlag = 1;// 设定地主
		}
		if (i == 0) {
			point.x = 80;
			point.y = 20;
			main.dizhuFlag = 0;
		}
		if (i == 2) {
			point.x = 700;
			point.y = 20;
			main.dizhuFlag = 2;
		}
		main.dizhu.setLocation(point);
		main.dizhu.setVisible(true);
	}

	// 打开出牌按钮
	public void turnOn(boolean flag) {
		main.publishCard[0].setVisible(flag);
		main.publishCard[1].setVisible(flag);
		main.publishCard[2].setVisible(flag);
	}

	// 电脑0走牌(我代表1)
	public void computer0() {
		timeWait(1, 0); // 定时
		ShowCard(0); // 出牌
		
	}

	// 电脑2走牌(我代表1)
	public void computer2() {
		timeWait(1, 2); // 定时
		ShowCard(2); // 出牌
		
	}

	// 走牌
	public void ShowCard(int role) {
		Model model =Model.getModel(main.players[role].GetPlayerCards());
		// 待走的牌
		ArrayList<String> list = new ArrayList();
		// 如果是主动出牌
		if (main.time[(role + 1) % 3].getText().equals("不要")
				&& main.time[(role + 2) % 3].getText().equals("不要")) {
			// 有单出单 (除开3带，飞机能带的单牌)
			if (model.a1.size() > (model.a111222.size() * 2 + model.a3.size())) {
				list.add(model.a1.get(model.a1.size() - 1));
			}// 有对子出对子 (除开3带，飞机)
			else if (model.a2.size() > (model.a111222.size() * 2 + model.a3
					.size())) {
				list.add(model.a2.get(model.a2.size() - 1));
			}// 有顺子出顺子
			else if (model.a123.size() > 0) {
				list.add(model.a123.get(model.a123.size() - 1));
			}// 有3带就出3带，没有就出光3
			else if (model.a3.size() > 0) {
				// 3带单,且非关键时刻不能带王，2
				if (model.a1.size() > 0) {
					list.add(model.a1.get(model.a1.size() - 1));
				}// 3带对
				else if (model.a2.size() > 0) {
					list.add(model.a2.get(model.a2.size() - 1));
				}
				list.add(model.a3.get(model.a3.size() - 1));
			}// 有双顺出双顺
			else if (model.a112233.size() > 0) {
				list.add(model.a112233.get(model.a112233.size() - 1));
			}// 有飞机出飞机
			else if (model.a111222.size() > 0) {
				String name[] = model.a111222.get(0).split(",");
				// 带单
				if (name.length / 3 <= model.a1.size()) {
					list.add(model.a111222.get(model.a111222.size() - 1));
					for (int i = 0; i < name.length / 3; i++)
						list.add(model.a1.get(i));
				} else if (name.length / 3 <= model.a2.size())// 带双
				{
					list.add(model.a111222.get(model.a111222.size() - 1));
					for (int i = 0; i < name.length / 3; i++)
						list.add(model.a2.get(i));
				}
				// 有炸弹出炸弹
			} else if (model.a4.size() > 0) {
				// 4带2,1
				int sizea1 = model.a1.size();
				int sizea2 = model.a2.size();
				if (sizea1 >= 2) {
					list.add(model.a1.get(sizea1 - 1));
					list.add(model.a1.get(sizea1 - 2));
					list.add(model.a4.get(0));
				
				} else if (sizea2 >= 2) {
					list.add(model.a2.get(sizea1 - 1));
					list.add(model.a2.get(sizea1 - 2));
					list.add(model.a4.get(0));
					
				} else {// 直接炸
					list.add(model.a4.get(0));
					
				}

			}
		}// 如果是跟牌
		
		else {
			
			ArrayList<Card> player = main.currentList[(role + 2) % 3].size() > 0 
					? main.currentList[(role + 2) % 3]
					: main.currentList[(role + 1) % 3];
		
			//策略模式			
			Context context = new Context(main,Judge.jugdeType(player),model,player,list,role);
			
			//炸弹
			if(list.size()==0)
			{
				int len4=model.a4.size();
				if(len4>0)
					list.add(model.a4.get(len4-1));
			}
		}

		// 定位出牌
		main.currentList[role].clear();
		if (list.size() > 0) {
			Point point = new Point();
			if (role == 0)
				point.x = 200;
			if (role == 2)
				point.x = 550;
			point.y = (400 / 2) - (list.size() + 1) * 15 / 2;// 屏幕中部
			// 将name转换成Card		
			for (int i = 0, len = list.size(); i < len; i++) {
				ArrayList<Card> cards = (ArrayList<Card>) getCardByName(main.players[role].GetPlayerCards(),
						list.get(i));
				for (Card card : cards) {
					Move.move(card, card.getLocation(), point);
					point.y += 15;
					main.currentList[role].add(card);
					main.players[role].GetPlayerCards().remove(card);
				}
			}	
			Position.rePosition(main, main.players[role]);
		} else {
			main.time[role].setVisible(true);
			main.time[role].setText("不要");
		}
		for(Card card:main.currentList[role])
			card.turnFront();
	}

	// 按name获得Card，方便从Model取出
	public List getCardByName(ConcreteAggregate<Card> concreteAggregate, String n) {
		String[] name = n.split(",");
		List cardsList = new ArrayList<Card>();
		int j = 0;
		for (int i = 0, len = concreteAggregate.length(); i < len; i++) {
			if (j < name.length && concreteAggregate.get(i).name.equals(name[j])) {
				cardsList.add(concreteAggregate.get(i));
				i = 0;
				j++;
			}
		}
		return cardsList;
	}
	// 延时，模拟时钟
	public void timeWait(int n, int player) {
		//
		
		if (main.currentList[player].size() > 0)
			HideCards.hideCards(main.currentList[player]);
		
		if (player == 1)// 如果是我，10秒到后直接下一家出牌
		{
			int i = n;

			while (main.nextPlayer == false && i >= 0) {
				main.time[player].setText("倒计时:" + i);
				main.time[player].setVisible(true);
				second(1);
				i--;
			}
			if (i == -1) {
				main.time[player].setText("超时");
			}
			main.nextPlayer = false;
		} else {
			for (int i = n; i >= 0; i--) {
				second(1);
				// main.container.setComponentZOrder(main.time[player], 0);
				main.time[player].setText("倒计时:" + i);
				main.time[player].setVisible(true);
			}
		}
		main.time[player].setVisible(false);
	}
	
	//通过name估值
	public  int getValueInt(String n){
		String name[]=n.split(",");
		String s=name[0];
		int i=Integer.parseInt(s.substring(2, s.length()));
		if(s.substring(0, 1).equals("5"))
			i+=3;
		if(s.substring(2, s.length()).equals("1")||s.substring(2, s.length()).equals("2"))
			i+=13;
		return i;
	}	

}
