package org.gatt.ui.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.gatt.optimization.io.SolutionIO;
import org.gatt.optimization.ttga.OptimizationFacade;

public class SaveAction extends AbstractAction {

	public void actionPerformed(ActionEvent e) {
		//JOptionPane.showMessageDialog(null, "Save Action");
		OptimizationFacade o = OptimizationFacade.getInstance();
		SolutionIO s = new SolutionIO();
		s.saveSolution(o.getBestSolution());
	}

}
