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
	public Container container = null;// 定义容器
	JMenuItem start,exit, about;// 定义菜单按钮
	JButton landlord[]=new JButton[2];//抢地主按钮
	JButton publishCard[]=new JButton[3];//出牌按钮
	public Originator o=new Originator();
	public Originator o2=new Originator();
	public Originator o3=new Originator();
	public MementoCaretakerOne mc=MementoCaretakerOne.getInstance();
	public MementoCaretakerTwo mc2=MementoCaretakerTwo.getInstance();
	public MementoCaretakerThree mc3=MementoCaretakerThree.getInstance();
	public int dizhuFlag;//地主标志
	public int huipai;
	int turn;
	JLabel dizhu; //地主图标
	@SuppressWarnings("unchecked")
	ArrayList<Card> currentList[] =new ArrayList[3]; //  当前的出牌
	public Player players[]=new Player[3];
	ArrayList<Card> lordList;//地主牌
	Card card[] = new Card[56]; // 定义54张牌
	JTextField time[]=new JTextField[3]; //计时器
	Game t; //定时器（线程）
	boolean nextPlayer=false; //转换角色
	private static GameUI game;
	private GameUI(){
		Init();// 初始化
		SetMenu();// 创建菜单 按钮(抢地主，发牌,计时器)
		this.setVisible(true);
		CardInit();//发牌
		getLord(); //发完牌开始抢地主
		time[1].setVisible(true);
		//线程安全性,把非主线程的UI控制放到里面
		SwingUtilities.invokeLater(new MyTimer(this,10));	
	}
	// 初始化窗体
	public void Init() {
	
		this.setTitle("斗地主游戏---设计模式大作业-14281091");
		this.setSize(830, 620);
		background=new ImageIcon("images/background.jpg");
		 this.setSize(background.getIconWidth(),background.getIconHeight()); 	
		 JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
		 label.setBounds(0, 0, background.getIconWidth(),
				    background.getIconHeight());
		// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		  imagePanel = (JPanel) this.getContentPane();
		  imagePanel.setOpaque(false);
		  // 内容窗格默认的布局管理器为BorderLayout
		  imagePanel.setLayout(new FlowLayout());
		  this.getLayeredPane().setLayout(null);
		  // 把背景图片添加到分层窗格的最底层作为背景
		  this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  this.setSize(background.getIconWidth(), background.getIconHeight());
		  this.setResizable(false);
		  this.setVisible(true);	 	       
	       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	       this.setVisible(true);  
		setLocationRelativeTo(getOwner()); // 屏幕居中
		container = this.getContentPane();
		container.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container.setBackground(new Color(0, 112, 26)); // 背景为绿色		
	}

	// 创建菜单 功能按钮
	public void SetMenu() {
		JMenuBar jMenuBar = new JMenuBar();
		JMenu game = new JMenu("游戏");
		JMenu help = new JMenu("帮助");
		start = new JMenuItem("新游戏");
		exit = new JMenuItem("退出");
		about = new JMenuItem("关于");	
		start.addActionListener(this);
		exit.addActionListener(this);
		about.addActionListener(this);
		game.add(start);
		game.add(exit);
		help.add(about);
		jMenuBar.add(game);
		jMenuBar.add(help);
		this.setJMenuBar(jMenuBar);
		landlord[0]=new JButton("抢地主");
		landlord[1]=new JButton("不     抢");
		publishCard[0]= new JButton("出牌");
		publishCard[1]= new JButton("不要");
		publishCard[2]= new JButton("悔牌");
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
			time[i]=new JTextField("倒计时:");
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
	// 抢地主
	public void getLord(){
		for(int i=0;i<2;i++)
			landlord[i].setVisible(true);
	}
	//初始化牌
	// 发牌洗牌
	public void CardInit() {
		
		int count = 1;
		//初始化牌
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 13; j++) {
				if ((i == 5) && (j > 2))
					break;
				else {
					//享元模式
					card[count] = CardFlyweightFactory.CreateCard( i + "-" + j);
					card[count].setLocation(350, 50);
					container.add(card[count]);
					count++;
				}
			}
		}
		//打乱顺序
		for(int i=0;i<100;i++){
			Random random=new Random();
			int a=random.nextInt(54)+1;
			int b=random.nextInt(54)+1;
			Card k=card[a];
			card[a]=card[b];
			card[b]=k;
		}
		//开始发牌
		for(int i=0;i<3;i++){
			players[i]=PlayerFactory.CreatePlayer(i);
			lordList=new ArrayList<Card>();//地主牌三张
		}
		int t=0;
		for(int i=1;i<=54;i++)
		{
			if(i>=52)//地主牌
			{
				Move.move(card[i], card[i].getLocation(),new Point(300+(i-52)*80,10));
				lordList.add(card[i]);
				continue;
			}
			switch ((t++)%3) {
			case 0:
				//左边玩家
				Move.move(card[i], card[i].getLocation(),new Point(50,60+i*5));
				players[0].AddPlayerCard(card[i]);
				break;
			case 1:
				//我
				Move.move(card[i], card[i].getLocation(),new Point(180+i*7,450));
				players[1].AddPlayerCard(card[i]);
				card[i].turnFront(); //显示正面
				break;
			case 2:
				//右边玩家
				Move.move(card[i], card[i].getLocation(),new Point(700,60+i*5));
				players[2].AddPlayerCard(card[i]);		
				break;
			}		
			container.setComponentZOrder(card[i], 0);			
		}
		//发完牌排序，从大到小
		/*
		for(int i=0;i<3;i++)
		{	
			players[i].Order();
			Position.rePosition(this,players[i]);//重新定位
			
		}
		*/
		//
		//创建ObjectStructure 
		ObjectStructure os = new ObjectStructure(); 
		//创建要加入对象结构的元素
		for(int i=0;i<3;i++){
		Element eleA = new ConcreteElementA(players[i]); 
		Element eleB = new ConcreteElementB(this,players[i]);
		//把元素加入对象结构 
		os.addElement(eleA); 
		os.addElement(eleB);    //
		//创建访问者
		Visitor visitora = new ConcreteVisitorA();    
		Visitor visitorb = new ConcreteVisitorB(); 
		//调用业务处理的方法 
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
		SetMenu();// 创建菜单 按钮(抢地主，发牌,计时器)
		this.repaint();
		CardInit();//发牌
		getLord(); //发完牌开始抢地主
		t.SetRun(false);
		time[1].setVisible(true);
		//线程安全性,把非主线程的UI控制放到里面
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
			JOptionPane.showMessageDialog(this, "设计模式大作业,14281091李博");
		}
		if(e.getSource()==landlord[0])
		{
			time[1].setText("抢地主");	
			t.SetRun(false);
		}
		if(e.getSource()==landlord[1])
		{
			time[1].setText("不抢");
			t.SetRun(false);
		}
		
		//如果是悔牌
		
		
		if(e.getSource()==publishCard[2])
		{				
			this.turn=1;
			//迭代器模式
			//观察者模式
			ConcreteSubject concretesubject=new ConcreteSubject();
			ConcreteObserver observer0 =new ConcreteObserver(this,this.players[0]);
			ConcreteObserver observer1 =new ConcreteObserver(this,this.players[1]);
			ConcreteObserver observer2 =new ConcreteObserver(this,this.players[2]);
			concretesubject.attach(observer0);
			concretesubject.attach(observer1);
			concretesubject.attach(observer2);
			concretesubject.setSubjectState("悔牌");
		//	concretesubject.
				concretesubject.detach(observer0);
				concretesubject.detach(observer1);
				concretesubject.detach(observer2);
			o.RestoreMemento(mc.restore());	//
	
			ConcreteIterator <Card> its = new ConcreteIterator<Card> (o.GetState());
			
			while(its.hasNext())
			{	
				its.Next().SetUnClicked();
				
				//迭代遍历
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
			concretesubject.setSubjectState("恢复手牌");	
			this.repaint();
			time[1].setText("悔牌");//	
			huipai=1;
		}
		if(e.getSource()==publishCard[1])
		{
			this.nextPlayer=true;
			currentList[1].clear();
			time[1].setText("不要");
		}
		//如果是出牌按钮
		if(e.getSource()==publishCard[0])
		{	
			try {
				o.SetState(players[1].GetPlayerCards());//克隆
				o2.SetState(players[2].GetPlayerCards());//克隆
				o3.SetState(players[0].GetPlayerCards());//克隆
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
			//点选出牌
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
			//如果我主动出牌
			if(time[0].getText().equals("不要")&&time[2].getText().equals("不要"))
			{			
				if(Judge.jugdeType(c)!=CardType.c0)
					flag=1;//表示可以出牌
			}//如果我跟牌
			
			else{			
				flag=Check.checkCards(c,currentList);
			}
			//判断是否符合出牌
			if(flag==1 || huipai==1)
			{	
				huipai=0;
				currentList[1]=(ArrayList<Card>) c;
				players[1].GetPlayerCards().removeAll(currentList[1]);//移除走的牌
				//定位出牌
				Point point=new Point();
				point.x=(770/2)-(currentList[1].size()+1)*15/2;;
				point.y=300;
				for(int i=0,len=currentList[1].size();i<len;i++)
				{
					Card card=currentList[1].get(i);
					Move.move(card, card.getLocation(), point);
					point.x+=15;
				}
				//出完牌后重新整理牌
				Position.rePosition(this, players[1]);
				time[1].setVisible(false);
				this.nextPlayer=true;
			}

		}
	}
}