package chess;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Promotion_GUI extends JPanel {

    public void startPromotionGUI(String Color, int aimedRow, int aimedColumn){

        // Declare GUI
        JFrame frame = new JFrame("Promotion");

        // Define GUI variables
        frame.setSize(270,150);
        frame.setLayout(null);

        // Promotion text
        JLabel promotionText = new JLabel();
        promotionText.setText("Promotion!");
        promotionText.setBounds(100,10,200,40);
        frame.getContentPane().add(promotionText);

        // Pieces buttons
        JButton horse = new JButton();
        JButton tower = new JButton();
        JButton bishop = new JButton();
        JButton queen = new JButton();

        // Declare values of the buttons
        horse.setBounds(20,50,50,50);
        tower.setBounds(75,50,50,50);
        bishop.setBounds(130,50,50,50);
        queen.setBounds(185,50,50,50);
        switch(Color){
            case "W":
                // White horse
                horse.setToolTipText("WH");
                horse.setIcon(GUI.upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/WH.png"))))));
                // White tower
                tower.setToolTipText("WT");
                tower.setIcon(GUI.upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/WT.png"))))));
                // White bishop
                bishop.setToolTipText("WB");
                bishop.setIcon(GUI.upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/WB.png"))))));
                // White queen
                queen.setToolTipText("WQ");
                queen.setIcon(GUI.upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/WQ.png"))))));
                break;
            case "B":
                // Black horse
                horse.setToolTipText("BH");
                horse.setIcon(GUI.upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/BH.png"))))));
                // Black tower
                tower.setToolTipText("BT");
                tower.setIcon(GUI.upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/BT.png"))))));
                // Black bishop
                bishop.setToolTipText("BB");
                bishop.setIcon(GUI.upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/BB.png"))))));
                // Black queen
                queen.setToolTipText("BQ");
                queen.setIcon(GUI.upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/BQ.png"))))));
                break;
            default:
                break;
        }
        horse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                executePromotion(horse.getToolTipText(), aimedRow, aimedColumn);
                frame.setVisible(false);
            }
        });
        tower.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                executePromotion(tower.getToolTipText(), aimedRow, aimedColumn);
                frame.setVisible(false);
            }
        });
        bishop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                executePromotion(bishop.getToolTipText(), aimedRow, aimedColumn);
                frame.setVisible(false);
            }
        });
        queen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                executePromotion(queen.getToolTipText(), aimedRow, aimedColumn);
                frame.setVisible(false);
            }
        });

        // Add buttons to the frame
        frame.getContentPane().add(horse);
        frame.getContentPane().add(tower);
        frame.getContentPane().add(bishop);
        frame.getContentPane().add(queen);

        // Set frame visible
        frame.setVisible(true);
    }

    public void executePromotion(String piece, int currentRow, int currentColumn){

        switch(piece){
            case "WH":
                GUI.button[currentRow][currentColumn].setToolTipText("WH");
                GUI.button[currentRow][currentColumn].setIcon(GUI.upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/WH.png"))))));
                break;
            case "WT":
                GUI.button[currentRow][currentColumn].setToolTipText("WT");
                GUI.button[currentRow][currentColumn].setIcon(GUI.upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/WT.png"))))));
                break;
            case "WB":
                GUI.button[currentRow][currentColumn].setToolTipText("WB");
                GUI.button[currentRow][currentColumn].setIcon(GUI.upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/WB.png"))))));
                break;
            case "WQ":
                GUI.button[currentRow][currentColumn].setToolTipText("WQ");
                GUI.button[currentRow][currentColumn].setIcon(GUI.upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/WQ.png"))))));
                break;
            case "BH":
                GUI.button[currentRow][currentColumn].setToolTipText("BH");
                GUI.button[currentRow][currentColumn].setIcon(GUI.upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/BH.png"))))));
                break;
            case "BT":
                GUI.button[currentRow][currentColumn].setToolTipText("BT");
                GUI.button[currentRow][currentColumn].setIcon(GUI.upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/BT.png"))))));
                break;
            case "BB":
                GUI.button[currentRow][currentColumn].setToolTipText("BB");
                GUI.button[currentRow][currentColumn].setIcon(GUI.upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/BB.png"))))));
                break;
            case "BQ":
                GUI.button[currentRow][currentColumn].setToolTipText("BQ");
                GUI.button[currentRow][currentColumn].setIcon(GUI.upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/BQ.png"))))));
                break;
        }

    }

}
