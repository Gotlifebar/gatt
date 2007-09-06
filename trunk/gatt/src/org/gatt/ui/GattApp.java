package org.gatt.ui;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.fife.plaf.OfficeXP.OfficeXPLookAndFeel;


public class GattApp {

	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel(new OfficeXPLookAndFeel());
		}catch (UnsupportedLookAndFeelException lfEx) {
			lfEx.printStackTrace();
	    }

	    GattFrame win = GattFrame.getInstance();
		win.setVisible(true);
	}

}
