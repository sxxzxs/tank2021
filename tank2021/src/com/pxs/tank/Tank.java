package com.pxs.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Tank {
	private int x, y;
	
	private Dir dir = Dir.DOWN;
	private static final int SPEED = 2;
	
	public static int WIDTH = ResourceMgr.goodTankU.getWidth();	//坦克图片的宽度
	public static int HEIGHT = ResourceMgr.goodTankU.getHeight();
	
	private boolean moving = true;
	
	private TankFrame tf = null;
	
	private boolean living = true;
	
	private Random random = new Random();
	
	private Group group = Group.BAD;
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
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
	
	public Tank(int x, int y, Dir dir,Group group,TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.tf = tf;
	}
	
	public void paint(Graphics g) {
		//Color c= g.getColor();
		if(!living) tf.tanks.remove(this);	//如果没活着移除
		
		switch (dir){
			case LEFT:
				g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
				break;
			case UP:
				g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
				break;
			case RIGHT:
				g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
				break;
			case DOWN:
				g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
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
		
		//移动后通过产生随机数来打出随机炮弹
		if(this.group  == Group.BAD && random.nextInt(100) > 95) this.fire();
		
		if(this.group == Group.BAD && random.nextInt(100) > 95)
		randomDir();
		
		boundsCheck();
		
	}
	
	//边界碰撞检测
	private void boundsCheck() {
		if(this.x < 2) x = 2;
		if (this.y < 28) y = 28;
		if(this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2) x = TankFrame.GAME_WIDTH- Tank.WIDTH - 2;
		if(this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2) y = TankFrame.GAME_HEIGHT- Tank.HEIGHT - 2;
			
		
		
	}

	//让坦克随机向四个方向走动
	private void randomDir() {
		
		this.dir = Dir.values()[random.nextInt(4)];		
	}

	public void fire() {
		int bx = this.x +Tank.WIDTH/2 - Bullet.WIDTH/2;
		int by = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
		
		tf.bullets.add(new Bullet(bx,by,this.dir,this.group,this.tf));		
		
		//加入开火时候的声音
		if(this.group == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
	}

	public void die() {
		this.living = false;
		
	}
	
	

}
