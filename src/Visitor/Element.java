package Visitor;

//被访问的元素的接口 
public abstract class Element { 
	//接受访问者的访问
	public abstract void accept(Visitor visitor);
}
