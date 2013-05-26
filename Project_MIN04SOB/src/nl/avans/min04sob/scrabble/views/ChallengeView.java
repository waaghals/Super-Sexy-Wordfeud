package nl.avans.min04sob.scrabble.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.core.CorePanel;
import nl.avans.min04sob.scrabble.core.CoreView;
	public class ChallengeView implements CoreView  {
		// extends??
		private JTextField Opponent;
	//	http://www.oracle.com/technetwork/articles/javase/index-142890.html
		private JPanel chpanel = new JPanel();
		public JPanel tochallenge = new JPanel();
		private JPanel response=new JPanel();
		private JButton accept = new JButton("accept");
		private JButton decline = new JButton("decline");
		private JButton oke = new JButton("oke");
		private JButton oke2 =new JButton("oke");
		private JFrame jframe = new JFrame("popup");
		private JTextField gameidchallenger = new JTextField(10);
		private JTextField nameChallenged = new JTextField(10);
		public ArrayList <String> challenge  = new ArrayList<String>();	
		public ChallengeView()
		{
			jframe.setTitle("Challenge");
			jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		/// pas aan
		public void viewArraylistRemove()
		{
			challenge.removeAll(challenge);
		}
		public void viewArrayListadd(String msg)
		{
			challenge.add(msg);
			showChallenge();
		}
		
		public void showChallenge()//gui 
		{ 
			jframe.setVisible(true);
			jframe.setContentPane(chpanel);
			chpanel.setLayout(new BoxLayout(jframe, BoxLayout.PAGE_AXIS));
			int index=0;
			while(index < challenge.size())
			{
			chpanel.add(new JLabel(challenge.get(index)));
			}
			chpanel.add(gameidchallenger);
			accept.setFont(new Font("Serif", Font.ITALIC, 14));
			decline.setFont(new Font("Serif", Font.ITALIC, 14));
			jframe.pack();
		}
		//wordt gebruikt of niet
		// 
		
		public void toChallenge()//gui
		{	
			jframe.setVisible(true);
			jframe.setContentPane(tochallenge);
			tochallenge.setLayout(new BoxLayout(tochallenge, BoxLayout.PAGE_AXIS));
			tochallenge.setPreferredSize(new Dimension(210,120));
			tochallenge.add(new JLabel("insert game id"));
			tochallenge.add(gameidchallenger);
			tochallenge.add(new JLabel("insert opponent"));
			tochallenge.add(nameChallenged);
			jframe.add(oke);
			jframe.setResizable(false);
			jframe.pack();
		}
		
		public void response(String msg)//gui
		{
			jframe.setResizable(false);
			jframe.setVisible(true);
			jframe.setContentPane(response);
			response.setPreferredSize(new Dimension(130,70));
			response.add(new JLabel("msg"));
			response.add(oke2);
			jframe.pack();
		}
		public String getUsername() 
		{
			return nameChallenged.getText();
		}
		public String getspelID() 
		{
			return gameidchallenger.getText();
		}
		
		public void addActionListenerAccept(ActionListener listener) {
			accept.addActionListener(listener);
		}
		
		public void addActionListenerOke(ActionListener listener) {
			oke.addActionListener(listener);
		} 
		public void addActionListenerDecline(ActionListener listener) {
			decline.addActionListener(listener);
		}
		public  JFrame javaFrame()		
		{
			return jframe;
		}
		@Override
		public void modelPropertyChange(PropertyChangeEvent evt) {
			// TODO Auto-generated method stub
			
		}
	}
	///
