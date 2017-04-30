package Visitor;

public interface Visitor {
	//访问元素A，相当于给元素A添加访问者的功能 
	public void visitConcreteElementA (ConcreteElementA elementA); 
	//访问元素B，相当于给元素B添加访问者的功能 
	public void visitConcreteElementB (ConcreteElementB elementB);

}
