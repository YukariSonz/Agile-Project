/*
 * The Board class represents the board.
 * A board includes 18 holes (9 for each player)
 * There are methods to return the current board state and to changes the state
 * 
 * Vitesh Dav Soni (12/11/2018)
 */
public class Board 
{
    
    private Hole[] arrayOfHoles; //The current state of the board.
    
    public Board()
    {
        arrayOfHoles = new Hole[18];
        for(int i = 0; i < arrayOfHoles.length; i++)
        {
            arrayOfHoles[i] = new Hole();
        }
        // 0 to 8 belongs to the player. 9 to 17 belongs to computer (AI Player)
    }
    
    /*
     * Method to return the board state 
     * @return the new board state
     */
    public Hole[] getBoardState()
    {
        return arrayOfHoles;
    }
    
    /*
     * Method to change the board state
     * Changes the number of the korgools in a particular hole.
     * This method can be used to set the initial board state (if the player
     * chooses to play a custom game)
     * 
     * @return the new board state
     * @param index: index of hole you want to change.
     * @param numberOfKorgoolsThisHoleShouldHave: number of korgools you want to have in this hole
     */
    public Hole[] setBoardState(int index, int numberOfKorgoolsThisHoleShouldHave)
    {
        arrayOfHoles[index].setNumberOfKorgools(numberOfKorgoolsThisHoleShouldHave);
        return arrayOfHoles;
    }
    
    /*
     * Method of compare two different boards
     * 
     * @param anotherBoard: Another board to compare
     * @return: if two board has the same value or not
     */
    public boolean compareBoards(Board anotherBoard) {
        Hole[] anotherArray = anotherBoard.getBoardState();
        for (int i =0;i < arrayOfHoles.length; i++){
            if (arrayOfHoles[i].getNumberOfKorgools() != anotherArray[i].getNumberOfKorgools()){
                return false;
            }
        }
        return true;
    }
    
    
    /*
     * Copy this board
     * As if we do a = b in JAVA, a will have the same ADDRESS as B instead
     * of creat a copy of b
     * 
     * @return: a copy of this board
     */
    public Board copyBoard(){
        Board copied = new Board();
        for (int i =0;i < arrayOfHoles.length; i++){
            int iNum = arrayOfHoles[i].getNumberOfKorgools();
            copied.setBoardState(i,iNum);
            boolean tuzState = arrayOfHoles[i].getTuz();
            if (tuzState){
                copied.getBoardState()[i].setTuz();
            }
        }
        return copied;
    }
}
