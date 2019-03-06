import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 Test class for Player class,

 @author Alexandros Doganis
 3/12/18
 */

public class AIPlayerTest {
    /**
     *  test move functionality
     */
    @Test
    public void pMoveTest() {
        AIPlayer p1 = new AIPlayer(true);
        AIPlayer p2 = new AIPlayer(false);
        Board board = new Board();

        Board tempB = board.copyBoard();
        board = p1.calcMove(board);

        boolean same = true;
        for(int i = 0; i < 18; i++) {
            if(tempB.getBoardState()[i].getNumberOfKorgools() != board.getBoardState()[i].getNumberOfKorgools()) { same = false; }
        }

        assertTrue(!same);

        Board tempC = board.copyBoard();
        board = p2.calcMove(board);

        boolean newSame = true;
        for(int i = 0; i < 18; i++) {
            if(tempC.getBoardState()[i].getNumberOfKorgools() != board.getBoardState()[i].getNumberOfKorgools()) { newSame = false; }
        }

        assertTrue(!newSame);
    }
}
