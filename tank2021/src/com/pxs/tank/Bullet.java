package com.pxs.tank;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
	private static final int SPEED = 10;
	public static int WIDTH = ResourceMgr.bulletD.getWidth();	//子弹图片的宽度
	public static int HEIGHT = ResourceMgr.bulletD.getHeight();
	
	private int x, y;
	private Dir dir;
	private TankFrame tf = null;
	private boolean live = true;
	
	public Bullet(int x, int y, Dir dir,TankFrame tf) {		
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
	}
	
	public void paint(Graphics g) {
			
			if(!live) {
				tf.bullets.remove(this);
			}
			//Color c = g.getColor();
			//g.setColor(Color.RED);
			//g.fillOval(x, y, WIDTH, HEIGHT);	//填充一个正方形
			//g.setColor(c);
			switch (dir){
			case LEFT:
				g.drawImage(ResourceMgr.bulletL, x, y, null);
				break;
			case UP:
				g.drawImage(ResourceMgr.bulletU, x, y, null);
				break;
			case RIGHT:
				g.drawImage(ResourceMgr.bulletR, x, y, null);
				break;
			case DOWN:
				g.drawImage(ResourceMgr.bulletD, x, y, null);
				break;
				
		}
			
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
		
		if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y>TankFrame.GAME_HEIGHT) live = false;
		
	
	}
	
}
