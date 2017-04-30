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
	
	public String name;//图片url名字
	boolean up;//是否正反面
	private boolean canClick=false;//是否可被点击
	private boolean clicked=false;//是否点击过
	public Card(){
		
	}
	//返回花色
		public int getColor(){
			return Integer.parseInt(this.name.substring(0,1));
		}
		//返回值
		public  int getValue(){
			int i= Integer.parseInt(this.name.substring(2,this.name.length()));
			if(this.name.substring(2,this.name.length()).equals("2"))
				i+=13;
			if(this.name.substring(2,this.name.length()).equals("1"))
				i+=13;
			if(this.getColor()==5)
				i+=2;//是王
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
	//正面
	public void turnFront() {
		this.setIcon(new ImageIcon("images/" + name + ".jpg"));
		this.up = true;
	}
	//反面
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
			int step; //移动的距离
			if(clicked)
				step=-20;
			else {
				step=20;
			}
			clicked=!clicked; //反向
			//当被选中的时候，向前移动一步/后退一步
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
