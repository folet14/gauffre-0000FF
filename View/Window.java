package View;

import Model.Cerveau;

import javax.swing.*;
import java.awt.*;

public class Window implements Runnable{
    private JFrame frame;
    private final Cerveau cerveau;

    private LevelGraphics levelGraphics;
    private JPanel historyPanel;
    private JPanel endGamePanel;
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

        // create info panel and place all widgets inside
        createInfoPanel(mainPanel);

        JPanel topPanel = new JPanel(new GridLayout(1,2));
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // create end game panel and add all widgets inside
        createEndGamePanel(topPanel);

        // create history panel and place all widgets inside
        createHistoryPanel(topPanel);

        // set GUI to "game in progress"
        setEndGameGUI(false);

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
    * Updates the bottom info labels with information found in the model
     */
    public void updateInfos() {
        if(cerveau.whowin() == 0) {
            currentPlayerLabel.setText("C'est au joueur " + cerveau.whosturn() + " !");
        } else {
            currentPlayerLabel.setText("Le joueur " + cerveau.whojustplayed() + " gagne la partie !");
        }
        currentTurnLabel.setText("Tour n°" + (cerveau.howmanyturn() + 1));
    }

    /*
    * Function changes the top panel.
    * True : the game is done. Display the end game buttons
    * False : the is in progress. Display the history buttons
     */
    public void setEndGameGUI(boolean isEndGame) {
        if(isEndGame) { // the game is done
            historyPanel.setVisible(false);
            endGamePanel.setVisible(true);
        } else { // the game is in progress
            historyPanel.setVisible(true);
            endGamePanel.setVisible(false);
        }
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
    private void createHistoryPanel(JPanel topPanel) {
        // create the history panel at the top
        historyPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(historyPanel);

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

    /*
    * Function creates the top end game panel that contains two buttons.
    * Buttons are a replay button and a quit button.
    * /!\ this panel should not be visible if history panel is visible /!\
     */
    private void createEndGamePanel(JPanel topPanel) {
        // create the end game panel at the top
        endGamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(endGamePanel);

        // create the end game buttons
        JButton replayButton = new JButton("rejouer");
        JButton quitButton = new JButton("quitter");

        // add action listeners to the buttons
        replayButton.addActionListener(actionEvent -> {
            cerveau.resetGame();
            setEndGameGUI(false);
            updateInfos();
            levelGraphics.repaint();
        });
        quitButton.addActionListener(actionEvent -> frame.dispose());

        // add buttons to the end game panel
        endGamePanel.add(quitButton);
        endGamePanel.add(replayButton);
    }
}
