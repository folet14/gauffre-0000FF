package Tests;

import Model.Cerveau;
import View.GameWindow;
import View.MenuWindow;

public class TestGraphics {

    public static void main(String[] args) throws InterruptedException {
//        int height = 5;
//        int width = 5;
//        Cerveau cerveau = new Cerveau();
//        cerveau.cuisiner(width, height, 1);
//        GameWindow gameWindow = new GameWindow(cerveau);
//        gameWindow.run();
//
//        Thread.sleep(1000);
//        cerveau.jouerCoup(0, 3, 3);
//        gameWindow.getLevelGraphics().repaint();
//
//        Thread.sleep(1000);
//        cerveau.jouerCoup(0, 1, 4);
//        gameWindow.getLevelGraphics().repaint();
//
//        Thread.sleep(1000);
//        cerveau.jouerCoup(0, 1, 1);
//        gameWindow.getLevelGraphics().repaint();
//
//        // last square cannot be eaten. Following code shouldn't change anything
//        Thread.sleep(1000);
//        cerveau.jouerCoup(0, 0, 0);
//        gameWindow.getLevelGraphics().repaint();

        MenuWindow menuWindow = new MenuWindow();
        menuWindow.run();
    }

}
