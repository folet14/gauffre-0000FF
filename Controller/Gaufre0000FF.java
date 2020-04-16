package Controller;

import View.MenuWindow;

public class Gaufre0000FF{
	
	public static void main(String[] args) {
		MenuWindow menuWindow = new MenuWindow();
		MenuController menuController = new MenuController(menuWindow);
		menuWindow.addObserver(menuController);
		menuWindow.run();

	}

}
