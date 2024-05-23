package chess;

public class Rules {

    public boolean validateMovement (int currentRow, int currentColumn, int aimedRow, int aimedColumn){

        String currentPiece = Board.main_board[currentRow][currentColumn];
        String aimedPiece = Board.main_board[aimedRow][aimedColumn];

        switch(currentPiece){
            // Validation for white pieces

            // Pawn
            case "WP":
                if(aimedRow == currentRow-1 && aimedPiece.equals("  ")){
                    return true;
                }else if(aimedPiece.startsWith("B") &&
                        (aimedRow == currentRow-1) &&
                        (aimedColumn == currentColumn+1 || aimedColumn == currentColumn-1)){
                    return true;
                }else{
                    return false;
                }
            // Horse
            case "WH":
                if((aimedPiece.startsWith("B") || aimedPiece.equals("  ")) && (
                   (aimedRow == currentRow+2 && (aimedColumn == currentColumn+1 || aimedColumn == currentColumn-1)) ||
                   (aimedRow == currentRow-2 && (aimedColumn == currentColumn+1 || aimedColumn == currentColumn-1)) ||
                   (aimedRow == currentRow+1 && (aimedColumn == currentColumn+2 || aimedColumn == currentColumn-2)) ||
                   (aimedRow == currentRow-1 && (aimedColumn == currentColumn+2 || aimedColumn == currentColumn-2)))){
                    return true;
                }
                else{
                    return false;
                }
            case "WB":
                break;
            // Tower
            case "WT":
                if(aimedPiece.equals("  ") || aimedPiece.startsWith("B")) {
                    if (aimedRow > currentRow && aimedColumn == currentColumn) {
                        for (int i = currentRow; i < aimedRow; i++) {
                            if (!Board.main_board[i][currentColumn].equals("  ")) {
                                return false;
                            }
                        }
                        return true;
                    } else if (aimedRow < currentRow && aimedColumn == currentColumn) {
                        for (int i = aimedRow; i < currentRow; i++) {
                            if (!Board.main_board[i][currentColumn].equals("  ")) {
                                return false;
                            }
                        }
                        return true;
                    } else if (aimedRow == currentRow && aimedColumn > currentColumn) {
                        for (int i = currentColumn; i < aimedColumn; i++) {
                            if (!Board.main_board[currentRow][i].equals("  ")) {
                                return false;
                            }
                        }
                        return true;
                    } else if (aimedRow == currentRow && aimedColumn < currentColumn) {
                        for (int i = aimedColumn; i < currentColumn; i++) {
                            if (!Board.main_board[currentRow][i].equals("  ")) {
                                return false;
                            }
                        }
                        return true;
                    } else {
                        return false;
                    }
                }
            case "WK":
                break;
            case "WQ":
                break;

            // Validation for black pieces

            // Pawn
            case "BP":
                if(aimedRow == currentRow+1 && aimedPiece.equals("  ")){
                    return true;
                }else if(aimedPiece.startsWith("W") &&
                        (aimedRow == currentRow+1) &&
                        (aimedColumn == currentColumn+1 || aimedColumn == currentColumn-1)){
                    return true;
                }else{
                    return false;
                }
            // Horse
            case "BH":
                if((aimedPiece.startsWith("W") || aimedPiece.equals("  ")) && (
                   (aimedRow == currentRow+2 && (aimedColumn == currentColumn+1 || aimedColumn == currentColumn-1)) ||
                   (aimedRow == currentRow-2 && (aimedColumn == currentColumn+1 || aimedColumn == currentColumn-1)) ||
                   (aimedRow == currentRow+1 && (aimedColumn == currentColumn+2 || aimedColumn == currentColumn-2)) ||
                   (aimedRow == currentRow-1 && (aimedColumn == currentColumn+2 || aimedColumn == currentColumn-2)))){
                    return true;
                }
                else{
                    return false;
                }
            case "BB":
                break;
            // Tower
            case "BT":
                if(aimedPiece.equals("  ") || aimedPiece.startsWith("W")) {
                    if (aimedRow > currentRow && aimedColumn == currentColumn) {
                        for (int i = currentRow; i < aimedRow; i++) {
                            if (!Board.main_board[i][currentColumn].equals("  ")) {
                                return false;
                            }
                        }
                        return true;
                    } else if (aimedRow < currentRow && aimedColumn == currentColumn) {
                        for (int i = aimedRow; i < currentRow; i++) {
                            if (!Board.main_board[i][currentColumn].equals("  ")) {
                                return false;
                            }
                        }
                        return true;
                    } else if (aimedRow == currentRow && aimedColumn > currentColumn) {
                        for (int i = currentColumn; i < aimedColumn; i++) {
                            if (!Board.main_board[currentRow][i].equals("  ")) {
                                return false;
                            }
                        }
                        return true;
                    } else if (aimedRow == currentRow && aimedColumn < currentColumn) {
                        for (int i = aimedColumn; i < currentColumn; i++) {
                            if (!Board.main_board[currentRow][i].equals("  ")) {
                                return false;
                            }
                        }
                        return true;
                    } else {
                        return false;
                    }
                }
            case "BK":
                break;
            case "BQ":
                break;
        }
        return false;
    }
}
