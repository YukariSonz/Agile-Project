
import static org.junit.Assert.*;
import org.junit.Test;

import javax.swing.*;


public class GUITest {

    public static GUI g;
    // This method does two consecutive moves and checks ,for each of them,if the number of koorgools in every hole , the scores and the turns are properly displayed on the GUI. 
    @Test
    public void main() {
        int[] initial = {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9};
        g = new GUI(initial, true, 0, 0);
        int[] firstMoveScores = {1, 10, 10, 10, 10, 10, 10, 10, 10, 9, 9, 9, 9, 9, 9, 9, 9, 9};
        g.getHoles()[0].addActionListener(e-> checkMoveFromHole(firstMoveScores,false,0,0));
        int[] secondMoveScores = {2, 10, 10, 10, 10, 10, 10, 10,10,10,1,10,10,10,10,10,10,10 };
        g.getHoles()[10].addActionListener(e-> checkMoveFromHole(secondMoveScores,true,10,0));
    }

  // Returns the updated holes after a move has been done. 
  public JButton[] updatedHoles(int [] newScores, boolean turn, int s1 , int s2)
  {
      g.update(newScores,turn,s1,s2);
      return g.getHoles();
  }

    // Checks that the GUI properly displays all the info after the moves are executed. 
    public void checkMoveFromHole(int[] newScores ,boolean turn, int s1 , int s2 )
    {
        JButton[] newHoles = updatedHoles(newScores,turn,s1,s2);
        checkTurns(turn);
        checkScores(s1,s2);
        for(int i=0; i<newScores.length;i++)
        {
            try
            {
                assertEquals(""+newScores[i],newHoles[i].getText());
                System.out.println("Test passed");
            }
            catch(AssertionError a)
            {
                System.out.println("Test failed");
            }

        }
    }

    // Checks that the turns are properly displayed via the checkboxes. 
    public void checkTurns(boolean turn)
    {
        try {
            if (turn) {
                assertFalse(g.getPlayer1CheckBox().isSelected());
                assertTrue(g.getPlayer2CheckBox().isSelected());
            } else {
                assertFalse(g.getPlayer2CheckBox().isSelected());
                assertTrue(g.getPlayer1CheckBox().isSelected());
            }
            System.out.println("Turn test passed");

        }
        catch (AssertionError a)
        {
            System.out.println("Turn test failed");
        }
    }

    // Asserts that the scores are correctly displayed on the GUI. 
    public void checkScores(int score1, int score2)
    {
        try {
            assertEquals("Score AIPlayer : " + score1, g.getSp1().getText());
            assertEquals("Score Player : " + score2, g.getSp2().getText());
            System.out.println("Score test passed");
        }
        catch (AssertionError a)
        {
            System.out.println("Score test failed");
        }
    }

}




