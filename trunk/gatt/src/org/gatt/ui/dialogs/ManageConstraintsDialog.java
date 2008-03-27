package org.gatt.ui.dialogs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.gatt.constraint.ConstraintInfo;
import org.gatt.constraint.io.XMLConstraintRepository;
import org.gatt.constraint.io.XMLConstraintWriter;
import org.gatt.ui.dialogs.commands.DeleteConstraintAction;
import org.gatt.ui.dialogs.commands.DetailConstraintAction;
import org.gatt.ui.dialogs.commands.EditConstraintAction;
import org.gatt.ui.dialogs.commands.NewConstraintAction;
import org.gatt.ui.wizards.helper.ConstraintWizardInfoWrapper;
import org.gatt.util.GattConfigLocator;
import org.igfay.jfig.JFig;
import org.igfay.jfig.JFigException;
import org.igfay.jfig.JFigIF;
import org.igfay.jfig.JFigLocatorIF;

public class ManageConstraintsDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane spConstraints = null;
	private JTable tabConstraints = null;
	private JPanel panelConstraints = null;
	private JButton bNew = null;
	private JButton bDelete = null;
	private JButton bDetail = null;
	private JButton bEdit = null;

	private final int COLUMN_ID = 0;
	private final int COLUMN_NAME = 1;
	private final int COLUMN_DESCRIPTION = 2;
	private final int COLUMN_SIGNIFICANCE = 3;
	
	/**
	 * constructor
	 * @param owner
	 */
	public ManageConstraintsDialog(Frame owner) {
		super(owner);
		initialize();
	}
	
	/**
	 * This method loads the constraint defined in the file of constraints
	 */
	private ConstraintsTableModel loadConstraints() {
		JFigLocatorIF locator = new GattConfigLocator("config.xml","config");
		JFigIF config = JFig.getInstance(locator);
		
		String xmlConstraintFilePath = null;
		
		//URI uri = null;
		
		try{
			xmlConstraintFilePath = config.getValue("XMLWriterInfo", "FilePath");
			//uri = getClass().getResource(xmlConstraintFilePath).toURI();
		}catch(JFigException jFigEx){
			jFigEx.printStackTrace();
		}/*catch(URISyntaxException uriEx){
			uriEx.printStackTrace();
		}*/
		
		XMLConstraintRepository repository = new XMLConstraintRepository(new File(xmlConstraintFilePath));
		
		if(!repository.load()){
			try{
				throw new Exception("Error cargando restricciones");
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		Collection<ConstraintInfo> constraints = repository.getAllConstraints();
		Vector<String> columnNames = new Vector<String>();
		columnNames.addElement("ID");
		columnNames.addElement("Nombre");
		columnNames.addElement("Descripción");
		columnNames.addElement("Significancia");
		
		Vector<Vector> data = new Vector<Vector>();
		
		Vector row;
		for (Iterator iterator = constraints.iterator(); iterator.hasNext();) {
			ConstraintInfo constraint = (ConstraintInfo) iterator.next();
			row = new Vector();
			row.addElement(constraint.getId());
			row.addElement(constraint.getName());
			row.addElement(constraint.getDescription());
			row.addElement(constraint.getSignificance());
			data.addElement(row);
		}
		
		return new ConstraintsTableModel(data,columnNames);
	}
	
	/**
	 * add a constraint to the list of constraint in the constraints dialog
	 * @param cWrapper
	 */
	public void addConstraint(ConstraintWizardInfoWrapper cWrapper){
		
		ConstraintInfo constraint = cWrapper.getConstraintInfo();
		
		XMLConstraintWriter writer = XMLConstraintWriter.getInstance();
		writer.write(constraint);
		writer.writeConstraintWrapper(cWrapper);
		
		Vector row = new Vector();
		row.addElement(constraint.getId());
		row.addElement(constraint.getName());
		row.addElement(constraint.getDescription());
		row.addElement(constraint.getSignificance());

		DefaultTableModel model = (DefaultTableModel)this.getTabConstraints().getModel();
		model.addRow(row);
	}
	
	/**
	 * updates the information of a constraints in the list of constraints in the
	 * constraints dialog
	 * @param cWrapper
	 */
	public void updateConstraint(ConstraintWizardInfoWrapper cWrapper){
		ConstraintInfo constraint = cWrapper.getConstraintInfo();
		
		XMLConstraintWriter writer = XMLConstraintWriter.getInstance();
		writer.updateConstraint(constraint);
		writer.writeConstraintWrapper(cWrapper);
		
		DefaultTableModel model = (DefaultTableModel)this.getTabConstraints().getModel();
		
		int row = 0;
		
		for(int i=0; i < model.getRowCount(); i++){
			if(model.getValueAt(i, COLUMN_ID).equals(constraint.getId())){
				row = i;
				break;
			}
		}

		model.setValueAt(constraint.getName(), row, COLUMN_NAME);
		model.setValueAt(constraint.getDescription(), row, COLUMN_DESCRIPTION);
		model.setValueAt(constraint.getSignificance(), row, COLUMN_SIGNIFICANCE);
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(578, 320);
		this.setResizable(false);
		this.setTitle("Administrar restricciones");
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getPanelConstraints(), null);
			jContentPane.add(getBNew(), null);
			jContentPane.add(getBDelete(), null);
			jContentPane.add(getBDetail(), null);
			jContentPane.add(getBEdit(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes spConstraints	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getSpConstraints() {
		if (spConstraints == null) {
			spConstraints = new JScrollPane();
			spConstraints.setViewportView(getTabConstraints());
		}
		return spConstraints;
	}

	/**
	 * This method initializes tabConstraints	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getTabConstraints() {
		if (tabConstraints == null) {
			tabConstraints = new JTable(loadConstraints());
		}
		return tabConstraints;
	}

	/**
	 * This method initializes panelConstraints	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelConstraints() {
		if (panelConstraints == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.weightx = 1.0;
			panelConstraints = new JPanel();
			panelConstraints.setLayout(new GridBagLayout());
			panelConstraints.setBounds(new Rectangle(16, 18, 541, 222));
			panelConstraints.setBorder(BorderFactory.createTitledBorder(null, "Listado de restricciones", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			panelConstraints.add(getSpConstraints(), gridBagConstraints);
		}
		return panelConstraints;
	}

	/**
	 * This method initializes bNew	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBNew() {
		if (bNew == null) {
			bNew = new JButton();
			bNew.setText("Nueva");
			bNew.setSize(new Dimension(71, 23));
			bNew.setLocation(new Point(213, 248));
			bNew.addActionListener(new NewConstraintAction(this));
		}
		return bNew;
	}

	/**
	 * This method initializes bDelete	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBDelete() {
		if (bDelete == null) {
			bDelete = new JButton();
			bDelete.setText("Eliminar");
			bDelete.setLocation(new Point(472, 248));
			bDelete.setSize(new Dimension(85, 23));
			bDelete.addActionListener(new DeleteConstraintAction(this));
		}
		return bDelete;
	}
	
	/**
	 * @author Chucho
	 * The model for the constraints list
	 */
	class ConstraintsTableModel extends DefaultTableModel {
		
		/**
		 * constructor
		 * @param data
		 * @param columnNames
		 */
		public ConstraintsTableModel(Vector data, Vector columnNames) {
			super(data, columnNames);
			// TODO Auto-generated constructor stub
		}

		/* (non-Javadoc)
		 * @see javax.swing.table.DefaultTableModel#isCellEditable(int, int)
		 */
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
		
	}

	/**
	 * This method initializes bDetail	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBDetail() {
		if (bDetail == null) {
			bDetail = new JButton();
			bDetail.setBounds(new Rectangle(294, 248, 80, 23));
			bDetail.setText("Detallar");
			bDetail.addActionListener(new DetailConstraintAction(this));
		}
		return bDetail;
	}

	/**
	 * This method initializes bEdit	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBEdit() {
		if (bEdit == null) {
			bEdit = new JButton();
			bEdit.setText("Editar");
			bEdit.setSize(new Dimension(80, 23));
			bEdit.setLocation(new Point(383, 248));
			bEdit.addActionListener(new EditConstraintAction(this));
		}
		return bEdit;
	}
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
