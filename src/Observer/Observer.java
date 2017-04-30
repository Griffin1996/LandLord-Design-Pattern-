package Observer;

import FlyweightFactory.Card;
import Iterator.ConcreteAggregate;

public interface Observer {
	public void update(Subject subject);

}
