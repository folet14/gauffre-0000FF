package Controller;

import java.util.Random;

import Model.Cerveau;
import View.LevelGraphics;
import View.GameWindow;

public class IASimple implements IA {
    private final Cerveau cerveau;
    private final GameWindow window;
    private final LevelGraphics levelGraphics;
    private final Random random;

    public IASimple(Cerveau cerveau, GameWindow window) {
        this.cerveau = cerveau;
        this.window = window;
        this.levelGraphics = window.getLevelGraphics();
		// set the info label to the current player
        window.updateInfos();
        this.random = new Random();
    }

    @Override
    public void jouer() {
        int player = cerveau.whosturn();

        if (cerveau.gaufre.get(0).get(1) == false) {
            // coup gagnant colone
            cerveau.jouerCoup(player, 0, 1);
        } else if (cerveau.gaufre.get(1).get(0) == false) {
            // coup gagnant ligne
            cerveau.jouerCoup(player, 1, 0);
        } else {
            // coup aleatoire
            boolean coupPerdantInevitable = !cerveau.gaufre.get(2).get(0) && !cerveau.gaufre.get(1).get(1) && !cerveau.gaufre.get(0).get(2);
            boolean aJouer;
            do {
                int x = random.nextInt(cerveau.largeur);
                int y = random.nextInt(cerveau.hauteur);
                boolean estCoupPerdant = (x == 0 && y == 1) || (x == 1 && y == 0);

                if (estCoupPerdant && !coupPerdantInevitable) {
                    aJouer = false;
                } else {
                    aJouer = cerveau.jouerCoup(player, x, y) != 0;
                }
            } while(!aJouer);
        }

		levelGraphics.repaint();

		// set the info label to the current player
		window.updateInfos();

		// if it's the end of the game, notify the GUI
		if (cerveau.whowin() != 0) {
			window.setEndGameGUI(true);
		}
    }
}