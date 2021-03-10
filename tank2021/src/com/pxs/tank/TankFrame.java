package com.pxs.tank;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//继承是为了重写paint方法
public class TankFrame extends Frame {
	
	Tank myTank = new Tank(200,200,Dir.DOWN);
	
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
		
		myTank.paint(g);		
		
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
