package Adapter;

import Game.GameUI;

public class Adapter  implements Judge {
	JudgeIsWin judge;
	public Adapter(JudgeIsWin judge) {
		// TODO Auto-generated constructor stub
		this.judge=judge;
	}



	@Override
	public boolean IsWin(GameUI main) {
		return judge.win(main);
	}
	
}
