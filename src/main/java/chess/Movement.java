package chess;

public class Movement {

    Promotion_GUI promotionGui = new Promotion_GUI();

    public boolean validateMovement (int currentRow, int currentColumn, int aimedRow, int aimedColumn){

        String currentPiece = GUI.button[currentRow][currentColumn].getToolTipText();
        String aimedPiece = GUI.button[aimedRow][aimedColumn].getToolTipText();

        // Pawn movements
        boolean shortForwardWhite = aimedRow == currentRow-1 && aimedPiece.equals("  ") && currentColumn == aimedColumn;
        boolean shortForwardBlack = aimedRow == currentRow+1 && aimedPiece.equals("  ") && currentColumn == aimedColumn;
        boolean diagonalKillWhite = (aimedRow == currentRow-1) && (aimedColumn == currentColumn+1 || aimedColumn == currentColumn-1);
        boolean diagonalKillBlack = (aimedRow == currentRow+1) && (aimedColumn == currentColumn+1 || aimedColumn == currentColumn-1);

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
                if(shortForwardWhite || ((aimedPiece.startsWith("B") && diagonalKillWhite))){
                    if(aimedRow == 0){
                        promotionGui.startPromotionGUI(currentPiece.substring(0,1), aimedRow, aimedColumn);
                    }
                    return true;
                }
                return false;
            // Horse
            case "WH":
                return (aimedPiece.startsWith("B") || aimedPiece.equals("  ")) && (L1 || L2 || L3 || L4);
            // Bishop
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
                if(shortForwardBlack || (aimedPiece.startsWith("W") && diagonalKillBlack)){
                    if(aimedRow == 7){
                        promotionGui.startPromotionGUI(currentPiece.substring(0,1), aimedRow, aimedColumn);
                    }
                    return true;
                }
                return false;
            // Horse
            case "BH":
                return (aimedPiece.startsWith("W") || aimedPiece.equals("  ")) && (L1 || L2 || L3 || L4);
            // Bishop
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
                        if(!GUI.button[currentRow][i].getToolTipText().equals("  ")){
                            return false;
                        }
                    }
                }
                else{
                    for(int i = currentColumn-1; i > aimedColumn; i--){
                        if(!GUI.button[currentRow][i].getToolTipText().equals("  ")){
                            return false;
                        }
                    }
                }
                return true;
            case "vertical":
                if(aimedRow - currentRow > 0){
                    for(int i = currentRow+1; i < aimedRow; i++){
                        if(!GUI.button[i][currentColumn].getToolTipText().equals("  ")){
                            return false;
                        }
                    }
                }
                else{
                    for(int i = currentRow-1; i > aimedRow; i--){
                        if(!GUI.button[i][currentColumn].getToolTipText().equals("  ")){
                            return false;
                        }
                    }
                }
                return true;
            case "diagonal":
                if(currentRow < aimedRow && currentColumn < aimedColumn){
                    for(int i = currentRow+1; i < aimedRow;i++){
                        for(int e = currentColumn+1; e < aimedColumn;e++){
                            if(!GUI.button[i][e].getToolTipText().equals("  ")){
                                return false;
                            }
                        }
                    }
                }
                else if(currentRow < aimedRow){
                    for(int i = currentRow+1; i < aimedRow;i++){
                        for(int e = currentColumn-1; e > aimedColumn;e--){
                            if(!GUI.button[i][e].getToolTipText().equals("  ")){
                                return false;
                            }
                        }
                    }
                }
                else if(currentRow > aimedRow && currentColumn < aimedColumn){
                    for(int i = currentRow-1; i > aimedRow;i--){
                        for(int e = currentColumn+1; e < aimedColumn;e++){
                            if(!GUI.button[i][e].getToolTipText().equals("  ")){
                                return false;
                            }
                        }
                    }
                }
                else if(currentRow > aimedRow){
                    for(int i = currentRow-1; i > aimedRow;i--){
                        for(int e = currentColumn-1; e > aimedColumn;e--){
                            if(!GUI.button[i][e].getToolTipText().equals("  ")){
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
    public boolean checkPlayerPiece(int row, int column){

        String currentPiece = GUI.button[row][column].getToolTipText();
        switch (Main.currentPlayer){
            case 1:
                if(!currentPiece.startsWith("W")){
                    return false;
                }
                break;
            case 2:
                if(!currentPiece.startsWith("B")){
                    return false;
                }
                break;
        }
        return true;
    }
}
