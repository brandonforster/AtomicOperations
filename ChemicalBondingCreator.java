// Brandon Forster
// COP 4600- Operating Systems
// Homework 2- Threading
// 2 December 2013
// ChemicalBondingCreator.java
// Bonds atoms together.

import java.util.concurrent.*;

public class ChemicalBondingCreator implements Runnable {

	public Semaphore waiton = new Semaphore(0);
	
	public Semaphore semaphore = new Semaphore(0);
	
	public ConcurrentLinkedQueue<Atom> aList = 
			new ConcurrentLinkedQueue<>();
			
	private int numBonds[];

	public ChemicalBondingCreator(int[] numBonds) {
		this.numBonds= numBonds;
	}

	@Override
	public void run() {
		while (true) {
			System.out.println("Chemical bonding creator: looking for bonding");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// block until enough permits to create the molecule
			try {
				semaphore.acquire(4);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			System.out.println("Chemical bonding creator: "
					+ "enough atoms to create a methane molecule");
			
			// mutex on aList
			synchronized(aList)
			{
				// remove the first hydrogen from the list four times and unblock it
				for (int i=0; i < 4; i++)
					aList.poll().allowBond();
			}
		}
	}

}
