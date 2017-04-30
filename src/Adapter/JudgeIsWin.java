package Adapter;

import javax.swing.JOptionPane;

import Common.Judge;
import Game.GameUI;

public class JudgeIsWin implements JudgeWin {


	@Override
	public boolean win(GameUI main) {
		// TODO Auto-generated method stub
		//≈–∂œ ‰”Æ
		
		for(int i=0;i<3;i++){
			if(main.players[i].GetCardSize()==0)
			{
				String s;
				if(i==1)
				{
					s="πßœ≤ƒ„£¨ §¿˚¡À!";
				}else {
					s="πßœ≤µÁƒ‘"+i+",”Æ¡À! ƒ„µƒ÷«…Ã”–¥˝Ã·∏ﬂ≈∂";
				}
				JOptionPane.showMessageDialog(main, s);
				return true;
			}
		}
		return false;

	}
}
