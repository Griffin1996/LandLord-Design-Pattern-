package Observer;
import java.util.ArrayList;
public class Subject {
	//��������ע��Ĺ۲��߶��� 
	public ArrayList<Observer> observers = new ArrayList<Observer>(); 
	//ע��۲��߶��� 
	public void attach(Observer observer) { 
		observers.add(observer);
		}
	//ɾ���۲��߶��� 
	public void detach(Observer observer) { 
		observers.remove(observer);
		} 
	//֪ͨ����ע��Ĺ۲��߶��� 
	protected void notifyObservers() { 
		for(Observer observer : observers)
		{ observer.update(this); 
		} 
		}

}
