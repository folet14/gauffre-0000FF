package Controller;

import View.MenuWindow;

import java.util.Observable;
import java.util.Observer;

public class MenuController implements Observer {

    private MenuWindow menuWindow;

    public MenuController(MenuWindow menuWindow) {
        this.menuWindow = menuWindow;
    }

    @Override
    public void update(Observable observable, Object o) {
        System.out.println("observer notified");
        System.out.println((String) o);
    }
}
