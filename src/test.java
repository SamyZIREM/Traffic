import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class test {
	
	private JFrame frame;
	
	JPanel panel = new JPanel();
	DefaultMutableTreeNode racine = new DefaultMutableTreeNode("Elements");
	DefaultMutableTreeNode circuit = new DefaultMutableTreeNode("Circuit");
	final JTree tree = new JTree(racine);
	JScrollPane scrollPane = new JScrollPane(tree);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test window = new test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dataclass.chargement();
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 990, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		for (RoadElement element : dataclass.roadelements) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(element);
			circuit.add(node);
		}
		racine.add(circuit);

		tree.setCellRenderer(new TreeCellRendererRoad());

		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 307, 649);
		frame.getContentPane().add(scrollPane);

		panel.setBackground(Color.WHITE);
		panel.setBounds(324, 0, 650, 649);
		panel.setFocusable(true);
		frame.getContentPane().add(panel);

		JButton btnNewButton = new JButton("Valider Circuit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Point p = panel.getLocationOnScreen();
				Dimension dim = panel.getSize();
				Rectangle rect = new Rectangle(p, dim);

				try {
					Robot robot = new Robot();
					BufferedImage img = robot.createScreenCapture(rect);
					File f = new File("circuit.png");
					ImageIO.write(img,"png",f);
					Simulation sim = new Simulation(frame);
					dataclass.su=new SimUtility();
					/*VoitureManager vm = new VoitureManager(sim);
					Thread t = new Thread(vm);
					t.start();*/
					sim.setframe(frame);
					frame.setVisible(false);
				} catch (AWTException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(584, 660, 118, 23);
		frame.getContentPane().add(btnNewButton);

		tree.addMouseListener(ml);
	}

	public void positionnement() {
		for (int i = 0; i < dataclass.indexret; i++) {
			try {
				panel.getGraphics().drawImage(ImageIO.read(new File(dataclass.ret[i].getR().getFlagIcon())), dataclass.ret[i].getX(),
						dataclass.ret[i].getY(), panel);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	MouseListener ml = new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			int selRow = tree.getRowForLocation(e.getX(), e.getY());
			TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
			if (selRow != -1) {
				if (e.getClickCount() == 2) {
					// DefaultMutableTreeNode node = (DefaultMutableTreeNode)
					// tree.getLastSelectedPathComponent();
					switch (selRow) {
					case 0, 1: {

					}
						break;
					default: {
						for (RoadElement element : dataclass.roadelements) {
							if (element.toString().equals(selPath.getPathComponent(2).toString())) {
								String name = element.getName();
								for (RoadElement element2 : dataclass.roadelementsbig) {
									if (element2.getName().equals(name)) {
										try {
											dataclass.ret[dataclass.indexret] = new RoadElementDetail(dataclass.indexret, element2, 0, 0);
											dataclass.indexret++;
											panel.add(dataclass.ret[dataclass.indexret-1].getLabel());
											//panel.getGraphics().drawImage(
												//	ImageIO.read(new File(element2.getFlagIcon())), 0, 0, panel);
											removem();
											panel.requestFocus();
											panel.addMouseMotionListener(mml);
											panel.addMouseListener(ml2);
										} catch (Exception e1) {
											e1.printStackTrace();
										}
									}
								}

							}
						}
					}
					}
				}
			}
		}
	};
	MouseMotionListener mml = new MouseMotionAdapter() {
		public void mouseMoved(MouseEvent e) {
			RoadElementDetail red = dataclass.ret[dataclass.indexret - 1];
			if (e.getY() > 0 && e.getY() < panel.getHeight() && e.getX() > 0 && e.getX() < panel.getWidth()) {
				red.setY(e.getY());
				red.setX(e.getX());
				dataclass.ret[dataclass.indexret - 1] = red;
				positionnement();
				panel.update(panel.getGraphics());
			}
		}
	};
	MouseListener ml2 = new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			if(e.getButton()==MouseEvent.BUTTON1) {
				tree.requestFocus();
				tree.addMouseListener(ml);
				removek();
				positionnement();
			}
			
		}
	};

	public void removek() {
		panel.removeMouseMotionListener(mml);
		panel.removeMouseListener(ml2);
	}

	public void removem() {
		tree.removeMouseListener(ml);
	}
}
