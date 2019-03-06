/**
 * A Player of Toguz Korgool has a score and can make moves
 * Moves are inline with the Toguz Korgol ruleset
 *
 * @author Alexandros Doganis (13/11/2018)
 */
public class Player {
    //  private variables
    private int score;
    protected boolean isWhite;
    private boolean hasTuz;

    //  public methods

    /**
     *  Constructor initializes score to 0
     *  Sets side to given side
     *  @param side true if player is white side
     */
    public Player(boolean side) {
        score = 0;
        isWhite = side;
        hasTuz = false;
    }

    /**
     *  Gets side
     *
     *  @return boolean true if the player is white
     */
    public boolean getSide() { return isWhite; }

    /**
     *  Gets Score
     *
     *  @return score of player
     */
    public int getScore() { return score; }

    /**
     *  Sets score
     *
     *  @param newScore new score to assign to player
     */
    public void setScore(int newScore) { score = newScore; }

    /**
     *   Increments score by given value
     *
     *   @param increment increments value
     */
    public void incrementScore(int increment) { score += increment; }
    
    /**
    *   Checks if player has Tuz
    */
    public boolean checkTuz() { return hasTuz;}

    /**
     *  Determines if a desired move is valid
     *  Then makes move and updates score
     *
     * @param currBoard the current board state
     * @return the new board state
     */
    public Board move(Board currBoard, int moveHole) throws InvalidMoveException {
        int offset = 0; // offset for score update
        int lastHole = 0;

        // Check if move is within my side
        if(isWhite) {
            offset = 9;
            if(!(moveHole >= 0 && moveHole <= 8)) { throw new InvalidMoveException(); }
        }
        else {
            if(!(moveHole >= 9 && moveHole <= 17)) { throw new InvalidMoveException(); }
        }

        // Check if Tuz
        if(currBoard.getBoardState()[moveHole].getTuz()) { throw new InvalidMoveException(); }

        // Check desired move is not empty
        if(currBoard.getBoardState()[moveHole].getNumberOfKorgools() == 0) { throw new InvalidMoveException(); }

        // Case when only 1 Korgool is moved
        if(currBoard.getBoardState()[moveHole].getNumberOfKorgools() == 1) {
            currBoard.getBoardState()[moveHole].setNumberOfKorgools(0);
            currBoard.getBoardState()[(moveHole + 1) % 18].addKorgools(1);
        }

        //  Case when multiple are moved
        //  increment corresponding holes

        else{
            int amount = currBoard.getBoardState()[moveHole].getNumberOfKorgools() - 1;
            for(int i = 1; i <= amount; i++) {
                currBoard.getBoardState()[(moveHole + i) % 18].addKorgools(1);
            }
            currBoard.getBoardState()[moveHole].setNumberOfKorgools(1);
            lastHole = (moveHole+amount)%18;
        }
        //  reset moveHole to have 1
        
        
        
        //  Update Score
        /*
        if(currBoard.getBoardState()[moveHole + offset].getNumberOfKorgools() % 2 == 0 ) {
            score += currBoard.getBoardState()[i + offset].getNumberOfKorgools();
            currBoard.getBoardState()[i + offset].setNumberOfKorgools(0);
        }
        */
        if (isWhite){
            if (lastHole>=9){
                if (currBoard.getBoardState()[lastHole].getNumberOfKorgools() == 3 && !hasTuz && lastHole != 17 && !currBoard.getBoardState()[lastHole].getTuz() && !currBoard.getBoardState()[lastHole-9].getTuz()){
                    //SET TUZ
                    //System.out.println("Hole "+lastHole+" is now became Tuz, any Korgools drop on this whole will be scored");
                    currBoard.getBoardState()[lastHole].setTuz(); 
                    hasTuz = true;
                    score += currBoard.getBoardState()[lastHole].getNumberOfKorgools();
                    currBoard.getBoardState()[lastHole].setNumberOfKorgools(0);
                }
                if(currBoard.getBoardState()[lastHole].getNumberOfKorgools() % 2 == 0 ) {
                    score += currBoard.getBoardState()[lastHole].getNumberOfKorgools();
                    currBoard.getBoardState()[lastHole].setNumberOfKorgools(0);
                }
            }
        }
        else{
            if (lastHole<9){
                if (currBoard.getBoardState()[lastHole].getNumberOfKorgools() == 3 && !hasTuz && lastHole != 8 && !currBoard.getBoardState()[lastHole].getTuz() && !currBoard.getBoardState()[lastHole+9].getTuz()){
                    //SET TUZ
                    //System.out.println("Hole "+lastHole+" is now became Tuz, any Korgools drop on this whole will be scored");
                    currBoard.getBoardState()[lastHole].setTuz();  
                    hasTuz = true;
                    score += currBoard.getBoardState()[lastHole].getNumberOfKorgools();
                    currBoard.getBoardState()[lastHole].setNumberOfKorgools(0);
                }
                if(currBoard.getBoardState()[lastHole].getNumberOfKorgools() % 2 == 0 ) {
                    score += currBoard.getBoardState()[lastHole].getNumberOfKorgools();
                    currBoard.getBoardState()[lastHole].setNumberOfKorgools(0);
                }
            }
        }
        
        
        return currBoard;
    }
}
