import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Voiture {
	private final int height = 9, width=10;
	private ImageIcon img;
	private int id,x,y,vitesse=1;//(int) (Math.random() * (4))+1;
	private JLabel v;
	private boolean running=false;
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public Voiture(ImageIcon i,int index,JFrame fr) {
		id=index;
		img=i;
		x=dataclass.ret[0].getX()+2;
		y=dataclass.ret[0].getY()+2;
		configurationvoiture();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ImageIcon getImg() {
		return img;
	}
	public void setImg(ImageIcon img) {
		this.img = img;
	}
	public void setVitesse(int v) {
		vitesse=v;
	}
	public int getVitesse() {
		return vitesse;
	}
	public JLabel getLabel() {
		return v;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		v.setBounds(x, y, width, height);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		v.setBounds(x, y, width, height);
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void configurationvoiture() {
		v = new JLabel("");
		v.setBounds(x,y,width,height);
		v.setIcon(img);
	}
}
