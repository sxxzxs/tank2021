package com.pxs.tank;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		
		TankFrame tf = new TankFrame();
		
		//初始化敌方坦克
		for(int i = 0; i < 5; i++) {
			tf.tanks.add(new Tank(50 + i *80,200,Dir.DOWN,Group.BAD,tf));
		}
		
		//背景音乐
		new Thread(()->new Audio("audio/war1.wav").loop()).start();
		
		//循环，让线程隔50ms调用一次repaint
		while(true) {
			Thread.sleep(25);
			tf.repaint();	//调用一次repaint默认调用一次paint
		}
	}
	
}
