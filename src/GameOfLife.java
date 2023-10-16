import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOfLife extends JFrame{

	private static final long serialVersionUID = 1L;
	
	
	private int speed = 100;
	private static int size = 20;
	private static Square[][] board = new Square[size][size];
	private JButton clear = new JButton("Clear");
	private JButton move = new JButton("Move");
	private JButton start = new JButton("Start");
	private JPanel centerPanel = new JPanel();
	private JPanel southPanel = new JPanel();
	private boolean running = false;

	public GameOfLife() {
		setTitle("Game of Life");
		setSize(1000,1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		for(int r=0;r<board.length;r++) {
			for(int c=0; c<board[r].length;c++) {
				board[r][c]=new Square();
				board[r][c].setBackground(null);
				centerPanel.add(board[r][c]);

			}
		}
		setLayout(new BorderLayout());
		centerPanel.setLayout(new GridLayout(size,size));
		add(centerPanel,BorderLayout.CENTER);
		southPanel.add(clear);
		clear.addActionListener(new ClearAction());
		southPanel.add(move);
		move.addActionListener(new MoveAction());
		southPanel.add(start);
		start.addActionListener(new StartAction());
		add(southPanel,BorderLayout.SOUTH);
		setVisible(true);
	}

	class ClearAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			for(int r=0;r<board.length;r++) {
				for(int c=0; c<board[r].length;c++) {
					board[r][c].setBackground(null);
					board[r][c].setLife(false);

				}

			}
		}
	}

	class MoveAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			for(int r=0; r<board.length;r++) {
				for (int c=0; c<board[r].length;c++) {
					int lifeCount=0;
					for(int i = -1; i<=+1; i++) {
						for(int j= -1; j<=+1; j++) {
							try {
								if (board[r+i][c+j].getlifeSigns()) lifeCount++;
							} catch (ArrayIndexOutOfBoundsException f) {}
						}
					}

					if (board[r][c].getlifeSigns()) {
						lifeCount--;
						if (lifeCount<2 || lifeCount>3) {
							board[r][c].setToDie(true);
						}
					} else if (lifeCount==3){
						board[r][c].setToBirth(true);
					}
				}
			}


			for(int r=0; r<board.length;r++) {
				for (int c=0; c<board[r].length;c++) {
					if (board[r][c].getToDie()) {
						board[r][c].setToBirth(false);
						board[r][c].setToDie(false);
						board[r][c].setLife(false);
						board[r][c].setBackground(null);

					} if(board[r][c].getToBirth()) {
						board[r][c].setToBirth(false);
						board[r][c].setToDie(false);
						board[r][c].setLife(true);
						board[r][c].setBackground(Color.black);
					}
				}
			}
		}

	}
	
	class StartAction implements ActionListener{
		
		Timer t =new Timer(speed, new MoveAction());
		
		public void actionPerformed(ActionEvent e) {
			if (!running) {
				start.setText("Stop");
				start.setBackground(Color.red);
				running=true;
				t.start();
				
			} else {
				start.setText("Start");
				start.setBackground(Color.green);
				running=false;
				t.stop();
			}
		}
		
	}

	public static void main(String[] args) {
		new GameOfLife();

	}
}
