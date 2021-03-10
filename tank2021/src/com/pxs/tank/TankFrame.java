package com.pxs.tank;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//继承是为了重写paint方法
public class TankFrame extends Frame {
	int x = 200, y = 200;
	Dir dir = Dir.DOWN;
	private static final int SPEED = 10;
	
	public TankFrame() {
		//Frame f = new Frame();		//新建一窗口,
		setVisible(true);		//将窗口设为可见
		setSize(800,600);
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
	
	//窗口重新绘制时候自动调用paint
	@Override
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
			
			default:
				break;
			}
			setMainTankDir();
		}

		private void setMainTankDir() {
			if(bL) dir = Dir.LEFT;
			if(bU) dir = Dir.UP;
			if(bR) dir = Dir.RIGHT;
			if(bD) dir = Dir.DOWN;
		}
		
		
	}
}
