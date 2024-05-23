package chess;

public class Main {

    public static void main(String[] args){

        // Variables
        boolean game = true;
        Board board = new Board();
        Behavior behavior = new Behavior();

        // Preparations for the game
        board.setUpBoard();
        board.printBoard();

        // Game loop
        while(game){

            // Standard game behaviour
            behavior.getMovement();
            board.printBoard();

            // Player swap
            // switch (behavior.currentPlayer){
            //    case 1:
            //        behavior.currentPlayer = 2;
            //        break;
            //    case 2:
            //        behavior.currentPlayer = 1;
            //        break;
            //}
        }

    }
}