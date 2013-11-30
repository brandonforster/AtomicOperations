// Brandon Forster
// COP 4600- Operating Systems
// Homework 2- Threading
// 2 December 2013
// AtomGenerator.java
// Generates atoms at random intervals.

import java.util.Random;

public class AtomGenerator implements Runnable {

	private Random random = new Random();
	int count = 0;
	private ChemicalBondingCreator cbc;
	private int atomicNumber;

	public AtomGenerator(ChemicalBondingCreator cbc, int atomicNumber) {
		this.cbc = cbc;
		this.atomicNumber= atomicNumber;
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
			Atom ha = new Atom(cbc, count++, atomicNumber);
			Thread thread = new Thread(ha);
			thread.start();
		}
	}

}
