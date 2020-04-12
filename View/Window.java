package View;

import Model.Cerveau;

import javax.swing.*;

public class Window implements Runnable{
    private JFrame frame;
    private Cerveau cerveau;
    private LevelGraphics levelGraphics;

    public Window(Cerveau cerveau) {
        this.cerveau = cerveau;
    }

    @Override
    public void run() {
        // create the window
        frame = new JFrame("0000FF waffle");

        // createthe graphical representation of the game
        levelGraphics = new LevelGraphics(cerveau);

        // add the game to the window
        frame.add(levelGraphics);

        // When red X is clicked
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // give it a default size and lets roll
        frame.setSize(800, 800);
        frame.setVisible(true);
    }

    public LevelGraphics getLevelGraphics() {
        return levelGraphics;
    }
}
