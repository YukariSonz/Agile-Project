import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.SystemColor;
import java.util.stream.*;
import javax.swing.JOptionPane;

public class Welcome {

	private JFrame frmToguzKorgool;
	//private JTextField txtName;

	private JButton btnStartGame = new JButton("Start Game");

	private int[] board = {9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9};
	private boolean playerStarting = true;
	private int[] scores = {0,0};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome window = new Welcome();
					window.frmToguzKorgool.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Welcome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmToguzKorgool = new JFrame();
		frmToguzKorgool.setTitle("Toguz Korgool");
		frmToguzKorgool.setBounds(100, 100, 800, 800);
		frmToguzKorgool.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmToguzKorgool.getContentPane().setLayout(new MigLayout("", "[127px,grow][][100px,grow][][111px,grow]", "[80px][grow][pref!][20px][23px][fill]"));

		/*
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 32));
		txtName.setToolTipText("Enter name...");
		txtName.setText("Name");
		frmToguzKorgool.getContentPane().add(txtName, "cell 4 3,growx,aligny top");
		txtName.setColumns(10);

		JButton btnChangeName = new JButton("Change Name");
		btnChangeName.setFont(new Font("Tahoma", Font.PLAIN, 32));
		btnChangeName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name = txtName.getText();
			}
		});
		frmToguzKorgool.getContentPane().add(btnChangeName, "cell 4 4,growx,aligny top");

		JLabel lblEnterName = new JLabel("Enter Name");
		lblEnterName.setFont(new Font("Tahoma", Font.PLAIN, 32));
		frmToguzKorgool.getContentPane().add(lblEnterName, "cell 4 2,growx,aligny bottom");
		*/

		JButton btnCustomGame = new JButton("Custom Game");
		btnCustomGame.setFont(new Font("Tahoma", Font.PLAIN, 32));
		frmToguzKorgool.getContentPane().add(btnCustomGame, "cell 2 4,growx,aligny top");
		btnCustomGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomGame dialog = new CustomGame();
				dialog.getOK().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int a = 0;
						for(int i = 0;i<18;i++){
							a = a + dialog.getValues()[i];
						}
						if((a + dialog.getScores()[0] + dialog.getScores()[1]) <= 162){
							board = dialog.getValues();
							playerStarting = dialog.isPlayerStarting();
							scores = dialog.getScores();
							dialog.dispose();
							btnStartGame.doClick();
						} else {
							JOptionPane.showMessageDialog(dialog, "Invalid setup, please make sure there are at most 162 kurgols", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});

		btnStartGame.setFont(new Font("Tahoma", Font.PLAIN, 32));
		frmToguzKorgool.getContentPane().add(btnStartGame, "cell 0 4,growx,aligny top");

		JLabel lblToguzKorgool = new JLabel("Toguz Korgool");
		lblToguzKorgool.setFont(new Font("Tahoma", Font.BOLD, 94));
		frmToguzKorgool.getContentPane().add(lblToguzKorgool, "cell 0 0 5 1,alignx center,growy");

		JTextPane txtpnGameDetails = new JTextPane();
		txtpnGameDetails.setBackground(SystemColor.control);
		txtpnGameDetails.setEditable(false);
		txtpnGameDetails.setText("Game details:\r\n\r\n-You are blue side\r\n\r\n-AI will take 3 seconds to make a move\r\n\r\n-When a tuz is marked the button color will be marked as red\r\n\r\n-If no moves remain use the 'Give up' option in the menu bar");
		frmToguzKorgool.getContentPane().add(txtpnGameDetails, "cell 4 5,grow");
	}

	public int[] getBoard() {
		return board;
	}

	public JFrame getFrame() {
		return frmToguzKorgool;
	}

	public JButton getStart() {
		return btnStartGame;
	}

	public boolean isPlayerStarting() {
		return playerStarting;
	}

	public int[] getScores() {
		return scores;
	}

	public void reset(){
		int[] boardN = {9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9};
		playerStarting = true;
		int[] scoresN = {0,0};
		board = boardN;
		scores = scoresN;
	}

}
