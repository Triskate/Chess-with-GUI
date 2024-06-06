package chess;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Main {

    // Main variables
    public static int currentPlayer = 1;

    public static void main(String[] args)
            throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        // Instantiate GUI
        GUI gui = new GUI();

        // Start GUI
        gui.startGUI();

    }
}