package nl.avans.min04sob.scrabble.views;

 
	import java.awt.BorderLayout;
	import java.awt.Dimension;
	import java.awt.Font;
	import java.awt.LayoutManager;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	import javax.swing.BoxLayout;
	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JPanel;

	public class ChallengeView extends JFrame{
//test
		private JPanel chpanel = new JPanel();
		private JButton accept = new JButton("accept");
		private JButton decline = new JButton("decline");
		
		public ChallengeView()
		{
			
			this.setTitle("Challenge");
			setVisible(true);
			showChallenge();
			setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			pack();
		}
		
		public void showChallenge()
		{
			this.setContentPane(chpanel);
			chpanel.setPreferredSize(new Dimension(180,70));
			accept.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					dispose();
					// TODO Auto-generated method stub
					
				}});
			decline.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					dispose();
				}});
		 
			JLabel text = new JLabel("You have received a challenge");
		
			chpanel.add( text);
			chpanel.add(accept);
			chpanel.add(decline);

			accept.setFont(new Font("Serif", Font.ITALIC, 14));
			decline.setFont(new Font("Serif", Font.ITALIC, 14));
		}
	}
	
	// test
