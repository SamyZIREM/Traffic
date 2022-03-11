import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Simulation {
	private File fi = new File("circuit.png") ;
	private Image backgroundimage;
	private JFrame frameb;
	private JFrame frame;
	JLabel background = new JLabel("");
	private SimulationManager sm = new SimulationManager(this);
	public Simulation(JFrame f) {
		frameb=f;
		
		main(null);
		
	}
	/**
	 * Launch the application.
	 */
	
	public void setframe(JFrame f) {
		this.frameb=f;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Simulation window = new Simulation();
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
	public Simulation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			backgroundimage = ImageIO.read(fi);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				frameb.setVisible(true);
			}
		});
		
		background.setIcon(new ImageIcon(backgroundimage));
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		background.setHorizontalAlignment(SwingConstants.CENTER);
		background.setBounds(0,0, backgroundimage.getWidth(null), backgroundimage.getHeight(null));
		frame.setBounds(100, 100, backgroundimage.getWidth(null)+19, backgroundimage.getHeight(null)+39);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			Thread.sleep(1000);
			//Voiture v = new Voiture(new ImageIcon(ImageIO.read(new File("voiture.png"))),dataclass.ret.length,frame);
			//v.getLabel().set
			//frame.getContentPane().add(v.getLabel());
			frame.getContentPane().add(background);
			frame.setComponentZOrder(background, dataclass.compteurvoiture);
			frame.validate();
			frame.setVisible(true);
			//rouler(v);
			Thread t = new Thread(sm);
			t.start();
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	public void lancer(Voiture v) {
		frame.getContentPane().add(v.getLabel());
		frame.setComponentZOrder(v.getLabel(), v.getId());
		//rouler(v);
	}
	public void rpnt() {
		frame.repaint();
		frame.update(frame.getGraphics());
	}
	public void rouler(Voiture v) {
		for(int k=0;k<dataclass.indexret;k++) {
			//sm.entry(v, dataclass.ret[k],k);
			while(dataclass.ret[k].isOccupied()) {
				
			}
			dataclass.ret[k].setOccupied(true);
			if(k!=0) {
				dataclass.ret[k-1].setOccupied(false);
				//notifyAll();
				//System.out.println("notified");
			}
			switch (dataclass.ret[k].getR().getName()) {
				case "Ligne H" :{
					v.setX(dataclass.ret[k].getX()+2);
					v.setY(dataclass.ret[k].getY()+32);
					int i=0;
					while(v.getX()<dataclass.ret[k].getX()+79-v.getVitesse()-v.getWidth()) {
						i++;
						if(i==10000000) {
							v.setX(v.getX()+v.getVitesse());
							frame.update(frame.getGraphics());
							i=0;
						}
					}
					
				}break;
				case "Ligne V" :{
					v.setX(dataclass.ret[k].getX()+2);
					v.setY(dataclass.ret[k].getY()+2);
					int i=0;
					while(v.getY()<dataclass.ret[k].getY()+79-v.getVitesse()-v.getHeight()) {
						i++;
						if(i==10000000) {
							v.setY(v.getY()+v.getVitesse());
							frame.update(frame.getGraphics());
							i=0;
						}
					}
				}break;
				case "Coin HG" :{
					v.setX(dataclass.ret[k].getX()+2);
					v.setY(dataclass.ret[k].getY()+84);
					int i=0,cnt=0,cn=0;
					while(v.getY()>dataclass.ret[k].getY()+v.getVitesse() || v.getX()<dataclass.ret[k].getX()+89-v.getVitesse()-v.getWidth()) {
						if(!(v.getY()>dataclass.ret[k].getY()+v.getVitesse())) {
							i++;
							if(i==10000000) {
								v.setX(v.getX()+v.getVitesse());
								frame.update(frame.getGraphics());
								i=0;
							}
							
						}else {
							i++;
							if(i==10000000) {
								v.setY(v.getY()-v.getVitesse());
								frame.update(frame.getGraphics());
								i=0;
							}
							if(!(v.getX()<dataclass.ret[k].getX()+89-v.getVitesse()-v.getWidth())) {
								
							}else {
								i++;
								if(i==10000000) {
									if(cnt<21) {
										cnt++;
										v.setY(v.getY()-v.getVitesse());
										frame.update(frame.getGraphics());
										i=0;
									}else {
										if(cn==0) {
											cn=0;
											v.setX(v.getX()+2);
										}else {
											cn++;
										}
										v.setY(v.getY()-3);
										frame.update(frame.getGraphics());
										i=0;
									}
									
								}
							}
						}
					}
				}break;
				case "Coin HD" :{
	
				}break;
				case "Coin BG" :{
				
				}break;
				case "Coin BD" :{
				
				}break;
				/*case "Carrefour" :{

				}break;
				case "RondPoint" :{
	
				}break;
				case "Intersection H" :{
				
				}break;
				case "Intersection B" :{
				
				}break;
				case "Intersection G" :{

				}break;
				case "Intersection D" :{
	
				}break;*/
				default :{
				
				}break;
			}
		}
		frame.getContentPane().remove(v.getLabel());
		v.setX(2000);
		v.setY(2000);
		rpnt();
		//sm.exit(v);
		dataclass.ret[dataclass.indexret-1].setOccupied(false);
		//notifyAll();
		dataclass.compteurvoiture--;
	}

}
