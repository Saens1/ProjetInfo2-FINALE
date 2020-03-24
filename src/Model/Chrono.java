package Model;

import java.util.ArrayList;

public class Chrono implements Runnable, Time{   // Chrono est une observable
	private Thread thread;
    private Game game;
    private int seconde;
    private ArrayList<TimeObserver> observers = new ArrayList<TimeObserver>();

    public Chrono(Game game){
        thread  = new Thread(this);
        thread.start();
        this.game=game;
    }


    //Thread qui sert de chrono, s'arrete quand le jeu est en pause
	public void run() {
		try {
			while (true) {
				Thread.sleep(1000);
				if (!(game.getPause())){
					seconde +=1;
					notifyTimeObserver();	
				}			
			}
		}
		catch(Exception e){
			System.out.println("Exception in Chrono");
		}		
	}


	public void notifyTimeObserver() {
		for (TimeObserver obs : observers) {
			obs.updateChrono(seconde);   // on applique la méthode updateChrono sur tous les observeurs
		}
	}
	
	public void addObserver(TimeObserver obs) {
		observers.add(obs);	
	}


} 