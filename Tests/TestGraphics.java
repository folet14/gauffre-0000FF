package Tests;

import Model.Cerveau;
import View.Window;

public class TestGraphics {

    public static void main(String[] args) throws InterruptedException {
        int height = 5;
        int width = 5;
        Cerveau cerveau = new Cerveau();
        cerveau.cuisiner(width, height, 1);
        Window window = new Window(cerveau);
        window.run();

        Thread.sleep(1000);
        cerveau.manger(0, 3, 3);
        window.getLevelGraphics().repaint();

        Thread.sleep(1000);
        cerveau.manger(0, 1, 4);
        window.getLevelGraphics().repaint();

        Thread.sleep(1000);
        cerveau.manger(0, 1, 1);
        window.getLevelGraphics().repaint();

        // last square cannot be eaten. Following code shouldn't change anything
        Thread.sleep(1000);
        cerveau.manger(0, 0, 0);
        window.getLevelGraphics().repaint();
    }

}
