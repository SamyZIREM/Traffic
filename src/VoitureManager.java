
public class VoitureManager extends Thread{
	private Voiture v ;
	public VoitureManager(Voiture v) {
		this.v=v;
	}
	@Override
	public void run() {
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
									i=0;
								}
								
							}else {
								i++;
								if(i==10000000) {
									v.setY(v.getY()-v.getVitesse());
									i=0;
								}
								if(!(v.getX()<dataclass.ret[k].getX()+89-v.getVitesse()-v.getWidth())) {
									
								}else {
									i++;
									if(i==10000000) {
										if(cnt<21) {
											cnt++;
											v.setY(v.getY()-v.getVitesse());
											i=0;
										}else {
											if(cn==0) {
												cn=0;
												v.setX(v.getX()+2);
											}else {
												cn++;
											}
											v.setY(v.getY()-3);
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
			v.setX(2000);
			v.setY(2000);
			//sm.exit(v);
			dataclass.ret[dataclass.indexret-1].setOccupied(false);
			//notifyAll();
			dataclass.compteurvoiture--;
		
	}
}
