package chess;

import javax.swing.*;
import java.util.Objects;

public class WinnerGUI extends JPanel {

    public void startWinnerGUI(int player, String whiteName, String blackName){

        // Declare GUI
        JFrame frame = new JFrame("Winner!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Define GUI variables
        ImageIcon icon = GUI.upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/Icon.png")))));
        frame.setIconImage(icon.getImage());
        frame.setSize(270,150);
        frame.setLayout(null);

        // Winner text
        JLabel winnerText = new JLabel();
        winnerText.setBounds(70,30,200,40);

        switch(player){
            case 1:
                winnerText.setText(whiteName + " is the winner!");
                break;
            case 2:
                winnerText.setText(blackName + " is the winner!");
                break;
        }

        frame.getContentPane().add(winnerText);
        frame.setVisible(true);
    }
}
