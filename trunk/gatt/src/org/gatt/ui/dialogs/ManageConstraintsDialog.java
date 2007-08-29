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
import java.net.URI;
import java.net.URISyntaxException;
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
import org.gatt.ui.dialogs.commands.NewConstraintAction;
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

	/**
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
	
	public void addConstraint(ConstraintInfo constraint){
		
		XMLConstraintWriter writer = XMLConstraintWriter.getInstance();
		writer.write(constraint);
		
		Vector row = new Vector();
		row.addElement(constraint.getId());
		row.addElement(constraint.getName());
		row.addElement(constraint.getDescription());
		row.addElement(constraint.getSignificance());

		DefaultTableModel model = (DefaultTableModel)this.getTabConstraints().getModel();
		model.addRow(row);
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(578, 323);
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
			bNew.setText("Nueva restricción");
			bNew.setSize(new Dimension(142, 26));
			bNew.setLocation(new Point(251, 250));
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
			bDelete.setText("Eliminar restricción");
			bDelete.setLocation(new Point(404, 250));
			bDelete.setSize(new Dimension(152, 26));
			bDelete.addActionListener(new DeleteConstraintAction(this));
		}
		return bDelete;
	}
	
	class ConstraintsTableModel extends DefaultTableModel {
		
		public ConstraintsTableModel(Vector data, Vector columnNames) {
			super(data, columnNames);
			// TODO Auto-generated constructor stub
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
		
	}
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
