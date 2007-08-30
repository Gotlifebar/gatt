package org.gatt.ui;

import java.text.ParseException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.javasoft.plaf.synthetica.SyntheticaBlueSteelLookAndFeel;


public class GattApp {

	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel(new SyntheticaBlueSteelLookAndFeel());
		}catch (UnsupportedLookAndFeelException lfEx) {
			lfEx.printStackTrace();
	    }catch(ParseException pEx){
	    	pEx.printStackTrace();
	    }

	    GattFrame win = GattFrame.getInstance();
		win.setVisible(true);
	}

}
