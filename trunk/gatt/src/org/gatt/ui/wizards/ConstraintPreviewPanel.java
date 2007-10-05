package org.gatt.ui.wizards;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.freixas.jwizard.JWizardPanel;

public class ConstraintPreviewPanel extends JWizardPanel {

	private static final long serialVersionUID = 1L;
	private JTextArea taConstraint = null;
	private JPanel pConstraintPreview = null;

	/**
	 * This is the default constructor
	 */
	public ConstraintPreviewPanel() {
		super();
		this.setStepTitle("Finalizar creación de restricción");
		initialize();
		setBackStep(-1);
	    setNextStep(-1);
	}

	protected void makingVisible(){
		 // Asignarle al label la previsualización de la restriccion
		getWizardParent().setPreferredSize(new Dimension(620,445));
		getWizardParent().setSize(620, 445);
	    ConstraintWizard wiz = (ConstraintWizard)this.getWizardParent();
	    taConstraint.setText(wiz.getConstraintProducer().getConstraintPreview());
	    
		getWizardParent().repaint();
		super.makingVisible();
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.getContentPane().setSize(602, 356);
		this.getContentPane().setLayout(null);
		this.getContentPane().add(getPConstraintPreview(), null);
	}

	/**
	 * This method initializes pConstraintPreview	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPConstraintPreview() {
		if (pConstraintPreview == null) {
			pConstraintPreview = new JPanel();
			pConstraintPreview.setLayout(new BorderLayout());
			pConstraintPreview.setBounds(new Rectangle(15, 15, 572, 310));
			pConstraintPreview.setBorder(BorderFactory.createTitledBorder(null, "Vista previa de la restricción", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			pConstraintPreview.add(getTaConstraint(), BorderLayout.CENTER);
		}
		return pConstraintPreview;
	}
	
	private JTextArea getTaConstraint(){
		if(taConstraint == null){
			taConstraint = new JTextArea();
			taConstraint.setText("");
			taConstraint.setOpaque(true);
			taConstraint.setBackground(Color.white);
			taConstraint.setLineWrap(true);
			taConstraint.setEditable(false);
		}
		return taConstraint;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
