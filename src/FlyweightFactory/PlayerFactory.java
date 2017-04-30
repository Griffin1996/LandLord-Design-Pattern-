package FlyweightFactory;
public class PlayerFactory {
	public static Player CreatePlayer(int i){
		return new Player(i);
}
}
