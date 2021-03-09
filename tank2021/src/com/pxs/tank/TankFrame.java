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
		x += 10;
		//y += 10;
	}

	class MyKeyListener extends KeyAdapter{

		//一个键被按下去时候调用
		@Override
		public void keyPressed(KeyEvent e) {
			//x += 200;
			//repaint();	//默认调用一次paint
		}
		
		//一个键被抬起来时候调用
		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println(".");
		}
		
		
	}
}
