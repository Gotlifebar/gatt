package org.gatt.ui.wizards;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.freixas.jwizard.JWizardPanel;

public class ConstraintPreviewPanel extends JWizardPanel {

	private static final long serialVersionUID = 1L;
	private JLabel labelConstraint = null;
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
		ConstraintWizard wizard = (ConstraintWizard)getWizardParent();
		
		// La fachada
		/*if(wizard.getConstraintType() == ConstraintWizard.ConstraintType.SIMPLE){
			this.setBackStep(1);
		}else{
			this.setBackStep(2);
		}*/
		
		
		super.makingVisible();
	}
	
	protected void back(){
		ConstraintWizard wizard = (ConstraintWizard)getWizardParent();
		
		if(wizard.isSameRound() && wizard.getCurrentRound() > 1){
			wizard.setLastRound(wizard.getLastRound()-1);
		}
		
		super.back();
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		labelConstraint = new JLabel();
		labelConstraint.setText("");
		labelConstraint.setHorizontalTextPosition(SwingConstants.LEFT);
		labelConstraint.setVerticalAlignment(SwingConstants.TOP);
		labelConstraint.setVerticalTextPosition(SwingConstants.TOP);
		labelConstraint.setOpaque(true);
		labelConstraint.setBackground(Color.white);
		labelConstraint.setHorizontalAlignment(SwingConstants.LEFT);
		this.setSize(602, 356);
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
			pConstraintPreview.setBounds(new Rectangle(15, 15, 572, 323));
			pConstraintPreview.setBorder(BorderFactory.createTitledBorder(null, "Vista previa de la restricción", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			pConstraintPreview.add(labelConstraint, BorderLayout.CENTER);
		}
		return pConstraintPreview;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
