package Controller;

import Model.Cerveau;
import View.Window;

public class Gaufre0000FF {
	private static final int WIDTH = 5;
	private static final int HEIGHT = 5;
	private static final int NB_PLAYER = 2;
	
	public static void main(String[] args) {
		Cerveau cerveau = new Cerveau();
		Window window = new Window(cerveau);
		
		cerveau.cuisiner(WIDTH, HEIGHT, NB_PLAYER);
		window.run();
		
		window.getLevelGraphics().addMouseListener(new MangiareCialde(cerveau, window.getLevelGraphics()));
	}
}
