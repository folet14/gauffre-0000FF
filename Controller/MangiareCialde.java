package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Model.Cerveau;
import View.LevelGraphics;
import View.Window;

public class MangiareCialde extends MouseAdapter {
	private Cerveau cerveau;
	private Window window;
	private LevelGraphics levelGraphics;
	
	public MangiareCialde(Cerveau cerveau, Window window) {
		this.cerveau = cerveau;
		this.window = window;
		this.levelGraphics = window.getLevelGraphics();
		// set the info label to the current player
		window.updateInfos();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(cerveau.whowin() == 0) {
			int x = e.getX() / (levelGraphics.getWidth()/cerveau.largeur);
			int y = e.getY() / (levelGraphics.getHeight()/cerveau.hauteur);
			
			System.out.println("Joueur " + cerveau.whosturn() + " mange la case " + x + ", " + y);
			cerveau.manger(cerveau.whosturn(), x, y);

			// set the info label to the current player
			window.updateInfos();
			
			levelGraphics.repaint();
		} else {
			System.out.println("Joueur " + cerveau.whojustplayed() + " gagne la partie !");

			// set the info label to display the winner
			window.updateInfos();
		}
	}
	
}
