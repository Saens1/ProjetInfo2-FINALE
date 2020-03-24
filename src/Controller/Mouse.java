package Controller;

import Model.Game;

public class Mouse {
    private Game game;
    

    public Mouse(Game game) {
        this.game = game;
    }

	public void mapEvent(int x, int y) {
		synchronized(game) {
			game.mouseEvent(x,y);
		}
		
	}
	
    //lance des actions lorsqu'on clique à un certain endroit
	public void statusEvent(int x, int y) {
					
			if(x>170 && x<270 && y>140 && y<240) {				
				game.changeActivePlayer(2);			
			}
			else if(x>40 && x<140 && y>140 && y<240) {				
				game.changeActivePlayer(1);			
			}
			else if(x>300 && x<400 && y>140 && y<240) {				
				game.changeActivePlayer(3);			
			}
			else if(x>145 && x<265 && y>310 && y< 390) {
				game.usePotionEnergy();
			}
			else if(x>30 && x<145 && y>310 && y< 390) {
				game.usePotionLife();
			}
		}
		
	}

