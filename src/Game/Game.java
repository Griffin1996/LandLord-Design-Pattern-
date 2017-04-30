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
			main.time[1].setText("����ʱ:" + i--);
			second(1);// ��һ��
		}
		if (i == -1)// �����սᣬ˵����ʱ
		main.time[1].setText("����");
		main.landlord[0].setVisible(false);
		main.landlord[1].setVisible(false);		
		//������ģʽ
		ConcreteIterator <Card> it = new ConcreteIterator<Card> (main.players[1].GetPlayerCards());
		while(it.hasNext())
		{	
			it.Next().SetCanClick();
			//��������
		}	
		// ����Լ���������
		//�۲���ģʽ
		if (main.time[1].getText().equals("������")) {
			// �õ�������
			main.players[1].GetPlayerCards().addAll(main.lordList); 
			openlord(true);
			second(2);// �ȴ�����
			main.players[1].Order();//
			Position.rePosition(main, main.players[1]);
			setlord(1);
			} 
		else {
			// ����ѡ����		
			if (GetLandLord.getScore(main.players[0].GetPlayerCards()) < GetLandLord
					.getScore(main.players[2].GetPlayerCards())) {
				main.time[2].setText("������");
				main.time[2].setVisible(true);
				setlord(2);// �趨����
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
				main.time[0].setText("������");
				main.time[0].setVisible(true);
				setlord(0);// �趨����
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
		// ѡ������� �رյ�����ť
		main.landlord[0].setVisible(false);
		main.landlord[1].setVisible(false);
		turnOn(false);
		for (int i = 0; i < 3; i++)
		{
			main.time[i].setText("��Ҫ");
			main.time[i].setVisible(false);
		}
		// ��ʼ��Ϸ ���ݵ�����ͬ˳��ͬ
		main.turn=main.dizhuFlag;
		while (true) {			
			if(main.turn==1) //��
			{				
				turnOn(true);// ���ư�ť --�ҳ���
				timeWait(30, 1);// ���Լ��Ķ�ʱ��
				turnOn(false);//ѡ��ر�
				main.turn=(main.turn+1)%3;
				if(judge.IsWin(main))//�ж���Ӯ
					break;
			}
			if (main.turn==0) 
			{
				computer0();
				main.turn=(main.turn+1)%3;
				if(judge.IsWin(main))//�ж���Ӯ
					break;
			}
			if(main.turn==2)
			{
				computer2();
				main.turn=(main.turn+1)%3;
				if(judge.IsWin(main))//�ж���Ӯ
					break;
			}
		}
	}
	// �ȴ�i��
	public void second(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// �����Ʒ���
	public void openlord(boolean is) {
		for (int i = 0; i < 3; i++) {
			if (is)
				main.lordList.get(i).turnFront(); // �����Ʒ���
			else {
				main.lordList.get(i).turnRear(); // �����Ʊպ�
			}
			main.lordList.get(i).SetCanClick() ;// �ɱ����
		}
	}

	// �趨����
	public void setlord(int i) {
		Point point = new Point();
		if (i == 1)// ���ǵ���
		{
			point.x = 80;
			point.y = 430;
			main.dizhuFlag = 1;// �趨����
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

	// �򿪳��ư�ť
	public void turnOn(boolean flag) {
		main.publishCard[0].setVisible(flag);
		main.publishCard[1].setVisible(flag);
		main.publishCard[2].setVisible(flag);
	}

	// ����0����(�Ҵ���1)
	public void computer0() {
		timeWait(1, 0); // ��ʱ
		ShowCard(0); // ����
		
	}

	// ����2����(�Ҵ���1)
	public void computer2() {
		timeWait(1, 2); // ��ʱ
		ShowCard(2); // ����
		
	}

	// ����
	public void ShowCard(int role) {
		Model model =Model.getModel(main.players[role].GetPlayerCards());
		// ���ߵ���
		ArrayList<String> list = new ArrayList();
		// �������������
		if (main.time[(role + 1) % 3].getText().equals("��Ҫ")
				&& main.time[(role + 2) % 3].getText().equals("��Ҫ")) {
			// �е����� (����3�����ɻ��ܴ��ĵ���)
			if (model.a1.size() > (model.a111222.size() * 2 + model.a3.size())) {
				list.add(model.a1.get(model.a1.size() - 1));
			}// �ж��ӳ����� (����3�����ɻ�)
			else if (model.a2.size() > (model.a111222.size() * 2 + model.a3
					.size())) {
				list.add(model.a2.get(model.a2.size() - 1));
			}// ��˳�ӳ�˳��
			else if (model.a123.size() > 0) {
				list.add(model.a123.get(model.a123.size() - 1));
			}// ��3���ͳ�3����û�оͳ���3
			else if (model.a3.size() > 0) {
				// 3����,�ҷǹؼ�ʱ�̲��ܴ�����2
				if (model.a1.size() > 0) {
					list.add(model.a1.get(model.a1.size() - 1));
				}// 3����
				else if (model.a2.size() > 0) {
					list.add(model.a2.get(model.a2.size() - 1));
				}
				list.add(model.a3.get(model.a3.size() - 1));
			}// ��˫˳��˫˳
			else if (model.a112233.size() > 0) {
				list.add(model.a112233.get(model.a112233.size() - 1));
			}// �зɻ����ɻ�
			else if (model.a111222.size() > 0) {
				String name[] = model.a111222.get(0).split(",");
				// ����
				if (name.length / 3 <= model.a1.size()) {
					list.add(model.a111222.get(model.a111222.size() - 1));
					for (int i = 0; i < name.length / 3; i++)
						list.add(model.a1.get(i));
				} else if (name.length / 3 <= model.a2.size())// ��˫
				{
					list.add(model.a111222.get(model.a111222.size() - 1));
					for (int i = 0; i < name.length / 3; i++)
						list.add(model.a2.get(i));
				}
				// ��ը����ը��
			} else if (model.a4.size() > 0) {
				// 4��2,1
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
					
				} else {// ֱ��ը
					list.add(model.a4.get(0));
					
				}

			}
		}// ����Ǹ���
		
		else {
			
			ArrayList<Card> player = main.currentList[(role + 2) % 3].size() > 0 
					? main.currentList[(role + 2) % 3]
					: main.currentList[(role + 1) % 3];
		
			//����ģʽ			
			Context context = new Context(main,Judge.jugdeType(player),model,player,list,role);
			
			//ը��
			if(list.size()==0)
			{
				int len4=model.a4.size();
				if(len4>0)
					list.add(model.a4.get(len4-1));
			}
		}

		// ��λ����
		main.currentList[role].clear();
		if (list.size() > 0) {
			Point point = new Point();
			if (role == 0)
				point.x = 200;
			if (role == 2)
				point.x = 550;
			point.y = (400 / 2) - (list.size() + 1) * 15 / 2;// ��Ļ�в�
			// ��nameת����Card		
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
			main.time[role].setText("��Ҫ");
		}
		for(Card card:main.currentList[role])
			card.turnFront();
	}

	// ��name���Card�������Modelȡ��
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
	// ��ʱ��ģ��ʱ��
	public void timeWait(int n, int player) {
		//
		
		if (main.currentList[player].size() > 0)
			HideCards.hideCards(main.currentList[player]);
		
		if (player == 1)// ������ң�10�뵽��ֱ����һ�ҳ���
		{
			int i = n;

			while (main.nextPlayer == false && i >= 0) {
				main.time[player].setText("����ʱ:" + i);
				main.time[player].setVisible(true);
				second(1);
				i--;
			}
			if (i == -1) {
				main.time[player].setText("��ʱ");
			}
			main.nextPlayer = false;
		} else {
			for (int i = n; i >= 0; i--) {
				second(1);
				// main.container.setComponentZOrder(main.time[player], 0);
				main.time[player].setText("����ʱ:" + i);
				main.time[player].setVisible(true);
			}
		}
		main.time[player].setVisible(false);
	}
	
	//ͨ��name��ֵ
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
