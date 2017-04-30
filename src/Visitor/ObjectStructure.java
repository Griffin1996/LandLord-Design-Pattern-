package Visitor;

import java.util.ArrayList;
import java.util.Collection;

//����ṹ,ͨ���������Ԫ�ض�����б������÷������ܷ���
//�����е�Ԫ��
public class ObjectStructure {
	//ʾ�⣬��ʾ����ṹ��������һ����Ͻṹ���Ǽ���
	private Collection<Element> col = new ArrayList<Element>(); 
	//�ṩ���ͻ��˲����ĸ߲�ӿ�
	public void handleRequest(Visitor visitor){
		//ѭ������ṹ�е�Ԫ�أ����ܷ��� 
		for(Element ele : col)
		{ 
			ele.accept(visitor);
			}     
	}
	//ʾ�ⷽ�����齨����ṹ�������ṹ�����Ԫ�ء�
	//��ͬ�Ķ���ṹ�в�ͬ�Ĺ�����ʽ 
	public void addElement(Element ele){ 
		this.col.add(ele);
	}  
}