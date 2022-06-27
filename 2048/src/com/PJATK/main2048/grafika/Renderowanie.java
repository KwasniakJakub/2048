package com.PJATK.main2048.grafika;

import com.PJATK.main2048.Main;

public class Renderowanie {
	
	public static int szerokosc = Main.SZEROKOSC, wysokosc = Main.WYSOKOSC;
	public static int[] pixele = new int[szerokosc * wysokosc];
	
	public static void renderujTlo() {
		for(int y = 0; y < wysokosc; y++) {
			for(int x = 0; x < szerokosc; x++) {
				pixele[x + y * szerokosc] = 0xfff4f4f4;
				
				if(x % 100 < 3 || x % 100 > 97 || y % 100 < 3 || y % 100 > 97) {
					pixele[x + y * szerokosc] = 0xffcccccc;
				}
			}
		}
	}
	
	public static void renderujKafelek(Kafelek kafelek, int punktX, int punktY) {
		if(punktX < -kafelek.szerokosc || punktX > szerokosc || punktY < -kafelek.wysokosc || punktY > wysokosc) return;
		
		for(int y = 0; y < kafelek.wysokosc; y++) {
			int yTemp = y + punktY;
			if(yTemp < 0 || yTemp > wysokosc) continue;
			for(int x = 0; x < kafelek.szerokosc; x++) {
				int xTemp = x + punktX;
				if(xTemp < 0 || xTemp > szerokosc) continue;
				int kolor = kafelek.pixele[x + y * kafelek.szerokosc];
				if(kolor == 0xffff00ff) continue;
				else pixele[xTemp + yTemp * szerokosc] = kolor;
			}
		}
		
	}
	
}
