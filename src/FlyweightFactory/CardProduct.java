package FlyweightFactory;
public interface CardProduct {
	public String name = null;//图片url名字
	public boolean up=false;//是否正反面
	public boolean canClic=false;//是否可被点击
	public boolean clicked=false;//是否点击过
	public void SetCanClick();
	public boolean IsClicked();
	public boolean IsCanClicked();
	//正面
	public void turnFront();
	//反面
	public void turnRear();
}
