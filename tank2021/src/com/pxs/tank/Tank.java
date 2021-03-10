package com.pxs.tank;

import java.awt.Graphics;

public class Tank {
	private int x, y;
	private Dir dir = Dir.DOWN;
	
	
	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	private static final int SPEED = 10;
	public Tank(int x, int y, Dir dir) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	public void paint(Graphics g) {
		
		g.fillRect(x, y, 50, 50);	//填充一个正方形
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
		//x += 10;
		//y += 10;
		
	}
	
	

}
