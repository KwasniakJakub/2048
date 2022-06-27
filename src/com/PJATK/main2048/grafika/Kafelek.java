package com.PJATK.main2048.grafika;

public class Kafelek {

	public int szerokosc, wysokosc;
	public int[] pixele;
	
	public Kafelek(int szerokosc, int wysokosc, int kolor) {
		this.szerokosc = szerokosc;
		this.wysokosc = wysokosc;
		this.pixele = new int[szerokosc * wysokosc];
		
		for(int y = 0; y < wysokosc; y++) {
			for(int x = 0; x < szerokosc; x++) {
				pixele[x + y * szerokosc] = kolor;
				
				if(x % 100 < 3 || x % 100 > 97 || y % 100 < 3 || y % 100 > 97) {
					pixele[x + y * szerokosc] = 0xffff00ff;
				}
			}
		}
	}
	
}
