package org.gatt.ui;

import javax.swing.UIManager;

import org.fife.plaf.OfficeXP.OfficeXPLookAndFeel;

import de.javasoft.plaf.synthetica.SyntheticaGreenDreamLookAndFeel;


public class GattApp {

	public static void main(String[] args) {
		try{
			SyntheticaGreenDreamLookAndFeel.setAntiAliasEnabled(true);
			UIManager.setLookAndFeel(new OfficeXPLookAndFeel());
		}catch (Exception ex) {
			ex.printStackTrace();
	    }

	    GattFrame win = GattFrame.getInstance();
		win.setVisible(true);
	}

}
