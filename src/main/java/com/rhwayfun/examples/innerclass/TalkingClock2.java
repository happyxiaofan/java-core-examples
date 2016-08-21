package com.rhwayfun.examples.innerclass;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Timer;


public class TalkingClock2 {

	public void start(int internal,final boolean beep){
		
		class TimePrinter implements ActionListener{

			public void actionPerformed(ActionEvent e) {
				Date now = new Date();
				System.out.println("现在时间是：" + now);
				//这里TimePrinter是TalkingClock的内部类，严格访问beep变量的原发应该是TalkingClock.this.beep
				if(beep){
					Toolkit.getDefaultToolkit().beep();
				}
			}
			
		}
		
		ActionListener listener = new TimePrinter();
		Timer t = new Timer(internal,listener);
		t.start();
	}
	
	
}