import java.util.Random;

/**
 * A AI Player of Toguz Korgool is a player with logic to make moves
 *
 * @author Alexandros Doganis (19/11/2018)
 */

public class AIPlayer extends Player {
    private Random rand;    // random number generator

    /**
     *  Constructor for AI player
     *
     *  @param side true if player is white
     */
    public AIPlayer(boolean side) {
        super(side);
        rand = new Random();
    }

    /*
     *  Calculates and makes own move with random integer
     */
    public Board calcMove(Board currBoard) {
        int move = rand.nextInt(9);

        int offset = 9;
        if(super.isWhite) { offset = 0; }

        try {
            return super.move(currBoard, move + offset);
        }
        catch(InvalidMoveException e) {
            return this.calcMove(currBoard);
        }

    }
}