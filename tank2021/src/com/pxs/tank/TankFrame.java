package com.pxs.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//继承是为了重写paint方法
public class TankFrame extends Frame {
	
	Tank myTank = new Tank(200,400,Dir.DOWN,Group.GOOD,this);
	List<Bullet> bullets = new ArrayList<>();	//加入容器发多颗子弹
	List<Tank> tanks = new ArrayList<>();
	Bullet b = new Bullet(300, 300, Dir.DOWN,Group.BAD,this);
	static final int GAME_WIDTH = 800,GAME_HEIGHT = 600;
	
	public TankFrame() {
		//Frame f = new Frame();		//新建一窗口,
		setVisible(true);		//将窗口设为可见
		setSize(GAME_WIDTH,GAME_HEIGHT);
		setResizable(false); 	//窗口大小不可变
		setTitle("tank war");	//标题栏名称
		
		this.addKeyListener(new MyKeyListener());	//添加键盘监听
		
		//添加window监听器，监听windowClosing,当点击窗口拔插就会触发windowClosing
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}			
		});
	}
	
	//处理双缓存解决闪烁问题(repaint先调用update,咱们截获update，首先把画出来的东西，如坦克，子弹先画在内存的图片中，图片大小和游戏画面一直，把内存中图片一次性画到屏幕上--把内存的内容复制到显存)
	Image offScreenImage = null;
	@Override
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	//窗口重新绘制时候自动调用paint
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("子弹的数量: " + bullets.size(),10,60);
		g.drawString("敌人的数量: " + tanks.size(),10,80);
		g.setColor(c);
		
		myTank.paint(g);
		for(int i = 0;i < bullets.size(); i++) {
			bullets.get(i).paint(g);
		}
		
		//出现敌方坦克
		for(int i = 0;i < tanks.size(); i++) {
			tanks.get(i).paint(g);
		}
		
		for(int i = 0; i <bullets.size(); i++) {
			for(int j = 0; j < tanks.size(); j++) {
				bullets.get(i).collideWith(tanks.get(j));
			}
			
		}
		
		/*
		 * for(Iterator<Bullet> it = bullets.iterator();it.hasNext()) { Bullet b =
		 * it.next(); if(!b.live) it.remove(); }
		 */
		
	}

	class MyKeyListener extends KeyAdapter{
		
		boolean bL = false;
		boolean bU = false;
		boolean bR = false;
		boolean bD = false;

		//一个键被按下去时候调用
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();	//哪个键按下取出哪个键的代码
			switch (key) {
			case KeyEvent.VK_LEFT: {
				bL = true;
				break;
			}
			case KeyEvent.VK_UP: {
				bU = true;
				break;
			}
			case KeyEvent.VK_RIGHT: {
				bR = true;
				break;
			}
			case KeyEvent.VK_DOWN: {
				bD = true;
				break;
			}
			
			default:
				break;
			}
			//x += 200;
			//repaint();	//默认调用一次paint
			
			setMainTankDir();
		}
		
		//一个键被抬起来时候调用
		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();	//哪个键按下取出哪个键的代码
			switch (key) {
			case KeyEvent.VK_LEFT: {
				bL = false;
				break;
			}
			case KeyEvent.VK_UP: {
				bU = false;
				break;
			}
			case KeyEvent.VK_RIGHT: {
				bR = false;
				break;
			}
			case KeyEvent.VK_DOWN: {
				bD = false;
				break;
			}
			//按下ctrl键开火
			case KeyEvent.VK_CONTROL:{
				myTank.fire();
				break;
			}
			
			default:
				break;
			}
			setMainTankDir();
		}

		private void setMainTankDir() {
			if(!bL && !bU && !bR && !bD) myTank.setMoving(false);	//如果所有键没按下去，表示静止状态
			else {
				
				myTank.setMoving(true);
				
				if(bL) myTank.setDir(Dir.LEFT);
				if(bU) myTank.setDir(Dir.UP);
				if(bR) myTank.setDir(Dir.RIGHT);
				if(bD) myTank.setDir(Dir.DOWN);
				
			}
						
		}
		
		
	}
}
