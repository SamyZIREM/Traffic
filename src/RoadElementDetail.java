import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class RoadElementDetail {
	
	private int indextable;
	private RoadElement r;
	private int x,y;
	private JLabel v;
	private boolean occupied;
	
	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public RoadElementDetail(int index,RoadElement re, int x, int y) {
		indextable=index;
		r=re;
		this.x=x;
		this.y=y;
		occupied=false;
		configurationr();
	}
	
	public void setR(RoadElement re) {
		r=re;
	}
	public RoadElement getR() {
		return r;
	}
	public void setIndex(int index) {
		indextable=index;
	}
	public int getIndex() {
		return indextable;
	}
	public void setX(int X) {
		x=X;
		v.setBounds(x, y, r.getWidth(), r.getHeight());
	}
	public int getX() {
		return x;
	}
	public void setY(int Y) {
		y=Y;
		v.setBounds(x, y, r.getWidth(), r.getHeight());
	}
	public int getY() {
		return y;
	}
	public void configurationr() {
		v = new JLabel("");
		v.setBounds(x,y,r.getWidth(),r.getHeight());
		try {
			v.setIcon(new ImageIcon(ImageIO.read(new File(r.getFlagIcon()))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public JLabel getLabel() {
		return v;
	}

	public void setLabel(JLabel v) {
		this.v = v;
	}
}
