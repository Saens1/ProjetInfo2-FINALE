package View;

import Model.GameObject;
import Model.Player;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Mouse;

@SuppressWarnings("serial")
public class Window extends JFrame {              
	private JPanel groupPanel = new JPanel(new BorderLayout());   
    private Map map;
    private Status status = new Status();

    public Window(String title, int MAP_SIZE, int BLOC_SIZE) {
    	super(title);
    	map = new Map(MAP_SIZE,BLOC_SIZE);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // Ferme le jeu lorsque l'on ferme la frame          
        this.setBounds(200, 50, 10, 102);   
        this.getContentPane().setBackground(Color.gray);  
        groupPanel.add(map, BorderLayout.LINE_START); // map a droite
        groupPanel.add(status, BorderLayout.LINE_END); // map à gauche
        this.getContentPane().add(this.groupPanel);
        this.setVisible(true);
        this.pack();
    }
    
    public void update() {
    	groupPanel.repaint();
    }
    
    
    public void updateChrono(String time) {
    	status.setChrono(time);
    }
    
    public void setGameObjects(ArrayList<GameObject> objects, int numeroMap) {
        this.map.setObjects(objects,numeroMap);
        this.map.redraw();
    }



    public void setKeyListener(KeyListener keyboard) {
        this.map.addKeyListener(keyboard);
    }

    public void setMouseListener(Mouse m) {
        this.map.addMouse(m);
        this.status.addMouse(m);
    }

	public int getMapSize() {
		return map.getMapSize();
	}
	
	public void setPlayer(Player p1,Player p2,Player p3,Player active_player,int numeroMap) {
		status.setPlayer(p1,p2,p3,active_player,numeroMap);
		map.setPlayer(p1,p2,p3,active_player,numeroMap);
	}
	public void setBlocSize(int BLOC_SIZE) {
		map.setBlocSize(BLOC_SIZE);
	}
	public void setZoom(int zoom) {
		map.setZoom(zoom);
	}
	public void setName(String name) {
		status.setName(name);
	}
	public void setTime(String time) {
		status.setTime(time);
	}
	public void setAge(int age) {
		status.setAge(age);
	}
	public void setBestScore(int score) {
		status.setBestScore(score);
	}
}
