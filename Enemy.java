package com.akul.gaming;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Enemy extends Sprite {
	public Enemy(int x, int speed) {
		w=175;
		h=175;
		this.x= x; //350
		this.speed= speed;
		y=5;
		image= new ImageIcon(Enemy.class.getResource("spider-sprite2.gif"));
	}
	public void move() {
		if(y>820) {
			y=0;
		}
		y= y+speed;
	}
}
