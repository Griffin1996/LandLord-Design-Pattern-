package FlyweightFactory;
public interface CardProduct {
	public String name = null;//ͼƬurl����
	public boolean up=false;//�Ƿ�������
	public boolean canClic=false;//�Ƿ�ɱ����
	public boolean clicked=false;//�Ƿ�����
	public void SetCanClick();
	public boolean IsClicked();
	public boolean IsCanClicked();
	//����
	public void turnFront();
	//����
	public void turnRear();
}
