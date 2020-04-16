package View;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class MenuWindow extends Observable implements Runnable {
    private JFrame frame;

    @Override
    public void run() {
        // create the window
        frame = new JFrame("0000FF waffle");

        // create the main panel
        JPanel mainPanel = new JPanel(new GridLayout(4, 1));
        frame.add(mainPanel);

        // create the panel and the label for the title
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mainPanel.add(titlePanel);
        JLabel titleLabel = new JLabel("The Wafle Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titlePanel.add(titleLabel);

        // create the spinner model for the rows and columns selection
        SpinnerModel inputSpinnerModel = new SpinnerNumberModel(5, 2, 10, 1);

        // create the rows input panel
        JPanel rowsPanel = new JPanel(new GridLayout(1, 2));
        JLabel rowsLabel = new JLabel("nombre de ligne :");
        rowsPanel.add(rowsLabel);
        JSpinner rowsSpinner = new JSpinner(inputSpinnerModel);
        rowsPanel.add(rowsSpinner);
        mainPanel.add(rowsPanel);

        // create the collumn input panel
        JPanel colsPanel = new JPanel(new GridLayout(1, 2));
        JLabel colsLabel = new JLabel("nombre de colonne :");
        colsPanel.add(colsLabel);
        JSpinner colsSpinner = new JSpinner(inputSpinnerModel);
        colsPanel.add(colsSpinner);
        mainPanel.add(colsPanel);

        // create the play buttons widget and panel
        JPanel playPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mainPanel.add(playPanel);
        JButton playButton = new JButton("JOUER !");
        playButton.setFont(new Font("Arial", Font.BOLD, 15));
        playPanel.add(playButton);

        playButton.addActionListener(actionEvent -> {
            System.out.println("notifing observers");
            notifyObservers();
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setVisible(true);
    }
}
