package Controller;

import Model.Cerveau;
import View.GameWindow;
import View.MenuWindow;

import java.util.Observable;
import java.util.Observer;

public class Gaufre0000FF{
	private static final int WIDTH = 5;
	private static final int HEIGHT = 5;
	private static final int NB_PLAYER = 2;
	
	public static void main(String[] args) {
		Cerveau cerveau = new Cerveau();
		GameWindow gameWindow = new GameWindow(cerveau);

		cerveau.cuisiner(WIDTH, HEIGHT, NB_PLAYER);
		gameWindow.run();

		gameWindow.getLevelGraphics().addMouseListener(new MangiareCialde(cerveau, gameWindow));

	}

}
