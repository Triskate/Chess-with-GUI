package chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GUI extends JPanel{

    // Initiate movement
    Movement movement = new Movement();

    // Main variables
    static JButton[][] button = new JButton[8][8];
    int clicks = 0;

    int currentRow = 0;
    int currentColumn;
    int aimedRow;
    int aimedColumn;

    public void startGUI(){

        // Declare GUI
        JFrame frame = new JFrame("Chess");
        // Define closing operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Define GUI variables
        frame.setSize(500,500);
        frame.setLayout(new GridLayout(8,8));

        // Create and initiate buttons
        for (int i = 0; i < 8; i++){
            for (int e = 0; e < 8; e++){
                button[i][e] = new JButton("  ");
                button[i][e].setSize(50,50);
                button[i][e].setName(i + "" + e);
                button[i][e].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        JButton clickedButton = (JButton) e.getSource();

                        switch(clicks){
                            case 0:

                                currentRow = Integer.parseInt(clickedButton.getName().substring(0,1));
                                currentColumn = Integer.parseInt(clickedButton.getName().substring(1,2));
                                clicks++;
                                break;

                            case 1:

                                aimedRow = Integer.parseInt(clickedButton.getName().substring(0,1));
                                aimedColumn = Integer.parseInt(clickedButton.getName().substring(1,2));

                                String currentPiece = button[currentRow][currentColumn].getToolTipText();
                                String aimedPiece = button[aimedRow][aimedColumn].getToolTipText();

                                // Make all the validations needed before moving the piece
                                if(movement.checkPlayerPiece(currentRow, currentColumn) &&movement.validateMovement(currentRow, currentColumn, aimedRow, aimedColumn)){
                                    if(aimedPiece.equals("BK") || aimedPiece.equals("WK")){
                                        // Closing operation
                                    }
                                    //currentPiece = checkPromotion(currentRow, currentColumn, aimedRow);
                                    button[aimedRow][aimedColumn].setToolTipText(currentPiece);
                                    button[aimedRow][aimedColumn].setIcon(button[currentRow][currentColumn].getIcon());
                                    button[currentRow][currentColumn].setToolTipText("  ");
                                    button[currentRow][currentColumn].setIcon(null);

                                    // Swap players at the end of the turn
                                    switch (Main.currentPlayer){
                                        case 1:
                                            Main.currentPlayer = 2;
                                            break;
                                        case 2:
                                            Main.currentPlayer = 1;
                                            break;
                                    }
                                }
                                clicks--;
                                System.out.println(Main.currentPlayer);
                                System.out.println(clicks);
                                break;
                        }

                    }
                });
            }
        }
        setUpBoard();

        // Fill frame with buttons
        for (int i = 0; i < 8; i++){
            for (int e = 0; e < 8; e++){
                frame.getContentPane().add(button[i][e]);
            }
        }
        frame.setVisible(true);
    }
    public void setUpBoard(){

        // Variables
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

        Color white = new Color(255,255,255);
        Color black = new Color(0,0,0);

        // Set background color of the board
        for (int i = 0; i < 8; i++){
            for (int e = 0; e < 8; e++){
                if((e-i) % 2 != 0) {
                    button[i][e].setBackground(black);
                }
                else{
                    button[i][e].setBackground(white);
                }
            }
        }

        // Fill board with empties
        for (int i = 0; i < 8; i++){
            for (int e = 0; e < 8; e++){
                button[i][e].setToolTipText(empty);
            }
        }

        // Fill top rows with black pieces
        // Black tower
        button[0][0].setToolTipText(black_tower);
        button[0][0].setIcon(upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/BT.png"))))));
        // Black horse
        button[0][1].setToolTipText(black_horse);
        button[0][1].setIcon(upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/BH.png"))))));
        // Black bishop
        button[0][2].setToolTipText(black_bishop);
        button[0][2].setIcon(upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/BB.png"))))));
        // Black king
        button[0][3].setToolTipText(black_king);
        button[0][3].setIcon(upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/BK.png"))))));
        // Black queen
        button[0][4].setToolTipText(black_queen);
        button[0][4].setIcon(upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/BQ.png"))))));
        // Black bishop
        button[0][5].setToolTipText(black_bishop);
        button[0][5].setIcon(upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/BB.png"))))));
        // Black horse
        button[0][6].setToolTipText(black_horse);
        button[0][6].setIcon(upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/BH.png"))))));
        // Black tower
        button[0][7].setToolTipText(black_tower);
        button[0][7].setIcon(upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/BT.png"))))));
        // Black pawns
        for(int i = 0; i < button.length; i++){
            button[1][i].setToolTipText(black_pawn);
            button[1][i].setIcon(upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/BP.png"))))));
        }

        // Fill bottom rows with white pieces
        // White pawns
        for(int i = 0; i < button.length; i++){
            button[6][i].setToolTipText(white_pawn);
            button[6][i].setIcon(upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/WP.png"))))));
        }
        // White tower
        button[7][0].setToolTipText(white_tower);
        button[7][0].setIcon(upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/WT.png"))))));
        // White horse
        button[7][1].setToolTipText(white_horse);
        button[7][1].setIcon(upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/WH.png"))))));
        // White bishop
        button[7][2].setToolTipText(white_bishop);
        button[7][2].setIcon(upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/WB.png"))))));
        // White King
        button[7][3].setToolTipText(white_king);
        button[7][3].setIcon(upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/WK.png"))))));
        // White queen
        button[7][4].setToolTipText(white_queen);
        button[7][4].setIcon(upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/WQ.png"))))));
        // White bishop
        button[7][5].setToolTipText(white_bishop);
        button[7][5].setIcon(upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/WB.png"))))));
        // White horse
        button[7][6].setToolTipText(white_horse);
        button[7][6].setIcon(upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/WH.png"))))));
        // White tower
        button[7][7].setToolTipText(white_tower);
        button[7][7].setIcon(upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/WT.png"))))));
    }

    // Function to upscale images used as icons
    public ImageIcon upscaleIcon(ImageIcon original){
        Image originalImage = original.getImage();
        Image upscaledImage = originalImage.getScaledInstance(60, 55, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(upscaledImage);
    }
}
