import java.util.ArrayList;

public class dataclass {
	public dataclass() {
		
	}
	public static RoadElement[] roadelements, roadelementsbig;
	public static RoadElementDetail[] ret = new RoadElementDetail[10000];
	public static int indexret = 0;
	public static int compteurvoiture=0;
	public static ArrayList<Voiture> voitureattente= new ArrayList<Voiture>(),voituremarchante = new ArrayList<Voiture>();
	public static SimUtility su;
	public static void chargement() {
		roadelementsbig = new RoadElement[] { new RoadElement("Ligne H", "assets/lineforward.png",26,79),
				new RoadElement("Ligne V", "assets/lineupward.png",79,26), new RoadElement("Coin HG", "assets/cornertl.png",88,89),
				new RoadElement("Coin HD", "assets/cornertr.png",89,88), new RoadElement("Coin BG", "assets/cornerbl.png",89,88),
				new RoadElement("Coin BD", "assets/cornerbr.png",88,89)/*,
				new RoadElement("RondPoint", "assets/roundpoint.png",128,128),
				new RoadElement("Carrefour", "assets/pluscross.png",78,79),
				new RoadElement("Intersection B", "assets/tcrossdw.png",52,79),
				new RoadElement("Intersection H", "assets/tcrossupw.png",52,79),
				new RoadElement("Intersection G", "assets/tcrossl.png",79,52),
				new RoadElement("Intersection D", "assets/tcrossr.png",79,52) */};
		roadelements = new RoadElement[] { new RoadElement("Ligne H", "/icons/lineforward.png"),
				new RoadElement("Ligne V", "/icons/lineupward.png"), new RoadElement("Coin HG", "/icons/cornertl.png"),
				new RoadElement("Coin HD", "/icons/cornertr.png"), new RoadElement("Coin BG", "/icons/cornerbl.png"),
				new RoadElement("Coin BD", "/icons/cornerbr.png")/*,
				new RoadElement("RondPoint", "/icons/roundpoint.png"),
				new RoadElement("Carrefour", "/icons/pluscross.png"),
				new RoadElement("Intersection B", "/icons/tcrossdw.png"),
				new RoadElement("Intersection H", "/icons/tcrossupw.png"),
				new RoadElement("Intersection G", "/icons/tcrossl.png"),
				new RoadElement("Intersection D", "/icons/tcrossr.png") */};
		
	}
	
}
