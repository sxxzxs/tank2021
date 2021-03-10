package com.pxs.tank;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
	private static final int SPEED = 10;
	private static int WIDTH = 15, HEIGHT = 15;
	
	private int x, y;
	private Dir dir;
	
	public Bullet(int x, int y, Dir dir) {		
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	public void paint(Graphics g) {
			
			Color c = g.getColor();
			g.setColor(Color.RED);
			g.fillOval(x, y, WIDTH, HEIGHT);	//填充一个正方形
			g.setColor(c);
			
			move();
					
			//x += 10;
			//y += 10;
			
		}

	private void move() {			
		
		switch(dir) {
		case LEFT:
			x -= SPEED;
			break;
		case UP:
			y -= SPEED;
			break;
		case RIGHT:
			x += SPEED;
			break;
		case DOWN:
			y += SPEED;
			break;
		}
	
	}
	
}