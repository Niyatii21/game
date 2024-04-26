package com.akul.gaming;

import javax.swing.ImageIcon;

public class Player extends Sprite {
	public Player() {
		w=200;
		h=225;
		x=20;
		y=500;
		image= new ImageIcon(Player.class.getResource("goku-sprite.gif"));
	}
	public void move() {
		x= x+speed;
	}
	public boolean outofScreen() {
		return x+200>1500;
	}
}
