package org.gatt.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import org.gatt.ui.commands.AboutAction;
import org.gatt.ui.commands.HelpContentsAction;
import org.gatt.ui.commands.ManageConstraintsAction;
import org.gatt.ui.commands.OccupationRoomAction;
import org.gatt.ui.commands.OccupationTimeAction;
import org.gatt.ui.commands.OptimizeAction;
import org.gatt.ui.commands.OptimizePreviousAction;
import org.gatt.ui.commands.PauseAction;
import org.gatt.ui.commands.PrintAction;
import org.gatt.ui.commands.ReportByDayAction;
import org.gatt.ui.commands.ReportByRoomAction;
import org.gatt.ui.commands.SaveAction;
import org.gatt.ui.commands.StatisticsAction;
import org.gatt.ui.commands.StopAction;
import java.awt.GridBagLayout;
import javax.swing.JProgressBar;
import java.awt.Rectangle;
import java.awt.Point;

/**
 * @author Chucho
 * this class represents the main frame of the application
 */
public class GattFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static GattFrame instance;

	private JPanel cpContainer = null;

	private JMenuBar mbMenuBar = null;

	private JMenu mOptimization = null;

	private JMenu mReports = null;

	private JMenu mConstraints = null;

	private JMenuItem miOptimize = null;

	private JMenuItem miOptimizePrevious = null;

	private JMenuItem miPause = null;

	private JMenuItem miStop = null;

	private JMenuItem miManageConstraint = null;

	private JMenuItem miReportRoom = null;

	private JMenuItem miReportDay = null;

	private JMenuItem miOccupationRoom = null;

	private JMenuItem miOccupationTime = null;

	private JMenuItem miStatistics = null;

	private JMenuItem miSaveSolution = null;

	private JToolBar tbToolBar = null;

	private JButton bOptimize = null;

	private JButton bPause = null;

	private JButton bStop = null;

	private JButton bSave = null;

	private JTabbedPane tpTabs = null;

	private JPanel pStatus = null;

	private JButton bPrint = null;

	private JPanel pProgress = null;

	private JProgressBar pbOpProgress = null;

	private JLabel lbIterations = null;
	
	/**
	 * return the instance of the frame
	 */
	public static GattFrame getInstance(){
		if(instance == null)
			instance = new GattFrame();
		
		return instance;
	}
	
	/**
	 * This is the default constructor
	 */
	public GattFrame() {
		super();
		initialize();
		disablePauseCommands();
		disableStopCommands();
	}
	
	/**
	 * sets a status message for the frame
	 * @param msg
	 */
	public void setStatusMessage(String msg){
		
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(489, 334);
		this.setContentPane(getCpContainer());
		this.setJMenuBar(getMbMenuBar());
		this.setTitle("GATT - Genetic Algorithm for Timetabling");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	/**
	 * disable the optimization commands
	 */
	public void disableOptimizationCommands(){
		getBOptimize().setEnabled(false);
		getMiOptimize().setEnabled(false);
	}
	
	/**
	 * enable the optimization commands
	 */
	public void enableOptimizationCommands(){
		getBOptimize().setEnabled(true);
		getMiOptimize().setEnabled(true);
	}
	
	/**
	 * disable pause commands
	 */
	public void disablePauseCommands(){
		getBPause().setEnabled(false);
		getMiPause().setEnabled(false);
	}
	
	/**
	 * enable pause commands
	 */
	public void enablePauseCommands(){
		getBPause().setEnabled(true);
		getMiPause().setEnabled(true);
	}
	
	/**
	 * disbale stop commands
	 */
	public void disableStopCommands(){
		getBStop().setEnabled(false);
		getMiStop().setEnabled(false);
	}
	
	/**
	 * enable stop commands
	 */
	public void enableStopCommands(){
		getBStop().setEnabled(true);
		getMiStop().setEnabled(true);
	}
	
	/**
	 * This method initializes cpContainer
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getCpContainer() {
		if (cpContainer == null) {
			cpContainer = new JPanel();
			cpContainer.setLayout(new BorderLayout());
			cpContainer.add(getTbToolBar(), BorderLayout.NORTH);
			cpContainer.add(getTpTabs(), BorderLayout.CENTER);
			cpContainer.add(getPStatus(), BorderLayout.SOUTH);
		}
		return cpContainer;
	}

	/**
	 * This method initializes mbMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getMbMenuBar() {
		if (mbMenuBar == null) {
			mbMenuBar = new JMenuBar();
			mbMenuBar.setBorderPainted(true);
			mbMenuBar.add(getMOptimization());
			mbMenuBar.add(getMConstraints());
			mbMenuBar.add(getMReports());
		}
		return mbMenuBar;
	}

	/**
	 * This method initializes mOptimization	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getMOptimization() {
		if (mOptimization == null) {
			mOptimization = new JMenu();
			mOptimization.setText("Optimización");
			mOptimization.add(getMiOptimize());
			mOptimization.add(getMiOptimizePrevious());
			mOptimization.add(getMiPause());
			mOptimization.add(getMiStop());
			mOptimization.add(getMiSaveSolution());
			mOptimization.insertSeparator(2);
			mOptimization.insertSeparator(5);
			
		}
		return mOptimization;
	}

	/**
	 * This method initializes mReports	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getMReports() {
		if (mReports == null) {
			mReports = new JMenu();
			mReports.setText("Reportes");
			mReports.add(getMiReportRoom());
			mReports.add(getMiReportDay());
			//mReports.add(getMiOccupationRoom());
			//mReports.add(getMiOccupationTime());
			//mReports.add(getMiStatistics());
			//mReports.insertSeparator(2);
			//mReports.insertSeparator(5);
		}
		return mReports;
	}

	/**
	 * This method initializes mConstraints	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getMConstraints() {
		if (mConstraints == null) {
			mConstraints = new JMenu();
			mConstraints.setText("Restricciones");
			mConstraints.add(getMiManageConstraint());
		}
		return mConstraints;
	}

	/**
	 * This method initializes miOptimize	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMiOptimize() {
		if (miOptimize == null) {
			miOptimize = new JMenuItem();
			miOptimize.setText("Optimizar");
			miOptimize.setIcon(new ImageIcon(getClass().getResource("/resources/Play.png")));
			miOptimize.addActionListener(new OptimizeAction(this));
		}
		return miOptimize;
	}

	/**
	 * This method initializes miOptimizePrevious	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMiOptimizePrevious() {
		if (miOptimizePrevious == null) {
			miOptimizePrevious = new JMenuItem();
			miOptimizePrevious.setText("Optimizar desde solución anterior");
			miOptimizePrevious.addActionListener(new OptimizePreviousAction(this));
		}
		return miOptimizePrevious;
	}

	/**
	 * This method initializes miPause	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMiPause() {
		if (miPause == null) {
			miPause = new JMenuItem();
			miPause.setText("Pausar optimización");
			miPause.setIcon(new ImageIcon(getClass().getResource("/resources/Pause.png")));
			miPause.addActionListener(new PauseAction(this));
		}
		return miPause;
	}

	/**
	 * This method initializes miStop	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMiStop() {
		if (miStop == null) {
			miStop = new JMenuItem();
			miStop.setText("Detener optimización");
			miStop.setIcon(new ImageIcon(getClass().getResource("/resources/Stop.png")));
			miStop.addActionListener(new StopAction(this));
		}
		return miStop;
	}

	/**
	 * This method initializes miManageConstraint	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMiManageConstraint() {
		if (miManageConstraint == null) {
			miManageConstraint = new JMenuItem();
			miManageConstraint.setText("Administrar restricciones...");
			miManageConstraint.addActionListener(new ManageConstraintsAction(this));
		}
		return miManageConstraint;
	}

	/**
	 * This method initializes miReportRoom	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMiReportRoom() {
		if (miReportRoom == null) {
			miReportRoom = new JMenuItem();
			miReportRoom.setText("Reporte por aula...");
			miReportRoom.addActionListener(new ReportByRoomAction(this));
		}
		return miReportRoom;
	}

	/**
	 * This method initializes miReportDay	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMiReportDay() {
		if (miReportDay == null) {
			miReportDay = new JMenuItem();
			miReportDay.setText("Reporte por día...");
			miReportDay.addActionListener(new ReportByDayAction(this));
		}
		return miReportDay;
	}

	/**
	 * This method initializes miOccupationRoom	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMiOccupationRoom() {
		if (miOccupationRoom == null) {
			miOccupationRoom = new JMenuItem();
			miOccupationRoom.setText("Ocupación por aula");
			miOccupationRoom.addActionListener(new OccupationRoomAction());
		}
		return miOccupationRoom;
	}

	/**
	 * This method initializes miOccupationTime	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMiOccupationTime() {
		if (miOccupationTime == null) {
			miOccupationTime = new JMenuItem();
			miOccupationTime.setText("Ocupación por franja horaria");
			miOccupationTime.addActionListener(new OccupationTimeAction());
		}
		return miOccupationTime;
	}

	/**
	 * This method initializes miStatistics	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMiStatistics() {
		if (miStatistics == null) {
			miStatistics = new JMenuItem();
			miStatistics.setText("Estadísticas");
			miStatistics.addActionListener(new StatisticsAction());
		}
		return miStatistics;
	}

	/**
	 * This method initializes miSaveSolution	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMiSaveSolution() {
		if (miSaveSolution == null) {
			miSaveSolution = new JMenuItem();
			miSaveSolution.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
			miSaveSolution.setText("Guardar solución");
			miSaveSolution.setIcon(new ImageIcon(getClass().getResource("/resources/Save.png")));
			miSaveSolution.addActionListener(new SaveAction(this));
		}
		return miSaveSolution;
	}

	/**
	 * This method initializes tbToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getTbToolBar() {
		if (tbToolBar == null) {
			tbToolBar = new JToolBar();
			tbToolBar.setFloatable(false);
			tbToolBar.setBorderPainted(true);
			tbToolBar.add(getBOptimize());
			tbToolBar.add(getBPause());
			tbToolBar.add(getBStop());
			tbToolBar.addSeparator();
			tbToolBar.add(getBSave());
			tbToolBar.addSeparator();
			tbToolBar.add(getBPrint());
		}
		return tbToolBar;
	}

	/**
	 * This method initializes bOptimize	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBOptimize() {
		if (bOptimize == null) {
			bOptimize = new JButton();
			bOptimize.setText("Optimizar");
			bOptimize.setIcon(new ImageIcon(getClass().getResource("/resources/Play.png")));
			bOptimize.addActionListener(new OptimizeAction(this));
		}
		return bOptimize;
	}

	/**
	 * This method initializes bPause	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBPause() {
		if (bPause == null) {
			bPause = new JButton();
			bPause.setText("Pausar");
			bPause.setIcon(new ImageIcon(getClass().getResource("/resources/Pause.png")));
			bPause.addActionListener(new PauseAction(this));
		}
		return bPause;
	}

	/**
	 * This method initializes bStop	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBStop() {
		if (bStop == null) {
			bStop = new JButton();
			bStop.setText("Detener");
			bStop.setIcon(new ImageIcon(getClass().getResource("/resources/Stop.png")));
			bStop.addActionListener(new StopAction(this));
		}
		return bStop;
	}

	/**
	 * This method initializes bSave	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBSave() {
		if (bSave == null) {
			bSave = new JButton();
			bSave.setText("Guardar");
			bSave.setIcon(new ImageIcon(getClass().getResource("/resources/Save.png")));
			bSave.addActionListener(new SaveAction(this));
		}
		return bSave;
	}

	/**
	 * This method initializes tpTabs	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	public JTabbedPane getTpTabs() {
		if (tpTabs == null) {
			tpTabs = new JTabbedPane();
			//tpTabs.addTab("Reporte", null);
		}
		return tpTabs;
	}
	
	private void initTabComponent(int i){
		tpTabs.setTabComponentAt(i, new ButtonTabComponent(tpTabs));
	}
	
	/**
	 * This method initializes pStatus	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPStatus() {
		if (pStatus == null) {
			pStatus = new JPanel();
			pStatus.setLayout(new BorderLayout());
			pStatus.setPreferredSize(new Dimension(0, 25));
			pStatus.add(getPProgress(), BorderLayout.WEST);
		}
		return pStatus;
	}

	/**
	 * This method initializes bPrint	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBPrint() {
		if (bPrint == null) {
			bPrint = new JButton();
			bPrint.setText("Imprimir");
			bPrint.setIcon(new ImageIcon(getClass().getResource("/resources/print.png")));
			bPrint.addActionListener(new PrintAction(this));
		}
		return bPrint;
	}
	
	/**
	 * Add a report to the frame
	 * @param title
	 * @param report
	 */
	public void addReport(String title, JScrollPane report){
		tpTabs.add(title,report);
		int i = tpTabs.getTabCount() - 1;
		initTabComponent(i);
		tpTabs.setSelectedIndex(i);
	}

	/**
	 * This method initializes pProgress	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPProgress() {
		if (pProgress == null) {
			lbIterations = new JLabel();
			lbIterations.setBounds(new Rectangle(96, 4, 144, 17));
			lbIterations.setText("Iteración: 0");
			pProgress = new JPanel();
			pProgress.setLayout(null);
			pProgress.setPreferredSize(new Dimension(250, 0));
			pProgress.add(getPbOpProgress(), null);
			pProgress.add(lbIterations, null);
			pProgress.setVisible(false);
		}
		return pProgress;
	}

	/**
	 * This method initializes pbOpProgress	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	private JProgressBar getPbOpProgress() {
		if (pbOpProgress == null) {
			pbOpProgress = new JProgressBar();
			pbOpProgress.setSize(new Dimension(84, 19));
			pbOpProgress.setLocation(new Point(7, 3));
			pbOpProgress.setIndeterminate(true);
			pbOpProgress.setToolTipText("El algoritmo de optimización se está ejecutando.");
		}
		return pbOpProgress;
	}
	
	/**
	 * increases the progress panel shown in the status bar
	 * @param iteration
	 */
	public void increaseProgressPanel(int iteration){
		lbIterations.setText("Iteración: " + iteration);
		getPbOpProgress().setValue(iteration);
	}
	
	/**
	 * shows the progress panel
	 */
	public void showProgressPanel(){
		getPProgress().setVisible(true);
	}
	
	/**
	 * hides the progress panel
	 */
	public void hideProgressPanel(){
		getPProgress().setVisible(false);
	}
	
	/**
	 * pauses the progress bar
	 */
	public void pauseProgressBar(){
		getPbOpProgress().setIndeterminate(false);
		getPbOpProgress().setToolTipText("El algoritmo de optimización está pausado.");
	}
	
	/**
	 * restarts the progress bar
	 */
	public void restartProgressBar(){
		getPbOpProgress().setIndeterminate(true);
		getPbOpProgress().setToolTipText("El algoritmo de optimización se está ejecutando.");
	}
	
	/**
	 * disable some menu options
	 */
	public void disableOptions(){
		getBSave().setEnabled(false);
		getMiReportDay().setEnabled(false);
		getMiReportRoom().setEnabled(false);
		getMiStatistics().setEnabled(false);
		getMiOccupationRoom().setEnabled(false);
		getMiOccupationTime().setEnabled(false);
		getMiManageConstraint().setEnabled(false);
		getMiSaveSolution().setEnabled(false);
		getMiOptimizePrevious().setEnabled(false);
	}
	
	/**
	 * enable some menu options
	 */
	public void enableOptions(){
		getBSave().setEnabled(true);
		getMiReportDay().setEnabled(true);
		getMiReportRoom().setEnabled(true);
		getMiStatistics().setEnabled(true);
		getMiOccupationRoom().setEnabled(true);
		getMiOccupationTime().setEnabled(true);
		getMiManageConstraint().setEnabled(true);
		getMiSaveSolution().setEnabled(true);
		getMiOptimizePrevious().setEnabled(true);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
