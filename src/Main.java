
import Controller.Keyboard;
import Controller.Mouse;

import Model.Game;
import View.GameMenu;
import View.Window;



@SuppressWarnings("unused")
public class Main {
    public static void main(String[] args){
    	GameMenu menu = new GameMenu();     // lance le jeu via le menu
    	menu.setVisible(true);
    	
    	
    	
    	// Sans Menu 
    	/*
        Window window = new Window("Game",31,30);
        Game game = Game.getInstance(window, false,false, 1,"name");
     		
        Keyboard keyboard = new Keyboard(game);
        Mouse mouse = new Mouse(game);
        window.setKeyListener(keyboard);
        window.setMouseListener(mouse);
        */
        }
}    
