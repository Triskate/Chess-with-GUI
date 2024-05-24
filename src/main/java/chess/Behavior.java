package chess;

import java.util.Scanner;

public class Behavior {

    Scanner scanner = new Scanner(System.in);
    Movement movement = new Movement();

    // Current player 1 equals to White and 2 equals to Black
    int currentPlayer = 1;

    public void getMovement(){

        int currentRow;
        int currentColumn;

        int aimedRow;
        int aimedColumn;

        switch(currentPlayer){
            case 1:
                System.out.println("White turn!");
                break;
            case 2:
                System.out.println("Black turn!");
                break;
            default:
                System.out.println("Error: actual player not defined");
                break;
        }

        // Receive piece to move and check is valid
        System.out.println("Please introduce the column of the piece you want to move");
        currentColumn = Integer.parseInt(scanner.nextLine()) - 1;
        System.out.println("Please introduce the row of the piece you want to move");
        currentRow = Integer.parseInt(scanner.nextLine()) - 1;
        checkIfInBounds(currentRow, currentColumn);
        checkPlayerPiece(currentRow, currentColumn);

        // Receive coordinates chosen to move and validate them
        System.out.println("Please introduce the column you want to move the piece to");
        aimedColumn = Integer.parseInt(scanner.nextLine()) - 1;
        System.out.println("Please introduce the row you want to move the piece to");
        aimedRow = Integer.parseInt(scanner.nextLine()) - 1;
        checkIfInBounds(currentRow, currentColumn);
        movementValidation(currentRow,currentColumn,aimedRow,aimedColumn);

    }
    public void checkPlayerPiece(int row, int column){

        String currentPiece = Board.main_board[row][column];
        switch (currentPlayer){
            case 1:
                if(!currentPiece.startsWith("W")){
                    System.out.println("Error: selected piece is not white");
                    getMovement();
                }
                break;
            case 2:
                if(!currentPiece.startsWith("B")){
                    System.out.println("Error: selected piece is not black");
                    getMovement();
                }
                break;
        }
    }

    public void movementValidation(int currentRow, int currentColumn, int aimedRow, int aimedColumn){

        String currentPiece;
        String aimedPiece = Board.main_board[aimedRow][aimedRow];

        if(movement.validateMovement(currentRow,currentColumn,aimedRow,aimedColumn)){

            if(aimedPiece.equals("BK") || aimedPiece.equals("WK")){
                Main.game = false;
            }

            currentPiece = checkPromotion(currentRow, currentColumn, aimedRow);
            Board.main_board[aimedRow][aimedColumn] = currentPiece;
            Board.main_board[currentRow][currentColumn] = "  ";
        }else{
            System.out.println("Invalid movement, try again");
            getMovement();
        }
    }

    public void checkIfInBounds(int row, int column){
        if(!(row >= 0 && row <= 7) && (column >= 0 && column <= 7)){
            System.out.println("Error: wrong coordinates (out of bounds)");
            getMovement();
        }
    }

    public String checkPromotion(int currentRow, int currentColumn, int aimedRow){

        String currentPiece = Board.main_board[currentRow][currentColumn];

        switch (currentPiece){
            case "WP":
                if(aimedRow == 0){
                    System.out.print("Select one of the following pieces to replace the pawn: WH WB WT WQ\n");
                    String selectedPiece = scanner.nextLine();
                    if(selectedPiece.equals("WH") || selectedPiece.equals("WB") || selectedPiece.equals("WT") ||selectedPiece.equals("WQ")){
                        return selectedPiece;
                    }
                    else{
                        System.out.println("Error: Selected piece is not valid");
                        checkPromotion(currentRow, currentColumn, aimedRow);
                    }
                }
            case "BP":
                if(aimedRow == 8){
                    System.out.print("Select one of the following pieces to replace the pawn: BH BB BT BQ\n");
                    String selectedPiece = scanner.nextLine();
                    if(selectedPiece.equals("BH") || selectedPiece.equals("BB") || selectedPiece.equals("BT") ||selectedPiece.equals("BQ")){
                        return selectedPiece;
                    }
                    else{
                        System.out.println("Error: Selected piece is not valid");
                        checkPromotion(currentRow, currentColumn, aimedRow);
                    }
                }
            default:
                return currentPiece;
        }
    }
}
