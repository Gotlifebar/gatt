package org.gatt.ui.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.gatt.optimization.io.SolutionIO;
import org.gatt.optimization.ttga.OptimizationFacade;
import org.gatt.ui.GattFrame;

public class SaveAction extends AbstractAction {
	
	private GattFrame frame;
	
	public SaveAction(GattFrame frame){
		this.frame = frame;
	}
	
	public void actionPerformed(ActionEvent e) {
		//JOptionPane.showMessageDialog(null, "Save Action");
		OptimizationFacade o = OptimizationFacade.getInstance();
		SolutionIO s = new SolutionIO();
		try{
			s.saveSolution(o.getBestSolution());
		}catch(Exception ex){
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, 
									"Ocurri� un error intentando guardar la soluci�n.",
									"Guardar Soluci�n", 
									JOptionPane.ERROR_MESSAGE);
			return;
		}
		JOptionPane.showMessageDialog(frame, 
				"La soluci�n ha sido guardada satisfactoriamente.",
				"Guardar Soluci�n", 
				JOptionPane.INFORMATION_MESSAGE);
	}

}
