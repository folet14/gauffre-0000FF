package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Model.Cerveau;
import View.LevelGraphics;
import View.Window;

public class MangiareCialde extends MouseAdapter {
	private final Cerveau cerveau;
	private final Window window;
	private final LevelGraphics levelGraphics;
	private final IAAleatoire ia;
	
	public MangiareCialde(Cerveau cerveau, Window window) {
		this.cerveau = cerveau;
		this.window = window;
		this.levelGraphics = window.getLevelGraphics();
		// set the info label to the current player
		window.updateInfos();
		this.ia = new IAAleatoire(cerveau, window);
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
		window.updateInfos();

		// if it's the end of the game, notify the GUI
		if (cerveau.whowin() != 0) {
			window.setEndGameGUI(true);
		}

		ia.jouer();
	}
	
}
