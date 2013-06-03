package testmains;

import javax.swing.JFrame;

import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.views.ChangePassPanelAdministrator;
public class Testadmin {

	public static void main(String args[])
	{
		ChangePassPanelAdministrator q = new ChangePassPanelAdministrator(); 
		JFrame paard = new JFrame();
		AccountModel steen = new AccountModel();
		q.fillPlayerList(steen.getPlayers());
		paard.add(q);
		
		paard.setVisible(true);
		
		paard.pack();
	}
}
