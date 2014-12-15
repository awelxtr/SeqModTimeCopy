package com.awelxtr.seqmodtimecopy;

import javax.swing.SwingUtilities;

import com.awelxtr.sqlmodtimecopy.windows.SwingUIMainWindow;

public class MainDriver {

	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new SwingUIMainWindow();
			}
			
		});
	}
}
