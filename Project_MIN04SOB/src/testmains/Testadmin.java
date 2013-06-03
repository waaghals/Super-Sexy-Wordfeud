package testmains;

import javax.swing.JFrame;
import nl.avans.min04sob.scrabble.views.ChangePassPanelAdministrator;
public class Testadmin {

	public static void main(String args[])
	{
		
		JFrame paard = new JFrame();
		paard.add(new ChangePassPanelAdministrator());
		paard.setVisible(true);
		paard.pack();
	}
}
