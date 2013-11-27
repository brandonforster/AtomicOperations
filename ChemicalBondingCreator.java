import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ChemicalBondingCreator implements Runnable {

	public Semaphore waiton = new Semaphore(0);
	public List<HydrogenAtom> haList = new ArrayList<>();
	public List<CarbonAtom> caList = new ArrayList<>();

	@Override
	public void run() {
		while (true) {
			System.out.println("Chemical bonding creator, looking for bonding");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (haList.size() >= 4 && caList.size() >= 1) {
				System.out
						.println("Chemical bonding creator: enough atoms to create a methane molecule");
				// hopefully, this will release the
				for (int i = 0; i != 5; i++) {
					waiton.release(1);
				}
				continue;
			}
			if (haList.size() >= 4) {
				System.out
						.println("Chemical bonding creator: enough H atoms, but no C atom");
				continue;
			}
		}
	}

}
