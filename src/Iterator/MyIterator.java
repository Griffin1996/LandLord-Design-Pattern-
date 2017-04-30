package Iterator;

public interface  MyIterator<E> {
	public  E First();
	public  E Next();
	public  boolean IsDone();
	public E CurrentItem();

}
