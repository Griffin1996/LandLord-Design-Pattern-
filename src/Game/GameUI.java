package Game;
import Common.Check;
import Common.Judge;
import Common.Move;
import Common.Position;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import FlyweightFactory.*;
import Iterator.ConcreteIterator;
import Memeto.MementoCaretakerOne;
import Memeto.MementoCaretakerThree;
import Memeto.MementoCaretakerTwo;
import Memeto.Originator;
import Observer.ConcreteObserver;
import Observer.ConcreteSubject;
import State.CardType;
import Visitor.ConcreteElementA;
import Visitor.ConcreteElementB;
import Visitor.ConcreteVisitorA;
import Visitor.ConcreteVisitorB;
import Visitor.Element;
import Visitor.ObjectStructure;
import Visitor.Visitor;

public class GameUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private ImageIcon background;
	private JPanel imagePanel; 
	public Container container = null;// ��������
	JMenuItem start,exit, about;// ����˵���ť
	JButton landlord[]=new JButton[2];//��������ť
	JButton publishCard[]=new JButton[3];//���ư�ť
	public Originator o=new Originator();
	public Originator o2=new Originator();
	public Originator o3=new Originator();
	public MementoCaretakerOne mc=MementoCaretakerOne.getInstance();
	public MementoCaretakerTwo mc2=MementoCaretakerTwo.getInstance();
	public MementoCaretakerThree mc3=MementoCaretakerThree.getInstance();
	public int dizhuFlag;//������־
	public int huipai;
	int turn;
	JLabel dizhu; //����ͼ��
	@SuppressWarnings("unchecked")
	ArrayList<Card> currentList[] =new ArrayList[3]; //  ��ǰ�ĳ���
	public Player players[]=new Player[3];
	ArrayList<Card> lordList;//������
	Card card[] = new Card[56]; // ����54����
	JTextField time[]=new JTextField[3]; //��ʱ��
	Game t; //��ʱ�����̣߳�
	boolean nextPlayer=false; //ת����ɫ
	private static GameUI game;
	private GameUI(){
		Init();// ��ʼ��
		SetMenu();// �����˵� ��ť(������������,��ʱ��)
		this.setVisible(true);
		CardInit();//����
		getLord(); //�����ƿ�ʼ������
		time[1].setVisible(true);
		//�̰߳�ȫ��,�ѷ����̵߳�UI���Ʒŵ�����
		SwingUtilities.invokeLater(new MyTimer(this,10));	
	}
	// ��ʼ������
	public void Init() {
	
		this.setTitle("��������Ϸ---���ģʽ����ҵ-14281091");
		this.setSize(830, 620);
		background=new ImageIcon("images/background.jpg");
		 this.setSize(background.getIconWidth(),background.getIconHeight()); 	
		 JLabel label = new JLabel(background);// �ѱ���ͼƬ��ʾ��һ����ǩ����
		 label.setBounds(0, 0, background.getIconWidth(),
				    background.getIconHeight());
		// �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
		  imagePanel = (JPanel) this.getContentPane();
		  imagePanel.setOpaque(false);
		  // ���ݴ���Ĭ�ϵĲ��ֹ�����ΪBorderLayout
		  imagePanel.setLayout(new FlowLayout());
		  this.getLayeredPane().setLayout(null);
		  // �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����
		  this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  this.setSize(background.getIconWidth(), background.getIconHeight());
		  this.setResizable(false);
		  this.setVisible(true);	 	       
	       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	       this.setVisible(true);  
		setLocationRelativeTo(getOwner()); // ��Ļ����
		container = this.getContentPane();
		container.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container.setBackground(new Color(0, 112, 26)); // ����Ϊ��ɫ		
	}

	// �����˵� ���ܰ�ť
	public void SetMenu() {
		JMenuBar jMenuBar = new JMenuBar();
		JMenu game = new JMenu("��Ϸ");
		JMenu help = new JMenu("����");
		start = new JMenuItem("����Ϸ");
		exit = new JMenuItem("�˳�");
		about = new JMenuItem("����");	
		start.addActionListener(this);
		exit.addActionListener(this);
		about.addActionListener(this);
		game.add(start);
		game.add(exit);
		help.add(about);
		jMenuBar.add(game);
		jMenuBar.add(help);
		this.setJMenuBar(jMenuBar);
		landlord[0]=new JButton("������");
		landlord[1]=new JButton("��     ��");
		publishCard[0]= new JButton("����");
		publishCard[1]= new JButton("��Ҫ");
		publishCard[2]= new JButton("����");
		for(int i=0;i<3;i++){
			publishCard[i].setBounds(320+i*100, 400, 60, 20);
			container.add(publishCard[i]);
			publishCard[i].setVisible(false);
			publishCard[i].addActionListener(this);
		}
		for(int i=0;i<2;i++)
		{
			
			landlord[i].setBounds(320+i*100, 400,75,20);
			container.add(landlord[i]);
			landlord[i].addActionListener(this);
			landlord[i].setVisible(false);
			
		}
		for(int i=0;i<3;i++){
			time[i]=new JTextField("����ʱ:");
			time[i].setVisible(false);
			container.add(time[i]);
		}
		time[0].setBounds(140, 230, 60, 20);
		time[1].setBounds(374, 360, 60, 20);
		time[2].setBounds(620, 230, 60, 20);
		for(int i=0;i<3;i++)
		{
			currentList[i]=new ArrayList<Card>();
		}
		
	}
	
	public static GameUI GetGame(){
		if(game==null)
		game =new GameUI();
		return game;
	}
	// ������
	public void getLord(){
		for(int i=0;i<2;i++)
			landlord[i].setVisible(true);
	}
	//��ʼ����
	// ����ϴ��
	public void CardInit() {
		
		int count = 1;
		//��ʼ����
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 13; j++) {
				if ((i == 5) && (j > 2))
					break;
				else {
					//��Ԫģʽ
					card[count] = CardFlyweightFactory.CreateCard( i + "-" + j);
					card[count].setLocation(350, 50);
					container.add(card[count]);
					count++;
				}
			}
		}
		//����˳��
		for(int i=0;i<100;i++){
			Random random=new Random();
			int a=random.nextInt(54)+1;
			int b=random.nextInt(54)+1;
			Card k=card[a];
			card[a]=card[b];
			card[b]=k;
		}
		//��ʼ����
		for(int i=0;i<3;i++){
			players[i]=PlayerFactory.CreatePlayer(i);
			lordList=new ArrayList<Card>();//����������
		}
		int t=0;
		for(int i=1;i<=54;i++)
		{
			if(i>=52)//������
			{
				Move.move(card[i], card[i].getLocation(),new Point(300+(i-52)*80,10));
				lordList.add(card[i]);
				continue;
			}
			switch ((t++)%3) {
			case 0:
				//������
				Move.move(card[i], card[i].getLocation(),new Point(50,60+i*5));
				players[0].AddPlayerCard(card[i]);
				break;
			case 1:
				//��
				Move.move(card[i], card[i].getLocation(),new Point(180+i*7,450));
				players[1].AddPlayerCard(card[i]);
				card[i].turnFront(); //��ʾ����
				break;
			case 2:
				//�ұ����
				Move.move(card[i], card[i].getLocation(),new Point(700,60+i*5));
				players[2].AddPlayerCard(card[i]);		
				break;
			}		
			container.setComponentZOrder(card[i], 0);			
		}
		//���������򣬴Ӵ�С
		/*
		for(int i=0;i<3;i++)
		{	
			players[i].Order();
			Position.rePosition(this,players[i]);//���¶�λ
			
		}
		*/
		//
		//����ObjectStructure 
		ObjectStructure os = new ObjectStructure(); 
		//����Ҫ�������ṹ��Ԫ��
		for(int i=0;i<3;i++){
		Element eleA = new ConcreteElementA(players[i]); 
		Element eleB = new ConcreteElementB(this,players[i]);
		//��Ԫ�ؼ������ṹ 
		os.addElement(eleA); 
		os.addElement(eleB);    //
		//����������
		Visitor visitora = new ConcreteVisitorA();    
		Visitor visitorb = new ConcreteVisitorB(); 
		//����ҵ����ķ��� 
		os.handleRequest(visitora);  
		os.handleRequest(visitorb);  
		}
		
		//
		dizhu=new JLabel(new ImageIcon("images/dizhu.jpg"));
		dizhu.setVisible(false);
		dizhu.setSize(40, 40);
		container.add(dizhu);		
	}
	@SuppressWarnings("deprecation")
	public void restart(){
		t.stop();
		container.removeAll();
		this.repaint();
		SetMenu();// �����˵� ��ť(������������,��ʱ��)
		this.repaint();
		CardInit();//����
		getLord(); //�����ƿ�ʼ������
		t.SetRun(false);
		time[1].setVisible(true);
		//�̰߳�ȫ��,�ѷ����̵߳�UI���Ʒŵ�����
		SwingUtilities.invokeLater(new MyTimer(this,10));	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == exit) {
			this.dispose();
		}
		if (e.getSource() == start) {
			restart();		
		}
		if (e.getSource() == about) {
			JOptionPane.showMessageDialog(this, "���ģʽ����ҵ,14281091�");
		}
		if(e.getSource()==landlord[0])
		{
			time[1].setText("������");	
			t.SetRun(false);
		}
		if(e.getSource()==landlord[1])
		{
			time[1].setText("����");
			t.SetRun(false);
		}
		
		//����ǻ���
		
		
		if(e.getSource()==publishCard[2])
		{				
			this.turn=1;
			//������ģʽ
			//�۲���ģʽ
			ConcreteSubject concretesubject=new ConcreteSubject();
			ConcreteObserver observer0 =new ConcreteObserver(this,this.players[0]);
			ConcreteObserver observer1 =new ConcreteObserver(this,this.players[1]);
			ConcreteObserver observer2 =new ConcreteObserver(this,this.players[2]);
			concretesubject.attach(observer0);
			concretesubject.attach(observer1);
			concretesubject.attach(observer2);
			concretesubject.setSubjectState("����");
		//	concretesubject.
				concretesubject.detach(observer0);
				concretesubject.detach(observer1);
				concretesubject.detach(observer2);
			o.RestoreMemento(mc.restore());	//
	
			ConcreteIterator <Card> its = new ConcreteIterator<Card> (o.GetState());
			
			while(its.hasNext())
			{	
				its.Next().SetUnClicked();
				
				//��������
			}
			o2.RestoreMemento(mc2.restore());
			
			o3.RestoreMemento(mc3.restore());
			players[0].SetCard(o3.GetState());
			players[1].SetCard(o.GetState());
			players[2].SetCard(o2.GetState());
			 observer0 =new ConcreteObserver(this,this.players[0]);
			 observer1 =new ConcreteObserver(this,this.players[1]);
			 observer2 =new ConcreteObserver(this,this.players[2]);
			concretesubject.attach(observer0);
			concretesubject.attach(observer1);
			concretesubject.attach(observer2);
			concretesubject.setSubjectState("�ָ�����");	
			this.repaint();
			time[1].setText("����");//	
			huipai=1;
		}
		if(e.getSource()==publishCard[1])
		{
			this.nextPlayer=true;
			currentList[1].clear();
			time[1].setText("��Ҫ");
		}
		//����ǳ��ư�ť
		if(e.getSource()==publishCard[0])
		{	
			try {
				o.SetState(players[1].GetPlayerCards());//��¡
				o2.SetState(players[2].GetPlayerCards());//��¡
				o3.SetState(players[0].GetPlayerCards());//��¡
			} catch (CloneNotSupportedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			mc=MementoCaretakerOne.getInstance();
			mc2=MementoCaretakerTwo.getInstance();
			mc3=MementoCaretakerThree.getInstance();
			try {
				mc.save(o.CreateMemento());
				mc2.save(o2.CreateMemento());
				mc3.save(o3.CreateMemento());
			} catch (CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
			//MementoCaretaker 
			ArrayList<Card> c=new ArrayList<Card>();
			//
			//��ѡ����
		//	System.out.println(players[1].GetCardSize());
			for(int i=0;i<players[1].GetCardSize();i++)
			{
				Card card=players[1].GetPlayerCards().get(i);
				if(card.IsClicked())  //
				{
					c.add(card);
				}
			}
			int flag=1;			
			//�������������
			if(time[0].getText().equals("��Ҫ")&&time[2].getText().equals("��Ҫ"))
			{			
				if(Judge.jugdeType(c)!=CardType.c0)
					flag=1;//��ʾ���Գ���
			}//����Ҹ���
			
			else{			
				flag=Check.checkCards(c,currentList);
			}
			//�ж��Ƿ���ϳ���
			if(flag==1 || huipai==1)
			{	
				huipai=0;
				currentList[1]=(ArrayList<Card>) c;
				players[1].GetPlayerCards().removeAll(currentList[1]);//�Ƴ��ߵ���
				//��λ����
				Point point=new Point();
				point.x=(770/2)-(currentList[1].size()+1)*15/2;;
				point.y=300;
				for(int i=0,len=currentList[1].size();i<len;i++)
				{
					Card card=currentList[1].get(i);
					Move.move(card, card.getLocation(), point);
					point.x+=15;
				}
				//�����ƺ�����������
				Position.rePosition(this, players[1]);
				time[1].setVisible(false);
				this.nextPlayer=true;
			}

		}
	}
}