import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Start {

	public static final int NUM_HOLES = 18;

	public static void main(String[] args) {
		Timer timer = new Timer();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome window = new Welcome();
					window.getStart().addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int[] board = window.getBoard();
							boolean playerS = window.isPlayerStarting();
							int[] scores = window.getScores();
							Game game = new Game(board, scores[0], scores[1], playerS);
							GUI gui = new GUI(board, playerS, scores[0], scores[1]);
							gui.getGive().addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									gui.losePopUp();
									window.getFrame().setVisible(true);
									window.reset();
								}
							});
							for(int i = 0;i<NUM_HOLES;i++) {
								gui.getHoles()[i].addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										int move = gui.indexOfButton(((JButton)e.getSource()));
										Hole[] board = game.round(move);
										if (board == null){
											if(game.win() instanceof AIPlayer) {
												gui.losePopUp();
											} else {
												gui.winPopUp();
											}
											window.getFrame().setVisible(true);
											window.reset();
										}else if(board.length == 1) {
											JOptionPane.showMessageDialog(gui, "Invalid Move!", "Error", JOptionPane.ERROR_MESSAGE);
										} else {
											int[] ret = new int[NUM_HOLES];
											for(int j = 0; j < NUM_HOLES; j++) {
												ret[j] = board[j].getNumberOfKorgools();
											}
											gui.update(ret, game.isWhoseTurn(), game.getAIPlayer().getScore(), game.getPlayer().getScore());
											if(game.getPlayer().checkTuz()) {
												for(int j = 0 ; j< NUM_HOLES;j++) {
													if(board[j].getTuz()) gui.markHoleAsTuz(j);
												}
											}
											board = game.round(9);
											for(int j = 0; j < NUM_HOLES; j++) {
												ret[j] = board[j].getNumberOfKorgools();
											}
											timer.schedule(new TimerTask() {
												@Override
												public void run() {
													gui.update(ret, game.isWhoseTurn(), game.getAIPlayer().getScore(), game.getPlayer().getScore());
												}
											}, 3000);

											if(game.getAIPlayer().checkTuz()) {
												for(int j = 0 ; j< NUM_HOLES;j++) {
													if(board[j].getTuz()) gui.markHoleAsTuz(j);
												}
											}
										}
									}
								});
							}
							window.getFrame().setVisible(false);
						}
					});
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
