import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class CustomGame extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField[] holes = new JTextField[18];
	private JRadioButton rdbtnPlayer;
	private JRadioButton rdbtnPlayer_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblPlayer;
	private JLabel lblPlayer_1;
	private JLabel[] holeNumbers = new JLabel[18];
	private JButton okButton;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblPlayerPoints;
	private JLabel lblPlayerPoints_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CustomGame dialog = new CustomGame();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CustomGame() {
		setTitle("Custom Game");
		setBounds(100, 100, 800, 800);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow][grow][grow][]", "[][][grow][grow][][]"));
			{
				lblPlayer = new JLabel("Player 1");
				panel.add(lblPlayer, "cell 0 0 9 1,alignx center");
			}
			{
				for(int i = 0 ;i<9;i++) {
					holeNumbers[i] = new JLabel(""+(i+1));
					panel.add(holeNumbers[i], "cell "+i+" 1,alignx center");
				}
				for(int i = 9 ;i<18;i++) {
					holeNumbers[i] = new JLabel(""+(i-8));
					panel.add(holeNumbers[i], "cell "+(i-9)+" 4,alignx center");
				}
			}
			{
				for(int i = 0 ;i<9;i++) {
					holes[i] = new JTextField();
					holes[i].setText("9");
					panel.add(holes[i], "cell "+i+" 2,grow");
					holes[i].setColumns(10);
				}
				for(int i = 9 ;i<18;i++) {
					holes[i] = new JTextField();
					holes[i].setText("9");
					panel.add(holes[i], "cell "+(i-9)+" 3,grow");
					holes[i].setColumns(10);
				}
			}
			{
				lblPlayer_1 = new JLabel("Player 2");
				panel.add(lblPlayer_1, "cell 0 5 9 1,alignx center");
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.EAST);
			panel.setLayout(new MigLayout("", "[65px,grow]", "[][23px][][][][][][][]"));
			{
				JLabel lblStartingPlayer = new JLabel("Starting Player");
				panel.add(lblStartingPlayer, "cell 0 0");
			}
			{
				rdbtnPlayer = new JRadioButton("Player 1");
				rdbtnPlayer.setSelected(true);
				buttonGroup.add(rdbtnPlayer);
				panel.add(rdbtnPlayer, "cell 0 1");
			}
			{
				rdbtnPlayer_1 = new JRadioButton("Player 2");
				buttonGroup.add(rdbtnPlayer_1);
				panel.add(rdbtnPlayer_1, "cell 0 2");
			}
			{
				lblPlayerPoints = new JLabel("Player1 Points");
				panel.add(lblPlayerPoints, "cell 0 4");
			}
			{
				textField = new JTextField("0");
				panel.add(textField, "cell 0 5,growx");
				textField.setColumns(10);
			}
			{
				lblPlayerPoints_1 = new JLabel("Player 2 Points");
				panel.add(lblPlayerPoints_1, "cell 0 7");
			}
			{
				textField_1 = new JTextField("0");
				panel.add(textField_1, "cell 0 8,growx");
				textField_1.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public int[] getValues() {
		int[] ret = new int[18];
		for(int i=0;i<18;i++) {
			try {
				ret[i]=Integer.parseInt(holes[i].getText());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(contentPanel, "Please only enter in numbers!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		return ret;
	}

	public boolean isPlayerStarting() {
		if(rdbtnPlayer.isSelected()) {
			return true;
		}
		return false;
	}
	
	public JButton getOK() {
		return okButton;
	}
	
	public int[] getScores() {
		int[] a = {Integer.parseInt(textField.getText()),Integer.parseInt(textField_1.getText())};
		return a;
	}
}
