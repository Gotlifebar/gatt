package org.gatt.ui.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.gatt.optimization.io.SolutionIO;
import org.gatt.optimization.ttga.OptimizationFacade;
import org.gatt.ui.GattFrame;

/**
 * @author Chucho
 * save command
 */
public class SaveAction extends AbstractAction {
	
	/**
	 * frame instance
	 */
	private GattFrame frame;
	
	/**
	 * constructor
	 * @param frame
	 */
	public SaveAction(GattFrame frame){
		this.frame = frame;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		//JOptionPane.showMessageDialog(null, "Save Action");
		OptimizationFacade o = OptimizationFacade.getInstance();
		SolutionIO s = new SolutionIO();
		
		if(!o.isOptimizationProcessStarted()){
			JOptionPane.showMessageDialog(frame,
					"Debe haber iniciado un proceso de optimización antes de guardar alguna solución",
					"Guardar solución",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		try{
			s.saveSolution(o.getBestSolution());
		}catch(Exception ex){
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, 
									"Ocurrió un error intentando guardar la solución.",
									"Guardar Solución", 
									JOptionPane.ERROR_MESSAGE);
			return;
		}
		JOptionPane.showMessageDialog(frame, 
				"La solución ha sido guardada satisfactoriamente.",
				"Guardar Solución", 
				JOptionPane.INFORMATION_MESSAGE);
	}

}
