package org.gatt.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
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
import org.gatt.ui.commands.ReportByDayAction;
import org.gatt.ui.commands.ReportByRoomAction;
import org.gatt.ui.commands.SaveAction;
import org.gatt.ui.commands.StatisticsAction;
import org.gatt.ui.commands.StopAction;

public class GattFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static GattFrame instance;

	private JPanel cpContainer = null;

	private JMenuBar mbMenuBar = null;

	private JMenu mOptimization = null;

	private JMenu mReports = null;

	private JMenu mHelp = null;

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

	private JMenuItem miHelpContents = null;

	private JMenuItem miAbout = null;

	private JMenuItem miSaveSolution = null;

	private JToolBar tbToolBar = null;

	private JButton bOptimize = null;

	private JButton bPause = null;

	private JButton bStop = null;

	private JButton bSave = null;

	private JTabbedPane tpTabs = null;

	private JPanel pStatus = null;
	
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
			mbMenuBar.add(getMHelp());
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
			mReports.add(getMiOccupationRoom());
			mReports.add(getMiOccupationTime());
			mReports.add(getMiStatistics());
			mReports.insertSeparator(2);
			mReports.insertSeparator(5);
		}
		return mReports;
	}

	/**
	 * This method initializes mHelp	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getMHelp() {
		if (mHelp == null) {
			mHelp = new JMenu();
			mHelp.setText("Ayuda");
			mHelp.add(getMiHelpContents());
			mHelp.add(getMiAbout());
			mHelp.insertSeparator(1);
		}
		return mHelp;
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
			miOptimize.addActionListener(new OptimizeAction());
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
			miOptimizePrevious.addActionListener(new OptimizePreviousAction());
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
			miPause.addActionListener(new PauseAction());
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
			miStop.addActionListener(new StopAction());
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
			miReportRoom.addActionListener(new ReportByRoomAction());
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
			miReportDay.addActionListener(new ReportByDayAction());
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
	 * This method initializes miHelpContents	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMiHelpContents() {
		if (miHelpContents == null) {
			miHelpContents = new JMenuItem();
			miHelpContents.setText("Contenido de ayuda");
			miHelpContents.setIcon(new ImageIcon(getClass().getResource("/resources/help.png")));
			miHelpContents.addActionListener(new HelpContentsAction());
		}
		return miHelpContents;
	}

	/**
	 * This method initializes miAbout	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMiAbout() {
		if (miAbout == null) {
			miAbout = new JMenuItem();
			miAbout.setText("Acerca de Gatt...");
			miAbout.setIcon(new ImageIcon(getClass().getResource("/resources/about.png")));
			miAbout.addActionListener(new AboutAction());
		}
		return miAbout;
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
			miSaveSolution.addActionListener(new SaveAction());
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
			bOptimize.addActionListener(new OptimizeAction());
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
			bPause.addActionListener(new PauseAction());
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
			bStop.addActionListener(new StopAction());
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
			bSave.addActionListener(new SaveAction());
		}
		return bSave;
	}

	/**
	 * This method initializes tpTabs	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getTpTabs() {
		if (tpTabs == null) {
			tpTabs = new JTabbedPane();
		}
		return tpTabs;
	}

	/**
	 * This method initializes pStatus	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPStatus() {
		if (pStatus == null) {
			pStatus = new JPanel();
			pStatus.setLayout(new GridBagLayout());
			pStatus.setPreferredSize(new Dimension(0, 25));
		}
		return pStatus;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
