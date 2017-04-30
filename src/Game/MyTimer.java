package Game;

import Game.GameUI;
import Game.Game;

public class MyTimer implements Runnable{
	GameUI game;
	int i;
	public MyTimer(GameUI game,int i){
		this.game=game;
		this.i=i;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		game.t=new Game(game,10);//从10开始倒计时
		game.t.start();
	}
	
}
