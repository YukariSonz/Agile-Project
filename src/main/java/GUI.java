import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.stream.IntStream;


public class GUI extends JPanel {
    // The 18 holes of the game.
    private JButton[] holes; // all the holes of the game. // write a getter method for this array
    private JFrame frame;
    private int scorePlayer1, scorePlayer2; // players's scores.
    private JLabel sp1,sp2;
    private JCheckBox player1CheckBox,player2CheckBox;
    private boolean whoseTurn;

    private JMenuItem m3;

    GUI(int [] koorgools, boolean whoseTurn, int scorePlayer, int scoreAiPlayer) {
        holes = new JButton[18]; // size 18 => 9 holes for each player.
        this.whoseTurn = whoseTurn;
        scorePlayer1 = scorePlayer;
        scorePlayer2 = scoreAiPlayer;
        fillHoles(koorgools);
        build();
    }

    public static void main(String[] args) {
        int[] i = {9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9};
        GUI gui = new GUI(i,true,0,0);
    }


    // Builds the GUI of the board.
    private void build() {
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialises the menu for the window.
        JMenu m = new JMenu("Options");
        JMenuBar m1 = new JMenuBar();
        m1.add(m);
        m3 = new JMenuItem("Give up");
        m.add(m3);
        frame.setJMenuBar(m1);


        // Intialises the main panels.
        JPanel player1Holes = new JPanel(new GridLayout(0, 9, 5, 5));
        player1Holes.setPreferredSize(new Dimension(800, 200));
        JPanel kazans = new JPanel(new GridLayout(0, 2));
        JPanel player2Holes = new JPanel(new GridLayout(0, 9, 5, 5));
        player2Holes.setPreferredSize(new Dimension(800, 200));
        player1Holes.setBackground(new Color(28, 102, 251));
        player2Holes.setBackground(new Color(250, 203, 46));

        // Builds the holes panels.
        putPlayerHoles(player1Holes,9,true);
        putPlayerHoles(player2Holes,  18,false);

        // Initialises the player's kazans panels and the components displayed inside of them.
        JPanel player1Kazan = new JPanel();
        JPanel player2Kazan = new JPanel();
        player1Kazan.setLayout(new BoxLayout(player1Kazan, BoxLayout.PAGE_AXIS));
        player2Kazan.setLayout(new BoxLayout(player2Kazan, BoxLayout.PAGE_AXIS));
        player1CheckBox = new JCheckBox();
        player2CheckBox = new JCheckBox();
        sp1 = new JLabel("Score AIPlayer : " + scorePlayer1);
        sp1.setAlignmentX(CENTER_ALIGNMENT);
        sp2 = new JLabel("Score Player : " + scorePlayer2);
        sp2.setAlignmentX(CENTER_ALIGNMENT);

        // Sets the background of the kazans panels and adds the components to them.
        // Each of them bas a label the score of the player and a checkbox ticked if it is the corresponding player's turn to play.
        player1Kazan.add(Box.createRigidArea(new Dimension(50, 50)));
        player1Kazan.setBackground(new Color(250, 203, 46));
        player1Kazan.add(sp1);
        player1Kazan.add(Box.createRigidArea(new Dimension(10, 10)));
        player1Kazan.add(player1CheckBox);

        player2Kazan.add(Box.createRigidArea(new Dimension(50, 50)));
        player2Kazan.setBackground(new Color(28, 102, 251));
        player2Kazan.add(sp2);
        player2Kazan.add(Box.createRigidArea(new Dimension(10, 10)));
        player2Kazan.add(player2CheckBox);
        player2CheckBox.setSelected(true);

        // Adds the kazans and the holes panels to the screen.
        kazans.add(player1Kazan);
        kazans.add(player2Kazan);
        add(player1Holes, BorderLayout.SOUTH);
        add(kazans, BorderLayout.CENTER);
        add(player2Holes, BorderLayout.NORTH);
        frame.setContentPane(this);
        frame.setVisible(true);
        frame.pack();
    }


