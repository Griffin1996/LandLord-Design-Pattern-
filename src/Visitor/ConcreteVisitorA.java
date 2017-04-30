package Visitor;

public class ConcreteVisitorA implements Visitor {

	public void visitConcreteElementA(ConcreteElementA element) { 
		//访问ConcreteElementA时，需要执行的功能实现在这里
		//可能需要访问元素已有的功能，比如：
		element.opertionA(); 
		}

	public void visitConcreteElementB(ConcreteElementB elementB) {
		// TODO Auto-generated method stub
		
	}

	}

