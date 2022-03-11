
public class RoadElement {

	private String name;
    private String flagIcon;
    private int height,width;

    public RoadElement(String name, String flagIcon) {
        this.name = name;
        this.flagIcon = flagIcon;
    }
    public RoadElement(String name, String flagIcon,int height,int width) {
        this.name = name;
        this.flagIcon = flagIcon;
        this.height=height;
        this.width=width;
    }

    public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlagIcon() {
        return flagIcon;
    }

    public void setFlagIcon(String flagIcon) {
        this.flagIcon = flagIcon;
    }
}

