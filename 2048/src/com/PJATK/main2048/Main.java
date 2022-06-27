package com.PJATK.main2048;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.FileNotFoundException;

import javax.swing.JFrame;

import com.PJATK.main2048.gra.Gra;
import com.PJATK.main2048.obiektygry.ObiektyGry;
import com.PJATK.main2048.wejscie.Klawiatura;

public class Main extends Canvas implements Runnable {

	public static final int SZEROKOSC = 400, WYSOKOSC = 400;
	public static float scale = 2.0f;
	
	public JFrame ramka;
	public Thread watek;
	public Klawiatura key;
	public Gra game;
	public boolean running = false;
	public double StartTime;
	
	public static BufferedImage image = new BufferedImage(SZEROKOSC, WYSOKOSC, BufferedImage.TYPE_INT_RGB);
	public static int[] pixele = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public Main() throws FileNotFoundException {
		setPreferredSize(new Dimension((int) (SZEROKOSC * scale), (int) (WYSOKOSC * scale)));
		ramka = new JFrame();
		game = new Gra();	
		key = new Klawiatura();
		addKeyListener(key);
		
	}
	
	public void start() {	
		running = true;
		watek = new Thread(this, "loopThread");
		watek.start();
	}
	
	public void stop() {
		try {
			watek.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		long ostatniRazWNanoSekundach = System.nanoTime();
		long stoper = System.currentTimeMillis();
		double nanoSekundyNaAktualizacje = 1000000000.0 / 60.0;
		double updatesToPerform = 0.0;
		int ramki = 0;
		int fps = 0;		
		requestFocus();
		while(running) {
			long biezacyCzasWNanoSekundach = System.nanoTime();
			updatesToPerform += (biezacyCzasWNanoSekundach - ostatniRazWNanoSekundach) / nanoSekundyNaAktualizacje;
			if(updatesToPerform >= 1) {
				try {
					update();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fps++;
				StartTime += 1/60.0;
				updatesToPerform--;
			}
			ostatniRazWNanoSekundach = biezacyCzasWNanoSekundach;
			
			render();
			ramki++;
			
			if(System.currentTimeMillis() - stoper > 1000) {
				ramka.setTitle("2048 PJATK\n Klatki na sekunde: " + fps + ",                                                   czas:  " + Math.round(StartTime));
				fps = 0;
				ramki = 0;
				stoper += 1000;
			}
		}	
	}
	
	public void update() throws FileNotFoundException {
		game.aktualizuj();
		key.aktualizuj();
	}
	
	public void render() {
		BufferStrategy strategiaBufora = getBufferStrategy();
		if(strategiaBufora == null) {
			createBufferStrategy(3);
			return;
		}
		
		game.renderuj();
		
		Graphics2D grafika = (Graphics2D) strategiaBufora.getDrawGraphics();
		grafika.drawImage(image, 0, 0, (int) (SZEROKOSC * scale), (int) (SZEROKOSC * scale), null);
		game.renderujText(grafika);
		grafika.dispose();
		strategiaBufora.show();
	}	
	
	public static void main(String[] args) throws FileNotFoundException {
		Main m = new Main();
		m.ramka.setResizable(false);
		m.ramka.setTitle("2048");
		m.ramka.add(m);
		m.ramka.pack();
		m.ramka.setVisible(true);
		m.ramka.setLocationRelativeTo(null);
		m.ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.ramka.setAlwaysOnTop(true);
		m.start();
	}
}
