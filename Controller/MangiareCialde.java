package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Model.Cerveau;
import View.LevelGraphics;

public class MangiareCialde extends MouseAdapter {
	private Cerveau cerveau;
	private LevelGraphics levelGraphics;
	
	public MangiareCialde(Cerveau cerveau, LevelGraphics levelGraphics) {
		this.cerveau = cerveau;
		this.levelGraphics = levelGraphics;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(cerveau.whowin() == 0) {
			int x = e.getX() / (levelGraphics.getWidth()/cerveau.largeur);
			int y = e.getY() / (levelGraphics.getHeight()/cerveau.hauteur);
			
			System.out.println("Joueur " + cerveau.whosturn() + " mange la case " + x + ", " + y);
			cerveau.manger(cerveau.whosturn(), x, y);
			
			levelGraphics.repaint();
		} else {
			System.out.println("Joueur " + cerveau.whojustplayed() + " gagne la partie !");
		}
	}
	
}
