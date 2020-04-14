package View;

import Model.Cerveau;

import javax.swing.*;
import java.awt.*;

public class Window implements Runnable{
    private JFrame frame;
    private Cerveau cerveau;

    private LevelGraphics levelGraphics;
    private JLabel infoLabel;

    public Window(Cerveau cerveau) {
        this.cerveau = cerveau;
    }

    @Override
    public void run() {
        // create the window
        frame = new JFrame("0000FF waffle");

        // create the main panel, all other widgets will be here
        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);

        // create and add the game to the main panel
        levelGraphics = new LevelGraphics(cerveau);
        mainPanel.add(levelGraphics, BorderLayout.CENTER);

        // create and add the bottom info text
        infoLabel = new JLabel("DEFAULT TEXT");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        mainPanel.add(infoLabel, BorderLayout.SOUTH);

        // When red X is clicked
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // give it a default size and lets roll
        frame.setSize(800, 800);
        frame.setVisible(true);
    }

    public LevelGraphics getLevelGraphics() {
        return levelGraphics;
    }

    public void setInfoLabel(String newText) {
        infoLabel.setText(newText);
    }
}
