package chess;

public class Main {

    // Main variable
    public static boolean game = true;
    public static int currentPlayer = 1;

    public static void main(String[] args){

        // Instances of classes
        GUI gui = new GUI();

        // Preparations for the game
        gui.startGUI();

    }
}