package FlyweightFactory;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Common.Move;


@SuppressWarnings("serial")
public class Card extends JLabel implements MouseListener,CardProduct,Cloneable{
	
	public String name;//ͼƬurl����
	boolean up;//�Ƿ�������
	private boolean canClick=false;//�Ƿ�ɱ����
	private boolean clicked=false;//�Ƿ�����
	public Card(){
		
	}
	//���ػ�ɫ
		public int getColor(){
			return Integer.parseInt(this.name.substring(0,1));
		}
		//����ֵ
		public  int getValue(){
			int i= Integer.parseInt(this.name.substring(2,this.name.length()));
			if(this.name.substring(2,this.name.length()).equals("2"))
				i+=13;
			if(this.name.substring(2,this.name.length()).equals("1"))
				i+=13;
			if(this.getColor()==5)
				i+=2;//����
			return i;
		}
	public Card(String name){
		this.name=name;
		this.up=false;
	    if(this.up)
	    	this.turnFront();
	    else {
			this.turnRear();                                                                
		}
		this.setSize(71, 96);
		this.setVisible(true);
		this.addMouseListener(this);
	}
	@Override
	 public Object clone() throws CloneNotSupportedException {
		return (Card) super.clone();
	}
	public void SetCanClick(){
		canClick = true;
	}
	public void SetUnClicked(){
		clicked=false;
	}
	public boolean IsClicked(){
		return clicked;
	}
	public boolean IsCanClicked(){
		return canClick;
	}
	//����
	public void turnFront() {
		this.setIcon(new ImageIcon("images/" + name + ".jpg"));
		this.up = true;
	}
	//����
	public void turnRear() {
		Random r= new Random(); 
		int i=r.nextInt(10);
		this.setIcon(new ImageIcon("images/timg"+".jpg"));
		this.up = false;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(canClick)
		{
			Point from=this.getLocation();
			int step; //�ƶ��ľ���
			if(clicked)
				step=-20;
			else {
				step=20;
			}
			clicked=!clicked; //����
			//����ѡ�е�ʱ����ǰ�ƶ�һ��/����һ��
			Move.move(this,from,new Point(from.x,from.y-step));
		}
		
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
