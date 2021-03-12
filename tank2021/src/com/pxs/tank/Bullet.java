package com.pxs.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet {
	private static final int SPEED = 6;
	public static int WIDTH = ResourceMgr.bulletD.getWidth();	//子弹图片的宽度
	public static int HEIGHT = ResourceMgr.bulletD.getHeight();
	
	Rectangle rect = new Rectangle();
	
	private int x, y;
	private Dir dir;
	private TankFrame tf = null;
	private boolean living = true;
	
	private Group group = Group.BAD;
	
	public Bullet(int x, int y, Dir dir,Group group,TankFrame tf) {		
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.tf = tf;
		
		rect.x = this.x;
		rect.y = this.y;
		rect.width = WIDTH;
		rect.height = HEIGHT;
	}
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public void paint(Graphics g) {
			
			if(!living) {
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
		
		//更新rect位置
		rect.x = this.x;
		rect.y = this.y;
		
		if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y>TankFrame.GAME_HEIGHT) living = false;
		
	
	}

	public void collideWith(Tank tank) {
		
		if(this.group == tank.getGroup()) return;
					
		if(rect.intersects(tank.rect)) {
			tank.die();
			this.die();
			int ex = tank.getX() +Tank.WIDTH/2 - Explode.WIDTH/2;
			int ey = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
			tf.explodes.add(new Explode(ex, ey, tf));
		}
	}

	private void die() {
		this.living = false;
		
	}
	
}
