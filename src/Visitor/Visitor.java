package Visitor;

public interface Visitor {
	//����Ԫ��A���൱�ڸ�Ԫ��A��ӷ����ߵĹ��� 
	public void visitConcreteElementA (ConcreteElementA elementA); 
	//����Ԫ��B���൱�ڸ�Ԫ��B��ӷ����ߵĹ��� 
	public void visitConcreteElementB (ConcreteElementB elementB);

}
