package View;

import Model.Cerveau;

import javax.swing.*;
import java.awt.*;

public class LevelGraphics extends JComponent {

    private Cerveau cerveau;

    public LevelGraphics(Cerveau cerveau) {
        this.cerveau = cerveau;
    }

    @Override
    protected void paintComponent(Graphics g) {
        // a few base variables
        Graphics2D drawable = (Graphics2D) g;
        int windowWidth = getSize().width;
        int windowHeight = getSize().height;

        // colors that will be used later
        Color presentSquareColor = new Color(14,188, 232); // untouched square of the waffle
        Color absentSquareColor = new Color(168, 114, 47); // table color aka when the waffle square is eaten
        Color poisonColor = new Color(3, 158, 8);          // poison color (duh)

        // dimensions of a single square relative to the window size
        int squareWidth = windowWidth/cerveau.largeur;
        int squareHeight = windowHeight/cerveau.hauteur;

        // drawing each square of the waffle
        for(int i = 0; i < cerveau.hauteur; i++) {
            for(int j = 0; j < cerveau.largeur; j++) {
                // color is set relative to square presence
                if(cerveau.gaufre.get(j).get(i)) { // square is present
                    drawable.setColor(presentSquareColor);

                } else { // square has been eaten
                    drawable.setColor(absentSquareColor);
                }
                // draw current waffle square with the correct color
                drawable.fillRect(squareWidth * i, squareHeight * j, squareWidth, squareHeight);
            }
        }

        // drawing the outlines of each square.
        // Needs to be done after the squares have been drawn or else squares will overlap
        drawable.setColor(Color.BLUE);
        for(int i = 0; i < cerveau.hauteur; i++) {
            for (int j = 0; j < cerveau.largeur; j++) {
                if(cerveau.gaufre.get(j).get(i)) {
                    // only draw the bottom and left line. Avoids redundancy
                    drawable.drawLine(squareWidth * i, squareHeight * (j + 1), squareWidth * (i + 1), squareHeight * (j + 1));
                    drawable.drawLine(squareWidth * (i + 1), squareHeight * j, squareWidth * (i + 1), squareHeight * (j + 1));
                }
            }
        }
        // draw the poison circle. Weird coordinate modifiers allow the circle to not touch the side of the square
        drawable.setColor(poisonColor);
        drawable.fillOval((int) (squareWidth * 0.1), (int) (squareHeight * 0.1), (int) (squareWidth * 0.8), (int) (squareHeight * 0.8));
    }
}
