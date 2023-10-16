import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Square extends JButton{
	
	private boolean lifeSigns=false;
	private boolean toBirth;
	private boolean toDie;
	
	public Square() {
		setToBirth(false);
		setToDie(false);
		addActionListener(new squareAction());
	}
	
	public void setLife(boolean signs) {
		lifeSigns=signs;
	}
	
	public boolean getlifeSigns() {
		return lifeSigns;
	}

	public boolean getToDie() {
		return toDie;
	}

	public void setToDie(boolean d) {
		toDie = d;
	}

	public boolean getToBirth() {
		return toBirth;
	}

	public void setToBirth(boolean b) {
		toBirth = b;
	}

	class squareAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if (lifeSigns) {
				lifeSigns=false;
				setBackground(null);
			} else {
				lifeSigns=true;
				setBackground(Color.black);
			}
			
		}
		
	}	
}
