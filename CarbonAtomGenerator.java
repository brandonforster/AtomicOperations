// Brandon Forster
// COP 4600- Operating Systems
// Homework 2- Threading
// 2 December 2013
// CarbonAtomGenerator.java
// Generates Carbon atoms at random intervals.

import java.util.Random;

public class CarbonAtomGenerator implements Runnable {

	private Random random = new Random();
	int count = 0;
	private ChemicalBondingCreator cbc;

	public CarbonAtomGenerator(ChemicalBondingCreator cbc) {
		this.cbc = cbc;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep((long) (3000.0 * random.nextDouble()));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			CarbonAtom ca = new CarbonAtom(cbc, count++);
			Thread thread = new Thread(ca);
			thread.start();
		}
	}

}
