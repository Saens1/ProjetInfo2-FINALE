package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Model.Game;


public class Keyboard implements KeyListener {
    private Game game;

    public Keyboard(Game game) {
        this.game = game;
    }

    
    //lance des actions lorsqu'on presse une certaine touche
    @Override
    public void keyPressed(KeyEvent event) {//binding
        int key = event.getKeyCode();

        switch (key) {
        case KeyEvent.VK_RIGHT:
            game.movePlayer(1, 0);
            break;
        case KeyEvent.VK_LEFT:
            game.movePlayer(-1, 0);
            break;
        case KeyEvent.VK_DOWN:
            game.movePlayer(0, 1);
            break;
        case KeyEvent.VK_UP:
            game.movePlayer(0, -1);
             break;
         case KeyEvent.VK_SPACE:
             game.actionAttack();//attaque corps à corps
             break;
         case KeyEvent.VK_Q:
             game.stop();
             break;
         case KeyEvent.VK_T:
             game.shoot();
             break;
        case KeyEvent.VK_P:
             game.Pause();
             break;
        case KeyEvent.VK_S:
        	 game.backup();//sauvegarde
        	 break;
        case KeyEvent.VK_A:
       	     game.passagePorte();
       	     break;
        case KeyEvent.VK_E:
      	     game.useBomb();
      	     break;
        case KeyEvent.VK_G:
     	     game.useGrenade();
     	     break;
        case KeyEvent.VK_1:
        	 game.changeMap(1,false);
        	 break;
        case KeyEvent.VK_2:
       	 	 game.changeMap(2,false);
       	 	 break;
        case KeyEvent.VK_3:
        	 game.changeMap(3,false); 
        	 break;
        case KeyEvent.VK_4:
       	 	game.changeMap(4,false);
       	 	break;
        case KeyEvent.VK_5:
       	 	game.changeMap(5,false);
       	 	break;
        case KeyEvent.VK_M:
       	 	game.zoom();
       	 	break;
        	
        case KeyEvent.VK_R:
        	game.run();//courir
       	 	break;
        case KeyEvent.VK_Z:
        	game.teleportation();
       	 	break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
