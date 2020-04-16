package Controller;

import java.util.Random;

import Model.Cerveau;
import View.LevelGraphics;
import View.Window;

public class IAAleatoire {
    private final Cerveau cerveau;
    private final Window window;
    private final LevelGraphics levelGraphics;
    private final Random random;

    public IAAleatoire(Cerveau cerveau, Window window) {
        this.cerveau = cerveau;
        this.window = window;
        this.levelGraphics = window.getLevelGraphics();
		// set the info label to the current player
        window.updateInfos();
        this.random = new Random();
    }

    public void jouer() {
        boolean aJouer;
        do {
            int x = random.nextInt(cerveau.largeur);
            int y = random.nextInt(cerveau.hauteur);
            aJouer = cerveau.jouerCoup(cerveau.whosturn(), x, y) != 0;
        } while(!aJouer);

		levelGraphics.repaint();

		// set the info label to the current player
		window.updateInfos();

		// if it's the end of the game, notify the GUI
		if (cerveau.whowin() != 0) {
			window.setEndGameGUI(true);
		}
    }
}