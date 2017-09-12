package p1;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PingPongScoreVeld extends JPanel implements ActionListener
{
	PingPongSpeelveld speelveld;
	final private int WACHTTIJD = 1000;
	private JButton startKnop;
	private JLabel scoreLinks;
	private JLabel scoreRechts;
	private String speler1;
	private String speler2;
	javax.swing.Timer scoreTimer = new javax.swing.Timer(WACHTTIJD, this);
	
	public PingPongScoreVeld(PingPongSpeelveld speelveld, String speler1, String speler2)
	{
		this.speelveld = speelveld;
		this.speler1 = speler1;
		this.speler2 = speler2;
		scoreLinks = new JLabel(this.speler1 + " 0 punten");
		add(scoreLinks);
		
		startKnop = new JButton("Start");
		startKnop.addActionListener(this);
		add(startKnop);
		
		scoreRechts = new JLabel(this.speler2 + " 0 punten");
		add(scoreRechts);
		
		setBackground(Color.pink);	
		
		scoreTimer.start();
	}
	
	public void actionPerformed (ActionEvent e)
	{
		if (e.getSource() == startKnop)
		{
			speelveld.setTimerStart();
		}
		else
		{
			int scoreUpdateLinks = speelveld.scoreLinks;
			int scoreUpdateRechts = speelveld.scoreRechts;
			scoreLinks.setText(this.speler1 + " " + scoreUpdateLinks + " punten");
			scoreRechts.setText(this.speler2 + " " + scoreUpdateRechts + " punten");
			if(scoreUpdateLinks + scoreUpdateRechts >= 5)
			{
				if(scoreUpdateLinks > scoreUpdateRechts)
				{
					JOptionPane.showMessageDialog(null, "Het spel is afglepen, " + this.speler1 + " heeft gewonnen", "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Het spel is afglepen, " + this.speler2 + " heeft gewonnen", "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
				}
				speelveld.scoreLinks = 0;
				speelveld.scoreRechts = 0;
			}
		}
	}
}
