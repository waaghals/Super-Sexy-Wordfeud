package nl.avans.min04sob.mvcvoorbeeld;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import nl.avans.min04sob.scrabble.core.CoreConstraint;
import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.core.CoreWindow;

public class ChatControllerPATRICKVOORBEELD extends CoreController {

	ChatPanel sbPanel;
	ChatTableModelPATRICKVOORBEEELD chatModel;

	Random r;

	public ChatControllerPATRICKVOORBEELD(CoreWindow window) {
		chatModel = new ChatTableModelPATRICKVOORBEEELD();
		sbPanel = new ChatPanel();
		sbPanel.setChatTableModel(chatModel);

		sbPanel.addGeneratorButtonActionListner(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addRandomScore();
			}
		});

		addModel(chatModel);
		addView(sbPanel);

		window.add(sbPanel, new CoreConstraint(10, 10, 0, 0));

		r = new Random();
		Timer timer = new Timer(500, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addRandomScore();
			}
		});
		timer.start();

	}

	private void addRandomScore() {

		String[] names = { "Patrick", "Joep", "Thomas", "Aaron", "Alexander" };
		String[] berichten = { "Hoi", "Halloo", "Sup",
				"Does it smell like updog?", "Hois" };
		String randomName = names[r.nextInt(names.length)];
		String randomMessage = berichten[r.nextInt(berichten.length)];
		chatModel.addMessage(randomName, randomMessage);

	}

}
