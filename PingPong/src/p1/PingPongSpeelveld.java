package p1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class PingPongSpeelveld extends JPanel implements ActionListener
{
	private Random gen = new Random();
	private final int WACHTTIJD = 50;
	private final int BALDIAMETER = 15;
	private final int XPOSRACKETLINKS = 10;
	private final int XPOSRACKETRECHTS = 880;
	private final int RACKETBREEDTE = 10;	
	private int racketLengte = 75;
	private int horPlaats = 443;
	private int vertPlaats = 293;
	private int yPosRacketLinks = 263; 
	private int yPosRacketRechts = 263;
	private int xRichting = 1;
	private int xSnelheid = 15;
	private int yRichting = 1;
	private int ySnelheid = 0;
	private int verkleiner = 0;
	private int verhogingSnelheidTeller = 0;
	public int scoreLinks = 0;
	public int scoreRechts = 0;
	javax.swing.Timer autoTime = new javax.swing.Timer(WACHTTIJD, this);
	PingPongScoreVeld scoreveld;
	
	public PingPongSpeelveld()
	{
	}
	
	public void setRacketRichting(int racketLinksPositie, int racketRechtsPositie)
	{
		yPosRacketLinks += racketLinksPositie;
		yPosRacketRechts += racketRechtsPositie;
		if(yPosRacketLinks < 0)
		{
			yPosRacketLinks = 0;
		}
		if(yPosRacketRechts < 0)
		{
			yPosRacketRechts = 0;
		}
		if((yPosRacketLinks + racketLengte) > 530)
		{
			yPosRacketLinks = 530 - racketLengte;
		}
		if((yPosRacketRechts + racketLengte) > 530)
		{
			yPosRacketRechts = 530 - racketLengte;
		}
		repaint();
	}
	
	public void setTimerStart()
	{
		autoTime.start();	
	}
	
	public void setTimerStop()
	{
		autoTime.stop();
		ySnelheid = 0;
		xSnelheid = 7;
		resetRackets();
	}
	
	public void setXRichtingBal()
	{
		if(((horPlaats + BALDIAMETER) >= XPOSRACKETRECHTS) && ((vertPlaats <= (yPosRacketRechts + racketLengte)) && 
				((vertPlaats + BALDIAMETER) >= yPosRacketRechts)) || ((horPlaats) <= XPOSRACKETLINKS + RACKETBREEDTE) && 
				((vertPlaats <= (yPosRacketLinks + racketLengte)) && ((vertPlaats + BALDIAMETER) >= yPosRacketLinks)))
		{
			xRichting = xRichting * -1;
			ySnelheid = gen.nextInt(11) -5;
		}
		else if((horPlaats + BALDIAMETER) > (XPOSRACKETRECHTS + (BALDIAMETER * 2)))
		{
			horPlaats = 443;
			vertPlaats = 293;
			setTimerStop();
			scoreLinks++;
		}
		else if(horPlaats < ( 0 - (BALDIAMETER * 2)))
		{
			horPlaats = 443;
			vertPlaats = 293;
			setTimerStop();
			scoreRechts++;
		}
	}
	
	public void resetRackets()
	{
		racketLengte = 75;
		yPosRacketLinks = 263;
		yPosRacketRechts = 263;
	}
	
	public void setYRichtingBal()
	{
		if(vertPlaats <= 0 || (vertPlaats + BALDIAMETER >= 530))
		{
			yRichting = yRichting * -1;
		}
	}
	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillOval(horPlaats, vertPlaats, BALDIAMETER, BALDIAMETER);
		g.fillRect(XPOSRACKETLINKS, yPosRacketLinks, RACKETBREEDTE, racketLengte);  // linker racket
		g.fillRect(XPOSRACKETRECHTS, yPosRacketRechts, RACKETBREEDTE, racketLengte); // rechter racket
	}
	
	public void actionPerformed(ActionEvent e)
	{
		setXRichtingBal();
		setYRichtingBal();
		verhogingSnelheidTeller++;
		if(verhogingSnelheidTeller >= 200)
		{
			xSnelheid+=2;
			verkleiner++;
			verhogingSnelheidTeller = 0;
			if(verkleiner >= 5 && racketLengte > 5)
			{
				racketLengte -= 10;
				verkleiner = 0;
			}
		}
		horPlaats = horPlaats + (xSnelheid * xRichting);
		vertPlaats = vertPlaats + (ySnelheid * yRichting);
		repaint();
	}
}