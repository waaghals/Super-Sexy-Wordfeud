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
import javax.swing.JTextField;
	public class Challengeview extends JFrame{
		private JTextField Opponent;
		private JPanel chpanel = new JPanel();
		private JPanel tochallenge = new JPanel();
		private JPanel response=new JPanel();
		private JButton accept = new JButton("accept");
		private JButton decline = new JButton("decline");
		private JButton oke = new JButton("oke");
		private JTextField usernameField = new JTextField(10);
			
		public Challengeview()
		{
			this.setTitle("Challenge");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			pack();
		}
		
		public void showChallenge()
		{
			setResizable(false);
			setVisible(true);
			this.setContentPane(chpanel);
			chpanel.setPreferredSize(new Dimension(180,70));
			accept.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}});
			decline.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					dispose();
				}}); 
			chpanel.add( new JLabel("You have received a challenge") );
			chpanel.add(accept);
			chpanel.add(decline);

			accept.setFont(new Font("Serif", Font.ITALIC, 14));
			decline.setFont(new Font("Serif", Font.ITALIC, 14));
		}
		
		public void toChallenge()
		{	
			setVisible(true);
			this.setContentPane(tochallenge);
			tochallenge.setPreferredSize(new Dimension(180,70));
			tochallenge.add(usernameField);
			this.add(oke);
			oke.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					/// controller
					System.out.println(usernameField.getText());
				}});
		}
		
		public void response(String msg)
		{
			setResizable(false);
			setVisible(true);
			this.setContentPane(response);
			response.setPreferredSize(new Dimension(130,70));
			response.add(new JLabel("msg"));
			response.add(oke);
		}
		public String getUsername() {
			return usernameField.getText();
		}
	}
