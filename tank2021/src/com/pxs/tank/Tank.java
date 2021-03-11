package com.pxs.tank;

import java.awt.Color;
import java.awt.Graphics;

public class Tank {
	private int x, y;
	private Dir dir = Dir.DOWN;
	private static final int SPEED = 5;
	
	public static int WIDTH = ResourceMgr.tankD.getWidth();	//坦克图片的宽度
	public static int HEIGHT = ResourceMgr.tankD.getHeight();
	
	private boolean moving = false;
	
	private TankFrame tf = null;
	
	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}
	
	public Tank(int x, int y, Dir dir,TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
	}
	
	public void paint(Graphics g) {
		//Color c= g.getColor();
		
		switch (dir){
			case LEFT:
				g.drawImage(ResourceMgr.tankL, x, y, null);
				break;
			case UP:
				g.drawImage(ResourceMgr.tankU, x, y, null);
				break;
			case RIGHT:
				g.drawImage(ResourceMgr.tankR, x, y, null);
				break;
			case DOWN:
				g.drawImage(ResourceMgr.tankD, x, y, null);
				break;
				
		}
		
		
		
		move();
		//g.setColor(Color.YELLOW);
		//g.fillRect(x, y, 50, 50);	//填充一个正方形
		//g.setColor(c);
							
		//x += 10;
		//y += 10;
		
	}

	private void move() {
		
		if(!moving) return;
		
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

	public void fire() {
		int bx = this.x +Tank.WIDTH/2 - Bullet.WIDTH/2;
		int by = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
		
		tf.bullets.add(new Bullet(bx,by,this.dir,this.tf));		
		
	}
	
	

}
