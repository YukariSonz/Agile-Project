import java.util.Random;
import java.util.Scanner; //Only for non-GUI stage
/**
 * Main Game Class
 *
 * @Yilei Liang
 * @V1.0
 */
public class Game
{
    private Board currentBoard;
    private Player p1;
    private AIPlayer p2;
    private boolean whoseTurn; //True = P1's turn. False = P2's turn
    private int round;
    /**
     * Constructor for objects of class Game
     */
    public Game()
    {
        p1 = new Player(true);
        p2 = new AIPlayer(false);
        currentBoard = new Board();
        whoseTurn = true;
        round = 0;
    }

    public Game(boolean startSide){
        if(!startSide){
            p1 = new Player(startSide);
            p2 = new AIPlayer(!startSide);
            currentBoard = new Board();
            whoseTurn = false;
            round = 0;
        }
        else{
            p1 = new Player(true);
            p2 = new AIPlayer(false);
            currentBoard = new Board();
            whoseTurn = true;
            round = 0;
        }
    }
    
    public Game(int[] customBoard, int score1, int score2,  boolean startSide){
        //Custom Game
        if(!startSide){
            p1 = new Player(startSide);
            p2 = new AIPlayer(!startSide);
            currentBoard = new Board();
            for (int i =0; i<customBoard.length;i++){
                currentBoard.setBoardState(i,customBoard[i]);
            }
            p1.setScore(score1);
            p2.setScore(score2);
            whoseTurn = false;
            round = 0;
        }
        else{
            p1 = new Player(true);
            p2 = new AIPlayer(false);
            currentBoard = new Board();
            for (int i =0; i<customBoard.length;i++){
                currentBoard.setBoardState(i,customBoard[i]);
            }
            p1.setScore(score1);
            p2.setScore(score2);
            whoseTurn = true;
            round = 0;
        }
    }

    public Player getPlayer()
    {
        return p1;
    }

    public AIPlayer getAIPlayer()
    {
        return p2;
    }

    public boolean isWhoseTurn() {
        return whoseTurn;
    }

    /*
     * Check if someone won the game
     */
    public Player win(){
        int p1Score = p1.getScore();
        int p2Score = p2.getScore();
        if (p1Score >= 82){
            return p1;
        }
        else if (p2Score >= 82){
            return p2;
        }
        else{
            return null;
        }
    }


    /*
     * Update the score if there is a Tuz here
     */
    public void update(){
        boolean side = p1.getSide();
        if (!side){
            for (int i =0; i<9; i++){
                //If there is a hole in player side that is a Tuz
                //Then add the mark to the component side
                if (currentBoard.getBoardState()[i].getTuz()){
                    int currentScore=p1.getScore();
                    currentScore+=currentBoard.getBoardState()[i]. getNumberOfKorgools();
                    p1.setScore(currentScore);
                    currentBoard.getBoardState()[i].setNumberOfKorgools(0);
                }
            } 
            for (int i =9; i<18; i++){
                //If there is a hole in component side that is a Tuz
                //Then add the mark to the player
                if (currentBoard.getBoardState()[i].getTuz()){
                    int currentScore=p2.getScore();
                    currentScore+=currentBoard.getBoardState()[i]. getNumberOfKorgools();
                    p2.setScore(currentScore);
                    currentBoard.getBoardState()[i].setNumberOfKorgools(0);
                }
            }
        }
        else{
            for (int i =0; i<9; i++){
                if (currentBoard.getBoardState()[i].getTuz()){
                    int currentScore=p2.getScore();
                    currentScore+=currentBoard.getBoardState()[i]. getNumberOfKorgools();
                    p2.setScore(currentScore);
                    currentBoard.getBoardState()[i].setNumberOfKorgools(0);
                }
            } 
            for (int i =9; i<18; i++){
                if (currentBoard.getBoardState()[i].getTuz()){
                    int currentScore=p1.getScore();
                    currentScore+=currentBoard.getBoardState()[i]. getNumberOfKorgools();
                    p1.setScore(currentScore);
                    currentBoard.getBoardState()[i].setNumberOfKorgools(0);
                }
            }
        }
    }
    

    public Hole[] round(int index){
        //The game while continue until a winner is out
        boolean stageFinished = false;
        /*
         *   As player might have invalid move, we need to check if the player
         * finished a vaild move.
         */
        Board moveBoard = null;
        while (!stageFinished) {
            Board beforeMove = currentBoard.copyBoard();
            if (whoseTurn) {
                try {
					moveBoard = p1.move(beforeMove, index);
					stageFinished = true;
				} catch (InvalidMoveException e) {
					Hole[] bad = new Hole[1];
					return bad;
				}
            } else {
                moveBoard = p2.calcMove(beforeMove);
                if (!moveBoard.compareBoards(currentBoard)) {
                    stageFinished = true;
                    //Update the score of P2
                } else {
                    //System.out.println("Invaild Move on AI!");
                }
            }

        }
        whoseTurn = !whoseTurn;
        currentBoard = moveBoard; //Update the play board
        update();
        round++;
        /*System.out.println("Round: " + round);
        for (int i = 0; i < 18; i++) {
            System.out.println("Hole " + i + ": " + currentBoard.getBoardState()[i].getNumberOfKorgools());
        }
        System.out.println("P1 Score:" + p1.getScore());
        System.out.println("P2 Score:" + p2.getScore());
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        */
        if (win() != null) {
            return null; //Someone win the game
        }
        else
        {
            return currentBoard.getBoardState();
        }

    }

}
