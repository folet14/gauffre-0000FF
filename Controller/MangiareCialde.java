package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Model.Cerveau;
import View.LevelGraphics;
import View.GameWindow;

public class MangiareCialde extends MouseAdapter {
	private final Cerveau cerveau;
	private final GameWindow gameWindow;
	private final LevelGraphics levelGraphics;
	private final IA ia;
	
	public MangiareCialde(Cerveau cerveau, GameWindow gameWindow) {
		this.cerveau = cerveau;
		this.gameWindow = gameWindow;
		this.levelGraphics = gameWindow.getLevelGraphics();
		// set the info label to the current player
		this.ia = new IAAleatoire(cerveau, gameWindow);
		gameWindow.updateInfos();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		boolean aJouer;
		do {
			int x = e.getX() / (levelGraphics.getWidth() / cerveau.largeur);
			int y = e.getY() / (levelGraphics.getHeight() / cerveau.hauteur);
			aJouer = cerveau.jouerCoup(cerveau.whosturn(), x, y) != 0;
		} while (!aJouer);	

		levelGraphics.repaint();

		// set the info label to the current player
		gameWindow.updateInfos();

		// if it's the end of the game, notify the GUI
		if (cerveau.whowin() != 0) {
			gameWindow.setEndGameGUI(true);
		} else {
			ia.jouer();
		}
	}
	
}
