package com.pxs.tank;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		
		TankFrame tf = new TankFrame();
		
		//循环，让线程隔50ms调用一次repaint
		while(true) {
			Thread.sleep(50);
			tf.repaint();	//调用一次repaint默认调用一次paint
		}
	}
	
}
