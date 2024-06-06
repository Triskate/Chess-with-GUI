package chess;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class MenuGUI extends JPanel {

    GUI gui = new GUI();

    public void startMenuGUI(){

        // Declare GUI
        JFrame frame = new JFrame("Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Define GUI variables
        ImageIcon icon = GUI.upscaleIcon(new ImageIcon((Objects.requireNonNull(getClass().getResource("/icons/Icon.png")))));
        frame.setIconImage(icon.getImage());
        frame.setSize(500,500);
        frame.setLayout(null);

        // Declare menu buttons
        JButton play = new JButton();
        JButton options = new JButton();
        JButton quit = new JButton();

        play.setBounds(95,100,300,50);
        options.setBounds(95,200,300,50);
        quit.setBounds(95,300,300,50);

        Color white = new Color(255,255,255);

        play.setBackground(white);
        options.setBackground(white);
        quit.setBackground(white);

        play.setText("PLAY");
        options.setText("OPTIONS");
        quit.setText("QUIT");

        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    frame.setVisible(false);
                    gui.startGUI();
                } catch (UnsupportedAudioFileException|LineUnavailableException|IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System. exit(0);
            }
        });

        frame.getContentPane().add(play);
        frame.getContentPane().add(options);
        frame.getContentPane().add(quit);
        frame.setVisible(true);
    }
}
