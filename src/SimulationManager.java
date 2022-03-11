
public class SimulationManager extends Thread{
	private Simulation s ;
	public SimulationManager(Simulation s ) {
		this.s=s;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				sleep(2000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			s.rpnt();
			System.out.println(dataclass.ret[0].isOccupied()+" && "+dataclass.voitureattente.isEmpty());
			if(!(dataclass.ret[0].isOccupied()) && !(dataclass.voitureattente.isEmpty()) ) {
				//System.out.println("launching car");
				Voiture V = dataclass.voitureattente.get(0);
				dataclass.voituremarchante.add(dataclass.voitureattente.get(0));
				
				//System.out.println("removecar");
				dataclass.voitureattente.remove(0);
				s.rpnt();
				s.lancer(V);
				VoitureManager vm  = new VoitureManager(V);
				Thread t = new Thread(vm);
				t.start();
			}
		}
	}
	
	public synchronized void entry(Voiture v , RoadElementDetail r,int i) {
		while(r.isOccupied()) {
			/*try {
				wait();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("voiture" + v.getId()+" notified");*/
		}
					if(i!=0) {
							dataclass.ret[i-1].setOccupied(false);
							notifyAll();
							//System.out.println("notified");
					}
		r.setOccupied(true);
	}
	
	public synchronized void exit(Voiture v) {
		dataclass.ret[dataclass.indexret-1].setOccupied(false);
		//notifyAll();
		dataclass.compteurvoiture--;
	}
	
	
}