    // Change the number of holes of the given hole to the new number of holes
    private void changeNumberOfKoorgools(int index, int newNumberOfHoles) {
        holes[index].setText("" + newNumberOfHoles);
    }

    private void fillHoles(int[] k)
    {
        for(int i=0; i<18; i++)
        {

            holes[i] = new JButton(""+k[i]);
        }
    }

    JButton[] getHoles()
    {
        return holes;
    }

    JCheckBox getPlayer1CheckBox()
    {
        return player1CheckBox;
    }

    JCheckBox getPlayer2CheckBox()
    {
        return player2CheckBox;
    }

    JLabel getSp1()
    {
        return sp1;
    }

    JLabel getSp2()
    {
        return sp2;
    }

    // For a given panel ( here the players sides ) puts the nine holes with 9 initially written on it indicating that there are initially 9 stones inside.
    private void putPlayerHoles(JPanel panel,int upperBound, boolean side) {
        if(!side) panel.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        for (int i = upperBound - 9; i < upperBound; i++) {
            JPanel p = new JPanel(new BorderLayout());
            JButton hole = holes[i];
//            int [] scores = {1,10,10,10,10,10,10,10,10,9,9,9,9,9,9,9,9,9};
  //            hole.addActionListener(e -> update(scores,false,0,0));
            p.add(hole, BorderLayout.CENTER);
            if (side) {
                p.setBackground(new Color(28, 102, 251));
                p.add(new JLabel("          " + ((i % 9) +1)), BorderLayout.SOUTH);
            } else {
                p.setBackground(new Color(250, 203, 46));
                p.add(new JLabel("          " + ((i % 9) + 1 )), BorderLayout.NORTH);
            }
            panel.add(p);
        }
    }

    // Displays a pop up window whenever one of the players wins.
    public void winPopUp() {

        JDialog d = new JDialog();
        d.getContentPane().add(new JLabel("Congratulations you won the game. Click on OK to go back to the welcome screen."), BorderLayout.CENTER);
        JButton closeIt = new JButton("OK");
        closeIt.addActionListener(e -> {
            endGame();
            d.dispose();
        });
        d.getContentPane().add(closeIt, BorderLayout.SOUTH);
        d.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        d.setVisible(true);
        d.pack();
    }

    public void losePopUp() {

        JDialog d = new JDialog();
        d.getContentPane().add(new JLabel("You lost the game. Click on OK to go back to the welcome screen."), BorderLayout.CENTER);
        JButton closeIt = new JButton("OK");
        closeIt.addActionListener(e -> {
            endGame();
            d.dispose();
        });
        d.getContentPane().add(closeIt, BorderLayout.SOUTH);
        d.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        d.setVisible(true);
        d.pack();
    }

    // Ends the game and redirects the player to the home screen.
    private void endGame() {
        frame.dispose();
    }

    private void updateScores(int score1, int score2)
    {
      scorePlayer1 = score1;
      scorePlayer2 = score2;
      sp1.setText("Score AIPlayer : " + scorePlayer1);
      sp2.setText("Score Player : " + scorePlayer2);
    }

    private void updateTurns(boolean wT)
    {
        whoseTurn = wT;
        if(whoseTurn) {
            player1CheckBox.setSelected(false);
            player2CheckBox.setSelected(true);
        }
        else
        {
            player1CheckBox.setSelected(true);
            player2CheckBox.setSelected(false);
        }
    }

    public void update(int [] koorgools, boolean whoseTurn, int scoreAiPlayer,int scorePlayer) { // change the names later
            for (int i = 0; i < holes.length; i++) {
                changeNumberOfKoorgools(i, koorgools[i]);
            }
            updateScores(scoreAiPlayer,scorePlayer);
            updateTurns(whoseTurn);
        }

    public int indexOfButton(JButton b)
    {
        return IntStream.range(0,holes.length).filter( i -> b == holes[i]).findFirst().orElse(-1);
    }

    public void markHoleAsTuz(int index)
    {
     holes[index].setForeground(Color.RED);
    }

    public JMenuItem getGive() {
    	return m3;
    }


}
