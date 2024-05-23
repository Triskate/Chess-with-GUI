package chess;

import java.util.Scanner;

public class Behavior {

    Scanner scanner = new Scanner(System.in);
    Rules rules = new Rules();

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
        checkIfInBoard(currentRow, currentColumn);
        checkPlayerPiece(currentRow, currentColumn);

        // Receive coordinates chosen to move and validate them
        System.out.println("Please introduce the column you want to move the piece to");
        aimedColumn = Integer.parseInt(scanner.nextLine()) - 1;
        System.out.println("Please introduce the row you want to move the piece to");
        aimedRow = Integer.parseInt(scanner.nextLine()) - 1;
        checkIfInBoard(currentRow, currentColumn);
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

        String currentPiece = Board.main_board[currentRow][currentColumn];
        // String aimedPiece = Board.main_board[aimedRow][aimedRow]; (Will be useful if I make a graveyard)

        if(rules.validateMovement(currentRow,currentColumn,aimedRow,aimedColumn)){
            Board.main_board[aimedRow][aimedColumn] = currentPiece;
            Board.main_board[currentRow][currentColumn] = "  ";
        }else{
            System.out.println("Invalid movement, try again");
            getMovement();
        }
    }

    public void checkIfInBoard(int row, int column){
        if(!(row >= 0 && row <= 7) && (column >= 0 && column <= 7)){
            System.out.println("Error: wrong coordinates (out of bounds)");
            getMovement();
        }
    }
}
