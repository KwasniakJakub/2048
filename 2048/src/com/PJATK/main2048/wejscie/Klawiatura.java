package com.PJATK.main2048.wejscie;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Klawiatura implements KeyListener{

	public static boolean[] klawisze = new boolean[120];
	public static boolean[] ostatnieKlawisze = new boolean[120];
	
	public void aktualizuj() {
		for(int i = 0; i < klawisze.length; i++) {
			ostatnieKlawisze[i] = klawisze[i];
		}
	}
	
	public static boolean klawisz(int key) {
		return klawisze[key];
	}
	public static boolean klawiszDol(int klawisz) {
		return klawisze[klawisz] && !ostatnieKlawisze[klawisz];
	}
	public static boolean klawiszGora(int klawisz) {
		return !klawisze[klawisz] && ostatnieKlawisze[klawisz];
	}
	
	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		klawisze[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		klawisze[e.getKeyCode()] = false;
	}	
}
