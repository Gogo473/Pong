package p1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PingPongOpstart extends JFrame implements ActionListener
{
	String speler1;
	String speler2;
	final private int WACHTTIJD = 50;
	private boolean LUP, RUP, LDOWN, RDOWN = false;
	javax.swing.Timer testTimer = new javax.swing.Timer(WACHTTIJD, this);
	PingPongSpeelveld speelveld = new PingPongSpeelveld();	
	
	public PingPongOpstart()
	{
		speler1 = JOptionPane.showInputDialog(rootPane, "Speler 1, wat is je naam?", "Opgeven naam speler", JOptionPane.INFORMATION_MESSAGE);
		speler2 = JOptionPane.showInputDialog(rootPane, "Speler 2, wat is je naam?", "Opgeven naam speler", JOptionPane.INFORMATION_MESSAGE);
		JFrame venster = new JFrame();
		venster.setSize(900, 600);
		venster.setResizable(false);
		venster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		venster.setTitle("Ping pong spel");
		venster.setLocation(100, 100);
		testTimer.start();
		
			
		InputMap imap = speelveld.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap amap = speelveld.getActionMap();
		imap.put(KeyStroke.getKeyStroke(87, 0, false), "L_UP");
		amap.put("L_UP", new AbstractAction()
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						LUP = true;
					}
			
				});
		imap.put(KeyStroke.getKeyStroke(87, 0, true), "L_UP_REL");
		amap.put("L_UP_REL", new AbstractAction()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				LUP = false;
			}
	
		});
		imap.put(KeyStroke.getKeyStroke(38, 0, false), "R_UP");
		amap.put("R_UP", new AbstractAction()
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						RUP = true;
					}
			
				});
		imap.put(KeyStroke.getKeyStroke(38, 0, true), "R_UP_REL");
		amap.put("R_UP_REL", new AbstractAction()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				RUP = false;
			}
	
		});
		imap.put(KeyStroke.getKeyStroke(83, 0, false), "L_DOWN");
		amap.put("L_DOWN", new AbstractAction()
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						LDOWN = true;
					}
			
				});
		imap.put(KeyStroke.getKeyStroke(83, 0, true), "L_DOWN_REL");
		amap.put("L_DOWN_REL", new AbstractAction()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				LDOWN = false;
			}
	
		});
		imap.put(KeyStroke.getKeyStroke(40, 0, false), "R_DOWN");
		amap.put("R_DOWN", new AbstractAction()
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						RDOWN = true;
					}
			
				});
		imap.put(KeyStroke.getKeyStroke(40, 0, true), "R_DOWN_REL");
		amap.put("R_DOWN_REL", new AbstractAction()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				RDOWN = false;
			}
	
		});
		venster.requestFocus();
		venster.add(speelveld, BorderLayout.CENTER);
		
		PingPongScoreVeld scoreveld = new PingPongScoreVeld(speelveld, speler1, speler2);				
		scoreveld.setLayout(new FlowLayout());
		venster.add(scoreveld, BorderLayout.SOUTH);
		
		venster.setVisible(true);		
	}
	
	public void actionPerformed (ActionEvent e)
	{
		int yPosRacketLinks = 0;
		int yPosRacketRechts = 0;
		if(LUP)
		{
			yPosRacketLinks = -10;
		}
		if(RUP)
		{
			yPosRacketRechts = -10;
		}
		if(LDOWN)
		{
			yPosRacketLinks = 10;
		}
		if(RDOWN)
		{
			yPosRacketRechts = 10;
		}
		speelveld.setRacketRichting(yPosRacketLinks, yPosRacketRechts);
	}
	
	public static void main(String[] args) 
	{
		
		new PingPongOpstart ();
	}
}
