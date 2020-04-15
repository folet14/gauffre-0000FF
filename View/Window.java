package View;

import Model.Cerveau;

import javax.swing.*;
import java.awt.*;

public class Window implements Runnable{
    private JFrame frame;
    private Cerveau cerveau;

    private LevelGraphics levelGraphics;
    private JLabel currentPlayerLabel;
    private JLabel currentTurnLabel;

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

        //create historic panel and place all widgets inside
        createInfoPanel(mainPanel);

        //create history panel and place all widgets inside
        createHistoryPanel(mainPanel);

        // When red X is clicked
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // give it a default size and lets roll
        frame.setSize(800, 800);
        frame.setVisible(true);
    }

    public LevelGraphics getLevelGraphics() {
        return levelGraphics;
    }

    /*
    * Updates the bottom info labels
     */
    public void updateInfos() {
        currentPlayerLabel.setText("C'est au joueur " + cerveau.whosturn() + " !");
        currentTurnLabel.setText("Tour n°" + cerveau.howmanyturn() + 1);
    }

    /*
    * Create the the bottom info panel and adds all widgets.
    * Widgets are two labels : current player and turn number
     */
    private void createInfoPanel(JPanel mainPanel) {
        // create and add the info panel
        JPanel infoPanel = new JPanel(new BorderLayout());
        mainPanel.add(infoPanel, BorderLayout.SOUTH);

        // create the info font
        Font infoFont = new Font("Arial", Font.PLAIN, 30);

        // create and add the current player text
        currentPlayerLabel = new JLabel("DEFAULT TEXT");
        currentPlayerLabel.setFont(infoFont);
        infoPanel.add(currentPlayerLabel, BorderLayout.WEST);

        // create and add current turn
        currentTurnLabel = new JLabel("DEFAULT TEXT");
        currentTurnLabel.setFont(infoFont);
        infoPanel.add(currentTurnLabel, BorderLayout.EAST);
    }

    /*
    * Function creates the top history panel and adds all widgets.
    * Widgets are two buttons : undo and redo.
    * Both have a action listener linked to the model
     */
    private void createHistoryPanel(JPanel mainPanel) {
        // create and add the history panel at the top
        JPanel historyPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        mainPanel.add(historyPanel, BorderLayout.NORTH);

        // create the history buttons
        JButton undoButton = new JButton("arrière");
        JButton redoButton = new JButton("avant");

        // add action listeners to the buttons
        undoButton.addActionListener(actionEvent -> {
            cerveau.arriere();
            updateInfos();
            levelGraphics.repaint();
        });
        redoButton.addActionListener(actionEvent -> {
            cerveau.avant();
            updateInfos();
            levelGraphics.repaint();
        });

        // add buttons to the historic panel (top panel)
        historyPanel.add(undoButton);
        historyPanel.add(redoButton);
    }
}
