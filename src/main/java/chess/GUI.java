package chess;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class GUI extends JPanel{

    // Initiate classes
    Movement movement = new Movement();
    WinnerGUI winnerGUI = new WinnerGUI();

    // Main variables
    static JButton[][] button = new JButton[8][8];
    int clicks = 0;

    int currentRow = 0;
    int currentColumn;
    int aimedRow;
    int aimedColumn;

    Color selected = new Color(215,229, 88);
    Color currentColor;

    public void startGUI()
            throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        // Declare GUI
        JFrame frame = new JFrame("Chess");
        // Define closing operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Define GUI variables
        frame.setSize(700,455);
        frame.setLayout(null);

        // Set up sounds
        URL move = new URL("file:./src/main/resources/sounds/move.wav");
        AudioInputStream movementAis = AudioSystem.getAudioInputStream(move);
        Clip clip = AudioSystem.getClip();
        clip.open(movementAis);

        // Create and initiate frame fields
        JTextField nameWhites = new JTextField();
        JLabel turnIndicator = new JLabel();
        JTextField nameBlacks = new JTextField();

        nameWhites.setEditable(true);
        nameWhites.setBounds(500,320,100,25);
        nameWhites.setText("White");
        nameWhites.setHorizontalAlignment(JTextField.CENTER);
        frame.add( nameWhites );

        turnIndicator.setText(nameWhites.getText() + "'s turn!");
        turnIndicator.setBounds(500,200,100,25);
        turnIndicator.setHorizontalAlignment(JTextField.CENTER);
        frame.add( turnIndicator );

        nameBlacks.setEditable(true);
        nameBlacks.setBounds(500,80,100,25);
        nameBlacks.setText("Black");
        nameBlacks.setHorizontalAlignment(JTextField.CENTER);
        frame.add( nameBlacks );

        // Create and initiate buttons

        int x = 10;
        int y = 10;

        for (int i = 0; i < 8; i++){
            for (int e = 0; e < 8; e++){
                button[i][e] = new JButton("  ");
                button[i][e].setName(i + "" + e);
                button[i][e].setBounds(x,y,50,50);
                button[i][e].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        JButton clickedButton = (JButton) e.getSource();

                        switch(clicks){
                            case 0:

                                currentRow = Integer.parseInt(clickedButton.getName().substring(0,1));
                                currentColumn = Integer.parseInt(clickedButton.getName().substring(1,2));
                                currentColor = button[currentRow][currentColumn].getBackground();
                                button[currentRow][currentColumn].setBackground(selected);
                                clicks++;
                                break;

                            case 1:

                                aimedRow = Integer.parseInt(clickedButton.getName().substring(0,1));
                                aimedColumn = Integer.parseInt(clickedButton.getName().substring(1,2));

                                String currentPiece = button[currentRow][currentColumn].getToolTipText();
                                String aimedPiece = button[aimedRow][aimedColumn].getToolTipText();

                                // Make all the validations needed before moving the piece
                                if(movement.checkPlayerPiece(currentRow, currentColumn) && movement.validateMovement(currentRow, currentColumn, aimedRow, aimedColumn)){
                                    if(aimedPiece.equals("BK") || aimedPiece.equals("WK")){
                                        winnerGUI.startWinnerGUI(Main.currentPlayer, nameWhites.getText(), nameBlacks.getText());
                                        frame.setVisible(false);
                                    }
                                    //currentPiece = checkPromotion(currentRow, currentColumn, aimedRow);
                                    button[aimedRow][aimedColumn].setToolTipText(currentPiece);
                                    button[aimedRow][aimedColumn].setIcon(button[currentRow][currentColumn].getIcon());
                                    button[currentRow][currentColumn].setToolTipText("  ");
                                    button[currentRow][currentColumn].setIcon(null);
                                    clip.setFramePosition(0);
                                    clip.start();

                                    // Swap players at the end of the turn
                                    switch (Main.currentPlayer){
                                        case 1:
                                            turnIndicator.setText(nameBlacks.getText() + "'s turn!");
                                            Main.currentPlayer = 2;
                                            break;
                                        case 2:
                                            turnIndicator.setText(nameWhites.getText() + "'s turn!");
                                            Main.currentPlayer = 1;
                                            break;
                                    }
                                }
                                button[currentRow][currentColumn].setBackground(currentColor);
                                clicks--;
                                break;
                        }
                    }
                });
                x = x + 50;
            }
            y = y + 50;
            x = 10;
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
    public static ImageIcon upscaleIcon(ImageIcon original){
        Image originalImage = original.getImage();
        Image upscaledImage = originalImage.getScaledInstance(53, 53, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(upscaledImage);
    }
}
