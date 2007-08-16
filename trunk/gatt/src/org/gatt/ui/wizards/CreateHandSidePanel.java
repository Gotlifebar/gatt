package org.gatt.ui.wizards;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.freixas.jwizard.JWizardPanel;
import org.gatt.ui.wizards.commands.AddANDComparisonAction;
import org.gatt.ui.wizards.commands.AddORComparisonAction;

public class CreateHandSidePanel extends JWizardPanel {

	private static final long serialVersionUID = 1L;
	private JLabel labelActualHandSide = null;
	private JButton buttonAddAndAttributeComparation = null;
	private JButton buttonAddOrAttributeComparation = null;
	private JPanel pConstraintPreview = null;
	
	
	/**
	 * This is the default constructor
	 */
	public CreateHandSidePanel() {
		super();
		this.setStepTitle("Crear restricción compuesta");
		initialize();
		setBackStep(1);
	    setNextStep(3);	    
	}
	
	public void doNext(){
		
		next();
	}
	
	protected void next(){
		ConstraintWizard wizard = (ConstraintWizard)getWizardParent();
		/*wizard.setLastRound(wizard.getLastRound()+1);
		
		if(wizard.isSameRound()){
			setNextStep(3);
		}else{
			setNextStep(1);
		}*/
		//int speed = wizard.getNextPanel();
		//System.out.println("Loling ..." + speed );
		setNextStep(wizard.getNextPanel());
		super.next();
	}
	
	protected void makingVisible(){
		super.makingVisible();
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		labelActualHandSide = new JLabel();
		labelActualHandSide.setText("");
		labelActualHandSide.setHorizontalAlignment(SwingConstants.LEFT);
		labelActualHandSide.setHorizontalTextPosition(SwingConstants.LEFT);
		labelActualHandSide.setVerticalAlignment(SwingConstants.TOP);
		labelActualHandSide.setVerticalTextPosition(SwingConstants.TOP);
		labelActualHandSide.setOpaque(true);
		labelActualHandSide.setBackground(Color.white);
		labelActualHandSide.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelActualHandSide.setPreferredSize(new Dimension(500, 100));
		this.setSize(602, 356);
		this.getContentPane().setLayout(null);
		this.getContentPane().setSize(602, 356);
		this.getContentPane().add(getButtonAddAndAttributeComparation(), null);
		this.getContentPane().add(getButtonAddOrAttributeComparation(), null);
		this.getContentPane().add(getPConstraintPreview(), null);
	}

	/**
	 * This method initializes buttonAddAndAttributeComparation	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButtonAddAndAttributeComparation() {
		if (buttonAddAndAttributeComparation == null) {
			buttonAddAndAttributeComparation = new JButton();
			buttonAddAndAttributeComparation.setText("Agregar comparación AND");
			buttonAddAndAttributeComparation.setSize(new Dimension(201, 26));
			buttonAddAndAttributeComparation.setLocation(new Point(176, 224));
			buttonAddAndAttributeComparation.addActionListener(new AddANDComparisonAction(this));
		}
		return buttonAddAndAttributeComparation;
	}

	/**
	 * This method initializes buttonAddOrAttributeComparation	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButtonAddOrAttributeComparation() {
		if (buttonAddOrAttributeComparation == null) {
			buttonAddOrAttributeComparation = new JButton();
			buttonAddOrAttributeComparation.setText("Agregar comparación OR");
			buttonAddOrAttributeComparation.setLocation(new Point(386, 224));
			buttonAddOrAttributeComparation.setSize(new Dimension(201, 26));
			buttonAddOrAttributeComparation.addActionListener(new AddORComparisonAction(this));
		}
		return buttonAddOrAttributeComparation;
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
			pConstraintPreview.setBounds(new Rectangle(15, 15, 572, 196));
			pConstraintPreview.setBorder(BorderFactory.createTitledBorder(null, "Previsualización de la restricción hasta el momento", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			pConstraintPreview.add(labelActualHandSide, BorderLayout.CENTER);
		}
		return pConstraintPreview;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
