package com.akul.gaming;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.akul.gaming.sprites.Enemy;
import com.akul.gaming.sprites.Player;

public class Board extends JPanel {
	Timer timer;
	BufferedImage backgroundimage;
	Player playerimage;
	Enemy enemyimages[]= new Enemy[3];
	public Board() {
		setSize(1500,820);
		loadBackgroundImage();
		playerimage= new Player();	
		loadEnemies();
		playerLoop();
		bindEvents();
		setFocusable(true);
	}
	private void gameOver(Graphics pen) {
		if(playerimage.outofScreen()) {
			pen.setFont(new Font("times", Font.BOLD, 30));
			pen.setColor(Color.red);
			pen.drawString("Congratulations, You have Passed", 550, 820/2);
			timer.stop();
			return;
		}
		for(Enemy enemy: enemyimages) {
			if(isCollide(enemy)) {
				pen.setFont(new Font("times", Font.BOLD, 30));
				pen.setColor(Color.red);
				pen.drawString("Game Over", 700, 820/2);
				timer.stop();
			}
		}
	}
	private boolean isCollide(Enemy enemy) {
		int xDistance= Math.abs(playerimage.x- enemy.x);
		int yDistance= Math.abs(playerimage.y- enemy.y);
		int maxW= Math.max(playerimage.w, enemy.w);
		int maxH= Math.max(playerimage.h, enemy.h);
		return xDistance < maxW-125 && yDistance < maxH-125;
	}
	private void bindEvents() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
					playerimage.speed= 12;
					}
				else if(e.getKeyCode()==KeyEvent.VK_LEFT){
					playerimage.speed=-12;
				}
				// TODO Auto-generated method stub
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent e) {
				playerimage.speed= 0;
				// TODO Auto-generated method stub
			}
			
		});
	}
	private void loadEnemies() {
		int x= 350;
		int gap= 380;
		int speed= 5;
		for(int i=0;i<enemyimages.length;i++) {
			enemyimages[i]= new Enemy(x, speed);
			x= x+gap;
			speed = speed+5;
		}
	}
	private void playerLoop() {
		timer= new Timer(50, (e)->repaint());
		timer.start();
	}
	private void loadBackgroundImage() {
		try {
			backgroundimage= ImageIO.read(Board.class.getResource("game-bg-transformed.jpeg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Background Image not found.....");
			System.exit(1);
			e.printStackTrace();
		}
	}
	private void printEnemies(Graphics pen) {
		for(Enemy enemy: enemyimages) {
			enemy.draw(pen);
			enemy.move();
		}
	}
	@Override
	public void paintComponent(Graphics pen) {
		// all printing logic will be here
		// all printing logic will be here
		super.paintComponent(pen); //clean up
		pen.drawImage(backgroundimage,0,0,1530,830,null);
		playerimage.draw(pen);
		playerimage.move();
		printEnemies(pen);
		gameOver(pen);
	}
}
