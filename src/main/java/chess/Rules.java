package chess;

public class Rules {

    public boolean validateMovement (int currentRow, int currentColumn, int aimedRow, int aimedColumn){

        String currentPiece = Board.main_board[currentRow][currentColumn];
        String aimedPiece = Board.main_board[aimedRow][aimedColumn];

        // Pawn movements
        boolean shortForward = aimedRow == currentRow-1 && aimedPiece.equals("  ");
        boolean diagonalKill = (aimedRow == currentRow-1) && (aimedColumn == currentColumn+1 || aimedColumn == currentColumn-1);

        // Horse movements
        boolean L1 = aimedRow == currentRow+2 && (aimedColumn == currentColumn+1 || aimedColumn == currentColumn-1);
        boolean L2 = aimedRow == currentRow-2 && (aimedColumn == currentColumn+1 || aimedColumn == currentColumn-1);
        boolean L3 = aimedRow == currentRow+1 && (aimedColumn == currentColumn+2 || aimedColumn == currentColumn-2);
        boolean L4 = aimedRow == currentRow-1 && (aimedColumn == currentColumn+2 || aimedColumn == currentColumn-2);

        // Bishop movement
        boolean diagonal = Math.abs(currentRow - aimedRow) == Math.abs(currentColumn - aimedColumn);

        // Tower movements
        boolean up = aimedRow == currentRow && aimedColumn < currentColumn;
        boolean down = aimedRow == currentRow && aimedColumn > currentColumn;
        boolean left = aimedRow < currentRow && aimedColumn == currentColumn;
        boolean right = aimedRow > currentRow && aimedColumn == currentColumn;

        // King movement
        boolean shortStep = (aimedRow == currentRow-1 || aimedRow == currentRow+1 || aimedRow == currentRow) &&
                            (aimedColumn == currentColumn-1 || aimedColumn == currentColumn+1 || aimedColumn == currentColumn);

        // Queen movements
        boolean vertical = (aimedRow == currentRow && aimedColumn != currentColumn);
        boolean horizontal = (aimedRow != currentRow && aimedColumn == currentColumn);;

        switch(currentPiece){

            // Validation for white pieces
            // Pawn
            case "WP":
                return shortForward || (aimedPiece.startsWith("B") && diagonalKill);
            // Horse
            case "WH":
                return (aimedPiece.startsWith("B") || aimedPiece.equals("  ")) && (L1 || L2 || L3 || L4);
            // Bishop (Need to fix jumping through pieces)
            case "WB":
                return (aimedPiece.startsWith("B") || aimedPiece.equals("  ")) &&
                       diagonal && clearPath(currentRow, currentColumn, aimedRow, aimedColumn);
            // Tower
            case "WT":
                return (aimedPiece.startsWith("B") || aimedPiece.equals("  ")) &&
                       (up || down || left || right) && clearPath(currentRow, currentColumn, aimedRow, aimedColumn);
            // King
            case "WK":
                return (aimedPiece.startsWith("B") || aimedPiece.equals("  ")) && shortStep;
            // Queen
            case "WQ":
                return (aimedPiece.startsWith("B") || aimedPiece.equals("  ")) &&
                       (horizontal || vertical || diagonal) && clearPath(currentRow, currentColumn, aimedRow, aimedColumn);

            // Validation for black pieces
            // Pawn
            case "BP":
                return shortForward || (aimedPiece.startsWith("W") && diagonalKill);
            // Horse
            case "BH":
                return (aimedPiece.startsWith("W") || aimedPiece.equals("  ")) && (L1 || L2 || L3 || L4);
            // Bishop (Need to fix jumping through pieces)
            case "BB":
                return (aimedPiece.startsWith("W") || aimedPiece.equals("  ")) &&
                       diagonal && clearPath(currentRow, currentColumn, aimedRow, aimedColumn);
            // Tower
            case "BT":
                return (aimedPiece.startsWith("W") || aimedPiece.equals("  ")) &&
                       (up || down || left || right) && clearPath(currentRow, currentColumn, aimedRow, aimedColumn);
            // King
            case "BK":
                return (aimedPiece.startsWith("W") || aimedPiece.equals("  ")) && shortStep;
            // Queen
            case "BQ":
                return (aimedPiece.startsWith("W") || aimedPiece.equals("  ")) &&
                       (horizontal || vertical || diagonal) && clearPath(currentRow, currentColumn, aimedRow, aimedColumn);
        }
        return false;
    }
    public boolean clearPath(int currentRow, int currentColumn, int aimedRow, int aimedColumn){

        String direction = "";

        if(aimedRow == currentRow && aimedColumn != currentColumn){
            direction = "horizontal";
        }
        else if(aimedRow != currentRow && aimedColumn == currentColumn){
            direction = "vertical";
        }
        else if(Math.abs(currentRow - aimedRow) == Math.abs(currentColumn - aimedColumn)){
            direction = "diagonal";
        }

        switch (direction){
            case "horizontal":
                if(aimedColumn - currentColumn > 0){
                    for(int i = currentColumn+1; i < aimedColumn; i++){
                        if(!Board.main_board[currentRow][i].equals("  ")){
                            return false;
                        }
                    }
                }
                else{
                    for(int i = currentColumn-1; i > aimedColumn; i--){
                        if(!Board.main_board[currentRow][i].equals("  ")){
                            return false;
                        }
                    }
                }
                return true;
            case "vertical":
                if(aimedRow - currentRow > 0){
                    for(int i = currentRow+1; i < aimedRow; i++){
                        if(!Board.main_board[i][currentColumn].equals("  ")){
                            return false;
                        }
                    }
                }
                else{
                    for(int i = currentRow-1; i > aimedRow; i--){
                        if(!Board.main_board[i][currentColumn].equals("  ")){
                            return false;
                        }
                    }
                }
                return true;
            case "diagonal":
                if(currentRow < aimedRow && currentColumn < aimedColumn){
                    for(int i = currentRow+1; i < aimedRow;i++){
                        for(int e = currentColumn+1; e < aimedColumn;e++){
                            if(!Board.main_board[i][e].equals("  ")){
                                return false;
                            }
                        }
                    }
                }
                else if(currentRow < aimedRow && currentColumn > aimedColumn){
                    for(int i = currentRow+1; i < aimedRow;i++){
                        for(int e = currentColumn-1; e > aimedColumn;e--){
                            if(!Board.main_board[i][e].equals("  ")){
                                return false;
                            }
                        }
                    }
                }
                else if(currentRow > aimedRow && currentColumn < aimedColumn){
                    for(int i = currentRow-1; i > aimedRow;i--){
                        for(int e = currentColumn+1; e < aimedColumn;e++){
                            if(!Board.main_board[i][e].equals("  ")){
                                return false;
                            }
                        }
                    }
                }
                else if(currentRow > aimedRow && currentColumn > aimedColumn){
                    for(int i = currentRow-1; i > aimedRow;i--){
                        for(int e = currentColumn-1; e > aimedColumn;e--){
                            if(!Board.main_board[i][e].equals("  ")){
                                return false;
                            }
                        }
                    }
                }
                return true;
            default:
                return false;
        }
    }
}
