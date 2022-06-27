package com.PJATK.main2048.obiektygry;

import java.util.Random;

import com.PJATK.main2048.Main;
import com.PJATK.main2048.gra.Gra;
import com.PJATK.main2048.grafika.Renderowanie;
import com.PJATK.main2048.grafika.Kafelek;

public class ObiektyGry {
	
	public double x, y;
	public int szerokosc, wysokosc;
	public Kafelek kafelek;
	public int wartosc, predkosc = 8;
	public boolean ruszajacySie = false, usun = false, poruszylSie = false;
	
	Random losowy = new Random();
	
	public ObiektyGry(double x, double y) {
		this.x = x;
		this.y = y;
		this.wartosc = (losowy.nextBoolean() ? 2 : 4);
		utworzKafelek();
		this.szerokosc = kafelek.szerokosc;
		this.wysokosc = kafelek.wysokosc;
	}
	
	public ObiektyGry(double x, double y, int wartosc, Boolean usun) 
	{
		this.x = x;
		this.y = y;
		this.wartosc = wartosc;
		this.usun = usun;
		utworzKafelek();
		this.szerokosc = kafelek.szerokosc;
		this.wysokosc = kafelek.wysokosc;
	}
	
	public void utworzKafelek() {
		if(this.wartosc == 2) {
			this.kafelek = new Kafelek(100, 100, 0xefe5db);
		}else if(this.wartosc == 4) {
			this.kafelek = new Kafelek(100, 100, 0xece0c8);
		}else if(this.wartosc == 8) {
			this.kafelek = new Kafelek(100, 100, 0xf1b078);
		}else if(this.wartosc == 16) {
			this.kafelek = new Kafelek(100, 100, 0xEB8C52);
		}else if(this.wartosc == 32) {
			this.kafelek = new Kafelek(100, 100, 0xF57C5F);
		}else if(this.wartosc == 64) {
			this.kafelek = new Kafelek(100, 100, 0xEC563D);
		}else if(this.wartosc == 128) {
			this.kafelek = new Kafelek(100, 100, 0xF2D86A);
		}else if(this.wartosc == 256) {
			this.kafelek = new Kafelek(100, 100, 0xECC750);
		}else if(this.wartosc == 512) {
			this.kafelek = new Kafelek(100, 100, 0xE5BF2D);
		}else if(this.wartosc == 1024) {
			this.kafelek = new Kafelek(100, 100, 0xE2B913);
		}else if(this.wartosc == 2048) {
			this.kafelek = new Kafelek(100, 100, 0xEDC22E);
		}else if(this.wartosc == 4096) {
			this.kafelek = new Kafelek(100, 100, 0x5DDB92);
		}else if(this.wartosc == 8192) {
			this.kafelek = new Kafelek(100, 100, 0xEC4D58);
		}
	}
 
	public boolean mozeSieRuszyc() {
		if(x < 0 || x + szerokosc > Main.SZEROKOSC || y < 0 || y + wysokosc > Main.WYSOKOSC) {
			return false;
		}
		for(int i = 0; i < Gra.objects.size(); i++) {
			ObiektyGry o = Gra.objects.get(i);
			if(this == o) continue;
			if(x + szerokosc > o.x && x < o.x + o.szerokosc && y + wysokosc > o.y && y < o.y + o.wysokosc && wartosc != o.wartosc) {
				return false;
			}
		}
		return true;
	}
	
	public void aktualizuj() {
		if(Gra.moving) {
			if(!poruszylSie) {
				poruszylSie = true;
			}
			if(mozeSieRuszyc()) {
				ruszajacySie = true;
			}
			
			if(ruszajacySie) {
				if(Gra.dir == 0) x -= predkosc;
				if(Gra.dir == 1) x += predkosc;
				if(Gra.dir == 2) y -= predkosc;
				if(Gra.dir == 3) y += predkosc;
			}
			if(!mozeSieRuszyc()) {
				ruszajacySie = false;
				x = Math.round(x / 100) * 100;
				y = Math.round(y / 100) * 100;
			}
		}
	
	}
	public void koniecGry(){
		if(!mozeSieRuszyc()) {
			System.exit(0);
		}
		return ;
	}
	
	public void render() {
		Renderowanie.renderujKafelek(kafelek, (int) x, (int) y);
	}
}
