package chess;

public class Board {

    // Main board that will display on the UI
    static String[][] main_board = new String[8][8];

    // Set up the initial pieces on the main board
    public void setUpBoard(){

        String empty = "  ";

        String black_pawn = "BP";
        String black_horse = "BH";
        String black_bishop = "BB";
        String black_tower = "BT";
        String black_king = "BK";
        String black_queen = "BQ";

        String white_pawn = "WP";
        String white_horse = "WH";
        String white_bishop = "WB";
        String white_tower = "WT";
        String white_king = "WK";
        String white_queen = "WQ";

        // Fill all spaces with empty to not have nullPointer exceptions
        for(int i = 0; i < main_board.length; i++){
            for(int e = 0; e < main_board.length; e++){
                main_board[i][e] = empty;
            }
        }

        // Fill top rows with black pieces
        main_board[0][0] = black_tower;
        main_board[0][1] = black_horse;
        main_board[0][2] = black_bishop;
        main_board[0][3] = black_king;
        main_board[0][4] = black_queen;
        main_board[0][5] = black_bishop;
        main_board[0][6] = black_horse;
        main_board[0][7] = black_tower;
        for(int i = 0; i < main_board.length; i++){
            main_board[1][i] = black_pawn;
        }

        // Fill bottom rows with white pieces
        for(int i = 0; i < main_board.length; i++){
            main_board[6][i] = white_pawn;
        }
        main_board[7][0] = white_tower;
        main_board[7][1] = white_horse;
        main_board[7][2] = white_bishop;
        main_board[7][3] = white_king;
        main_board[7][4] = white_queen;
        main_board[7][5] = white_bishop;
        main_board[7][6] = white_horse;
        main_board[7][7] = white_tower;

    }

    // Temporal console board print until I design the UI
    public void printBoard(){

        System.out.print("\n          1       2       3       4       5       6       7      8    ");
        for(int i = 0; i < main_board.length; i++){
            System.out.print("\n      |------||------||------||------||------||------||------||------|\n");
            System.out.print("  " + (i+1) + "   ");
            for(int e = 0; e < main_board.length; e++){
                System.out.print("|  " + main_board[i][e] + "  |");
            }
            System.out.print("\n      |______||______||______||______||______||______||______||______|");
        }
        System.out.println();
    }
}
