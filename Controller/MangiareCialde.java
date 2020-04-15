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
	
	public MangiareCialde(Cerveau cerveau, Window window) {
		this.cerveau = cerveau;
		this.window = window;
		this.levelGraphics = window.getLevelGraphics();
		// set the info label to the current player
		window.updateInfos();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX() / (levelGraphics.getWidth()/cerveau.largeur);
		int y = e.getY() / (levelGraphics.getHeight()/cerveau.hauteur);

		cerveau.jouerCoup(cerveau.whosturn(), x, y);

		levelGraphics.repaint();

		// set the info label to the current player
		window.updateInfos();

		// if it's the end of the game, notify the GUI
		if (cerveau.whowin() != 0) {
			window.setEndGameGUI(true);
		}
	}
	
}
