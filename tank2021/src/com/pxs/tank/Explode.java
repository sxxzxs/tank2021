package com.pxs.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Explode {
	
	public static int WIDTH = ResourceMgr.explodes[0].getWidth();
	public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
	
	private int x, y;
	
	private boolean living = true;
	TankFrame tf = null;
	
	private int step = 0;
	
	public Explode(int x, int y, TankFrame tf) {
		this.x = x;
		this.y = y;
		this.tf = tf;
	}
	
	
//把图片一步步画出来
	public void paint(Graphics g) {
		
		g.drawImage(ResourceMgr.explodes[step++], x, y, null);
		
		if(step >= ResourceMgr.explodes.length) 
			step = 0;
	}
	
}
